package com.anything.alimTalk.send;

import com.anything.alimTalk.alim.AlimVO;
import com.anything.common.service.ApiService;
import com.anything.config.KakaoConfig;
import com.anything.login.OauthTokenDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SendService {
    private final SendRepository repository;
    private final ApiService apiService;
    private final KakaoConfig kakaoConfig;

    public void sendAction(SendVO sendVO) {
        // 스케줄러ID
        long systemId = 9999;
        boolean isScheduleJob = sendVO == null ? true : false;
        List<SendVO> sendList = null;
        Date now = new Date();

        // 즉시발송이면 파라미터만 처리
        if (isScheduleJob == false) {
            log.info("SENDACTION START!!!!");
            isScheduleJob = false;
            sendList = new ArrayList<SendVO>();
            sendList.add(sendVO);
        }
        // 스케줄러면 대상 건 조회
        else {
            log.info("SCHEDULER START!!!!");
            sendList = repository.list(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
        }

        // 발송대상 없으면 종료
        if (sendList.size() == 0) {
            return;
        }

        long sixHourMilis = 21600000;
        long twoMonthMilis = 5184000 * 3600;
        long currentTimeMilis = System.currentTimeMillis();

        for (SendVO trgt : sendList) {
            // 스케줄러 실행이면 등록자 systemId로 세팅
            if (isScheduleJob) {
                trgt.setRegId(systemId);
            }

            long loginTimeMilis = trgt.getLoginDt().getTime();

            boolean isExpireAccessToken = currentTimeMilis > loginTimeMilis + sixHourMilis ? true : false;
            boolean isExpireRefreshToken = currentTimeMilis > loginTimeMilis + twoMonthMilis ? true : false;

            // access_token은 만료됐지만, refresh_token은 만료되지않았다면 갱신
            if (isExpireAccessToken && isExpireRefreshToken == false) {
                OauthTokenDto newToken = null;

                try {
                    newToken = getRefreshToken(trgt.getRefreshToken());
                } catch (Exception e) {
                    log.error("CONTENT SEQ : " + trgt.getContentSeq() + "GET TOKEN ERROR : " +e.getMessage());
                    trgt.setSendCd("E");
                    trgt.setSendStusMsg("GET_TOKEN_ERROR");
                    continue;
                }
                String newAcsToken = newToken.getAccess_token();
                String newRfshToken = newToken.getAccess_token();

                Map<Long, List<SendVO>> groupedByMemberId = sendList.stream()
                        .collect(Collectors.groupingBy(SendVO::getMemberId));

                groupedByMemberId.forEach((memberId, memberList) -> {
                    // 중복된 memberId 값 최신화
                    memberList.forEach(updateTrgt -> {
                        updateTrgt.setAccessToken(newAcsToken);
                        updateTrgt.setRefreshToken(newRfshToken);
                        updateTrgt.setLoginDt(now);
                    });
                });

                //MEMBER 테이블에 토큰 정보 업데이트
                repository.updateTokenByRefresh(trgt);
                log.info("TOKEN UPDATE!!!!");
            }

            // 둘 다 만료라면 DB에 SEND_CD 'E'(만료)로 업데이트
            else if (isExpireAccessToken && isExpireRefreshToken) {
                trgt.setSendCd("E");
                trgt.setSendStusMsg("ALIM SEQ : " + trgt.getContentSeq() + "EXPIRED_TOKEN");
                continue;
            }

            // 발송처리
            try {
                sendMsg(trgt);
            }
            catch (Exception e) {
                log.error("ALIM SEQ : " + trgt.getContentSeq() +  "SEND ERROR : " +e.getMessage());
                trgt.setSendCd("E");
                trgt.setSendStusMsg("SEND_ERROR");
                continue;
            }

            trgt.setSendCd("Y");
            trgt.setSendStusMsg("SUCCESS");
        }

        repository.insert(sendList);

        // 즉시 발송은 원부 후처리 없이 종료
        if (isScheduleJob) {
            log.info("SCHEDULER END!!!!");
            return;
        }

        // alim 테이블 후처리 대상
        List<SendVO> alimSendList = sendList.stream()
                                            .filter(t -> "ALIMTALK".equals(t.getContentType()))
                                            .collect(Collectors.toList());

        // notepad 테이블 후처리 대상
        List<SendVO> notepadSendList = sendList.stream()
                .filter(t -> "NOTEPAD".equals(t.getContentType()))
                .collect(Collectors.toList());

        if (alimSendList.size() > 0) repository.updateAlimSendCd(alimSendList);
        if (alimSendList.size() > 0) repository.updateNotepadSendCd(notepadSendList);

        log.info("SENDACTION END!!!!");
    }

    private OauthTokenDto getRefreshToken(String refreshToken) throws Exception {

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();

        requestParam.add("grant_type", "refresh_token");
        requestParam.add("client_id", kakaoConfig.apiKey);
        requestParam.add("refresh_token", refreshToken);

        ResponseEntity<String> response = null;

        response = apiService.callApi(kakaoConfig.refreshTokenUrl, null, requestParam, HttpMethod.POST);

        return new Gson().fromJson(response.getBody(), OauthTokenDto.class);
    }

    private void sendMsg(SendVO sendTrgt) throws Exception {
        String accessToken = sendTrgt.getAccessToken();

        JSONObject linkObj = new JSONObject();
        linkObj.put("web_url", "www.naver.com");
        linkObj.put("mobile_web_url", "www.naver.com");

        JSONObject requestBody = new JSONObject();
        requestBody.put("object_type", "text");
        requestBody.put("text", sendTrgt.getContent());
        requestBody.put("link", linkObj);

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add("template_object", requestBody.toString());

        ResponseEntity<String> response = apiService.callApi(kakaoConfig.sendMsgUrl, accessToken, requestParam, HttpMethod.POST);

        log.info("SEND API RESPONSE : " + response.toString());
    }
}

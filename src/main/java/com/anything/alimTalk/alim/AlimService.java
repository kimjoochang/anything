package com.anything.alimTalk.alim;

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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AlimService implements IAlimService{
    private final AlimRepository repository;
    @Override
    public List<AlimVO> list(AlimVO alimVO) {
        return repository.list(alimVO);
    }
    @Override
    public List<String> listSendTime(AlimVO alimVO) {
        return repository.listSendTime(alimVO);
    }

    @Override
    public int insertAction(long memberId, AlimVO alimVO) {
        alimVO.setMemberId(memberId);
        return repository.insert(alimVO);
    }

    @Override
    public int updateAction(AlimVO alimVO) {
        return repository.updateAction(alimVO);
    }

    @Override
    public boolean sendAction() {
        String sendApiUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        // 로그아웃 전 토큰
        String accessToken = "EZqvObDRe_2Ox5ewdJm3ykRH72gZlv_-B48KKcjYAAABjrjwLy2IenTzhLqDRQ";

        HttpHeaders header = new HttpHeaders();

        header.set("Authorization", "Bearer " + accessToken);
        header.set("Content-Type", "application/x-www-form-urlencoded");
        header.add("Accept", "application/json");

        JSONObject linkObj = new JSONObject();
        linkObj.put("web_url", "www.naver.com");
        linkObj.put("mobile_web_url", "www.naver.com");

        JSONObject requestBody = new JSONObject();
        requestBody.put("object_type", "text");
        requestBody.put("text", "발송테스트");
        requestBody.put("link", linkObj);

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add("template_object", requestBody.toString());

        HttpEntity<?> requestEntity = new HttpEntity<>(requestParam, header);
        ResponseEntity<String> response = new RestTemplate().exchange(sendApiUrl, HttpMethod.POST, requestEntity,String.class);
        log.info("send Response =======>" + response.toString());
        return true;
    }
}

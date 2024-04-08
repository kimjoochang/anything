package com.anything.login;

import com.anything.common.service.ApiService;
import com.anything.config.KakaoConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService implements ILoginService {
    private final LoginRepository loginRepository;
    private final KakaoConfig kakaoConfig;
    private final ApiService apiService;

    @Override
    public Optional<MemberDto> saveAction(OauthToken oauthToken) {
        ResponseEntity<String> userInfoResponse = null;
        try {
            userInfoResponse = apiService.callApi(kakaoConfig.userInfoUrl, oauthToken.getAccess_token(), null, HttpMethod.GET);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        HashMap<String,Object> userInfoMap = new Gson().fromJson(String.valueOf(userInfoResponse.getBody()),HashMap.class);
        LinkedTreeMap<String,Object> profileMap = (LinkedTreeMap<String, Object>)userInfoMap.get("properties");

        Double id = (Double) userInfoMap.get("id");
        String nickName = (String)profileMap.get("nickname");

        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(id.longValue());
        memberDto.setNickname(nickName);

        log.info(userInfoResponse.toString());
        // TODO : DB 저장 로직 구현 필요
        return Optional.of(memberDto);
    }

    @Override
    public Optional<OauthToken> getToken(String code) {

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();

        requestParam.add("grant_type", "authorization_code");
        requestParam.add("client_id", kakaoConfig.apiKey);
        requestParam.add("redirect_uri", kakaoConfig.redirecUri);
        requestParam.add("code", code);

        ResponseEntity<String> response = null;
        try {
            response = apiService.callApi(kakaoConfig.loginTokenUrl, null, requestParam, HttpMethod.POST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return Optional.of(new Gson().fromJson(response.getBody(), OauthToken.class));
    }

    @Override
    public void logout(String accessToken) {
        String logoutURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(logoutURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("AUTHORIZATION", "Bearer" + accessToken);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

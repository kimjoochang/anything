package com.anything.login;

import com.anything.common.service.ApiService;
import com.anything.config.FileConfig;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService implements ILoginService {
    private final LoginRepository repository;
    private final KakaoConfig kakaoConfig;
    private final FileConfig fileConfig;
    private final ApiService apiService;

    @Override
    public Optional<MemberVO> saveAction(OauthTokenDto oauthTokenDto) {
        final String KAKAO = "kakao";

        ResponseEntity<String> userInfoResponse = null;
        try {
            userInfoResponse = apiService.callApi(kakaoConfig.userInfoUrl, oauthTokenDto.getAccess_token(), null, HttpMethod.GET);
            log.info(userInfoResponse.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        OauthTokenDto userInfoDto = new Gson().fromJson(String.valueOf(userInfoResponse.getBody()),OauthTokenDto.class);

        long memberId = userInfoDto.getId().longValue();
        String accessToken = oauthTokenDto.getAccess_token();
        String refreshToken = oauthTokenDto.getRefresh_token();

        Optional<MemberVO> orgMember = repository.view(memberId);

        if (orgMember.isPresent()) {
            return repository.updateTokenByLogin(orgMember.get().updateToken(accessToken,refreshToken).get()) == 0 ? null : orgMember;
        }

        MemberVO member = new MemberVO().builder()
                .memberId(userInfoDto.getId().longValue())
                .nickname((String) userInfoDto.properties.get("nickname"))
                .type(KAKAO)
                .email((String) userInfoDto.kakao_account.get("email"))
                .accessToken(oauthTokenDto.getAccess_token())
                .refreshToken(oauthTokenDto.getRefresh_token())
                .fileMaxSize(fileConfig.fileMaxSize)
                .loginDt(new Date())
                .build();

        return repository.insert(member) == 0 ? null : Optional.of(member);
    }

    @Override
    public Optional<OauthTokenDto> getToken(String code) {

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
        return Optional.of(new Gson().fromJson(response.getBody(), OauthTokenDto.class));
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

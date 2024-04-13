package com.anything.common.service;

import com.anything.alimTalk.alim.AlimRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApiService {
    private final AlimRepository alimRepository;

    public ResponseEntity<String> callApi(String url, String token, MultiValueMap<String, String> requestParam, HttpMethod method) throws Exception{

        final String AUTHORIZATION_VALUE = "Bearer " + token;

        HttpHeaders header = new HttpHeaders();

        // 토큰발급은 access 토큰 없음
        if (StringUtils.isNotEmpty(token)) {
            header.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION_VALUE);
            header.add(HttpHeaders.ACCEPT, "application/json");
        }
        header.set(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        HttpEntity<?> requestEntity = null;

        if (requestParam == null) {
            requestEntity = new HttpEntity<>(header);
        } else {
            requestEntity = new HttpEntity<>(requestParam, header);
        }

        ResponseEntity<String> response = new RestTemplate().exchange(url, method, requestEntity, String.class);
        log.info("receive Response =======>" + response.toString());

        return response;
    }
}

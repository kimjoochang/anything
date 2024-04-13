package com.anything.config;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="kakao")
@Setter
public class KakaoConfig {
    public String apiKey;
    public String redirecUri;
    public String loginApiUrl;
    public String loginTokenUrl;
    public String userInfoUrl;
    public String refreshTokenUrl;
    public String sendMsgUrl;
}


package com.anything.login;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("login/index")
    public String index(Model model) {
        String restApiKey = "9198d76291763c4e9833e8307e3449c6";
        String redirectUri = "http://localhost:8080/login/callback";

        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + restApiKey + "&redirect_uri=" + redirectUri;
        model.addAttribute("kakaoUrl", kakaoUrl);
        return "login/index";
    }

    @GetMapping("login/callback")
    public String kakaoLogin(String code) {
        service.getToken(code);
        return "/alimTalk/index";
    }
}

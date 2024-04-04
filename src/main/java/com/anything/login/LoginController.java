package com.anything.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("login/index")
    public String index(Model model) {
        String restApiKey = "9198d76291763c4e9833e8307e3449c6";
        String redirectUri = "http://localhost:8080/login/callback";

        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + restApiKey + "&redirect_uri=" + redirectUri + "&prompt=login";
        model.addAttribute("kakaoUrl", kakaoUrl);
        return "login/index";
    }

    @GetMapping("login/callback")
    public String kakaoLogin(HttpServletRequest request, String code) {
        String accessToken = service.getToken(code);

        HttpSession session = request.getSession(true);
        session.setAttribute("accessToken", accessToken);
        System.out.println("로그인 후 session값 =======>" + session.getAttribute("accessToken"));
        return "/alimTalk/index";
    }

    @GetMapping("login/logout")
    public String kakaoLogout(HttpServletRequest request, String code) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            String accessToken = (String) session.getAttribute("accessToken");
            service.logout(accessToken);
            session.invalidate();
        }
        System.out.println("시발");
        return "redirect:/login/index";
    }
}

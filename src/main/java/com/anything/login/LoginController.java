package com.anything.login;

import com.anything.config.KakaoConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService service;
    private final KakaoConfig kakaoConfig;
    @GetMapping("login/index")
    public String index(Model model) {
        String loginUrl = kakaoConfig.loginApiUrl + "&client_id=" + kakaoConfig.apiKey + "&redirect_uri=" + kakaoConfig.redirecUri;
        model.addAttribute("kakaoUrl", loginUrl);
        return "login/index";
    }

    @GetMapping("login/callback")
    public String kakaoLogin(HttpServletRequest request, String code) {

        Optional<OauthTokenDto> accessToken = service.getToken(code);
        accessToken.orElseThrow(RuntimeException::new);

        Optional<MemberVO> member = service.saveAction(accessToken.get());
        member.orElseThrow(RuntimeException::new);

        request.getSession(true).setAttribute("member", member.get());

        return "redirect:/main/index";
    }

    @GetMapping("login/logout")
    public String kakaoLogout(HttpServletRequest request, String code) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            String accessToken = (String) session.getAttribute("accessToken");
            service.logout(accessToken);
            session.invalidate();
        }
        return "redirect:/main/index";
    }
}

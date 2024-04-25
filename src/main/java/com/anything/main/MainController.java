package com.anything.main;

import com.anything.config.KakaoConfig;
import com.anything.login.LoginService;
import com.anything.login.MemberVO;
import com.anything.login.OauthTokenDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@Slf4j

public class MainController {
    @GetMapping("main/index")
    public String index(Model model) {
        return "main/index";
    }

}

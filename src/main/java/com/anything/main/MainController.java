package com.anything.main;

import com.anything.config.KakaoConfig;
import com.anything.login.LoginService;
import com.anything.login.MemberVO;
import com.anything.login.OauthTokenDto;
import com.anything.notepad.NotepadService;
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
public class MainController {
    private final NotepadService notepadService;
    @GetMapping("main/index")
    public String index(HttpServletRequest request, Model model) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        model.addAttribute("notepadList", notepadService.list(member.getMemberId()));
        return "main/index";
    }

}

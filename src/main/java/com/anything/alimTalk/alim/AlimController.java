package com.anything.alimTalk.alim;

import com.anything.alimTalk.send.SendService;
import com.anything.login.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlimController {

    private final AlimService service;
    private final SendService sendService;
    @GetMapping("alimTalk/index")
    public String index(HttpServletRequest request) {

        String url = request.getSession(false) == null ? "redirect:/login/index" : "alimTalk/index";

        return url;
    }

    @GetMapping("alimTalk/list")
    public String list(Model model, AlimVO alimVO) {
        List<String> sendTimes = service.listSendTime(alimVO);
        log.info("list 값 확인 -====>" + sendTimes.toString());
        model.addAttribute("sendTimes", sendTimes);

        return "alimTalk/index :: #sendTimeUl";
    }

    @PostMapping("alimTalk/insert")
    public String insertAction(HttpServletRequest request, AlimVO alimVO) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        int result = service.insertAction(member.getMemberId(), alimVO);
        return "alimTalk/index";
    }

    @GetMapping("alimTalk/send")
    public String sendAction(HttpServletRequest request, AlimVO alimVO) {
        sendService.sendAction();
        return "alimTalk/index";
    }
}

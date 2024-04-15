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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AlimController {

    private final AlimService service;
    private final SendService sendService;
    @GetMapping("alimTalk/index")
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("hours", service.getHourList());
        model.addAttribute("minutes", service.getTimeList());

        return "alimTalk/index";
    }

    @GetMapping("alimTalk/list")
    public String list(Model model, AlimVO alimVO) {
        List<AlimVO> sendList = service.listSendTime(alimVO);
        model.addAttribute("sendList", sendList);

        return "alimTalk/index :: #sendTimeUl";
    }

    @GetMapping("alimTalk/view")
    public String view(Model model, AlimVO alimVO) {

        AlimVO alim = service.view(alimVO);

        String selectedHour = alim.getSendTime().startsWith("00")  ? "12" : alim.getSendTime().substring(0,2);
        int selectedHourNum = Integer.parseInt(alim.getSendTime().substring(0,2));
        String AMPM = "AM";
        if (selectedHourNum >= 12) {
            AMPM = "PM";
            int hour = selectedHourNum - 12;
            if (selectedHourNum == 12) {
                selectedHour = "12";
            }
            else if (hour-10 < 0) {
                selectedHour = "0" + Integer.toString(hour);
            } else {
                selectedHour = Integer.toString(hour);
            }
        }
        model.addAttribute("hours", service.getHourList());
        model.addAttribute("minutes", service.getTimeList());

        model.addAttribute("alimSeq", alim.getAlimSeq());
        model.addAttribute("sendDay", alim.getSendDay());
        model.addAttribute("title", alim.getTitle());
        model.addAttribute("content", alim.getContent());
        model.addAttribute("AMPM", AMPM);
        model.addAttribute("selectedHour", selectedHour);
        model.addAttribute("selectedMinute", alim.getSendTime().substring(3));
        return "alimTalk/index :: #inputTableDiv";
    }

    @PostMapping("alimTalk/insert")
    public String insertAction(HttpServletRequest request, AlimVO alimVO) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        int result = service.insertAction(member.getMemberId(), alimVO);
        return "alimTalk/index";
    }
    @PostMapping("alimTalk/update")
    public String updateAction(HttpServletRequest request, AlimVO alimVO) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        int result = service.updateAction(member.getMemberId(), alimVO);
        return "alimTalk/index";
    }

}

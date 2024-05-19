package com.anything.alimTalk.alim;

import com.anything.alimTalk.send.SendService;
import com.anything.common.service.CommonService;
import com.anything.login.MemberVO;
import com.anything.notepad.NotepadVO;
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
    private final CommonService commonService;
    @GetMapping("alimTalk/index")
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("hours", commonService.getHourList());
        model.addAttribute("minutes", commonService.getTimeList());

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

        String AMPM = "AM";
        String sendHour = alim.getSendTime().substring(0,2);
        String sendMinute = alim.getSendTime().substring(3);

        int sendHourInt = Integer.parseInt(sendHour);

        if (sendHourInt >= 12) {
            AMPM = "PM";
            sendHour = commonService.format12Hour(sendHourInt);
        }

        model.addAttribute("hours", commonService.getHourList());
        model.addAttribute("minutes", commonService.getTimeList());

        model.addAttribute("alimSeq", alim.getAlimSeq());
        model.addAttribute("sendDay", alim.getSendDay());
        model.addAttribute("title", alim.getTitle());
        model.addAttribute("content", alim.getContent());
        model.addAttribute("AMPM", AMPM);
        model.addAttribute("selectedHour", sendHour);
        model.addAttribute("selectedMinute", sendMinute);
        return "alimTalk/form :: #inputTableDiv";
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

    @GetMapping("alimTalk/form")
    public String form(Model model, AlimVO alimVO) {

        model.addAttribute("sendDay", alimVO.getSendDay());
        model.addAttribute("hours", commonService.getHourList());
        model.addAttribute("minutes", commonService.getTimeList());
        model.addAttribute("sendList", service.listSendTime(alimVO));

        return "alimTalk/form";
    }

}

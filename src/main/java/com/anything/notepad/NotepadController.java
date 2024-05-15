package com.anything.notepad;

import com.anything.common.service.CommonService;
import com.anything.login.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NotepadController {

    private final NotepadService service;
    private final CommonService commonService;

    @GetMapping("notepad/index")
    public String index(HttpServletRequest request, Model model) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");

        model.addAttribute("notepadList", service.list(member.getMemberId()));
        return "notepad/index";
    }

    @PostMapping("notepad/insert")
    public String insertAction(HttpServletRequest request, NotepadVO notepadVO) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        int result = service.insertAction(member.getMemberId(), notepadVO);
        return "notepad/index";
    }
    @PostMapping("notepad/update")
    public String updateAction(HttpServletRequest request, NotepadVO notepadVO) {
        MemberVO member = (MemberVO)request.getSession().getAttribute("member");
        int result = service.updateAction(member.getMemberId(), notepadVO);
        return "notepad/index";
    }

    @GetMapping("notepad/form")
    public String form(Model model, NotepadVO notepadVO) {

        NotepadVO notepad = notepadVO.getNotepadSeq() == 0 ? new NotepadVO() : service.view(notepadVO);

        String AMPM = "AM";
        String sendHour = "";
        String sendMinute = "";

        if (notepad == null) {
            notepad = new NotepadVO();
        }
        else {
            sendMinute = notepad.getSendTime().substring(3);
            sendHour = notepad.getSendTime().substring(0,2);
            int sendHourInt = Integer.parseInt(sendHour);

            if (sendHourInt >= 12) {
                AMPM = "PM";
                sendHour = commonService.format12Hour(sendHourInt);
            }
        }

        model.addAttribute("hours", service.getHourList());
        model.addAttribute("minutes", service.getTimeList());
        model.addAttribute("notepad", notepad);
        model.addAttribute("AMPM", AMPM);
        model.addAttribute("selectedHour", sendHour);
        model.addAttribute("selectedMinute", sendMinute);

        return "notepad/form";
    }
}

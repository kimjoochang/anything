package com.anything.notepad;

import com.anything.alimTalk.send.SendService;
import com.anything.alimTalk.send.SendVO;
import com.anything.common.service.ApiService;
import com.anything.login.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotepadService {
    private final NotepadRepository repository;
    public List<NotepadVO> list(long memberId) {
        return repository.list(memberId);
    }
    public NotepadVO view(NotepadVO notepadVO) {
        return repository.view(notepadVO);
    }
    public int insertAction(MemberVO member, NotepadVO notepadVO) {
        notepadVO.setMemberId(member.getMemberId());

        String sendDt = notepadVO.getSendDay()+ " " + notepadVO.getSendTime();
        try {
            notepadVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }
        return repository.insert(notepadVO);
    }
    public int updateAction(MemberVO member, NotepadVO notepadVO) {
        notepadVO.setMemberId(member.getMemberId());

        String sendDt = notepadVO.getSendDay()+ " " + notepadVO.getSendTime();
        try {
            notepadVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }
        return repository.update(notepadVO);
    }
    public SendVO createSendVO(NotepadVO notepadVO, MemberVO memberVO) {
        SendVO sendVO = new SendVO();

        sendVO.setContentSeq(notepadVO.getNotepadSeq());
        sendVO.setContentType("NOTEPAD");
        sendVO.setMemberId(memberVO.getMemberId());
        sendVO.setTitle(notepadVO.getTitle());
        sendVO.setContent(notepadVO.getContent());
        sendVO.setAccessToken(memberVO.getAccessToken());
        sendVO.setRefreshToken(memberVO.getRefreshToken());
        sendVO.setLoginDt(memberVO.getLoginDt());
        sendVO.setRegId(memberVO.getMemberId());

        return sendVO;
    }
}

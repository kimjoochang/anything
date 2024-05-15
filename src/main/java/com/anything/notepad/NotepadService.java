package com.anything.notepad;

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
public class NotepadService implements INotepadService {
    private final NotepadRepository repository;
    @Override
    public List<NotepadVO> list(long memberId) {
        return repository.list(memberId);
    }

    @Override
    public NotepadVO view(NotepadVO notepadVO) {
        return repository.view(notepadVO);
    }

    @Override
    public int insertAction(long memberId, NotepadVO notepadVO) {
        notepadVO.setMemberId(memberId);

        String sendDt = notepadVO.getSendDay()+ " " + notepadVO.getSendTime();
        try {
            notepadVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }
        return repository.insert(notepadVO);
    }

    @Override
    public int updateAction(long memberId, NotepadVO notepadVO) {
        notepadVO.setMemberId(memberId);

        String sendDt = notepadVO.getSendDay()+ " " + notepadVO.getSendTime();
        try {
            notepadVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }

       return repository.update(notepadVO);
    }

    @Override
    public List<String> getHourList() {
        List<String> hours = new ArrayList<>();

        for(int i=1; i < 13; i++) {
            if (i-10 < 0) {
                hours.add("0" + Integer.toString(i));
            } else {
                hours.add(Integer.toString(i));
            }
        }

        return hours;
    }

    @Override
    public List<String> getTimeList() {
        List<String> minutes = new ArrayList<>();

        for(int i=0; i < 60; i=i+5) {

            if (i-10 < 0) {
                minutes.add("0" + Integer.toString(i));
            } else {
                minutes.add(Integer.toString(i));
            }
        }
        return minutes;
    }

}

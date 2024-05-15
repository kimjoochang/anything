package com.anything.notepad;

import java.util.List;
public interface INotepadService {
    List<NotepadVO> list(long memberId);
    NotepadVO view(NotepadVO notepadVO);
    int insertAction(long memberId, NotepadVO notepadVO);
    int updateAction(long memberId, NotepadVO notepadVO);
    List<String> getHourList();
    List<String> getTimeList();
}

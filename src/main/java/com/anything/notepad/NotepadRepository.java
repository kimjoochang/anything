package com.anything.notepad;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface NotepadRepository {
    List<NotepadVO> list(long memberId);
    NotepadVO view(NotepadVO notepadVO);
    int insert(NotepadVO notepadVO);
    int update(NotepadVO notepadVO);
}

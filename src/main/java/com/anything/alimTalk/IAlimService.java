package com.anything.alimTalk;

import java.util.List;
public interface IAlimService {
    List<AlimDto> list(AlimDto alimDto);
    int insertAction(AlimDto alimDto);
    int updateAction(AlimDto alimDto);
}

package com.anything.alimTalk;

import java.util.List;
public interface IAlimService {
    List<AlimVO> list(AlimVO alimVO);
    int insertAction(AlimVO alimVO);
    int updateAction(AlimVO alimVO);

    boolean sendAction();
}

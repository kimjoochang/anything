package com.anything.alimTalk.alim;

import java.util.List;
public interface IAlimService {
    List<AlimVO> list(AlimVO alimVO);
    List<String> listSendTime(AlimVO alimVO);
    int insertAction(long memberId, AlimVO alimVO);
    int updateAction(AlimVO alimVO);

    boolean sendAction();
}

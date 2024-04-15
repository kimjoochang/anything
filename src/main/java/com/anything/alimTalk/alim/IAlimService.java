package com.anything.alimTalk.alim;

import java.util.List;
public interface IAlimService {
    List<AlimVO> list(AlimVO alimVO);
    AlimVO view(AlimVO alimVO);
    List<AlimVO> listSendTime(AlimVO alimVO);
    int insertAction(long memberId, AlimVO alimVO);
    int updateAction(long memberId,AlimVO alimVO);

    List<String> getHourList();
    List<String> getTimeList();
}

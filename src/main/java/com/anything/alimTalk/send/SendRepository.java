package com.anything.alimTalk.send;

import com.anything.alimTalk.alim.AlimVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SendRepository {
    List<SendVO> list(String sendDt);
    int insert(List<SendVO> successSendList);
    int updateTokenByRefresh(SendVO alimVO);
    int updateAlimSendCd(List<SendVO> sendList);
    int updateNotepadSendCd(List<SendVO> sendList);
}

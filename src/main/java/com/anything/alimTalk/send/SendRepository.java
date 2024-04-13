package com.anything.alimTalk.send;

import com.anything.alimTalk.alim.AlimVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SendRepository {
    List<SendVO> list(Map<String, Object> paramMap);
    int insert(List<SendVO> successSendList);
    int updateTokenByRefresh(SendVO alimVO);
    int updateSendCd(List<SendVO> sendList);
}

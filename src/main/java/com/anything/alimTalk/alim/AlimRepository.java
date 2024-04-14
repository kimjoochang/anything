package com.anything.alimTalk.alim;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AlimRepository {
    List<AlimVO> list(Map<String, Object> paramMap);
    AlimVO view(AlimVO alimVO);
    List<AlimVO> listSendTime(AlimVO alimVO);
    int insert(AlimVO alimVO);
    int update(AlimVO alimVO);
}

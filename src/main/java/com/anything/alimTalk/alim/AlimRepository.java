package com.anything.alimTalk.alim;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlimRepository {
    List<AlimVO> list(AlimVO alimVO);
    List<String> listSendTime(AlimVO alimVO);
    int insert(AlimVO alimVO);
    int updateAction(AlimVO alimVO);
}

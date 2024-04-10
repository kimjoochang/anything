package com.anything.alimTalk;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlimRepository {
    List<AlimVO> list(AlimVO alimVO);
    int insertAction(AlimVO alimVO);
    int updateAction(AlimVO alimVO);
}

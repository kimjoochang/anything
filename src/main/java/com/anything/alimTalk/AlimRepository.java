package com.anything.alimTalk;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlimRepository {
    List<AlimDto> list(AlimDto alimDto);
    int insertAction(AlimDto alimDto);
    int updateAction(AlimDto alimDto);
}

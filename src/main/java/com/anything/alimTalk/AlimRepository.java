package com.anything.alimTalk;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlimRepository {
    List<AlimDto> list(AlimDto alimDto);
    int insertAction(AlimDto alimDto);
    int updateAction(AlimDto alimDto);
}

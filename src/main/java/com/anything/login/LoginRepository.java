package com.anything.login;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LoginRepository {
    List<LoginDto> list(LoginDto loginDto);
    int insertAction(LoginDto loginDto);
    int updateAction(LoginDto loginDto);
}

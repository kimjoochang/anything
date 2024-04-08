package com.anything.login;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginRepository {
    List<MemberDto> list(MemberDto memberDto);
    int insertAction(MemberDto memberDto);
    int updateAction(MemberDto memberDto);
}

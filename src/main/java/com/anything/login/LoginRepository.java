package com.anything.login;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Mapper
public interface LoginRepository {
    Optional<MemberVO> view(long memberId);
    int insert(MemberVO member);
    int updateTokenByLogin(MemberVO member);
}

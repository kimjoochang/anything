package com.anything.login;

import com.anything.common.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Getter @Setter
@Builder
@AllArgsConstructor
public class MemberVO extends BaseVO {
    Long memberId;
    String nickname;
    String type;
    int role;
    String email;
    String fileMaxSize;
    String accessToken;
    String refreshToken;
    Date loginDt;

    public MemberVO () {}
    public Optional<MemberVO> updateToken (String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.loginDt = new Date();
        return Optional.of(this);
    }
}

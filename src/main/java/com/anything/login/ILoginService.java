package com.anything.login;

import java.util.Optional;

public interface ILoginService {
    Optional<MemberVO> saveAction(OauthTokenDto oauthTokenDto);
    Optional<OauthTokenDto> getToken(String code);
    void logout(String accessToken);
}

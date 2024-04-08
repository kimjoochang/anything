package com.anything.login;

import java.util.Optional;

public interface ILoginService {
    Optional<MemberDto> saveAction(OauthToken oauthToken);
    Optional<OauthToken> getToken(String code);
    void logout(String accessToken);
}

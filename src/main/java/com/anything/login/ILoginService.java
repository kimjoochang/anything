package com.anything.login;

import java.util.Optional;

public interface ILoginService {
    Optional<LoginDto> saveAction(OauthToken oauthToken);
    Optional<OauthToken> getToken(String code);
    void logout(String accessToken);
}

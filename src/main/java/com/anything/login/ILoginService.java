package com.anything.login;

import java.util.Optional;

public interface ILoginService {
    String getToken(String code);
}

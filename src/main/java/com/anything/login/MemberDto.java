package com.anything.login;

import com.anything.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
/*
"id":3414001470,
"connected_at":"2024-03-31T14:58:53Z",
"properties":
    {"nickname":"김주창"},
    "kakao_account":{"profile_nickname_needs_agreement":false,
                     "profile":{ "nickname":"김주창",
                                 "is_default_nickname":false},
                                 "has_email":true,
                                 "email_needs_agreement":false,
                                 "is_email_valid":true,
                                 "is_email_verified":true,
                                 "email":"kimjc0707@naver.com"}
* */
@Getter
@Setter
public class MemberDto extends BaseDto {
    Long memberId;
    String nickname;
    String type;
    int role;
    String phone;
    String email;
    OauthToken oauthToken;

}

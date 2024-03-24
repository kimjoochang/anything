package com.anything.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto extends BaseDto{
    String password;
    String name;
    String type;
    int role;
    String phone;
    String email;
    String fileMaxSize;
}

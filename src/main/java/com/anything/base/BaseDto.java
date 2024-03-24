package com.anything.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDto {
    Long memberId;
    Long regId;
    Date regDt;
    Long modiId;
    Date modiDt;
}

package com.anything.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseVO {
    Long memberId;
    Long regId;
    Date regDt;
    Long modiId;
    Date modiDt;
}

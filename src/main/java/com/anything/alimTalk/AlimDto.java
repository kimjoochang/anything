package com.anything.alimTalk;

import com.anything.base.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlimDto extends BaseDto {
    Long alimSeq;
    String title;
    String content;
    String sendDt;
    String sendCd;
    String sendStusMsg;
}

package com.anything.alimTalk;

import com.anything.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimDto extends BaseDto {
    Long alimSeq;
    String title;
    String description;
    String sendDt;
    String sendCd;
    String sendStusMsg;
}

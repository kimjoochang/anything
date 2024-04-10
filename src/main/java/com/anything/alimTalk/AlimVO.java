package com.anything.alimTalk;

import com.anything.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimVO extends BaseVO {
    Long alimSeq;
    String title;
    String description;
    String sendDt;
    String sendCd;
    String sendStusMsg;
}

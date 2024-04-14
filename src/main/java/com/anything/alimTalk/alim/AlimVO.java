package com.anything.alimTalk.alim;

import com.anything.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlimVO extends BaseVO {
    Long alimSeq;
    Long memberId;
    String title;
    String content;
    Date sendDt;
    String sendDay;
    String sendTime;
    String sendCd;
}

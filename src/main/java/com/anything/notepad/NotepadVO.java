package com.anything.notepad;

import com.anything.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotepadVO extends BaseVO {
    Long notepadSeq;
    Long memberId;
    String title;
    String content;
    Date sendDt;
    String sendYn;
    String sendDay;
    String sendTime;
    String sendCd;
}

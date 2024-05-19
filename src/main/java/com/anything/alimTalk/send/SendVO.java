package com.anything.alimTalk.send;

import com.anything.alimTalk.alim.AlimVO;
import com.anything.common.vo.BaseVO;
import com.anything.login.MemberVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SendVO extends BaseVO {
    Long sendSeq;
    String contentType;
    Long contentSeq;
    String title;
    String content;
    String sendCd;
    String sendStusMsg;
    String accessToken;
    String refreshToken;
    Date loginDt;
}

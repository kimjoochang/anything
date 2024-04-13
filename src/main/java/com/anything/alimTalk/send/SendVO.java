package com.anything.alimTalk.send;

import com.anything.alimTalk.alim.AlimVO;
import com.anything.common.vo.BaseVO;
import com.anything.login.MemberVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SendVO extends AlimVO {
    Long sendSeq;
    String sendStusMsg;
    String accessToken;
    String refreshToken;
    Date loginDt;
}

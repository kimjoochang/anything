package com.anything.alimTalk.alim;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AlimService implements IAlimService{
    private final AlimRepository repository;
    @Override
    public List<AlimVO> list(AlimVO alimVO) {
        alimVO.getSendDt();
        Map<String, Object> paramMap = new HashMap<>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            paramMap.put("startDt", formatter.parse(alimVO.getSendDay() + " " + "00:00:00"));
            paramMap.put("endDt", formatter.parse(alimVO.getSendDay() + " " + "23:59:59"));
        } catch (Exception e) {
            e.getMessage();
        }
        return repository.list(paramMap);
    }

    @Override
    public AlimVO view(AlimVO alimVO) {

        return repository.view(alimVO);
    }

    @Override
    public List<AlimVO> listSendTime(AlimVO alimVO) {
        return repository.listSendTime(alimVO);
    }

    @Override
    public int insertAction(long memberId, AlimVO alimVO) {
        alimVO.setMemberId(memberId);

        String sendDt = alimVO.getSendDay()+ " " + alimVO.getSendTime();
        try {
            alimVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }
        return repository.insert(alimVO);
    }

    @Override
    public int updateAction(long memberId, AlimVO alimVO) {
        alimVO.setMemberId(memberId);

        String sendDt = alimVO.getSendDay()+ " " + alimVO.getSendTime();
        try {
            alimVO.setSendDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendDt));
        } catch (Exception e) {
            e.getMessage();
        }

       return repository.update(alimVO);
    }
}

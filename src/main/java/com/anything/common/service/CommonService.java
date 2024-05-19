package com.anything.common.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommonService {

    public String format12Hour(int sendHour) {

        String formatHour = "";
        int hour = sendHour - 12;

        if (sendHour == 12) {
            formatHour = "12";
        }
        else if (hour-10 < 0) {
            formatHour = "0" + Integer.toString(hour);
        }
        else {
            formatHour = Integer.toString(hour);
        }

        return formatHour;
    }

    public List<String> getHourList() {
        List<String> hours = new ArrayList<>();

        for(int i=1; i < 13; i++) {
            if (i-10 < 0) {
                hours.add("0" + Integer.toString(i));
            } else {
                hours.add(Integer.toString(i));
            }
        }
        return hours;
    }

    public List<String> getTimeList() {
        List<String> minutes = new ArrayList<>();

        for(int i=0; i < 60; i=i+5) {
            if (i-10 < 0) {
                minutes.add("0" + Integer.toString(i));
            } else {
                minutes.add(Integer.toString(i));
            }
        }
        return minutes;
    }
}

package com.anything.alimTalk.send;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendScheduler {
    private final SendService service;

    @Scheduled (cron = "0 0/5 * * * ?") //5분
    public void run() {
        service.sendAction(null);
    }
}

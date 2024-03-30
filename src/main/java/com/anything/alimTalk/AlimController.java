package com.anything.alimTalk;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AlimController {

    private AlimService alimService;
    @GetMapping("alimTalk/index")
    public String index() {
        return "alimTalk/index";
    }

    @PostMapping("alimTalk/insert")
    public ResponseEntity insertAction(AlimDto alimDto) {
        int result = alimService.insertAction(alimDto);
        return null;
    }
}

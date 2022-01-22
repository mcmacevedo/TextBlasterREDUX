package Acevedo.Marc.TextBlasterRedux.controllers;

import Acevedo.Marc.TextBlasterRedux.models.TwilioMessage;
import Acevedo.Marc.TextBlasterRedux.services.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * API Controller with a single endpoint to be hit by Twilio webhook
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/sbs22")
public class ApiController {
    @Autowired
    SmsService service;

    @PostMapping("/twilio")
    public void twilioWebHookRequest(TwilioMessage message) {
        log.info(message.toString());
        service.processTwilioMessage(message);
    }
}

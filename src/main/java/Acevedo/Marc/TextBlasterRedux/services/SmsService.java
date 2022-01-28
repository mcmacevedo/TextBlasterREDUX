package Acevedo.Marc.TextBlasterRedux.services;

import Acevedo.Marc.TextBlasterRedux.dao.MessageRepository;
import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import Acevedo.Marc.TextBlasterRedux.models.TwilioMessage;
import Acevedo.Marc.TextBlasterRedux.repositories.ExcelRepository;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@Slf4j
public class SmsService {
    @Autowired
    ExcelRepository excelRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Value(value = "${admin.phoneNumber}")
    String adminPhoneNumber;

    @Value(value = "${twilio.accountSid}")
    String twilioAccountSid;

    @Value(value = "${twilio.authToken}")
    String twilioAuthToken;


    public void processTwilioMessage(TwilioMessage message) {
        String sender = message.getFrom();
        if (excelRepository.attendeesByPhoneNumber.containsKey(sender)) {
            messageRepository.save(message);
            if (adminPhoneNumber.equals(sender)) {
                log.info("Received message from Admin: {}", sender);
                // TODO: Process the text body for potential commands
            }
        }
    }

    @PostConstruct
    public void initializeTwilioContext() {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }
}

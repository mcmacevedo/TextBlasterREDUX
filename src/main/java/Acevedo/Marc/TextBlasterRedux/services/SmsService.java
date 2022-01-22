package Acevedo.Marc.TextBlasterRedux.services;

import Acevedo.Marc.TextBlasterRedux.dao.MessageRepository;
import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import Acevedo.Marc.TextBlasterRedux.models.TwilioMessage;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.MapUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class SmsService {
    @Autowired
    Map<String, Attendee> attendeesByPhoneNumber;

    @Autowired
    private MessageRepository messageRepository;

    @Value(value = "${admin.phoneNumber}")
    String adminPhoneNumber;

    public void processTwilioMessage(TwilioMessage message) {
        String sender = message.getFrom();
        if (attendeesByPhoneNumber.containsKey(sender)) {
            messageRepository.save(message);
            if (adminPhoneNumber.equals(sender)) {
                // TODO: Process the text body for potential commands
            }
        }
    }

    @PostConstruct
    public void initializeTwilioContext() {
        Twilio.init("AC2ad2ef4e9398442783404ea862263188", "456ddb4403ec5d38cf2e585fea9af69f");
    }
}

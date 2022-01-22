package Acevedo.Marc.TextBlasterRedux.services;

import Acevedo.Marc.TextBlasterRedux.dao.MessageRepository;
import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import Acevedo.Marc.TextBlasterRedux.models.TwilioMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GuiService {
    @Autowired
    Map<String, Attendee> attendeesByPhoneNumber;

    @Autowired
    private MessageRepository messageRepository;

    public List<String> getMessages() {
        List<TwilioMessage> messageList = messageRepository.findAllByOrderByIdDesc();
        return messageList.stream()
                .map(twilioMessage ->
                    String.format("%s said: %s", attendeesByPhoneNumber.get(twilioMessage.getFrom()).getFullName(), twilioMessage.getBody())
                ).collect(Collectors.toList());
    }
}

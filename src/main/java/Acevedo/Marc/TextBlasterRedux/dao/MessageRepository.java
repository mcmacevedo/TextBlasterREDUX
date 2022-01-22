package Acevedo.Marc.TextBlasterRedux.dao;

import Acevedo.Marc.TextBlasterRedux.models.TwilioMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<TwilioMessage, Long> {
    public List<TwilioMessage> findAllByOrderByIdDesc();
}

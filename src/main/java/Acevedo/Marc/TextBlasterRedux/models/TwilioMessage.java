package Acevedo.Marc.TextBlasterRedux.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "twilio_message")
public class TwilioMessage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String MessageSid;
        private String SmsSid;
        private String AccountSid;
        private String MessagingServiceSid;
        @Column(name = "sender")
        @NotNull
        private String From;
        @Column(name = "recipient")
        @NotNull
        private String To;
        @NotNull
        private String Body;
        private Integer NumMedia;

        public TwilioMessage(Long id, String messageSid, String smsSid, String accountSid, String messagingServiceSid, String from, String to, String body, Integer numMedia) {
                this.id = id;
                MessageSid = messageSid;
                SmsSid = smsSid;
                AccountSid = accountSid;
                MessagingServiceSid = messagingServiceSid;
                From = from;
                To = to;
                Body = body;
                NumMedia = numMedia;
        }

        public TwilioMessage() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getMessageSid() {
                return MessageSid;
        }

        public void setMessageSid(String messageSid) {
                MessageSid = messageSid;
        }

        public String getSmsSid() {
                return SmsSid;
        }

        public void setSmsSid(String smsSid) {
                SmsSid = smsSid;
        }

        public String getAccountSid() {
                return AccountSid;
        }

        public void setAccountSid(String accountSid) {
                AccountSid = accountSid;
        }

        public String getMessagingServiceSid() {
                return MessagingServiceSid;
        }

        public void setMessagingServiceSid(String messagingServiceSid) {
                MessagingServiceSid = messagingServiceSid;
        }

        public String getFrom() {
                return From;
        }

        public void setFrom(String from) {
                From = from;
        }

        public String getTo() {
                return To;
        }

        public void setTo(String to) {
                To = to;
        }

        public String getBody() {
                return Body;
        }

        public void setBody(String body) {
                Body = body;
        }

        public Integer getNumMedia() {
                return NumMedia;
        }

        public void setNumMedia(Integer numMedia) {
                NumMedia = numMedia;
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TwilioMessage that = (TwilioMessage) o;
                return Objects.equals(id, that.id) && Objects.equals(MessageSid, that.MessageSid) && Objects.equals(SmsSid, that.SmsSid) && Objects.equals(AccountSid, that.AccountSid) && Objects.equals(MessagingServiceSid, that.MessagingServiceSid) && Objects.equals(From, that.From) && Objects.equals(To, that.To) && Objects.equals(Body, that.Body) && Objects.equals(NumMedia, that.NumMedia);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, MessageSid, SmsSid, AccountSid, MessagingServiceSid, From, To, Body, NumMedia);
        }

        @Override
        public String toString() {
                return new StringJoiner(", ", TwilioMessage.class.getSimpleName() + "[", "]")
                        .add("id=" + id)
                        .add("MessageSid='" + MessageSid + "'")
                        .add("SmsSid='" + SmsSid + "'")
                        .add("AccountSid='" + AccountSid + "'")
                        .add("MessagingServiceSid='" + MessagingServiceSid + "'")
                        .add("From='" + From + "'")
                        .add("To='" + To + "'")
                        .add("Body='" + Body + "'")
                        .add("NumMedia=" + NumMedia)
                        .toString();
        }
}

package Acevedo.Marc.TextBlasterRedux.configs;

import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.*;

@Configuration
@Slf4j
public class Config extends WebSecurityConfigurerAdapter {
    @Value(value = "${excel.path}")
    String filePath;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/api/v1/sbs22/**").permitAll()
            .and()
            .csrf()
            .disable();
        http
            .authorizeRequests()
            .antMatchers("/gui")
            .authenticated()
            .and()
            .oauth2Login();
    }


    @SneakyThrows
    @Bean
    @Order(1)
    List<Attendee> getAttendees() {
        List<Attendee> attendeeList = new ArrayList<>();
        FileInputStream attendeesFile = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(attendeesFile);
        Iterator<Row> attendeeIterator = workbook.getSheetAt(0).rowIterator();
        while (attendeeIterator.hasNext()) {
            Row attendeeRow = attendeeIterator.next();
            if (!(attendeeRow.getRowNum() == 0)) {
                Attendee attendee = constructAttendee(attendeeRow);
                attendeeList.add(attendee);
            }
        }
        return attendeeList;
    }

    @Bean
    @Order(2)
    Map<String, Attendee> getAttendeesByPhoneNumber() {
        Map<String, Attendee> attendeesByPhoneNumber = new HashMap<>();
        getAttendees().forEach(attendee -> attendeesByPhoneNumber.put(attendee.phoneNumber(), attendee));
        return attendeesByPhoneNumber;
    };

    private Attendee constructAttendee(Row attendeeRow) {
        Attendee attendee =  new Attendee(
                attendeeRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                attendeeRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                attendeeRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                attendeeRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                (int)attendeeRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue()
        );
        log.info("Adding new Attendee {}", attendee);
        return attendee;
    }

}

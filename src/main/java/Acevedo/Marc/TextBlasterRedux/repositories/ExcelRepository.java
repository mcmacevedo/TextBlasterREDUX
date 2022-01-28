package Acevedo.Marc.TextBlasterRedux.repositories;

import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ExcelRepository {

    @Value(value = "excel.path")
    String filePath;

    FileInputStream attendeesFile = new FileInputStream(filePath);
    public List<Attendee> attendees;
    public Map<String, Attendee> attendeesByPhoneNumber;


    public ExcelRepository() throws FileNotFoundException {}

    @SneakyThrows
    @PostConstruct
    public void initializeAttendeesList() {
        XSSFWorkbook workbook = new XSSFWorkbook(attendeesFile);
        Iterator<Row> attendeeIterator = workbook.getSheetAt(0).rowIterator();
        while (attendeeIterator.hasNext()) {
            Row attendeeRow = attendeeIterator.next();
            if (!(attendeeRow.getRowNum() == 0)) {
                Attendee attendee = constructAttendee(attendeeIterator.next());
                attendees.add(attendee);
                attendeesByPhoneNumber.put(attendee.phoneNumber(), attendee);
            }
        }
    }




    private Attendee constructAttendee(Row attendeeRow) {
        Attendee attendee =  new Attendee(
            attendeeRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
            attendeeRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
            attendeeRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
            attendeeRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
            Integer.parseInt(attendeeRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue())
        );
        log.info("Adding new Attendee {}", attendee);
        return attendee;
    }
}

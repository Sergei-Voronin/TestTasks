import entities.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalculateTest {
    private static List<Ticket> ticketList = new ArrayList<>();
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yy");
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
    private static Сalculate calc = new Сalculate();
    @BeforeAll
    public static void createTicketList(){
        ticketList.add(new Ticket("VVO", "Владивосток", "TLV",
                "Тель-Авив", LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("10:00", timeFormat), LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("19:00", timeFormat), "SU", 3L, 12450L));
        ticketList.add(new Ticket("VVO", "Владивосток", "TLV",
                "Тель-Авив", LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("10:00", timeFormat), LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("18:00", timeFormat), "SU", 3L, 12450L));
        ticketList.add(new Ticket("VVO", "Владивосток", "TLV",
                "Тель-Авив", LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("10:00", timeFormat), LocalDate.parse("10.02.22", dateFormat),
                LocalTime.parse("17:00", timeFormat), "SU", 3L, 12450L));
    }

    @Test
    public void getAvgTest(){
        Assertions.assertEquals(LocalTime.parse("8:00", timeFormat), calc.getAvg(ticketList));
    }

    @Test
    public void getPercentileTest(){
        Assertions.assertEquals(LocalTime.parse("9:00", timeFormat), calc.getPercentile(ticketList, 90));
    }
}

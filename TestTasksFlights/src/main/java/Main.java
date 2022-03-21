import entities.Ticket;

import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Parser parser = new Parser();
        parser.preparationForParsing();
        List<Ticket> ticketList = parser.parse();


        Сalculate calc = new Сalculate();

        System.out.println("Среднее время полета между городами Владивосток и Тель-Авив: " + calc.getAvg(ticketList));
        System.out.println("90-й процентиль времени полета между городами Владивосток и Тель-Авив: " + calc.getPercentile(ticketList, 90));

    }
}

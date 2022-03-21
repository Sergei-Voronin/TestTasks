import entities.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Сalculate {

    public LocalTime getAvg (List<Ticket> tickets){
        List<Long> valuesInMillis = getValuesInMillis(tickets);
        long rezult = 0;
        for (Long value: valuesInMillis){
            rezult = rezult + value;
        }
        return LocalTime.ofSecondOfDay((rezult / tickets.size()) / 1000);
    }

    public LocalTime getPercentile (List<Ticket> tickets, int percentile){
        List<Long> valuesInMillis = getValuesInMillis(tickets);
        Collections.sort(valuesInMillis);
        int rez = (int) Math.ceil(percentile / 100.0 * valuesInMillis.size());
        return LocalTime.ofSecondOfDay(valuesInMillis.get(rez-1) / 1000);
    }

    private List<Long> getValuesInMillis(List<Ticket> tickets){
        List<Long> temp = new ArrayList<>();

        for (Ticket tic: tickets){
            LocalDateTime t1 = LocalDateTime.of(tic.getDepartureDate(), tic.getDepartureTime());
            LocalDateTime t2 = LocalDateTime.of(tic.getArrivalDate(), tic.getArrivalTime());
            long diff = Duration.between(t1, t2).toMillis();
            temp.add(diff);
        }
        return temp;
    }
}

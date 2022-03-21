
import entities.Ticket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yy");
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
    private String fileAddress;

    public void preparationForParsing(){
        System.out.println("Введите путь до файла tickets.json или введите exit для выхода из программы");
        Scanner console = new Scanner(System.in);
        while (fileAddress == null || !Files.exists(Paths.get(fileAddress))){
            fileAddress = console.nextLine();
            if (fileAddress.equalsIgnoreCase("exit")){
                System.exit(0);
            }
            else if (!Files.exists(Paths.get(fileAddress))){
                System.out.println("Файла по данному пути не существует! Попробуйте еще раз.");
            }
        }
    }

    public List<Ticket> parse(){
        JSONParser parser = new JSONParser();
        List<Ticket> ticketList = new ArrayList<>();
        try(FileReader reader = new FileReader(fileAddress)) {
            JSONObject object = (JSONObject)parser.parse(reader);
            JSONArray ticketsArray = (JSONArray)object.get("tickets");
            for (Object tick: ticketsArray){
                JSONObject ticket = (JSONObject)tick;
                String origin = (String)ticket.get("origin");
                String originName = (String)ticket.get("origin_name");
                String destination = (String)ticket.get("destination");
                String destinationName = (String)ticket.get("destination_name");
                LocalDate departureDate = LocalDate.parse((String)ticket.get("departure_date"), dateFormat);
                LocalTime departureTime = LocalTime.parse((String)ticket.get("departure_time"), timeFormat);
                LocalDate arrivalDate = LocalDate.parse((String)ticket.get("arrival_date"), dateFormat);
                LocalTime arrivalTime = LocalTime.parse((String)ticket.get("arrival_time"), timeFormat);
                String carrier = (String)ticket.get("carrier");
                Long stops = (Long)ticket.get("stops");
                Long price = (Long)ticket.get("price");
                ticketList.add(new Ticket(origin, originName, destination, destinationName, departureDate, departureTime,
                        arrivalDate, arrivalTime, carrier, stops, price));
            }
        }
        catch (Exception io){
            System.out.println("Парсинг не удался");
            io.printStackTrace();
        }
        return ticketList;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

}

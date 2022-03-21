import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParserTest {
    private static Parser parser = new Parser();


    @Test
    public void parseTest(){
        parser.setFileAddress("C:/Users/Atlas/IdeaProjects/TestTasks/TestTasksFlights/src/test/resources/ticketTest.json");
        Assertions.assertEquals("Москва", parser.parse().get(0).getOriginName());
        Assertions.assertEquals("Санкт-Петербург", parser.parse().get(0).getDestinationName());
    }


}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringReaderTest {
    private static final List<String> list1 = new ArrayList<>();
    private static final List<String> list2 = new ArrayList<>();
    private static final StringReader reader = new StringReader();
    private static final String fileAddress = "src/test/resources/TestStrings.txt";


    @BeforeAll
    private static void addValuesAtLists(){
        Collections.addAll(list1, "акварель", "шпатель", "веревка бельевая", "торф", "краска по металлу");
        Collections.addAll(list2, "шпатель 50 мм", "растворитель", "акварель белая");
    }

    @Test
    public void readStringsTest(){
        List<String> testList1 = new ArrayList<>();
        List<String> testList2 = new ArrayList<>();
        reader.read(fileAddress, testList1, testList2);
        Assertions.assertEquals(list1, testList1);
        Assertions.assertEquals(list2, testList2);
    }
}

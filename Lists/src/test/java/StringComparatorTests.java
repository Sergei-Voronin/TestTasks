import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringComparatorTests {
    private static final List<String> list1 = new ArrayList<>();
    private static final List<String> list2 = new ArrayList<>();
    private static final List<String> resultList = new ArrayList<>();
    private static final StringComparator comparator = new StringComparator();

    @BeforeAll
    private static void addValuesAtLists(){
        Collections.addAll(list1, "акварель", "шпатель", "веревка бельевая", "торф", "краска по металлу");
        Collections.addAll(list2, "шпатель 50 мм", "растворитель", "акварель белая");
        Collections.addAll(resultList, "акварель:акварель белая", "шпатель:шпатель 50 мм",
                "веревка бельевая:?", "торф:?", "краска по металлу:?");
    }

    @Test
    public void stringComparisonTest(){
        Assertions.assertEquals(resultList, comparator.stringComparison(list1, list2));
    }
}

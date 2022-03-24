import java.util.ArrayList;
import java.util.List;


public class Main {
    private static List<String> list1 = new ArrayList<>();
    private static List<String> list2 = new ArrayList<>();

    public static void main(String[] args) {
        StringReader reader = new StringReader();
        StringComparator comparator = new StringComparator();
        reader.read(list1, list2);
        System.out.println(comparator.stringComparison(list1, list2));
    }
}

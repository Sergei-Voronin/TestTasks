import java.util.ArrayList;
import java.util.List;

public class StringComparator {

    // Метод сравнивает строки в двух списках между собой
    public List<String> stringComparison(List<String> list1, List<String> list2){
        List<String> result = new ArrayList<>();

        // Проверяем, не пусты ли переданные в метод списки.
        if (list1.isEmpty() || list2.isEmpty()){
            System.out.println("Переданные списки пустые.");
        }

        for (String value : list1) {
            int count = 0;
            for (String s : list2) {
                /*
                 * Эта проверка позволяет понять, какая из сравниваемых строк длиннее. Это нужно для того,
                 * чтобы искать вхождение меньшей строки в большую. Искать вхождение большей строки в меньшую
                 * бессмысленно, это не даст правильного результата.
                 */
                if (value.length() < s.length()){
                    if (s.split(" ").length > 0){
                        /*
                         * Эта проверка позволяет понять, имеем ли мы дело со строкой с одним словом или строка содержит
                         * много слов. Если строка содержит много слов, то необходимо сравнить КАЖДОЕ это слово
                         * со строкой из второго списка. Это позволяет сделать вспомогательный метод arrayComparison.
                         * Он сравнивает каждое слово из составной строки со второй строкой. Результатом работы метода будет
                         * true - если совпадения есть или false - если совпадений нет.
                         */
                        if(arrayComparison(s.split(" "), value)){
                            result.add(value + ":" + s);
                            count++;
                        }
                    }
                    // Если строка состоит из одного слово, просто сравниваем его со второй строкой.
                    else if (s.contains(value)) {
                        result.add(value + ":" + s);
                        count++;
                    }
                }

                // Блок else аналогичен блоку if, с той лишь разницей, что строки сравниваются в другом порядке.
                else {
                    if (value.split(" ").length > 0){
                        if(arrayComparison(value.split(" "), s)){
                            result.add(value + ":" + s);
                            count++;
                        }
                    }
                    else if (value.contains(s)) {
                        result.add(value + ":" + s);
                        count++;
                    }
                }
            }
            if (count == 0){
                result.add(value + ":?");
            }
        }
        return result;
    }

    /*
    * Вспомогательный метод, необходимый для сравнения строк, в случае, если строки состоят более чем из одного слова.
    * Передаём в него массив слов, и строку для поиска вхождения элементов из этого массива.
    * Вернет true если вхождения есть, и false если вхождений нет.
    */
    private boolean arrayComparison (String[] stringsArray, String str){
        int count = 0;
        for (String s : stringsArray) {
            if (str.contains(s)) {
                count++;
            }
        }
        return count > 0;
    }
}

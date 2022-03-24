import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class StringReader {

    // Метод читает данные с консоли.
    public void read(List<String> list1, List<String> list2){
        Scanner console = new Scanner(System.in);
        System.out.println("Введите данные. " +
                "Затем введите continue для продолжения. " +
                "Для выхода из программы введите введите exit.");
        read(console, list1, list2);
    }

    // Перегруженный метод, читает данные из файла.
    public void read(String fileAddress, List<String> list1, List<String> list2){
        Path path = Paths.get(fileAddress);
        try(Scanner console = new Scanner(path)) {
            read(console, list1, list2);
        } catch (IOException e) {
            System.out.println("Возникли проблемы с чтением файла");
            e.printStackTrace();
        }

    }

    private void read(Scanner console, List<String> list1, List<String> list2){
        String line;
        /*
         * Этот счётчик нужен для переключения между списками. Всякий раз, когда мы встречаем цифру во введённых
         * мы увеличиваем значение счётчика. Это сигнализирует скрипту, в какой список помещать данные -
         * в первый или во второй.
         */
        int count = 0;

        while (true){
            if (console.hasNextInt()){
                console.nextInt();
                count++;
            }
            else if (console.hasNextLine()){

                line = console.nextLine();
                // Проверяем, не пустая ли строка или не содержит ли null
                if (line.equalsIgnoreCase("") || line == null){
                    continue;
                }
                /*
                 * Если введено слово "continue" - это означает, что данные закончились, списки заполненны
                 * и мы выходим из метода.
                 */
                else if (line.equalsIgnoreCase("continue")){
                    return;
                }
                // Если введено слово "exit", мы полностью прерываем работу программы.
                else if (line.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
                //Проверяем значение счётчика, чтобы решить, в какой список помещать данные.
                else {
                    if (count == 1){
                        list1.add(line);
                    }
                    if (count == 2){
                        list2.add(line);
                    }
                }
            }
        }
    }
}

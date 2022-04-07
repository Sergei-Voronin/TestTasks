import java.util.Scanner;

public class MainMenu {
    Scanner console = new Scanner(System.in);
    DBConnector dbConnector = DBConnector.getInstance();
    XmlCreator xmlCreator = new XmlCreator();
    public void start(){
        while(true){
            System.out.println("Главное меню");
            System.out.println("Введите номер интересующей Вас операции и нажмите Enter");
            System.out.println("1 - Создание оборудования на скважине");
            System.out.println("2 - Вывод общей информации об оборудовании на скважинах");
            System.out.println("3 - Экспорт всех данных в xml файл");
            System.out.println("4 - Выход из программы");
            String command = console.nextLine();
            switch (command){
                case  ("1"):
                    createWells();
                    break;
                case  ("2"):
                    getInfo();
                    break;
                case  ("3"):
                    exportXML();
                    break;
                case  ("4"):
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Не удалось распознать команду, попробуйте ещё раз");
                    break;
            }
        }
    }

    private void createWells(){
        System.out.println("Введите название скважины. Если скважины нет в базе, она будет создана");
        String well = console.nextLine();
        System.out.println("Введите количество создаваемого оборудования");
        int number = console.nextInt();
        dbConnector.createEquipment(well, number);
    }

    private void getInfo(){
        System.out.println("Введите название скважины или скважин через запятую или пробел");
        String well = console.nextLine();
        System.out.println(dbConnector.getWellsInfo(well));

    }

    private void exportXML(){
        System.out.println("Введите путь для сохранения XML файла");
        String address = console.nextLine() + "/wells.xml";
        xmlCreator.create(address);
    }

}

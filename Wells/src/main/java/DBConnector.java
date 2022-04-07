import model.Equipment;
import model.Well;

import java.sql.*;
import java.util.*;

public class DBConnector {
    private static final String CON_STR = "jdbc:sqlite::memory:";
    private static DBConnector instance = null;
    private static final NameGenerator nameGenerator = new NameGenerator();
    private final Connection connection;

    // Реализуем паттерн "Singleton", чтобы объект был в одном экземпляре.
    public static synchronized DBConnector getInstance(){
        if (instance == null) {
            try {
                instance = new DBConnector();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private DBConnector() throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
        createTables();
    }

    // Метод создаёт таблицы в базе, если их не существует.
    private void createTables(){
        try(Statement statement = this.connection.createStatement()) {
            String sqlCreateTableWell = "CREATE TABLE IF NOT EXISTS well(" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    name VARCHAR(32) NOT NULL UNIQUE" +
                    ");";
            String sqlCreateTableEquipment = "CREATE TABLE IF NOT EXISTS equipment(" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    name VARCHAR(32)," +
                    "    well_id INTEGER," +
                    "    FOREIGN KEY (well_id) REFERENCES well(id)" +
                    ");";
            statement.executeUpdate(sqlCreateTableWell);
            statement.executeUpdate(sqlCreateTableEquipment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Метод создаёт N кол-ва оборудования на скважине. Если скважина не существует, она будет создана
    public void createEquipment(String wellName, int number) {
        //Проверка существования скважины. Если её нет, создаём.
        if(!isWellExists(wellName)){
            addWell(new Well(wellName));
        }
        //Получаем объект Well из базы.
        Well well1 = getWell(wellName);

        for(int i = 0; i < number; i++){
            try {
                PreparedStatement statement = this.connection.prepareStatement("INSERT INTO equipment (name, well_id) VALUES (?, ?);");
                statement.setObject(1, nameGenerator.generate());
                statement.setObject(2, well1.getId());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Оборудование успешно создано.");
    }

    //Метод возвращает название шахты и количество оборудования на ней.
    public HashMap<String, Integer> getWellsInfo(String wells){
        HashMap<String, Integer> wellsMap = new HashMap<>();
        String[] wellsArray = wells.replaceAll(",", "").split(" ");
        for (String s : wellsArray) {
            if (!isWellExists(s)) {
                String warning = "Скважины '" + s + "' не существует!";
                System.out.println(warning);
                return null;
            }
            Well well = getWell(s);
            List<Equipment> equipmentList = getAllEquipmentInWell(well.getId());
            wellsMap.put(s, equipmentList.size());
        }
        return wellsMap;
    }

    //Метод проверяет, существует ли название шахты в базе
    private boolean isWellExists(String well){
        boolean isWellExists = false;
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT name FROM well WHERE name = ?;")) {
            ps.setObject(1, well);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isWellExists = true;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return isWellExists;
    }

    //Метод получает объект Well из базы по названию.
    private Well getWell(String wellName){
        Well well = new Well();
        try(Statement statement = this.connection.createStatement()) {
            String sql = "SELECT * FROM well WHERE name = '" + wellName + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            well.setId(resultSet.getInt("id"));
            well.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return well;
    }

    //Метод возвращает все скважины в базе.
    public List<Well> getAllWells() {
        try (Statement statement = this.connection.createStatement()) {
            List<Well> wells = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM well;");
            while (resultSet.next()) {
                wells.add(new Well(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
            return wells;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    //Метод возвращает всё оборудование на скважине.
    public List<Equipment> getAllEquipmentInWell(int wellNumber) {
        try (Statement statement = this.connection.createStatement()) {
            List<Equipment> equipments = new ArrayList<>();
            String sql = "SELECT * FROM equipment WHERE well_id = " + wellNumber + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                equipments.add(new Equipment(resultSet.getInt("id"),
                        resultSet.getString("name"), wellNumber));
            }
            return equipments;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Добавление скважины в БД
    public void addWell(Well well) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO well (name) VALUES (?);")) {
            statement.setObject(1, well.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

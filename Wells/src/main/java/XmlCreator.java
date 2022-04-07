import model.Equipment;
import model.Well;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class XmlCreator {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    DBConnector dbConnector;


    public void create(String address){
        dbConnector = DBConnector.getInstance();
        try {
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement = doc.createElement("dbinfo");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);

            List<Well> wellList = dbConnector.getAllWells();
            for (Well well : wellList) {
                Element wellElement = doc.createElement("well");
                wellElement.setAttribute("name", well.getName());
                wellElement.setAttribute("id", String.valueOf(well.getId()));
                rootElement.appendChild(wellElement);
                List<Equipment> equipmentList = dbConnector.getAllEquipmentInWell(well.getId());
                for (Equipment equipment: equipmentList){
                    Element equipmentElement = doc.createElement("equipment");
                    equipmentElement.setAttribute("name", equipment.getName());
                    equipmentElement.setAttribute("id", String.valueOf(equipment.getId()));
                    wellElement.appendChild(equipmentElement);
                }
            }
            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(address));

            //записываем данные
            //transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Готово! Файл сохранён");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

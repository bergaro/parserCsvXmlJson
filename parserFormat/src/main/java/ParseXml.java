import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParseXml {
    /**
     * Формирует список Map, где каждая Map - "объект" описаный в XML файле.
     * Key - имя тэга, Value -
     * @param inputFile XML файл
     * @param fieldsClass Поля сопоставляемого класса
     * @param objNode Наименование xml-тэга описывающего некий объект
     * @return список Map для дальнейшей инициализации полей класса
     */
    public List<Map<String, String>> readXmlToList(File inputFile, String[] fieldsClass, String objNode) {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder;
       List<Map<String, String>> objectsList = new ArrayList<>();
       try{
           builder = factory.newDocumentBuilder();
           Document document = builder.parse(inputFile);
           NodeList nodeList = document.getElementsByTagName(objNode);
           for(int i = 0; i < nodeList.getLength(); i++) {
               Node node = nodeList.item(i);
               objectsList.add(writeToMap(node, fieldsClass));
           }
       } catch (Exception ex) {
           System.out.println(ex.getMessage());
       }
       return objectsList;
    }
    /**
     * Внутренний метод формирующий Map с полями будущего java объекта.
     * Key - наименование XML тэга, Value - значение XML тэга.
     * @param node
     * @param fields
     * @return
     */
    private Map<String, String> writeToMap(Node node, String[] fields) {
        Map<String, String> xmlMap = new LinkedHashMap<>();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            for(String field : fields) {
                NodeList list = element.getElementsByTagName(field).item(0).getChildNodes();
                Node myNode = list.item(0);
               // System.out.println(field + " - " + myNode.getNodeValue());
                xmlMap.put(field, myNode.getNodeValue());
            }
        }
        return xmlMap;
    }
}
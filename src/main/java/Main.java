import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String SEPARATOR = File.separator;         // Для корректной работы на Linux/Win

    public static void main(String[] args) {
        List<Employee> staff = csvToJavaClass();
        javaClassToJsonFile(staff, "data.json");

        staff = xmlToJavaClass();
        javaClassToJsonFile(staff, "data2.json");

        jsonFileToJavaClass();
    }
    /**
     * Создаёт объекты класса Employee по файлу data.csv.
     *
     * @return Список объектов типа Employee
     */
    public static List<Employee> csvToJavaClass() {
        File file = new File("inputFiles"+SEPARATOR+"data.csv");
        ParseCsv parseCsv = new ParseCsv();
        String[] employeeFields = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> staff = parseCsv.readCsvToClass(file, employeeFields, Employee.class);
        return staff;
    }
    /**
     * Формирует список объектов типа - сотрудник.
     * Согласно содержимому XML документа.
     * информацию о сотруднике содержит XML-тэг - employee
     * @return
     */
    public static List<Employee> xmlToJavaClass() {
        List<Employee> staff = new ArrayList<>();
        File file = new File("inputFiles" + SEPARATOR + "data.xml");
        ParseXml parseXml = new ParseXml();
        String[] employeeFields = {"id", "firstName", "lastName", "country", "age"};
        List<Map<String, String>> objects = parseXml.readXmlToList(file, employeeFields, "employee");
        for(Map<String, String> object : objects) {
            long id = Long.parseLong(object.get(employeeFields[0]));
            String firstName = object.get(employeeFields[1]);
            String lastName = object.get(employeeFields[2]);
            String country = object.get(employeeFields[3]);
            System.out.println();
            int age = Integer.parseInt(object.get(employeeFields[4]));
            staff.add(new Employee(id, firstName, lastName, country, age));
        }
        return staff;
    }
    /**
     * Записывает объекты класса Employee в файл data.json
     * в JSON формате
     * @param staffList Список объектов для записи в файл
     */
    public static void javaClassToJsonFile(List<Employee> staffList, String fileExit) {
        try{
            File file = new File("exitFiles"+SEPARATOR+fileExit);
            ParseJson parseJson = new ParseJson();
            List<String> jsonFieldsList = parseJson.javaClassToJsonString(staffList);
            parseJson.jsonStringWriteToFile(jsonFieldsList, file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Преобразует json файл в объект класса Employee
     */
    public static void jsonFileToJavaClass() {
        List<Employee> staff = new ArrayList<>();
        File file = new File("exitFiles"+SEPARATOR+"data2.json");
        ParseJson parseJson = new ParseJson();
        for(String s : parseJson.readJsonFile(file)) {
            staff.add(parseJson.jsonToObject(s, new Employee(), Employee.class));
        }
        printList(staff);
    }
    /**
     * Выводит в консоль список объектов типа T
     * @param sthList некий список
     * @param <T> тип объекта в списке
     */
    private static <T> void printList(List<T> sthList) {
        for(T object : sthList) {
            System.out.println(object);
        }
    }
}

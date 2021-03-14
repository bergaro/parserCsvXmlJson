import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> staff = csvToJavaClass();
        javaClassToJsonFile(staff);
    }
    /**
     * Создаёт объекты класса Employee по файлу data.csv.
     *
     * @return Список объектов типа Employee
     */
    public static List<Employee> csvToJavaClass() {
        File file = new File("data.csv");
        ParseCsv parseCsv = new ParseCsv();
        String[] employeeFields = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> staff = parseCsv.readCsvToClass(file, employeeFields, Employee.class);
        return staff;
    }
    /**
     * Записывает объекты класса Employee в файл data.json
     * в JSON формате
     * @param staffList Список объектов для записи в файл
     */
    public static void javaClassToJsonFile(List<Employee> staffList) {
        try{
            File file = new File("data.json");
            ParseJson parseJson = new ParseJson();
            List<String> jsonFieldsList = parseJson.javaClassToJsonString(staffList);
            parseJson.jsonStringWriteToFile(jsonFieldsList, file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}

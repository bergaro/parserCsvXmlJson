import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class ParseCsv {
    /**
     * Приводит файл csv к объекту определённого класса,
     * возвращая список объектов этого класса.
     * @param file Файл с которого необходимо счиать объекты.
     * @param column Поля класса необходимые для иницииализации .
     * @param sthClass Некий класс объекты которого необходимо создать.
     * @param <T> Неизвестный класс.
     * @return Список обектов определённого Типа.
     */
    public <T> List<T> readCsvToClass(File file, String[] column, Class<T> sthClass) {
        List<T> staff = null;
        try(CSVReader csvReader = new CSVReader(new FileReader(file))) {
            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(sthClass);
            strategy.setColumnMapping(column);
            CsvToBean<T> csv = new CsvToBeanBuilder<T>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            staff = csv.parse();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return staff;
    }
}

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ParseJson {
    /**
     * Переобразовывает список объектов в строку,
     * добавляя список строк в List.
     * @param objectsList Список объектов.
     * @param <T> тип переданного объекта.
     * @return Список строк (1 строка - 1 объект).
     */
    public <T> List<String> javaClassToJsonString(List<T> objectsList) {
        List<String> jsonObjectsList = new ArrayList<>(objectsList.size());
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        for(T object : objectsList) {
            jsonObjectsList.add(gson.toJson(object));
        }
        return jsonObjectsList;
    }
    /**
     * Перобразует список строк в JSONObject и
     * складывает полученные объекты в JSONArray.
     * Запись в файл осуществляется при помощи вспомогательного
     * метода writeJSONToFile.
     * @param jsonFieldsList Список строк для формирования JSONObjects (1 объект - 1 строка).
     * @param file Файл для записи полученнго JSONArray из JSONObjects.
     * @throws ParseException
     */
    public void jsonStringWriteToFile (List<String> jsonFieldsList, File file) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(String field : jsonFieldsList) {
            jsonObject = (JSONObject) jsonParser.parse(field);
            jsonArray.add(jsonObject);
        }
        writeJsArrayToFile(jsonArray, file);
    }
    /**
     * Внутренний метод, производящий запись
     * объекта типа JSONArray в файл.
     * @param jsonArray некий массив объектов json
     * @param file файл в который необходимо произвести запись
     */
    public <T>void writeJsArrayToFile(JSONArray jsonArray, File file) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}


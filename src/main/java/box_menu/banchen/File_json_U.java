package box_forms.banchen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class File_json_U {

    /**
     *
     * @param filePath   json的文件路径
     * @return   返回JsonObject类型
     * @throws IOException
     */
    static JsonObject read_file_json(String filePath) throws IOException {
        JsonObject jsonObject = null;
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        JsonParser parser = new JsonParser();
        jsonObject = parser.parse(sb.toString()).getAsJsonObject();
        return jsonObject;
    }

    /**
     *
     * @param jsonObject   解析json
     * @return   List<Form>
     */
    static List<Form> file_to_json(JsonObject jsonObject) {
        List<Form> formList = new ArrayList<>(); // create a new list
        int id = 0;
        for (String key : jsonObject.keySet()) {
            JsonObject itemObject = jsonObject.getAsJsonObject(key);
            String type = itemObject.get("type").getAsString();
            String command = itemObject.get("command").getAsString();
            String command_form = itemObject.get("command_form").getAsString();
            // 处理物品数据值
            Form form = new Form(id, key, type, command, command_form);
            formList.add(form);
            id++;
        }
        return formList; // return the list of Form objects
    }

}

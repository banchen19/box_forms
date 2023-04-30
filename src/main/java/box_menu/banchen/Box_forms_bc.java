package box_menu.banchen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Box_forms_bc extends JavaPlugin {
    static String filePath = "./plugins/box_menu/config.json";
    String FilePath_a = "./plugins/box_menu";

    @Override
    public void onEnable() {
        File file = new File(filePath);
        File dir = new File(FilePath_a);
        if (!dir.exists() || !file.exists()) {
            dir.mkdirs();
            getLogger().info("正在创建配置文件：" + filePath);
            creat_file_json();
        }
        getLogger().info("正在读取配置文件：" + filePath);
        Bukkit.getServer().getPluginManager().registerEvents(new MyListener(), this);
        try {
            Bukkit.getPluginCommand("from_cd").setExecutor(new CdCommand(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //首次创建初始化json文件
    void creat_file_json() {
        // 创建一个新的json对象
        JsonObject itemObject1 = new JsonObject();
        itemObject1.addProperty("type", "command、from、opcommand、opfrom");
        itemObject1.addProperty("name", "别名");
        itemObject1.addProperty("command", "执行指令");
        itemObject1.addProperty("command_from", "目标json文件的地址，如：./plugins/box_menu/config.json");
        // 创建一个json对象来存储所有的物品数据值
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("BIRCH_WOOD", itemObject1);
        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String extractMiddleField(String input) {
        String fileName = input.substring(input.lastIndexOf('/') + 1, input.lastIndexOf('.'));
        return fileName;
    }
}

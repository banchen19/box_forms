package box_forms.banchen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static box_forms.banchen.File_json_U.file_to_json;
import static box_forms.banchen.File_json_U.read_file_json;

public class CdCommand implements CommandExecutor {
    static List<Form> formList;
    public CdCommand (List<Form> jsonObject)
    {
        this.formList=jsonObject;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        // 在这里编写执行cd指令的逻辑
        MyInventoryHolder holder = null;
        try {
            holder = new MyInventoryHolder(formList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.openInventory(holder.getInventory());
        return true;
    }


}


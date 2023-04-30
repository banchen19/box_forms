package box_forms.banchen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;

import java.io.*;
import java.util.*;

import static box_forms.banchen.File_json_U.file_to_json;
import static box_forms.banchen.File_json_U.read_file_json;

public class MyListener implements Listener {
    List<Form> formList;
    public  MyListener(List<Form> formList)
    {
        this.formList=formList;
    }
    //form的json
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws IOException {
        // 确保事件触发于当前打开的视图物品栏上
        Inventory inventory=event.getInventory();
        event.getInventory();
        if (!inventory.equals(inventory)) {
            return;
        }
        // 获取物品栏内的位置
        int slot = event.getRawSlot();
        Form form=formList.get(slot);
        // 获取玩家
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (form.getType().equals("command"))
        {
            //以玩家的名义执行命令
            player.closeInventory();
            player.performCommand(form.getCommand());
        }else if(form.getType().equals("opcommand"))
        {
            //以控制台的名义执行某个指令
            player.closeInventory();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), form.getCommand());
        }else
        {

            //打开新的表单
            MyInventoryHolder holder = null;
            try {
                holder = new MyInventoryHolder(file_to_json( read_file_json(form.getCommand_form())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.closeInventory();
            player.openInventory(holder.getInventory());
        }
    }

}

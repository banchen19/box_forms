package box_menu.banchen;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static box_menu.banchen.Box_forms_bc.extractMiddleField;
import static box_menu.banchen.File_json_U.file_to_json;
import static box_menu.banchen.File_json_U.read_file_json;

public class MyListener implements Listener {
    FormListSingleton formListSingleton;
    List<Form> formList;

    //form的json
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        formListSingleton = FormListSingleton.getInstance();
        formList = formListSingleton.getFormList();

        String title = event.getView().getTitle();

        if (title.equals(formListSingleton.getTitle())) {
            // 在这里添加处理代码
            Player player = (Player) event.getWhoClicked(); // 获取玩家对象

            int slot = event.getRawSlot();

            if (slot >= 0 && slot < formList.size()) {
                try {
                    Form form = formList.get(slot);
                    inventoryopen(form, player);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            event.setCancelled(true); // 取消事件
        }
    }

    void inventoryopen(Form form, Player player) throws IOException {
        switch (form.getType()) {
            case "command":
                // 以玩家的名义执行命令
                player.performCommand(form.getCommand());
                player.closeInventory();

                break;
            case "tell":
                player.sendMessage(form.getCommand());
                player.closeInventory();
                break;
            case "opcommand":

                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), form.getCommand());
                player.closeInventory();

                break;
            case "from":
                // 打开新的表单
                to_inventory(form, player);

                break;
            case "opfrom":
                // 打开新的表单
                if (player.isOp()) {
                    to_inventory(form, player);
                } else {
                    player.closeInventory();
                    player.sendMessage("权限不足");
                }
                break;
            default:
                // 处理未知类型
                player.closeInventory();
                break;
        }
    }

    private void to_inventory(Form form, Player player) {
        try {
            player.closeInventory();

            formListSingleton.setFormList(file_to_json(read_file_json(form.getCommand_form())));
            if (extractMiddleField(form.getCommand_form()).equals("config")) {
                formListSingleton.setTitle("主菜单");
            } else {
                formListSingleton.setTitle(extractMiddleField(form.getCommand_form()));
            }
            MyInventoryHolder holder = new MyInventoryHolder(file_to_json(read_file_json(form.getCommand_form())));
            player.openInventory(holder.getInventory());
        } catch (IOException e) {
            player.sendMessage("非法配置文件：" + e.getMessage() + "请联系伺服器技术人员");
        }
    }

}

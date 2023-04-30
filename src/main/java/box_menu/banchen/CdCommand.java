package box_menu.banchen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

import static box_menu.banchen.Box_forms_bc.filePath;
import static box_menu.banchen.File_json_U.file_to_json;
import static box_menu.banchen.File_json_U.read_file_json;

public class CdCommand implements CommandExecutor {
    String filePath;
    static List<Form> formList;
    FormListSingleton formListSingleton;
    public CdCommand (String filePath) throws IOException {
        this.filePath = filePath;
        formListSingleton = FormListSingleton.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        // 在这里编写执行cd指令的逻辑
        try {
                Player player = (Player) sender;

                formList=file_to_json(read_file_json(filePath));

                formListSingleton.setFormList(formList);
                formListSingleton.setTitle("主菜单");

                MyInventoryHolder holder = new MyInventoryHolder(formList);
                player.openInventory(holder.getInventory());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}


package box_forms.banchen;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MyInventoryHolder implements InventoryHolder {
    private static Inventory inventory;
    public MyInventoryHolder(List<Form> formList) throws IOException {
        //创建一个箱子菜单
        //物品类型，物品所在的位置
        inventory = Bukkit.createInventory(this, 27, formList.get(0).getCommand());
        for (Form form:formList) {
            ItemStack item =  new ItemStack(Objects.requireNonNull(Material.getMaterial(form.getItem_name())),1);
            ItemMeta meta = item.getItemMeta();
            if(form.getType().equals("command")){
                meta.setDisplayName("执行指令："+form.getCommand());
            }
            else {
                meta.setDisplayName("跳转菜单："+form.getCommand());
            }

            item.setItemMeta(meta);
            inventory.setItem(form.getId(), item);
        }
    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

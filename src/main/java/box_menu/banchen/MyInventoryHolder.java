package box_menu.banchen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MyInventoryHolder implements InventoryHolder {
    private static Inventory inventory;
    public MyInventoryHolder(List<Form> formList) throws IOException {
        FormListSingleton formListSingleton = FormListSingleton.getInstance();
        inventory = Bukkit.createInventory(this, 27, formListSingleton.getTitle());
        for (Form form:formList) {
            String from_str= String.valueOf(Material.getMaterial(form.getItem_name()));
            if (from_str.equals("null")) from_str="BIRCH_WOOD";
            ItemStack item =  new ItemStack(Objects.requireNonNull(Material.getMaterial(from_str)),1);
            ItemMeta meta = item.getItemMeta();
            if(form.getType().equals("command")){
                meta.setDisplayName("执行："+form.getName());
            }
            else  if(!form.getType().equals("opcommand")){
                meta.setDisplayName("打开："+form.getName());
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

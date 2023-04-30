package box_menu.banchen;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class FormListSingleton {

    private static FormListSingleton instance;
    private List<Form> formList;
    String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private FormListSingleton() {
        formList = new ArrayList<>();
    }

    public static FormListSingleton getInstance() {
        if (instance == null) {
            instance = new FormListSingleton();
        }
        return instance;
    }

    public List<Form> getFormList() {
        return formList;
    }

    public void setFormList(List<Form> formList) {
        this.formList = formList;
    }

}


package com.example.act5;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ClassMenuResponse {
    @SerializedName("menu")
    private List<ClassMenu> menu;

    public List<ClassMenu> getMenu() {
        return menu;
    }

    public void setMenu(List<ClassMenu> menu) {
        this.menu = menu;
    }
}

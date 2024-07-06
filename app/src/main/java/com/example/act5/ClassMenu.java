package com.example.act5;

import com.google.gson.annotations.SerializedName;

public class ClassMenu {
    @SerializedName("Menu_id")
    private String id;

    @SerializedName("AccountName")
    private String AccountName;

    @SerializedName("Price")
    private String Price;

    @SerializedName("Deskripsi")
    private String Deskripsi;

    @SerializedName("GameName")
    private String GameName;

    public ClassMenu(String AccountName, String Price, String Deskripsi, String GameName) {
        this.AccountName = AccountName;
        this.Price = Price;
        this.Deskripsi = Deskripsi;
        this.GameName = GameName;
    }

    public String getId() {
        return id;
    }

    public String getAccountName() {
        return AccountName;
    }

    public String getPrice() {
        return Price;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public String getGameName() {
        return GameName;
    }
}


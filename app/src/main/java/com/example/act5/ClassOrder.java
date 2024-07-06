package com.example.act5;

import com.google.gson.annotations.SerializedName;

public class ClassOrder {
    @SerializedName("GameName")
    private String gameName;

    @SerializedName("AccountName")
    private String accountName;

    @SerializedName("Username")
    private String username;

    @SerializedName("Status")
    private String status;

    public ClassOrder(String gameName, String accountName, String username, String status) {
        this.gameName = gameName;
        this.accountName = accountName;
        this.username = username;
        this.status = status;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAlamatEmail() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

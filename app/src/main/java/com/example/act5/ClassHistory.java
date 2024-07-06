package com.example.act5;

import com.google.gson.annotations.SerializedName;

public class ClassHistory {
    @SerializedName("Order_id")
    private int OrderId;

    @SerializedName("AccountName")
    private String AccountName;

    @SerializedName("GameName")
    private String GameName;

    @SerializedName("Username")
    private String Username;

    @SerializedName("Status")
    private String Status;

    public int getOrderId() {
        return OrderId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public String getGameName() {
        return GameName;
    }

    public String getUsername() {
        return Username;
    }

    public String getStatus() {
        return Status;
    }

    @Override
    public String toString() {
        return "ClassHistory{" +
            "OrderId=" + OrderId +
            ", AccountName='" + AccountName + '\'' +
            ", GameName='" + GameName + '\'' +
            ", Username='" + Username + '\'' +
            ", Status='" + Status + '\'' +
            '}';
    }
}

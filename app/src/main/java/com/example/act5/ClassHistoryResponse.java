package com.example.act5;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ClassHistoryResponse {
    @SerializedName("getOrderlist")
    private List<ClassHistory> getOrderlist;

    public List<ClassHistory> getHistoryOrder() {
        return getOrderlist;
    }
}

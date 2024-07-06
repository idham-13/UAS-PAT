package com.example.act5;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("nama")
    private String nama;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("email")
    private String email;

    public User(String username, String password, String nama, String noHp, String email) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.noHp = noHp;
        this.email = email;
    }

    // Getters and setters if necessary
}

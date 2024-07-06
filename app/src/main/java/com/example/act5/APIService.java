package com.example.act5;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("/login")
    Call<ResponseBody> loginUser(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<ResponseBody> registerUser(@Body User user);

    @POST("/create_menu")
    Call<ResponseBody> createMenu(@Body Menu menu);

    @GET("/menu")
    Call<ClassMenuResponse> getMenu();

    @GET("/menu")
    Call<ClassMenuResponse> getHistory();

    @POST("/order")
    Call<ResponseBody> createOrder(@Body ClassOrder order);

    @GET("/order")
    Call<ClassHistoryResponse> getHistoryOrder();
}

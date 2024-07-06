package com.example.act5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrder extends AppCompatActivity {
    Button btnBack, btnLogout;
    RecyclerView rv;

    private HistoryAdapter historyAdapter;
    private List<ClassHistory> allTask = new ArrayList<>(); // Initialize with an empty list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_order);

        btnBack = findViewById(R.id.btnBack);
        btnLogout = findViewById(R.id.btnLogoutHistory);
        rv = findViewById(R.id.rv); // Initialize your RecyclerView here

        Intent intent = getIntent();
        String loggedUser = intent.getStringExtra("loggedUser");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridLayoutManager layoutManager = new GridLayoutManager(HistoryOrder.this, 1);
        int layout = R.layout.history_item;

        historyAdapter = new HistoryAdapter(allTask, layout);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(historyAdapter);

        APIService apiService = RetrofitInstance.getClient().create(APIService.class);
        apiService.getHistoryOrder().enqueue(new Callback<ClassHistoryResponse>() {
            @Override
            public void onResponse(Call<ClassHistoryResponse> call, Response<ClassHistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ClassHistory> classOrders = response.body().getHistoryOrder();
                    List<ClassHistory> filteredOrders = new ArrayList<>();

                    if (classOrders != null) {
                        for (ClassHistory order : classOrders) {
                            if (order.getUsername().equals(loggedUser)) {
                                filteredOrders.add(order);
                            }
                        }
                    } else {
                        Toast.makeText(HistoryOrder.this, "No data available", Toast.LENGTH_SHORT).show();
                    }

//hghj//
                    allTask.clear();
                    allTask.addAll(filteredOrders);
                    historyAdapter.notifyDataSetChanged();

                } else {
                    Log.e("API Error", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<ClassHistoryResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HistoryOrder.this, MainActivity.class));
            }
        });
    }
}
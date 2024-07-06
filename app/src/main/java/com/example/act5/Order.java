package com.example.act5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tvNamaGame, tvNamaAkun, tvAlamatEmail;
        Button btnToOrder, btnBack;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String gameName = intent.getStringExtra("gameName");
        String accountName = intent.getStringExtra("accountName");
        String username = intent.getStringExtra("username");

        tvNamaGame = findViewById(R.id.etGameName);
        tvNamaAkun = findViewById(R.id.etNamaAkun);
        tvAlamatEmail = findViewById(R.id.etAlamatEmail);
        btnToOrder = findViewById(R.id.btnToOrder);
        btnBack = findViewById(R.id.btnBackOrder);

        tvNamaGame.setText(gameName);
        tvNamaGame.setEnabled(false);
        tvNamaAkun.setText(accountName);
        tvNamaAkun.setEnabled(false);
        tvAlamatEmail.setText(username);
        tvAlamatEmail.setEnabled(false);

        btnToOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createOrder(gameName, accountName, username);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createOrder(String gameName, String accountName, String alamatEmail) {
        ClassOrder order = new ClassOrder(gameName, accountName, alamatEmail, "Menunggu Konfirmasi");
        APIService apiService = RetrofitInstance.getClient().create(APIService.class);
        Call<ResponseBody> call = apiService.createOrder(order);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Order.this, "Order successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Order.this, "Order failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Order", "onFailure: " + t.getMessage());
                Toast.makeText(Order.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

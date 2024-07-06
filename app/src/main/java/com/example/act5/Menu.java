package com.example.act5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {
    TextView tvID, tvNamaGame, tvNamaGame2, tvNamaAkun, tvID2, tvNamaAkun2, tvHarga, tvHarga2, tvDeskripsi, tvDeskripsi2;
    Button btnOrder, btnOrder2, btnOrderHistory, btnLogout, btnOrderById;
    ImageView iv, iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String loggedUser = intent.getStringExtra("loggedUser");

        tvNamaGame = findViewById(R.id.tvNamaGame);
        tvNamaGame2 = findViewById(R.id.tvNamaGame2);
        tvNamaAkun = findViewById(R.id.tvNamaAkun);
        tvNamaAkun2 = findViewById(R.id.tvNamaAkun2);
        tvHarga = findViewById(R.id.tvHarga);
        tvHarga2 = findViewById(R.id.tvHarga2);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvDeskripsi2 = findViewById(R.id.tvDeskripsi2);
        tvID = findViewById(R.id.tvID);
        tvID2 = findViewById(R.id.tvID2);
        btnOrder = findViewById(R.id.btnOrder);
        btnOrder2 = findViewById(R.id.btnOrder2);
        btnOrderHistory = findViewById(R.id.btnOrderHistory);
        btnLogout = findViewById(R.id.btnLogout);
        btnOrderById = findViewById(R.id.btnToById);
        iv = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        APIService apiService = RetrofitInstance.getClient().create(APIService.class);
        apiService.getMenu().enqueue(new Callback<ClassMenuResponse>() {
            @Override
            public void onResponse(Call<ClassMenuResponse> call, Response<ClassMenuResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ClassMenu> classMenus = response.body().getMenu();
                    if (classMenus.size() > 0) {
                        if (classMenus.get(0).getGameName().equals("Mobile Legend")){
                            iv.setImageResource(R.drawable.ml_logo);
                        }else{
                            iv.setImageResource(R.drawable.valo_logo);
                        }
                        tvID.setText("Menu ID: " + classMenus.get(0).getId());
                        tvNamaGame.setText("Game Name: " + classMenus.get(0).getGameName());
                        tvNamaAkun.setText("Account Name: " + classMenus.get(0).getAccountName());
                        tvDeskripsi.setText("Description: " + classMenus.get(0).getDeskripsi());
                        tvHarga.setText("Price: " + classMenus.get(0).getPrice());
                    }
                    if (classMenus.size() > 1) {
                        if (classMenus.get(1).getGameName().equals("Mobile Legend")){
                            iv2.setImageResource(R.drawable.ml_logo);
                        }else{
                            iv2.setImageResource(R.drawable.valo_logo);
                        }
                        tvID2.setText("Menu ID: " + classMenus.get(1).getId());
                        tvNamaGame2.setText("Game Name: " + classMenus.get(1).getGameName());
                        tvNamaAkun2.setText("Account Name: " + classMenus.get(1).getAccountName());
                        tvDeskripsi2.setText("Description: " + classMenus.get(1).getDeskripsi());
                        tvHarga2.setText("Price: " + classMenus.get(1).getPrice());
                    }
                } else {
                    Log.e("API Error", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<ClassMenuResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Order.class);
                intent.putExtra("gameName", tvNamaGame.getText().toString().substring(11));
                intent.putExtra("accountName", tvNamaAkun.getText().toString().substring(14));
                intent.putExtra("username", loggedUser);
                startActivity(intent);
            }
        });

        btnOrder2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Order.class);
                intent.putExtra("gameName", tvNamaGame2.getText().toString().substring(11));
                intent.putExtra("accountName", tvNamaAkun2.getText().toString().substring(14));
                intent.putExtra("username", loggedUser);
                startActivity(intent);
            }
        });

        btnOrderHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, HistoryOrder.class);
                intent.putExtra("loggedUser", loggedUser);
                startActivity(intent);
            }
        });

        btnOrderById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuById.class);
                intent.putExtra("loggedUser", loggedUser);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,MainActivity.class));
            }
        });
    }
}

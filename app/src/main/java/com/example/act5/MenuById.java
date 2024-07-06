package com.example.act5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuById extends AppCompatActivity {
    TextView tvNamaGame, tvNamaAkun, tvDeskripsi, tvHarga;
    Button btnSearch, btnOrder, btnBack;
    EditText etSearch;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_by_id);

        Intent intent = getIntent();
        String loggedUser = intent.getStringExtra("loggedUser");

        tvNamaGame = findViewById(R.id.tvNamaGameById);
        tvNamaAkun = findViewById(R.id.tvNamaAkunById);
        tvDeskripsi = findViewById(R.id.tvDeskripsiById);
        tvHarga = findViewById(R.id.tvHargaById);
        btnOrder = findViewById(R.id.btnOrderMenuByid);
        btnSearch = findViewById(R.id.btnSearch);
        btnBack = findViewById(R.id.btnBackById);
        etSearch = findViewById(R.id.etByMenuId);
        iv = findViewById(R.id.imageViewById);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                APIService apiService = RetrofitInstance.getClient().create(APIService.class);
                apiService.getMenu().enqueue(new Callback<ClassMenuResponse>() {
                    @Override
                    public void onResponse(Call<ClassMenuResponse> call, Response<ClassMenuResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<ClassMenu> classMenus = response.body().getMenu();
                            List<ClassMenu> filteredOrders = new ArrayList<>();

                            if (classMenus != null) {
                                for (ClassMenu menu : classMenus) {
                                    if (menu.getId().equals(etSearch.getText().toString())) {
                                        filteredOrders.add(menu);
                                    }
                                }
                            } else {
                                Toast.makeText(MenuById.this, "Tidak Ada", Toast.LENGTH_SHORT).show();
                            }

                            if (filteredOrders.size() > 0) {
                                for (int i = 0; i < filteredOrders.size(); i++) {
                                    ClassMenu menu = filteredOrders.get(i);
                                    Log.d("Debug", "Order " + i + ": " + menu.toString());
                                    Log.d("Debug", String.valueOf(filteredOrders.get(i).getId()));
                                    Log.d("Debug", String.valueOf(filteredOrders.get(i).getGameName()));
                                    Log.d("Debug", String.valueOf(filteredOrders.get(i).getAccountName()));
                                }
                                if (filteredOrders.get(0).getGameName().equals("Mobile Legend")){
                                    iv.setImageResource(R.drawable.ml_logo);
                                }else{
                                    iv.setImageResource(R.drawable.valo_logo);
                                }
                                iv.setVisibility(View.VISIBLE);
                                tvNamaGame.setVisibility(View.VISIBLE);
                                tvNamaAkun.setVisibility(View.VISIBLE);
                                tvHarga.setVisibility(View.VISIBLE);
                                tvDeskripsi.setVisibility(View.VISIBLE);
                                btnOrder.setVisibility(View.VISIBLE);
                                tvNamaGame.setText("Game Name: " + filteredOrders.get(0).getGameName());
                                tvNamaAkun.setText("Account Name: " + filteredOrders.get(0).getAccountName());
                                tvHarga.setText("Price: " + filteredOrders.get(0).getPrice());
                                tvDeskripsi.setText("Description: " + filteredOrders.get(0).getDeskripsi());
                            }else{
                                iv.setVisibility(View.GONE);
                                tvNamaGame.setText("Menu Not Found!");
                                tvNamaAkun.setVisibility(View.GONE);
                                tvHarga.setVisibility(View.GONE);
                                tvDeskripsi.setVisibility(View.GONE);
                                btnOrder.setVisibility(View.GONE);
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
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MenuById.this, Order.class);
                intent.putExtra("gameName", tvNamaGame.getText().toString().substring(11));
                intent.putExtra("accountName", tvNamaAkun.getText().toString().substring(14));
                intent.putExtra("username", loggedUser);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }
}
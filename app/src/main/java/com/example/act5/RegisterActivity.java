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

import com.google.android.material.textfield.TextInputEditText;

import at.favre.lib.crypto.bcrypt.BCrypt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText etUsername, etPassword, etEmail, etPhoneNumber, etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Button btnToLogin = findViewById(R.id.btnToLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        etUsername = findViewById(R.id.etUsernameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etNomorhandphone);
        etName = findViewById(R.id.etNamapengguna);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone_number = etPhoneNumber.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || name.isEmpty() || phone_number.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, BCrypt.withDefaults().hashToString(12, password.toCharArray()), name, phone_number, email);
        APIService apiService = RetrofitInstance.getClient().create(APIService.class);
        Call<ResponseBody> call = apiService.registerUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed Username Already Taken", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("RegisterActivity", "onFailure: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

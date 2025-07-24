package com.atus05.tieuluan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvToRegister = findViewById(R.id.tvToRegister);
        CheckBox checkboxRemember = findViewById(R.id.checkboxRemember);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);

        // Kiểm tra có phải yêu cầu đăng nhập từ OrderInfoActivity không
        boolean requireLoginForOrder = getIntent().getBooleanExtra("require_login", false);

        // Nếu đã ghi nhớ đăng nhập thì tự động vào MainActivity hoặc OrderInfoActivity
        String savedUser = prefs.getString("username", null);
        if (savedUser != null) {
            Intent intent;
            if (requireLoginForOrder) {
                intent = new Intent(LoginActivity.this, OrderInfoActivity.class);
            } else {
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
            return;
        }

        // Nếu có email truyền sang từ RegisterActivity thì tự động điền vào ô đăng nhập
        String usernameFromRegister = getIntent().getStringExtra("username");
        if (usernameFromRegister != null) {
            edtUsername.setText(usernameFromRegister);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra hợp lệ email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    Toast.makeText(LoginActivity.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHelper.checkUserLogin(username, password)) {
                    // Lưu username và mật khẩu vào SharedPreferences khi đăng nhập thành công
                    prefs.edit().putString("username", username).putString("password", password).apply();
                    // Đồng bộ sang user_profile
                    SharedPreferences profilePrefs = getSharedPreferences("user_profile", MODE_PRIVATE);
                    profilePrefs.edit().putString("username", username).putString("password", password).apply();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Không còn code xử lý btnExitLogin
    }
} 
package com.atus05.tieuluan;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    // Khai báo biến thành viên cho các View cần dùng lại
    private EditText etName, etEmail, etPhone, etDiachi;
    private ImageView ivAvatar;
    private Button btnChangePassword, btnSaveProfile, btnLogout;

    private static final int REQUEST_LOGIN = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ánh xạ view cho form đăng nhập/đăng ký
        LinearLayout layoutLogin = findViewById(R.id.layoutLogin);
        LinearLayout layoutRegister = findViewById(R.id.layoutRegister);
        // LinearLayout layoutProfile = (LinearLayout) findViewById(android.R.id.content).findViewById(R.id.profileLayout); // Đã bỏ, không dùng

        EditText edtLoginUsername = findViewById(R.id.edtLoginUsername);
        EditText edtLoginPassword = findViewById(R.id.edtLoginPassword);
        Button btnLoginSubmit = findViewById(R.id.btnLoginSubmit);
        TextView tvToRegister = findViewById(R.id.tvToRegister);

        EditText edtRegisterUsername = findViewById(R.id.edtRegisterUsername);
        EditText edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        EditText edtRegisterConfirmPassword = findViewById(R.id.edtRegisterConfirmPassword);
        Button btnRegisterSubmit = findViewById(R.id.btnRegisterSubmit);
        TextView tvToLogin = findViewById(R.id.tvToLogin);

        // Ánh xạ các view thông tin cá nhân
        ivAvatar = findViewById(R.id.iv_avatar);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etDiachi = findViewById(R.id.etDiachi);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        btnLogout = findViewById(R.id.btn_logout);

        SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        String username = prefs.getString("username", null);
        boolean isLoggedIn = username != null && !username.isEmpty();

        // Hiển thị đúng layout theo trạng thái đăng nhập
        if (isLoggedIn) {
            layoutLogin.setVisibility(View.GONE);
            layoutRegister.setVisibility(View.GONE);
            ivAvatar.setVisibility(View.VISIBLE);
            etName.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.VISIBLE);
            etDiachi.setVisibility(View.VISIBLE);
            etPhone.setVisibility(View.VISIBLE);
            btnChangePassword.setVisibility(View.VISIBLE);
            btnSaveProfile.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            layoutLogin.setVisibility(View.VISIBLE);
            layoutRegister.setVisibility(View.GONE);
            ivAvatar.setVisibility(View.GONE);
            etName.setVisibility(View.GONE);
            etEmail.setVisibility(View.GONE);
            etDiachi.setVisibility(View.GONE);
            etPhone.setVisibility(View.GONE);
            btnChangePassword.setVisibility(View.GONE);
            btnSaveProfile.setVisibility(View.GONE);
            btnLogout.setVisibility(View.GONE);
        }

        // Chuyển sang form đăng ký
        tvToRegister.setOnClickListener(v -> {
            // Xóa thông tin đăng nhập cũ trước khi đăng ký tài khoản mới
            prefs.edit().remove("username").remove("password").apply();
            layoutLogin.setVisibility(View.GONE);
            layoutRegister.setVisibility(View.VISIBLE);
        });
        // Chuyển sang form đăng nhập
        tvToLogin.setOnClickListener(v -> {
            layoutLogin.setVisibility(View.VISIBLE);
            layoutRegister.setVisibility(View.GONE);
        });

        // Xử lý đăng nhập
        btnLoginSubmit.setOnClickListener(v -> {
            String loginUser = edtLoginUsername.getText().toString().trim();
            String loginPass = edtLoginPassword.getText().toString().trim();
            if (loginUser.isEmpty() || loginPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Kiểm tra hợp lệ email
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginUser).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            if (dbHelper.checkUserLogin(loginUser, loginPass)) {
                prefs.edit().putString("username", loginUser).putString("password", loginPass).apply();
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                layoutLogin.setVisibility(View.GONE);
                layoutRegister.setVisibility(View.GONE);
                // layoutProfile.setVisibility(View.VISIBLE);
                recreate(); // Reload lại để hiển thị thông tin cá nhân
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý đăng ký
        btnRegisterSubmit.setOnClickListener(v -> {
            String regUser = edtRegisterUsername.getText().toString().trim();
            String regPass = edtRegisterPassword.getText().toString().trim();
            String regConfirm = edtRegisterConfirmPassword.getText().toString().trim();
            if (regUser.isEmpty() || regPass.isEmpty() || regConfirm.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(regUser).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!regPass.equals(regConfirm)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            if (dbHelper.checkUserExists(regUser)) {
                Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean success = dbHelper.addUser(regUser, regPass);
            if (success) {
                // KHÔNG lưu vào prefs khi đăng ký thành công
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        etName.setText(prefs.getString("name", ""));
        etEmail.setText(prefs.getString("email", ""));
        etPhone.setText(prefs.getString("phone", ""));
        etDiachi.setText(prefs.getString("diachi", ""));

        btnSaveProfile.setOnClickListener(v -> {
            prefs.edit()
                .putString("name", etName.getText().toString().trim())
                .putString("email", etEmail.getText().toString().trim())
                .putString("phone", etPhone.getText().toString().trim())
                .putString("diachi", etDiachi.getText().toString().trim())
                .apply();
            Toast.makeText(this, "Đã lưu thông tin!", Toast.LENGTH_SHORT).show();
        });

        btnChangePassword.setOnClickListener(v -> showChangePasswordDialog());

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("username");
            editor.remove("password");
            editor.apply();
            Toast.makeText(this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileActivity.this, WelcomeActivity.class));
            finish();
        });

        // Không tự động chuyển sang LoginActivity nữa, chỉ hiển thị form login/register trong ProfileActivity
    }

    // Không cần onActivityResult nữa

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        etName.setText(prefs.getString("name", ""));
        etEmail.setText(prefs.getString("email", ""));
        etPhone.setText(prefs.getString("phone", ""));
        etDiachi.setText(prefs.getString("diachi", ""));
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đổi mật khẩu");
        final EditText etCurrent = new EditText(this);
        etCurrent.setHint("Mật khẩu hiện tại");
        final EditText etNew = new EditText(this);
        etNew.setHint("Mật khẩu mới");
        final EditText etConfirm = new EditText(this);
        etConfirm.setHint("Nhập lại mật khẩu mới");
        etCurrent.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etNew.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etConfirm.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.addView(etCurrent);
        layout.addView(etNew);
        layout.addView(etConfirm);
        builder.setView(layout);
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
            String current = etCurrent.getText().toString();
            String newPass = etNew.getText().toString();
            String confirm = etConfirm.getText().toString();
            String savedPass = prefs.getString("password", "123456"); // mặc định 123456 nếu chưa có
            if (!current.equals(savedPass)) {
                Toast.makeText(this, "Mật khẩu hiện tại không đúng!", Toast.LENGTH_SHORT).show();
            } else if (newPass.length() < 4) {
                Toast.makeText(this, "Mật khẩu mới quá ngắn!", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirm)) {
                Toast.makeText(this, "Nhập lại mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            } else {
                prefs.edit().putString("password", newPass).apply();
                Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
} 
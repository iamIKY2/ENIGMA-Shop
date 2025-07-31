package com.atus05.tieuluan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class OrderInfoActivity extends AppCompatActivity {
    private EditText etName, etPhone, etEmail, etAddress, etNote;
    private RadioGroup rgPaymentMethod;
    private String selectedPaymentMethod = "Tiền mặt khi nhận hàng";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        // Kiểm tra đăng nhập
        SharedPreferences userPrefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        String username = userPrefs.getString("username", null);
        if (username == null || username.isEmpty()) {
            // Chưa đăng nhập, chuyển sang LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("require_login", true);
            startActivity(intent);
            finish();
            return;
        }

        Product product = (Product) getIntent().getSerializableExtra("product");
        TextView tvProduct = findViewById(R.id.tvProductName);
        if (product != null) {
            tvProduct.setText("Sản phẩm: " + product.getName());
        }

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etNote = findViewById(R.id.etNote);
        Button btnConfirm = findViewById(R.id.btnConfirmOrder);

        // Đảm bảo luôn lấy thông tin mới nhất từ SharedPreferences khi vào màn hình
        etName.setText(userPrefs.getString("name", ""));
        etEmail.setText(userPrefs.getString("email", ""));
        etPhone.setText(userPrefs.getString("phone", ""));
        etAddress.setText(userPrefs.getString("diachi", ""));

        rgPaymentMethod = findViewById(R.id.rgPaymentMethod);
        rgPaymentMethod.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) selectedPaymentMethod = rb.getText().toString();
        });

        // Kiểm tra nếu là thanh toán từ giỏ hàng
        boolean isCartCheckout = getIntent().getBooleanExtra("isCartCheckout", false);

        btnConfirm.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String note = etNote.getText().toString().trim();
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Lưu lại thông tin người dùng vào user_profile
            userPrefs.edit()
                .putString("name", name)
                .putString("email", email)
                .putString("phone", phone)
                .putString("diachi", address)
                .apply();
            // Lưu đơn hàng vào SharedPreferences (thêm phương thức thanh toán)
            String productName;
            int productImageResId;
            if (isCartCheckout) {
                productName = getIntent().getStringExtra("productNames");
                if (productName == null) productName = "";
                // Lấy ảnh sản phẩm đầu tiên trong giỏ hàng (nếu có)
                List<Product> cartItems = CartManager.getCartItems(this);
                productImageResId = (cartItems.size() > 0) ? cartItems.get(0).getImageResId() : -1;
            } else {
                productName = product != null ? product.getName() : "";
                productImageResId = product != null ? product.getImageResId() : -1;
            }
            Order order = new Order(name, phone, email, address, note + " | PT: " + selectedPaymentMethod, productName, productImageResId, System.currentTimeMillis());
            SharedPreferences prefs = getSharedPreferences("order_prefs", MODE_PRIVATE);
            com.google.gson.Gson gson = new com.google.gson.Gson();
            String json = prefs.getString("orders", null);
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<java.util.List<Order>>(){}.getType();
            java.util.List<Order> orderList = json != null ? gson.fromJson(json, type) : new java.util.ArrayList<>();
            orderList.add(order);
            prefs.edit().putString("orders", gson.toJson(orderList)).apply();
            if (isCartCheckout) {
                CartManager.clearCart(this);
                // Đóng CartActivity nếu đang mở (để cập nhật lại giao diện)
                try {
                    android.app.Activity cart = CartManager.getCartActivityInstance();
                    if (cart != null) cart.finish();
                } catch (Exception ignored) {}
            }
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences profilePrefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        etName.setText(profilePrefs.getString("name", ""));
        etEmail.setText(profilePrefs.getString("email", ""));
        etPhone.setText(profilePrefs.getString("phone", ""));
        etAddress.setText(profilePrefs.getString("diachi", ""));
    }
} 
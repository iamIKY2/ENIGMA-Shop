package com.atus05.tieuluan;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        CartManager.setCartActivityInstance(this);

        RecyclerView rvCart = findViewById(R.id.rv_cart);
        TextView tvTotal = findViewById(R.id.tv_total);
        Button btnCheckout = findViewById(R.id.btn_checkout);
        rvCart.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        // Lấy sản phẩm trong giỏ
        List<Product> cartItems = CartManager.getCartItems(this);
        Log.d("CartActivity", "Số sản phẩm trong giỏ: " + cartItems.size());
        for (Product p : cartItems) {
            Log.d("CartActivity", "Sản phẩm trong giỏ: " + p.getName());
        }
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Giỏ hàng của bạn đang trống!", Toast.LENGTH_SHORT).show();
        }
        CartAdapter cartAdapter = new CartAdapter(this, cartItems, () -> {
            int total = CartManager.getTotalPrice(this);
            tvTotal.setText("Tổng: " + total + "đ");
        });
        rvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        // Hiển thị tổng tiền
        int totalPrice = CartManager.getTotalPrice(this);
        tvTotal.setText("Tổng: " + totalPrice + "đ");

        btnCheckout.setOnClickListener(v -> {
            List<Product> checkoutItems = CartManager.getCartItems(this);
            if (checkoutItems.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Tạo chuỗi tên sản phẩm
            StringBuilder productNames = new StringBuilder();
            for (Product p : checkoutItems) {
                if (productNames.length() > 0) productNames.append(", ");
                productNames.append(p.getName());
            }
            Intent intent = new Intent(this, OrderInfoActivity.class);
            intent.putExtra("isCartCheckout", true);
            intent.putExtra("productNames", productNames.toString());
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CartManager.clearCartActivityInstance();
    }
}

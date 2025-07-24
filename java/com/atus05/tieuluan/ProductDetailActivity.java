package com.atus05.tieuluan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertDialog;
import android.content.SharedPreferences;

public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Nhận Product từ Intent
        Product product = getProductFromIntent();
        if (product == null) {
            finish();
            return;
        }

        // Ánh xạ và thiết lập view
        setupViews(product);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart.setOnClickListener(v -> {
            if (!isLoggedIn()) {
                startActivity(new Intent(this, WelcomeActivity.class));
                return;
            }
            CartManager.addToCart(this, product);
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
        btnBuyNow.setOnClickListener(v -> {
            if (!isLoggedIn()) {
                startActivity(new Intent(this, WelcomeActivity.class));
                return;
            }
            if (product == null) {
                Toast.makeText(this, "Không có thông tin sản phẩm!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, OrderInfoActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
    }

    private Product getProductFromIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            showError("Lỗi: Không nhận được dữ liệu sản phẩm");
            return null;
        }

        Product product = (Product) intent.getSerializableExtra("product");
        if (product == null) {
            showError("Lỗi: Không nhận được sản phẩm");
            return null;
        }
        return product;
    }

    private void setupViews(Product product) {
        // Ánh xạ các view cơ bản
        TextView tvName = findViewById(R.id.tv_device_name);
        TextView tvHighlight = findViewById(R.id.tv_highlight_features);
        TextView tvReviews = findViewById(R.id.tv_reviews);
        TextView tvPrice = findViewById(R.id.tv_price);
        RecyclerView rvSpecs = findViewById(R.id.rv_specifications);

        // Hiển thị thông tin cơ bản
        tvName.setText(product.getName());
        tvHighlight.setText(product.getHighlight());
        tvReviews.setText(String.format(Locale.getDefault(), "%.1f ⭐ (%d đánh giá)",
                product.getRating(), product.getSold()));
        tvPrice.setText(product.getPrice());

        // Thiết lập RecyclerView cho thông số kỹ thuật
        setupSpecifications(rvSpecs, product);
    }

    private void setupSpecifications(RecyclerView rvSpecs, Product product) {
        // Tạo danh sách thông số
        List<SpecItem> specs = product.getSpecifications();

        // Nếu không có thông số, thêm thông báo
        if (specs == null || specs.isEmpty()) {
            specs = new ArrayList<>();
            specs.add(new SpecItem("Thông báo", "Không có thông số kỹ thuật"));
        }

        // Thiết lập RecyclerView
        rvSpecs.setLayoutManager(new LinearLayoutManager(this));
        SpecAdapter specAdapter = new SpecAdapter(specs);
        rvSpecs.setAdapter(specAdapter);
    }

    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        String username = prefs.getString("username", null);
        return username != null && !username.isEmpty();
    }

    private void showLoginRequiredDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Yêu cầu đăng nhập")
            .setMessage("Bạn cần đăng nhập để sử dụng chức năng này. Đăng nhập/Đăng ký ngay?")
            .setPositiveButton("Đăng nhập/Đăng ký", (dialog, which) -> {
                startActivity(new Intent(this, WelcomeActivity.class));
            })
            .setNegativeButton("Hủy", null)
            .show();
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }
}
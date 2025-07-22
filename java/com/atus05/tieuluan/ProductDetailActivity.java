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

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }
}
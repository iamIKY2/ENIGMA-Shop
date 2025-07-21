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

public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_product_detail);

            // Lấy dữ liệu sản phẩm từ Intent
            Intent intent = getIntent();
            if (intent == null) {
                Log.e(TAG, "Intent is null");
                Toast.makeText(this, "Lỗi: Không nhận được dữ liệu sản phẩm", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            String name = intent.getStringExtra("name");
            String highlight = intent.getStringExtra("highlight");
            String specs = intent.getStringExtra("specs");
            String reviews = intent.getStringExtra("reviews");
            String price = intent.getStringExtra("price");

            // Log dữ liệu nhận được
            Log.d(TAG, "Received data - Name: " + name + ", Highlight: " + highlight + ", Specs: " + specs + ", Reviews: " + reviews + ", Price: " + price);

            // Ánh xạ view
            TextView tvName = findViewById(R.id.tv_device_name);
            TextView tvHighlight = findViewById(R.id.tv_highlight_features);
            TextView tvSpecs = findViewById(R.id.tv_specifications);
            TextView tvReviews = findViewById(R.id.tv_reviews);
            TextView tvPrice = findViewById(R.id.tv_price);
            Spinner spinnerColor = findViewById(R.id.spinner_color);
            Spinner spinnerStorage = findViewById(R.id.spinner_storage);
            Button btnAddToCart = findViewById(R.id.btn_add_to_cart);

            // Kiểm tra null và gán dữ liệu lên view
            if (tvName != null) {
                tvName.setText(name != null ? name : "Không có tên sản phẩm");
            } else {
                Log.e(TAG, "tv_device_name is null");
            }

            if (tvHighlight != null) {
                tvHighlight.setText(highlight != null ? highlight : "Không có thông tin nổi bật");
            } else {
                Log.e(TAG, "tv_highlight_features is null");
            }

            if (tvSpecs != null) {
                tvSpecs.setText(specs != null ? specs : "Không có thông số kỹ thuật");
            } else {
                Log.e(TAG, "tv_specifications is null");
            }

            if (tvReviews != null) {
                tvReviews.setText(reviews != null ? reviews : "Không có đánh giá");
            } else {
                Log.e(TAG, "tv_reviews is null");
            }

            if (tvPrice != null) {
                tvPrice.setText(price != null ? price : "Không có giá");
            } else {
                Log.e(TAG, "tv_price is null");
            }

            // Kiểm tra spinner và button
            if (spinnerColor == null) {
                Log.e(TAG, "spinner_color is null");
            }
            if (spinnerStorage == null) {
                Log.e(TAG, "spinner_storage is null");
            }
            if (btnAddToCart == null) {
                Log.e(TAG, "btn_add_to_cart is null");
            }

            // TODO: Thiết lập dữ liệu cho spinnerColor, spinnerStorage nếu có
            // TODO: Xử lý sự kiện thêm vào giỏ hàng
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Đã xảy ra lỗi khi tải chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
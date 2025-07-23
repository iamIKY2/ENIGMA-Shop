package com.example.tieuluan;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_product_detail);

            // Nhận Product từ Intent
            Intent intent = getIntent();
            if (intent == null) {
                Log.e(TAG, "Intent is null");
                Toast.makeText(this, "Lỗi: Không nhận được dữ liệu sản phẩm", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            Product product = (Product) intent.getSerializableExtra("product");
            if (product == null) {
                Log.e(TAG, "Product is null");
                Toast.makeText(this, "Lỗi: Không nhận được sản phẩm", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Ánh xạ view
            TextView tvName = findViewById(R.id.tv_device_name);
            TextView tvHighlight = findViewById(R.id.tv_highlight_features);
            TextView tvShortConfig = findViewById(R.id.tv_short_config);
            TextView tvPrice = findViewById(R.id.tv_price);
            Button btnAddToCart = findViewById(R.id.btn_add_to_cart);
            RecyclerView rvSpecs = findViewById(R.id.rv_specifications);
            rvSpecs.setLayoutManager(new LinearLayoutManager(this)); // Thêm dòng này

            // Gán dữ liệu lên view
            if (tvName != null) {
                tvName.setText(product.getName() != null ? product.getName() : "Không có tên sản phẩm");
            }
            if (tvHighlight != null) {
                tvHighlight.setText(product.getHighlight() != null ? product.getHighlight() : "Không có thông tin nổi bật");
            }
            if (tvShortConfig != null) {
                tvShortConfig.setText(product.getDetail() != null ? product.getDetail() : "");
            }
            if (tvPrice != null) {
                tvPrice.setText(product.getPrice() != null ? product.getPrice() : "Không có giá");
            }

            // Hiển thị thông số kỹ thuật
            if (rvSpecs != null && product.getSpecifications() != null) {
                SpecAdapter specAdapter = new SpecAdapter(product.getSpecifications());
                rvSpecs.setAdapter(specAdapter);
            }

            // TODO: Xử lý sự kiện thêm vào giỏ hàng nếu cần
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Đã xảy ra lỗi khi tải chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}

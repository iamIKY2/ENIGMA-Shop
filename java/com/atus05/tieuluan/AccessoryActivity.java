package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class AccessoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFeatured, recyclerViewAllProducts;
    private FeaturedProductAdapter featuredAdapter;
    private ProductAdapter allProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory);

        // Ánh xạ các RecyclerView
        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        // Thiết lập layout cho từng RecyclerView
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllProducts.setLayoutManager(new GridLayoutManager(this, 2));


        // Lấy sản phẩm phụ kiện từ database (categoryId = 8)
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> allProducts = dbHelper.getProductsByCategory(8);
        // Lọc sản phẩm theo hãng (nếu cần)
        List<Product> filteredProducts = new ArrayList<>(allProducts);
        // Random 3 sản phẩm nổi bật từ filteredProducts
        List<Product> featuredProducts = new ArrayList<>();
        if (filteredProducts.size() > 0) {
            java.util.Collections.shuffle(filteredProducts);
            int featuredCount = Math.min(3, filteredProducts.size());
            for (int i = 0; i < featuredCount; i++) {
                featuredProducts.add(filteredProducts.get(i));
            }
        }
        // Adapter cho sản phẩm nổi bật
        featuredAdapter = new FeaturedProductAdapter(featuredProducts);
        recyclerViewFeatured.setAdapter(featuredAdapter);
        // Adapter cho tất cả sản phẩm phụ kiện
        allProductAdapter = new ProductAdapter(filteredProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);
    }
} 
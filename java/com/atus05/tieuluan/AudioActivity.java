package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class AudioActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBrands, recyclerViewFeatured, recyclerViewAllProducts;
    private SimpleStringAdapter brandAdapter;
    private FeaturedProductAdapter featuredAdapter;
    private ProductAdapter allProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        // Ánh xạ các RecyclerView
        recyclerViewBrands = findViewById(R.id.recyclerViewBrands);
        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        // Thiết lập layout cho từng RecyclerView
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllProducts.setLayoutManager(new GridLayoutManager(this, 2));

        // Dữ liệu hãng âm thanh
        List<String> brands = new ArrayList<>();
        brands.add("Tất cả");
        brands.add("Sony");
        brands.add("Apple");
        brands.add("Samsung");
        brands.add("JBL");
        brands.add("Edifier");
        brandAdapter = new SimpleStringAdapter(brands);
        recyclerViewBrands.setAdapter(brandAdapter);

        // Lấy sản phẩm âm thanh từ database (categoryId = 7)
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> allProducts = dbHelper.getProductsByCategory(7);
        // Lọc sản phẩm theo hãng
        List<Product> filteredProducts = new ArrayList<>();
        for (Product p : allProducts) {
            String name = p.getName().toLowerCase();
            if (name.contains("sony") || name.contains("apple") || name.contains("samsung") || name.contains("jbl") || name.contains("edifier")) {
                filteredProducts.add(p);
            }
        }
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
        // Adapter cho tất cả sản phẩm âm thanh
        allProductAdapter = new ProductAdapter(filteredProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);

        // Thêm sự kiện click cho adapter hãng âm thanh
        brandAdapter.setOnItemClickListener(new SimpleStringAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String brand) {
                // Lọc sản phẩm theo hãng được chọn
                List<Product> filtered = new ArrayList<>();
                for (Product p : allProducts) {
                    if (brand.equalsIgnoreCase("Tất cả") || p.getName().toLowerCase().contains(brand.toLowerCase())) {
                        filtered.add(p);
                    }
                }
                // Cập nhật adapter danh sách sản phẩm
                allProductAdapter = new ProductAdapter(filtered);
                recyclerViewAllProducts.setAdapter(allProductAdapter);
                // Lọc lại sản phẩm nổi bật theo hãng
                List<Product> featuredFiltered = new ArrayList<>();
                if (filtered.size() > 0) {
                    java.util.Collections.shuffle(filtered);
                    int featuredCount = Math.min(3, filtered.size());
                    for (int i = 0; i < featuredCount; i++) {
                        featuredFiltered.add(filtered.get(i));
                    }
                }
                featuredAdapter = new FeaturedProductAdapter(featuredFiltered);
                recyclerViewFeatured.setAdapter(featuredAdapter);
            }
        });
    }
} 
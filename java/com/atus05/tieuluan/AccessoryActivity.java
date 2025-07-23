package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class AccessoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBrands, recyclerViewNeeds, recyclerViewFeatured, recyclerViewAllProducts;
    private SimpleStringAdapter brandAdapter, needAdapter;
    private FeaturedProductAdapter featuredAdapter;
    private ProductAdapter allProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory);

        // Ánh xạ các RecyclerView
        recyclerViewBrands = findViewById(R.id.recyclerViewBrands);
        recyclerViewNeeds = findViewById(R.id.recyclerViewNeeds);
        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        // Thiết lập layout cho từng RecyclerView
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNeeds.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllProducts.setLayoutManager(new GridLayoutManager(this, 2));

        // Dữ liệu hãng phụ kiện
        List<String> brands = new ArrayList<>();
        brands.add("Tất cả");
        brands.add("Anker");
        brands.add("Apple");
        brands.add("Samsung");
        brands.add("Xiaomi");
        brands.add("Baseus");
        brandAdapter = new SimpleStringAdapter(brands, true);
        recyclerViewBrands.setAdapter(brandAdapter);

        // Dữ liệu nhu cầu phụ kiện
        List<String> needs = new ArrayList<>();
        needs.add("Sạc dự phòng");
        needs.add("Cáp sạc");
        needs.add("Ốp lưng");
        needs.add("Bảo vệ màn hình");
        needAdapter = new SimpleStringAdapter(needs);
        recyclerViewNeeds.setAdapter(needAdapter);

        // Lấy sản phẩm phụ kiện từ database (categoryId = 8)
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> allProducts = dbHelper.getProductsByCategory(8);
        // Lọc sản phẩm theo hãng
        List<Product> filteredProducts = new ArrayList<>();
        for (Product p : allProducts) {
            String name = p.getName().toLowerCase();
            if (name.contains("anker") || name.contains("apple") || name.contains("samsung") || name.contains("xiaomi") || name.contains("baseus")) {
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
        // Adapter cho tất cả sản phẩm phụ kiện
        allProductAdapter = new ProductAdapter(filteredProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);

        // Thêm sự kiện click cho adapter hãng phụ kiện
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
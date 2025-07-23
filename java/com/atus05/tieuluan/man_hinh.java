package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

// Activity hiển thị trang màn hình với hãng, nhu cầu và sản phẩm nổi bật
public class man_hinh extends AppCompatActivity {
    private RecyclerView recyclerViewBrands, recyclerViewNeeds, recyclerViewFeatured, recyclerViewAllProducts;
    private SimpleStringAdapter brandAdapter, needAdapter;
    private FeaturedProductLaptopAdapter featuredAdapter;
    private ProductLaptopAdapter allProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh);

        // Ánh xạ các RecyclerView
        recyclerViewBrands = findViewById(R.id.recyclerViewBrands);
        recyclerViewNeeds = findViewById(R.id.recyclerViewNeeds);
        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        // Thiết lập layout cho từng RecyclerView
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNeeds.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllProducts.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cột, chia trái phải

        // Dữ liệu hãng màn hình
        List<String> brands = new ArrayList<>();
        brands.add("Tất cả"); // Thêm mục Tất cả để xem toàn bộ sản phẩm
        brands.add("Asus");
        brands.add("LG");
        brands.add("E-Dra");
        brands.add("ViewSonic");
        brandAdapter = new SimpleStringAdapter(brands, true); // true: dùng layout có viền cho hãng
        recyclerViewBrands.setAdapter(brandAdapter);

        // Dữ liệu nhu cầu màn hình (bạn có thể chỉnh sửa lại cho phù hợp)
        List<String> needs = new ArrayList<>();
        needs.add("Văn phòng");
        needs.add("Gaming");
        needs.add("Đồ họa");
        needs.add("Giải trí");
        needAdapter = new SimpleStringAdapter(needs);
        recyclerViewNeeds.setAdapter(needAdapter);

        // Lấy sản phẩm màn hình từ database (categoryId = 5)
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> allProducts = dbHelper.getProductsByCategory(5);
        // Lọc sản phẩm theo hãng
        List<Product> filteredProducts = new ArrayList<>();
        for (Product p : allProducts) {
            String name = p.getName().toLowerCase();
            if (name.contains("asus") || name.contains("lg") || name.contains("e-dra") || name.contains("viewsonic")) {
                filteredProducts.add(p);
            }
        }
        // Random 3 sản phẩm nổi bật từ filteredProducts
        List<Product> featuredProducts = new ArrayList<>();
        if (filteredProducts.size() > 0) {
            java.util.Collections.shuffle(filteredProducts);
            int featuredCount = Math.min(4, filteredProducts.size());
            for (int i = 0; i < featuredCount; i++) {
                featuredProducts.add(filteredProducts.get(i));
            }
        }
        // Adapter cho sản phẩm nổi bật
        featuredAdapter = new FeaturedProductLaptopAdapter(this, featuredProducts);
        recyclerViewFeatured.setAdapter(featuredAdapter);
        // Adapter cho tất cả sản phẩm màn hình
        allProductAdapter = new ProductLaptopAdapter(this, filteredProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);

        // Thêm sự kiện click cho adapter hãng màn hình
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
                allProductAdapter = new ProductLaptopAdapter(man_hinh.this, filtered);
                recyclerViewAllProducts.setAdapter(allProductAdapter);
                // Lọc lại sản phẩm nổi bật theo hãng
                List<Product> featuredFiltered = new ArrayList<>();
                for (Product p : filtered) {
                    if (p.getRating() >= 4.7f) {
                        featuredFiltered.add(p);
                    }
                }
                featuredAdapter = new FeaturedProductLaptopAdapter(man_hinh.this, featuredFiltered);
                recyclerViewFeatured.setAdapter(featuredAdapter);
            }
        });
    }
} 
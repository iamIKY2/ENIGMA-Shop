package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import java.util.Arrays;

// Activity hiển thị trang laptop với hãng, nhu cầu và sản phẩm nổi bật
public class LaptopActivity extends AppCompatActivity {
    private RecyclerView recyclerViewBrands, recyclerViewNeeds, recyclerViewFeatured, recyclerViewAllProducts;
    private SimpleStringAdapter brandAdapter, needAdapter;
    private FeaturedProductLaptopAdapter featuredAdapter;
    private ProductLaptopAdapter allProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

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

        // Dữ liệu mẫu cho hãng laptop
        List<String> brands = new ArrayList<>();
        brands.add("Macbook");
        brands.add("Asus");
        brands.add("Lenovo");
        brands.add("Dell");
        brands.add("HP");
        brands.add("MSI");
        brands.add("Acer");
        brandAdapter = new SimpleStringAdapter(brands, true); // true: dùng layout có viền cho hãng
        recyclerViewBrands.setAdapter(brandAdapter);

        // Thêm sự kiện click cho adapter hãng laptop
        brandAdapter.setOnItemClickListener(new SimpleStringAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String brand) {
                // Nếu hãng là Macbook thì chuyển sang MacbookActivity
                if (brand.equalsIgnoreCase("Macbook")) {
                    Intent intent = new Intent(LaptopActivity.this, MacbookActivity.class);
                    startActivity(intent);
                }
                // ... có thể xử lý các hãng khác nếu cần
            }
        });

        // Dữ liệu mẫu cho nhu cầu laptop
        List<String> needs = new ArrayList<>();
        needs.add("Văn phòng");
        needs.add("Gaming");
        needs.add("Mỏng nhẹ");
        needs.add("Cảm ứng");
        needAdapter = new SimpleStringAdapter(needs);
        recyclerViewNeeds.setAdapter(needAdapter);

        // Khởi tạo DatabaseHelper để lấy dữ liệu từ database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        // Lấy tất cả sản phẩm laptop (giả sử id danh mục Laptop là 3)
        List<Product> allProducts = dbHelper.getProductsByCategory(3);
        // Lọc sản phẩm nổi bật (rating >= 4.7)
        List<Product> featuredProducts = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getRating() >= 4.7f) {
                featuredProducts.add(p);
            }
        }
        // Sử dụng FeaturedProductLaptopAdapter cho sản phẩm nổi bật
        featuredAdapter = new FeaturedProductLaptopAdapter(this, featuredProducts);
        recyclerViewFeatured.setAdapter(featuredAdapter);
        // Sử dụng ProductLaptopAdapter cho danh sách sản phẩm laptop
        allProductAdapter = new ProductLaptopAdapter(this, allProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);
    }
} 
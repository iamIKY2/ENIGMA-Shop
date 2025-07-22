package com.atus05.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Activity hiển thị danh sách các sản phẩm Macbook
public class MacbookActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFeatured, recyclerViewAllProducts;
    private FeaturedProductLaptopAdapter featuredAdapter;
    private ProductLaptopAdapter allProductsAdapter;
    private List<Product> featuredProducts;
    private List<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_macbook);

        // Ánh xạ các RecyclerView
        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        // Lấy dữ liệu Macbook từ database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Product> allProducts = dbHelper.getProductsByCategory(3); // 3 = Laptop
        // Lọc chỉ lấy sản phẩm Macbook
        List<Product> macbooks = new ArrayList<>();
        List<Product> featuredProducts = new ArrayList<>();
        for (Product p : allProducts) {
            if ((p.getName() != null && p.getName().toLowerCase().contains("macbook")) ||
                (p.getHighlight() != null && p.getHighlight().toLowerCase().contains("macbook"))) {
                macbooks.add(p);
                if (p.getRating() >= 4.7f) {
                    featuredProducts.add(p);
                }
            }
        }

        // Thiết lập adapter cho danh sách nổi bật
        featuredAdapter = new FeaturedProductLaptopAdapter(this, featuredProducts);
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFeatured.setAdapter(featuredAdapter);

        // Thiết lập adapter cho tất cả sản phẩm
        allProductsAdapter = new ProductLaptopAdapter(this, macbooks);
        recyclerViewAllProducts.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cột
        recyclerViewAllProducts.setAdapter(allProductsAdapter);
    }
} 
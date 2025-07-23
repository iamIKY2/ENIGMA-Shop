package com.example.tieuluan;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

        // Khởi tạo dữ liệu mẫu cho Macbook nổi bật
        featuredProducts = new ArrayList<>();
        featuredProducts.add(new Product(
                "Macbook Pro M4",
                "45.990.000₫",
                "49.990.000₫",
                4.9f,
                120,
                R.drawable.macbook_pro_m4,
                "Chip Apple M4, 8GB RAM, SSD 512GB",
                "Màn hình: 14\" Liquid Retina, RAM: 8GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M4 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "14 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "52,6 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "3024 x 1964 pixels"),
                        new SpecItem("Loại CPU", "Apple M4"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        featuredProducts.add(new Product(
                "Macbook Air M2",
                "28.990.000₫",
                "32.990.000₫",
                4.8f,
                90,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M2, 8GB RAM, SSD 256GB",
                "Màn hình: 13.6\" Liquid Retina, RAM: 8GB, SSD: 256GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M2 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "256GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.6 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "53,7 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "2560 x 1600 pixels"),
                        new SpecItem("Loại CPU", "Apple M2"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        featuredProducts.add(new Product(
                "Macbook Pro M3",
                "39.990.000₫",
                "43.990.000₫",
                4.7f,
                80,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M3, 16GB RAM, SSD 512GB",
                "Màn hình: 14\" Liquid Retina, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M3 GPU"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "14 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "54,3 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "3024 x 1964 pixels"),
                        new SpecItem("Loại CPU", "Apple M3"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        // Thêm các sản phẩm nổi bật khác nếu cần

        // Khởi tạo dữ liệu mẫu cho tất cả Macbook
        allProducts = new ArrayList<>();
        allProducts.add(new Product(
                "Macbook Pro M4",
                "45.990.000₫",
                "49.990.000₫",
                4.9f,
                120,
                R.drawable.macbook_pro_m4,
                "Chip Apple M4, 8GB RAM, SSD 512GB",
                "Màn hình: 14\" Liquid Retina, RAM: 8GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M4 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "14 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "52,6 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "3024 x 1964 pixels"),
                        new SpecItem("Loại CPU", "Apple M4"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        allProducts.add(new Product(
                "Macbook Air M2",
                "28.990.000₫",
                "32.990.000₫",
                4.8f,
                90,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M2, 8GB RAM, SSD 256GB",
                "Màn hình: 13.6\" Liquid Retina, RAM: 8GB, SSD: 256GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M2 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "256GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.6 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "53,7 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "2560 x 1600 pixels"),
                        new SpecItem("Loại CPU", "Apple M2"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        allProducts.add(new Product(
                "Macbook Pro M3",
                "39.990.000₫",
                "43.990.000₫",
                4.7f,
                80,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M3, 16GB RAM, SSD 512GB",
                "Màn hình: 14\" Liquid Retina, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M3 GPU"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "14 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "54,3 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "3024 x 1964 pixels"),
                        new SpecItem("Loại CPU", "Apple M3"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        allProducts.add(new Product(
                "Macbook Air M1",
                "22.990.000₫",
                "26.990.000₫",
                4.6f,
                110,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M1, 8GB RAM, SSD 256GB",
                "Màn hình: 13.3\" Retina, RAM: 8GB, SSD: 256GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M1 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "256GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.3 inches"),
                        new SpecItem("Công nghệ màn hình", "Retina"),
                        new SpecItem("Pin", "54 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "2560 x 1600 pixels"),
                        new SpecItem("Loại CPU", "Apple M1"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        allProducts.add(new Product(
                "Macbook Pro 2022",
                "35.990.000₫",
                "39.990.000₫",
                4.5f,
                70,
                R.drawable.macbook_pro_m4, // Thay hình nếu có
                "Chip Apple M2 Pro, 16GB RAM, SSD 512GB",
                "Màn hình: 16\" Liquid Retina, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M2 Pro GPU"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "16 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "90 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "3840 x 2400 pixels"),
                        new SpecItem("Loại CPU", "Apple M2 Pro"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        // Thêm các sản phẩm Macbook khác nếu cần

        // Thiết lập adapter cho danh sách nổi bật
        featuredAdapter = new FeaturedProductLaptopAdapter(this, featuredProducts);
        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFeatured.setAdapter(featuredAdapter);

        // Thiết lập adapter cho tất cả sản phẩm
        allProductsAdapter = new ProductLaptopAdapter(this, allProducts);
        recyclerViewAllProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAllProducts.setAdapter(allProductsAdapter);
    }
}

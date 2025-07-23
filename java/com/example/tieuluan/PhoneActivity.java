package com.example.tieuluan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PhoneActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFeatured, recyclerViewAllProducts;
    private FeaturedProductAdapter featuredAdapter;
    private ProductAdapter allProductAdapter;
    private List<Product> featuredProducts;
    private List<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smatphone);

        recyclerViewFeatured = findViewById(R.id.recyclerViewFeatured);
        recyclerViewAllProducts = findViewById(R.id.recyclerViewAllProducts);

        recyclerViewFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAllProducts.setLayoutManager(new LinearLayoutManager(this));

        // Dữ liệu mẫu cho sản phẩm nổi bật
        featuredProducts = new ArrayList<>();
        featuredProducts.add(new Product(
                "iPhone 16 Pro",
                "30.000.000đ",
                "33.000.000đ",
                4.9f,
                200,
                R.drawable.iphone_16_pr,
                "Chip A18, 8GB RAM, Camera 48MP",
                "Màn hình: 6.7\" OLED, RAM: 8GB, Bộ nhớ: 256GB",
                Arrays.asList(
                        new SpecItem("Camera", "48MP + 12MP"),
                        new SpecItem("Pin", "4500mAh"),
                        new SpecItem("Màn hình", "6.7 inches OLED"),
                        new SpecItem("CPU", "Apple A18"),
                        new SpecItem("RAM", "8GB"),
                        new SpecItem("ROM", "256GB")
                )
        ));

        featuredProducts.add(new Product(
                "Samsung Galaxy S24",
                "22.000.000đ",
                "25.000.000đ",
                4.8f,
                150,
                R.drawable.samsung_s24,
                "Snapdragon 8 Gen 3, 12GB RAM, Camera 200MP",
                "Màn hình: 6.8\" Dynamic AMOLED, RAM: 12GB, Bộ nhớ: 256GB",
                Arrays.asList(
                        new SpecItem("Camera", "200MP + 12MP + 10MP"),
                        new SpecItem("Pin", "5000mAh"),
                        new SpecItem("Màn hình", "6.8 inches Dynamic AMOLED"),
                        new SpecItem("CPU", "Snapdragon 8 Gen 3"),
                        new SpecItem("RAM", "12GB"),
                        new SpecItem("ROM", "256GB")
                )
        ));
        featuredProducts.add(new Product(
                "Xiaomi 14 Ultra",
                "18.000.000đ",
                "20.000.000đ",
                4.7f,
                100,
                R.drawable.xiaomi14ultra,
                "Snapdragon 8 Gen 3, 16GB RAM, Camera Leica",
                "Màn hình: 6.73\" AMOLED, RAM: 16GB, Bộ nhớ: 512GB",
                Arrays.asList(
                        new SpecItem("Camera", "50MP + 50MP + 50MP + 50MP"),
                        new SpecItem("Pin", "5300mAh"),
                        new SpecItem("Màn hình", "6.73 inches AMOLED"),
                        new SpecItem("CPU", "Snapdragon 8 Gen 3"),
                        new SpecItem("RAM", "16GB"),
                        new SpecItem("ROM", "512GB")
                )
        ));
                featuredProducts.add(new Product(
                        "Iphone 16 Promax 128gb",
                        "25.090.000đ",
                        "28.990.000đ",
                        4.9f,
                        120,
                        R.drawable.iphone_16_promax_128gb, // Bạn cần có ảnh này trong thư mục drawable
                        "Chip Apple A18 Pro, 8GB RAM, Camera ProRAW, Dynamic Island",
                        "Màn hình: 6.9\" OLED ProMotion, RAM: 8GB, Bộ nhớ: 256GB",
                        Arrays.asList(
                                new SpecItem("Camera", "48MP + 12MP + 12MP"),
                                new SpecItem("Pin", "4685mAh"),
                                new SpecItem("Màn hình", "6.9 inches OLED, 120Hz"),
                                new SpecItem("CPU", "Apple A18 Pro"),
                                new SpecItem("RAM", "8GB"),
                                new SpecItem("ROM", "256GB")
                        )
                ));
        featuredProducts.add(new Product(
                "iPhone 16 Pro Max 256GB",
                "30.390.000đ",
                "33.990.000đ",
                4.9f,
                120,
                R.drawable.iphone_16_promax_256gb, // bạn cần file ảnh tương ứng trong drawable
                "Chip Apple A18 Pro, 8GB RAM, Camera Leica‑style ProRAW, Dynamic Island",
                "Màn hình: 6.9\" Super Retina XDR OLED ProMotion; RAM: 8GB; Bộ nhớ: 256GB",
                Arrays.asList(
                        new SpecItem("Camera", "48MP (main) + 48MP (ultra‑wide) + 12MP telephoto 5×"),
                        new SpecItem("Pin", "4685 mAh (~33 giờ xem video)"),
                        new SpecItem("Màn hình", "6.9 inches OLED, 120 Hz, Always‑On"),
                        new SpecItem("CPU", "Apple A18 Pro"),
                        new SpecItem("RAM", "8  GB"),
                        new SpecItem("ROM", "256  GB")
                )
        ));

        featuredProducts.add(new Product(
                "iPhone 16 Pro Max 512GB",
                "36.790.000đ",
                "39.990.000đ",
                4.9f,
                80,
                R.drawable.iphone_16_promax_512gb, // bạn cần file ảnh tương ứng trong drawable
                "Chip Apple A18 Pro, 8GB RAM, Camera ProRAW, Dynamic Island",
                "Màn hình: 6.9\" Super Retina XDR OLED ProMotion; RAM: 8GB; Bộ nhớ: 512GB",
                Arrays.asList(
                        new SpecItem("Camera", "48MP (main) + 48MP (ultra‑wide) + 12MP telephoto 5×"),
                        new SpecItem("Pin", "4685 mAh (~33 giờ xem video)"),
                        new SpecItem("Màn hình", "6.9 inches OLED, 120 Hz, Always‑On"),
                        new SpecItem("CPU", "Apple A18 Pro"),
                        new SpecItem("RAM", "8  GB"),
                        new SpecItem("ROM", "512  GB")
                )
        ));
        featuredProducts.add(new Product(
                "Samsung Galaxy S24 Ultra 512GB",
                "32.990.000đ",
                "36.990.000đ",
                4.8f,
                110,
                R.drawable.samsung_s24_ultra,
                "Snapdragon 8 Gen 3, 12GB RAM, AI Zoom 100x",
                "Màn hình: 6.8\" AMOLED 120Hz; RAM: 12GB; Bộ nhớ: 512GB",
                Arrays.asList(
                        new SpecItem("Camera", "200MP + 50MP + 12MP + 10MP"),
                        new SpecItem("Pin", "5000mAh"),
                        new SpecItem("Màn hình", "6.8 inches Dynamic AMOLED 2X, 120Hz"),
                        new SpecItem("CPU", "Snapdragon 8 Gen 3 for Galaxy"),
                        new SpecItem("RAM", "12GB"),
                        new SpecItem("ROM", "512GB")
                )
        ));
        featuredProducts.add(new Product(
                "OPPO Find X7 Ultra",
                "27.490.000đ",
                "29.990.000đ",
                4.7f,
                85,
                R.drawable.oppo_find_x7_ultra,
                "Snapdragon 8 Gen 3, 16GB RAM, 4 camera 50MP",
                "Màn hình: 6.82\" AMOLED; RAM: 16GB; Bộ nhớ: 512GB",
                Arrays.asList(
                        new SpecItem("Camera", "50MP + 50MP + 50MP + 50MP"),
                        new SpecItem("Pin", "5000mAh"),
                        new SpecItem("Màn hình", "6.82 inches AMOLED, 120Hz"),
                        new SpecItem("CPU", "Snapdragon 8 Gen 3"),
                        new SpecItem("RAM", "16GB"),
                        new SpecItem("ROM", "512GB")
                )
        ));
        featuredProducts.add(new Product(
                "Vivo X100 Pro",
                "24.990.000đ",
                "26.990.000đ",
                4.6f,
                75,
                R.drawable.vivo_x100_pro,
                "Dimensity 9300, Camera ZEISS 50MP, chống nước IP68",
                "Màn hình: 6.78\" AMOLED; RAM: 16GB; Bộ nhớ: 512GB",
                Arrays.asList(
                        new SpecItem("Camera", "50MP (main) + 50MP (ultrawide) + 64MP (periscope)"),
                        new SpecItem("Pin", "5400mAh"),
                        new SpecItem("Màn hình", "6.78 inches AMOLED, 120Hz"),
                        new SpecItem("CPU", "MediaTek Dimensity 9300"),
                        new SpecItem("RAM", "16GB"),
                        new SpecItem("ROM", "512GB")
                )
        ));





        // Thêm các sản phẩm nổi bật khác nếu muốn

        // Dữ liệu mẫu cho tất cả sản phẩm điện thoại
        allProducts = new ArrayList<>();
        allProducts.addAll(featuredProducts); // Dùng lại dữ liệu mẫu, có thể thêm sản phẩm khác nếu muốn
        // Thêm các sản phẩm khác nếu muốn

        // Thiết lập adapter cho danh sách nổi bật
        featuredAdapter = new FeaturedProductAdapter(featuredProducts);
        recyclerViewFeatured.setAdapter(featuredAdapter);

        allProductAdapter = new ProductAdapter(allProducts);
        recyclerViewAllProducts.setAdapter(allProductAdapter);
    }
}

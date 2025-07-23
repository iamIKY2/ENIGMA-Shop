package com.example.tieuluan;


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
        needAdapter = new SimpleStringAdapter(needs, false, true);
        recyclerViewNeeds.setAdapter(needAdapter);

        // Dữ liệu mẫu cho sản phẩm nổi bật
        List<Product> featuredProducts = new ArrayList<>();
        featuredProducts.add(new Product(
                "Macbook Pro M4", // Tên sản phẩm
                "25.000.000đ",    // Giá mới
                "28.000.000đ",    // Giá cũ
                4.8f,              // Đánh giá
                100,               // Đã bán
                R.drawable.macbook_pro_m4, // Ảnh minh họa (thay bằng ảnh thật nếu có)
                "Chip M4, 8GB RAM, SSD 256GB", // Tính năng nổi bật
                "Màn hình: 13.6\" Liquid Retina, RAM: 8GB, SSD: 256GB", // Thông số kỹ thuật
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Apple M4 GPU"),
                        new SpecItem("Dung lượng RAM", "8GB"),
                        new SpecItem("Ổ cứng", "256GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.6 inches"),
                        new SpecItem("Công nghệ màn hình", "Liquid Retina"),
                        new SpecItem("Pin", "52,6 Wh"),
                        new SpecItem("Hệ điều hành", "MacOS"),
                        new SpecItem("Độ phân giải màn hình", "2560 x 1664 pixels"),
                        new SpecItem("Loại CPU", "Apple M4"),
                        new SpecItem("Cổng giao tiếp", "2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3")
                )
        ));
        featuredProducts.add(new Product(
                "Asus ROG Strix",
                "32.000.000đ",
                "35.000.000đ",
                4.7f,
                80,
                R.drawable.asus_rog_strix,
                "Intel i7, RTX 4060, 16GB RAM",
                "Màn hình: 15.6\" FHD 144Hz, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "RTX 4060"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "15.6 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD 144Hz"),
                        new SpecItem("Pin", "56 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i7"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop ASUS Gaming Vivobook 16X K3605VC-RP431W",
                "17.490.000đ",
                "20.990.000đ",
                4.6f,
                60,
                R.drawable.asus_vv_16x,
                "I5-13420H, 16GB RAM,  mỏng nhẹ",
                "Màn hình: 16 inches\" FHD, RAM: 16GB, SSD: 512GB,Card đồ họa: NVIDIA Geforce RTX 3050 4GB GDDR6",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "NVIDIA Geforce RTX 3050 4GB GDDR6"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "16 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD"),
                        new SpecItem("Pin", "50 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-13420H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop MSI Modern 14 C12MO-660VN",
                "11.436.300đ",
                "15.990.000đ",
                5f,
                120,
                R.drawable.msi_modern14,
                "I5-1235U/16GB/512GB PCIE/14.0 FHD/WIN11/ĐEN",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-1235U"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
        featuredProducts.add(new Product(
                "Laptop HP Gaming Victus 15-FA1139TX 8Y6W3PA",
                "16.190.000đ",
                "24.590.000đ",
                5f,
                120,
                R.drawable.hp_gaming_15,
                "I5-12450H, hứa hẹn sẽ mang đến trải nghiệm chơi game cực kỳ ấn tượng với phần cứng có 32GB ram, kết hợp với 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                Arrays.asList(
                        new SpecItem("Loại card đồ họa", "Intel UHD Graphics"),
                        new SpecItem("Dung lượng RAM", "16GB"),
                        new SpecItem("Ổ cứng", "512GB SSD"),
                        new SpecItem("Kích thước màn hình", "13.4 inches"),
                        new SpecItem("Công nghệ màn hình", "FHD Touch"),
                        new SpecItem("Pin", "51 Wh"),
                        new SpecItem("Hệ điều hành", "Windows 11"),
                        new SpecItem("Độ phân giải màn hình", "1920 x 1080 pixels"),
                        new SpecItem("Loại CPU", "Intel Core i5-12450H"),
                        new SpecItem("Cổng giao tiếp", "USB, HDMI, LAN")
                )
        ));
    }
}
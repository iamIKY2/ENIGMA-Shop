package com.atus05.tieuluan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Thông tin database
    private static final String DATABASE_NAME = "ShopDatabase";
    private static final int DATABASE_VERSION = 2;

    // Tên các bảng
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_USERS = "users";

    // Các cột của bảng categories
    public static final String COLUMN_CATEGORY_ID = "id";
    public static final String COLUMN_CATEGORY_NAME = "name";
    public static final String COLUMN_CATEGORY_ICON = "icon_resource";

    // Các cột của bảng products
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_PRICE = "price";
    public static final String COLUMN_PRODUCT_OLD_PRICE = "old_price";
    public static final String COLUMN_PRODUCT_RATING = "rating";
    public static final String COLUMN_PRODUCT_SOLD = "sold";
    public static final String COLUMN_PRODUCT_IMAGE = "image_resource";
    public static final String COLUMN_PRODUCT_HIGHLIGHT = "highlight";
    public static final String COLUMN_PRODUCT_SPECS = "specs";
    public static final String COLUMN_PRODUCT_CATEGORY_ID = "category_id";

    // Các cột của bảng users
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";

    // Tạo bảng categories
    private static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE " + TABLE_CATEGORIES + " (" +
                    COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CATEGORY_NAME + " TEXT NOT NULL, " +
                    COLUMN_CATEGORY_ICON + " INTEGER NOT NULL)";

    // Tạo bảng products
    private static final String CREATE_TABLE_PRODUCTS =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                    COLUMN_PRODUCT_PRICE + " TEXT NOT NULL, " +
                    COLUMN_PRODUCT_OLD_PRICE + " TEXT, " +
                    COLUMN_PRODUCT_RATING + " REAL, " +
                    COLUMN_PRODUCT_SOLD + " INTEGER, " +
                    COLUMN_PRODUCT_IMAGE + " INTEGER, " +
                    COLUMN_PRODUCT_HIGHLIGHT + " TEXT, " +
                    COLUMN_PRODUCT_SPECS + " TEXT, " +
                    COLUMN_PRODUCT_CATEGORY_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_PRODUCT_CATEGORY_ID + ") REFERENCES " +
                    TABLE_CATEGORIES + "(" + COLUMN_CATEGORY_ID + "))";

    // Tạo bảng users
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_USERNAME + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_USER_PASSWORD + " TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_USERS);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Thêm dữ liệu mẫu vào database
    private void insertSampleData(SQLiteDatabase db) {//các sản được chia theo thứ tự từ 1-8
        insertCategory(db, "Điện thoại", R.drawable.icon_phone);
        insertCategory(db, "Tablet", R.drawable.icon_tablet);
        insertCategory(db, "Laptop", R.drawable.icon_laptop);
        insertCategory(db, "Đồng hồ", R.drawable.icon_watch);
        insertCategory(db, "Màn Hình", R.drawable.icon_screen);
        insertCategory(db, "Tivi", R.drawable.icon_tv);
        insertCategory(db, "Âm Thanh", R.drawable.icon_tainghe);
        insertCategory(db, "Phụ Kiện", R.drawable.icon_phukien);
        insertProduct(db,
                "iPhone 15 Pro Max",
                "29.990.000đ",
                "32.990.000đ",
                4.8f, 230, R.drawable.iphone_16_pr,
                "Chip A17 Pro, Camera 48MP, Màn hình 120Hz, Pin 5000mAh",
                "Màn hình: 6.7\" OLED, RAM: 8GB, ROM: 256GB, Camera: 48MP+12MP+12MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "Samsung Galaxy A15",
                "4.500.000đ",
                "5.500.000đ",
                4.3f, 150, R.drawable.samsung_a15,
                "Pin 5000mAh, Màn hình 90Hz",
                "Màn hình: 6.5\" AMOLED, RAM: 6GB, ROM: 128GB, Camera: 50MP+5MP+2MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "iPhone 13",
                "18.990.000đ",
                "20.990.000đ",
                4.6f, 198, R.drawable.iphone13,
                "Chip A15 Bionic, Camera kép 12MP",
                "Màn hình: 6.1\" OLED, RAM: 4GB, ROM: 128GB, Camera: 12MP+12MP, Pin: 3240mAh",
                1);

        insertProduct(db,
                "Redmi Note 13",
                "5.290.000đ",
                "6.290.000đ",
                4.4f, 112, R.drawable.redmi_13,
                "Pin 5000mAh, Sạc nhanh 33W",
                "Màn hình: 6.67\" AMOLED, RAM: 8GB, ROM: 128GB, Camera: 108MP+8MP+2MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "OPPO Reno 11F",
                "7.990.000đ",
                "8.990.000đ",
                4.5f, 121, R.drawable.oppo_reno11f,
                "Sạc nhanh 67W, Camera 64MP",
                "Màn hình: 6.7\" AMOLED, RAM: 8GB, ROM: 256GB, Camera: 64MP+8MP+2MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "Realme C67",
                "4.990.000đ",
                "5.490.000đ",
                4.2f, 95, R.drawable.realme_c67,
                "Pin 5000mAh, Camera 108MP",
                "Màn hình: 6.72\" IPS LCD, RAM: 8GB, ROM: 128GB, Camera: 108MP+2MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "Xiaomi 14 Ultra",
                "18.990.000đ",
                "21.990.000đ",
                4.6f, 140, R.drawable.xiaomi14ultra,
                "Snapdragon 8 Gen 3, Camera Leica",
                "Màn hình: 6.73\" AMOLED, RAM: 12GB, ROM: 512GB, Camera: 50MP+50MP+50MP+50MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "Samsung S24 Ultra",
                "25.990.000đ",
                "28.990.000đ",
                4.7f, 190, R.drawable.samsung_s24,
                "Snapdragon 8 Gen 3, S Pen",
                "Màn hình: 6.8\" AMOLED, RAM: 12GB, ROM: 256GB, Camera: 200MP+12MP+10MP, Pin: 5000mAh",
                1);

        insertProduct(db,
                "Samsung Galaxy Tab A9+ WIFI 4GB 64GB",
                "4.070.000đ",
                "5.890.000đ",
                4.5f, 231, R.drawable.samsung_galaxy_tab_a9_plus_1,
                "Màn hình lớn, Pin 7040mAh",
                "Màn hình: 11\" TFT LCD, RAM: 4GB, ROM: 64GB, Camera: 8MP, Pin: 7040mAh",
                2);
        // --- Sản phẩm Macbook (ghi chú: chỉ dành cho Macbook) ---
      
        insertProduct(db,
                "Asus ROG Strix",
                "32.000.000đ",
                "35.000.000đ",
                4.7f, 80, R.drawable.asus_rog_strix,
                "Intel i7, RTX 4060, 16GB RAM",
                "Màn hình: 15.6\" FHD 144Hz, RAM: 16GB, SSD: 512GB",
                3);

        insertProduct(db,
                "Laptop ASUS Gaming Vivobook 16X",
                "17.490.000đ",
                "20.990.000đ",
                4.6f, 60, R.drawable.asus_vv_16x,
                "I5-13420H, 16GB RAM, mỏng nhẹ",
                "Màn hình: 16 inches FHD, RAM: 16GB, SSD: 512GB",
                3);

        insertProduct(db,
                "Laptop HP Gaming Victus 15",
                "16.190.000đ",
                "24.590.000đ",
                5f, 120, R.drawable.hp_gaming_15,
                "I5-12450H, 16GB RAM, 512GB SSD",
                "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB",
                3);

        insertProduct(db,
                "Laptop MSI Modern 14",
                "11.436.300đ",
                "15.990.000đ",
                5f, 120, R.drawable.msi_modern14,
                "I5-1235U/16GB/512GB PCIE/14.0 FHD/WIN11/ĐEN",
                "Màn hình: 14.0\" FHD, RAM: 16GB, SSD: 512GB",
                3);

        insertProduct(db,
                "Màn hình thông minh LG MyView 25SR50F 25 inch",
                "2.745.500đ",
                "5.990.000đ",
                4.8f, 0, R.drawable.icon_screen,
                "Tấm nền IPS, Tần số quét 60Hz, Độ phân giải 1920x1080",
                "Kích thước thực tế (bao gồm viền): 24.5 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 60 Hz, Thời gian phản hồi: 8ms, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI 2.1, USB-A 2EA, Đầu ra tai nghe, Kích thước có chân đế: 731.8 x 521.2 x 209.9 mm, Kích thước không chân đế: 731.8 x 440.5 x 45.0 mm, Trọng lượng có chân đế: 6.6 kg, Trọng lượng không chân đế: 5.4 kg, Độ phân giải màn hình: 1920 x 1080 pixels",
                5);

        insertProduct(db,
                "Màn hình Gaming ASUS TUF VG249Q3A 24 inch",
                "2.840.500đ",
                "3.990.000đ",
                4.8f, 0, R.drawable.icon_screen,
                "Tấm nền IPS, Tần số quét 180Hz, Độ phân giải 1920x1080",
                "Kích thước thực tế (bao gồm viền): 24 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 180 Hz, Thời gian phản hồi: 1ms, Treo tường: 100 x 100 mm, Cổng kết nối: DisplayPort 1.2 x 1, HDMI (v2.0) x 2, Jack tai nghe, Kích thước có chân đế: 541 x 394 x 174 mm, Kích thước không chân đế: 541 x 323 x 58 mm, Trọng lượng có chân đế: 3.5 kg, Trọng lượng không chân đế: 2.9 kg, Độ phân giải màn hình: 1920 x 1080",
                5);

        insertProduct(db,
                "Màn hình Gaming LG UltraGear 24GS60F-B 24 inch",
                "2.897.500đ",
                "3.990.000đ",
                4.7f, 0, R.drawable.icon_screen,
                "Tấm nền IPS, Tần số quét 180Hz, Độ phân giải 1920x1080",
                "Kích thước thực tế (bao gồm viền): 23.8 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 180 Hz, Thời gian phản hồi: 1ms, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI 2.0 x 1, DisplayPort 1.4, Cổng âm thanh, Kích thước có chân đế: 540.8 x 408.9 x 180.5 mm, Kích thước không chân đế: 540.8 x 323.8 x 42.8 mm, Trọng lượng có chân đế: 4 kg, Trọng lượng không chân đế: 3.5 kg, Độ phân giải màn hình: 1920 x 1080 pixels",
                5);

        insertProduct(db,
                "Màn hình EDRA EGM27F120H 27 inch",
                "2.244.200đ",
                "2.490.000đ",
                4.6f, 0, R.drawable.icon_screen,
                "Tấm nền IPS, Tần số quét 120Hz, Độ phân giải 1920x1080",
                "Kích thước thực tế (bao gồm viền): 27 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 120 Hz, Thời gian phản hồi: 1ms, Cổng kết nối: 1 x HDMI, 1 x VGA + DC, Kích thước vỏ hộp: 613 x 453.4 x 200.9 mm, Trọng lượng: 3.7 kg, Độ phân giải màn hình: 1920 x 1080 pixels",
                5);

        // --- Thêm sản phẩm màn hình mới ---
        insertProduct(db,
                "Màn hình E-Dra EGM24F100H 24 inch",
                "1.605.500đ",
                "2.490.000đ",
                4.6f, 0, R.drawable.icon_screen,
                "Tấm nền IPS, 100Hz, FHD",
                "Kích thước thực tế (bao gồm viền): 23.8 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 100 Hz, Thời gian phản hồi: 1ms, Treo tường: 75 x 75 mm, Cổng kết nối: 1xVGA + 1xHDMI + Audio out + DC, Độ phân giải màn hình: 1920 x 1080",
                5);

        insertProduct(db,
                "Màn hình Viewsonic VA2708-2K-MHD 27 inch",
                "3.518.000đ",
                "4.290.000đ",
                4.7f, 0, R.drawable.icon_screen,
                "IPS, 2K, 100Hz, nhiều cổng kết nối",
                "Kích thước thực tế (bao gồm viền): 27 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 100 Hz, Thời gian phản hồi: 1ms, Độ tương phản động: 50M:1, Treo tường: 75 x 75 mm, Cổng kết nối: 1x Đầu ra âm thanh 3,5 mm, 2x HDMI 2.0, 1x DisplayPort, DC Socket (Center Positive), Kích thước có chân đế: 613.19 x 449.89 x 190 mm, Kích thước không chân đế: 613.19 x 362.99 x 52.74 mm, Trọng lượng có chân đế: 4.1 kg, Trọng lượng không chân đế: 3.3 kg, Độ phân giải màn hình: 2560 x 1440 pixels",
                5);

        insertProduct(db,
                "Màn hình đồ họa ASUS ProArt PA248QV 24.1 inch",
                "4.740.500đ",
                "6.490.000đ",
                4.8f, 0, R.drawable.icon_screen,
                "Chuyên đồ họa, 16:10, nhiều cổng USB",
                "Kích thước thực tế (bao gồm viền): 24.1 inches, Tỉ lệ màn hình: 16:10, Tần số quét: 75 Hz, Thời gian phản hồi: 5ms, Độ tương phản động: 100000000:1, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI, D-Sub, DisplayPort, PC Audio Input: 3.5mm Mini-Jack, Earphone jack: 3.5mm Mini-Jack, USB Port(s): 3.0x4, Kích thước kèm với kệ: 533 x (375~505) x 211 mm, Kích thước không kèm kệ: 533 x 360 x 47 mm, Trọng lượng tịnh với chân đế: 6.1 Kg, Trọng lượng tịnh không có chân đế: 3.9 Kg, Độ phân giải màn hình: 1920 x 1200 pixels, Tính năng khác: 2W x 2 Stereo RMS, VESA Wall Mounting, Kensington lock",
                5);

        insertProduct(db,
                "Màn hình di động MSI Pro MP161 E2 15.6 inch",
                "2.840.500đ",
                "4.290.000đ",
                4.6f, 0, R.drawable.icon_screen,
                "Di động, IPS, 15.6 inch, nhẹ",
                "Kích thước thực tế (bao gồm viền): 15.6 inches, Độ phân giải màn hình: 1920 x 1080, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 60 Hz, Thời gian phản hồi: 4ms, Treo tường: Không hỗ trợ, Cổng kết nối: 2 x USB Type-C, 1 x Mini HDMI, Kích thước: 362.26 x 232.92 x 17.8 mm, Trọng lượng: 0.75 Kg",
                5);

// --- Thêm sản phẩm laptop mới ---
        insertProduct(db,
                "Laptop ASUS Vivobook 15 X1502VA-BQ885W",
                "13.085.300đ",
                "15.990.000đ",
                4.6f, 0, R.drawable.asus_rog_strix,
                "i5-13420H, 16GB, 512GB SSD, 15.6\" FHD",
                "Loại card đồ họa: Intel HD Graphics, Dung lượng RAM: 16GB, Loại RAM: DDR4, Số khe ram: 2 khe (8GB DDR4 on board + 8GB DDR4 SO-DIMM, Có thể nâng cấp; Cần tháo vỏ dưới/trên), Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Độ sáng 250nits, Độ phủ màu 45% NTSC, Màn hình chống chói, TÜV Rheinland-certified, Pin: 42WHrs, 3S1P, 3-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-13420H 2.1 GHz (12MB Cache, up to 4.6 GHz, 8 lõi, 12 luồng), Cổng giao tiếp: 1x USB 2.0 Type-A (480Mbps), 1x USB 3.2 Gen 1 Type-C (5Gbps), 2x USB 3.2 Gen 1 Type-A (5Gbps), 1x HDMI 1.4, 1x Giắc cắm âm thanh 3.5mm, 1x DC-in",
                3);

        insertProduct(db,
                "Laptop ASUS VivoBook S 16 OLED S5606MA-MX051W",
                "24.190.000đ",
                "29.990.000đ",
                4.8f, 0, R.drawable.asus_rog_strix,
                "Ultra 7 155H, OLED 16 inch, 16GB, 512GB SSD",
                "Loại card đồ họa: Intel Arc Graphics, Dung lượng RAM: 16GB, Loại RAM: LPDDR5X Onboard, Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 16 inches, Công nghệ màn hình: Độ sáng tối đa 600nits HDR, Gam màu 100% DCI-P3, Tỷ lệ tương phản 1.000.000:1, Ánh sáng xanh ít gây hại hơn 70%, TÜV Rheinland-certified, 1.07 tỷ màu, Pin: 75WHrs, 4S1P, 4-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 3200 x 2000 pixel (WQHD+), Loại CPU: Intel Core Ultra 7 155H 1.4 GHz (24MB Cache, up to 4.8 GHz, 16 lõi, 22 luồng), Cổng giao tiếp: 2x USB 3.2 Gen 1 Type-A, 2x Thunderbolt 4, 1x HDMI 2.1 TMDS, 1x 3.5mm Combo Audio Jack, Đầu đọc thẻ Micro SD",
                3);

        insertProduct(db,
                "Laptop ASUS Vivobook 15 OLED A1505VA-L1688W",
                "17.990.000đ",
                "20.990.000đ",
                4.7f, 0, R.drawable.asus_rog_strix,
                "i7-13620H, OLED 15.6 inch, 16GB, 512GB SSD",
                "Loại card đồ họa: Intel UHD Graphics, Dung lượng RAM: 16GB, Loại RAM: DDR4, Số khe ram: 2 khe (8GB DDR4 on board + 8GB DDR4 SO-DIMM), Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Thời gian phản hồi 0.2ms, Độ sáng tối đa 600nits HDR, Độ phủ màu 100% DCI-P3, Gamut mapping, HDR True Black 600, 1.07 tỷ màu, Giảm 70% ánh sáng xanh có hại, TÜV Rheinland, SGS, Pin: 50WHrs, 3S1P, 3-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i7-13620H 2.4 GHz (24MB Cache, up to 4.9 GHz, 10 lõi, 16 luồng), Cổng giao tiếp: 1x USB 2.0 Type-A, 1x USB 3.2 Gen 1 Type-C, 2x USB 3.2 Gen 1 Type-A, 1x HDMI 1.4, 1x Giắc cắm âm thanh 3.5mm, 1x DC-in",
                3);

        insertProduct(db,
                "Laptop MSI Katana 15 B13VEK-2256VN",
                "23.990.000đ",
                "26.990.000đ",
                4.7f, 0, R.drawable.msi_modern14,
                "i7-13620H, RTX 4050, 16GB, 512GB SSD",
                "Loại card đồ họa: Intel UHD Graphics + NVIDIA GeForce RTX 4050 6GB GDDR6, Dung lượng RAM: 16GB, Loại RAM: DDR5 5200MHz, Số khe ram: 2 khe (1x 16GB, Hỗ trợ nâng cấp tối đa 64GB), Ổ cứng: 1x 512GB M.2-2280 SSD slot (NVMe PCIe Gen4x4), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Độ phủ màu 45% NTSC, Màn hình chống chói, Pin: 3-cell, 53.5WHr, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i7-13620H, Cổng giao tiếp: 1x Type-C (USB3.2 Gen1 / DisplayPort), 2x Type-A USB3.2 Gen1, 1x Type-A USB2.0, 1x HDMI (4K @ 30Hz), 1x RJ45, 1x Mic-in/Headphone-out Combo Jack",
                3);

        insertProduct(db,
                "Laptop MSI Prestige 13 AI Evo A1MG-240VN",
                "25.990.000đ",
                "30.990.000đ",
                4.8f, 0, R.drawable.msi_modern14,
                "Ultra 5 125H, 32GB, 512GB SSD, 13.3\" 2.8K",
                "Chip AI: Intel AI Boost, Loại card đồ họa: Intel Arc Graphics, Dung lượng RAM: 32GB, Loại RAM: LPDDR5X/ 8533MHz, Số khe ram: Onboard, Ổ cứng: 512GB SSD PCIe (M.2 2280), Kích thước màn hình: 13.3 inches, Công nghệ màn hình: Màn hình bóng, Pin: 4 Cell Int (75Wh), Hệ điều hành: Windows 11 Home Single Language 64-bit, Độ phân giải màn hình: 2880 x 1800 pixels, Loại CPU: Intel Core Ultra 5 125H (14 lõi) - Max Turbo Frequency: 4.50 GHz, Cổng giao tiếp: 1 x USB 3.2 Gen 1 Type-A, 2 x Thunderbolt 4, 1 x HDMI, 1 x Headphone/Microphone combo audio jack",
                3);
        // --- Thêm sản phẩm tablet mới ---
        insertProduct(db,
                "iPad A16 Wifi 128GB 2025",
                "8.526.300đ", "9.990.000đ",
                4.8f, 120, R.drawable.icon_tablet,
                "Chip A16, Màn hình 11 inches, 128GB, iPadOS 18",
                "Kích thước màn hình: 11 inches, Công nghệ màn hình: Liquid Retina, Camera sau: 12MP, Camera trước: 12MP, Chipset: A16, Bộ nhớ trong: 128GB, Pin: 28,93Wh, Hệ điều hành: iPadOS 18, Độ phân giải: 2360 x 1640 pixel, Tính năng: Multi-Touch, True Tone, Độ sáng 500 nit, Lớp phủ kháng dầu, CPU: 5 lõi, Tương thích: Apple Pencil (USB‑C, Gen 1)",
                2);

        insertProduct(db,
                "iPad mini 7 2024 Wifi 128GB",
                "11.436.300đ", "13.990.000đ",
                4.7f, 90, R.drawable.icon_tablet,
                "Chip A17 Pro, Màn hình 8.3 inches, 128GB, iPadOS 18",
                "Kích thước màn hình: 8.3 inches, Công nghệ màn hình: Liquid Retina, Camera sau: 12MP, Camera trước: 12MP, Chipset: A17 Pro, Bộ nhớ trong: 128GB, Pin: 19,3Wh, Hệ điều hành: iPadOS 18, Độ phân giải: 2266 x 1488 pixels, Tính năng: Multi-Touch, True Tone, Độ sáng 500 nit, CPU: 6 lõi, Tương thích: Apple Pencil Pro/USB‑C",
                2);

        insertProduct(db,
                "Xiaomi Redmi Pad 2 WiFi 6GB 128GB",
                "4.930.500đ", "5.690.000đ",
                4.6f, 80, R.drawable.icon_tablet,
                "Helio G100-Ultra, 6GB RAM, 128GB, HyperOS 2",
                "Kích thước màn hình: 11 inches, Camera sau: 8MP, Camera trước: 5MP, Chipset: MediaTek Helio G100-Ultra, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 9000mAh, Hệ điều hành: HyperOS 2, Độ phân giải: 2560 x 1600, Tính năng: 90Hz, 500 nits, CPU: A76+A55, Tương thích: Redmi Smart Pen",
                2);

        insertProduct(db,
                "Xiaomi Pad 7 8GB 128GB",
                "9.200.000đ", "10.300.000đ",
                4.8f, 110, R.drawable.icon_tablet,
                "Snapdragon 7+ Gen 3, 8GB RAM, 128GB, HyperOS 2",
                "Kích thước màn hình: 11.2 inches, Công nghệ màn hình: IPS LCD, Camera sau: 13MP, Camera trước: 8MP, Chipset: Snapdragon 7+ Gen 3, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 8850mAh, Hệ điều hành: Android 15, HyperOS 2, Độ phân giải: 2136 x 3200, Tính năng: 144Hz, HDR10, Dolby Vision, CPU: Cortex-X4/A720/A520",
                2);

        insertProduct(db,
                "Xiaomi Redmi Pad Pro wifi 8GB 128GB",
                "6.750.000đ", "7.850.000đ",
                4.7f, 70, R.drawable.icon_tablet,
                "Snapdragon 7s Gen 2, 8GB RAM, 128GB, HyperOS",
                "Kích thước màn hình: 12.1 inches, Công nghệ màn hình: IPS LCD, Camera sau: 8MP, Camera trước: 8MP, Chipset: Snapdragon 7s Gen 2, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 10.000mAh, Hệ điều hành: HyperOS, Android U, Độ phân giải: 2560 x 1600, Tính năng: 120Hz, Dolby Vision, CPU: Octa-core 2.4GHz",
                2);

        insertProduct(db,
                "Samsung Galaxy Tab S10 Plus Wifi 12GB 256GB",
                "21.520.000đ", "25.520.000đ",
                4.9f, 150, R.drawable.samsung_galaxy_tab_a9_plus_1,
                "Dimensity 9300+, 12GB RAM, 256GB, Android 14",
                "Kích thước màn hình: 12.4 inches, Công nghệ màn hình: Dynamic AMOLED 2X, Camera sau: 13+8MP, Camera trước: 12MP, Chipset: Dimensity 9300+, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 10,090mAh, Hệ điều hành: Android 14, Độ phân giải: 1752 x 2800, Tính năng: 120Hz, 650 nits, CPU: 8 nhân 3.4GHz, Tương thích: S Pen",
                2);

        insertProduct(db,
                "Samsung Galaxy Tab S10 FE Plus Wifi 8GB 128GB",
                "12.401.500đ", "15.700.000đ",
                4.7f, 100, R.drawable.samsung_galaxy_tab_a9_plus_1,
                "Exynos 1580, 8GB RAM, 128GB, Android 15",
                "Kích thước màn hình: 13.1 inches, Công nghệ màn hình: TFT LCD, Camera sau: 13MP, Camera trước: 12MP, Chipset: Exynos 1580, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 10,090mAh, Hệ điều hành: Android 15, Độ phân giải: 2880 x 1800, Tính năng: 90Hz, 800 nits, CPU: 2.9/2.6/1.9GHz, Tương thích: S Pen",
                2);

        insertProduct(db,
                "Samsung Galaxy Tab S10 Ultra Wifi 12GB 256GB",
                "26.430.000đ", "30.430.000đ",
                4.9f, 160, R.drawable.samsung_galaxy_tab_a9_plus_1,
                "Dimensity 9300+, 12GB RAM, 256GB, Android 14",
                "Kích thước màn hình: 14.6 inches, Công nghệ màn hình: Dynamic AMOLED 2X, Camera sau: 13+8MP, Camera trước: 12+12MP, Chipset: Dimensity 9300+, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 11,200mAh, Hệ điều hành: Android 14, Độ phân giải: 1848 x 2960, Tính năng: 120Hz, 930 nits, CPU: 8 nhân 3.4GHz, Tương thích: S Pen",
                2);

        insertProduct(db,
                "Máy Tính Bảng Huawei Matepad 11.5''S 8GB 256GB",
                "11.550.000đ", "14.490.000đ",
                4.8f, 85, R.drawable.icon_tablet,
                "Kirin 9000WL, 8GB RAM, 256GB, HarmonyOS 4.2",
                "Kích thước màn hình: 11.5 inches, Công nghệ màn hình: TFT LCD, Camera sau: 13MP, Camera trước: 8MP, Chipset: Kirin 9000WL, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 8800mAh, Hệ điều hành: HarmonyOS 4.2, Độ phân giải: 2800 x 1840, Tính năng: 144Hz, 500 nit, CPU: Octa-core 3.2GHz, Tương thích: M-Pencil",
                2);

        insertProduct(db,
                "Huawei MatePad SE 11 inch 6GB 128GB",
                "4.590.000đ", "5.490.000đ",
                4.6f, 60, R.drawable.icon_tablet,
                "6GB RAM, 128GB, HarmonyOS 2.0",
                "Kích thước màn hình: 11 inches, Công nghệ màn hình: TFT LCD, Camera sau: 8MP, Camera trước: 5MP, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 7700mAh, Hệ điều hành: HarmonyOS 2.0, Độ phân giải: 1920 x 1200, Tính năng: 400 nits, Tương thích: M-Pen lite4",
                2);

        insertProduct(db,
                "Lenovo Idea Tab Pro Wifi 8GB 256GB ZAE40190VN kèm bút, bàn phím",
                "11.433.000đ", "11.990.000đ",
                4.7f, 75, R.drawable.icon_tablet,
                "Dimensity 8300, 8GB RAM, 256GB, LCD 12.7 inch, Android 14",
                "Công nghệ màn hình: LCD, Kích thước: 12.7 inch, Độ phân giải: 2944 x 1840, Tần số quét: 144Hz, 273 PPI, 400 nits, Paper-like-anti-glare-matte, Camera sau: 13MP, Camera trước: 8MP, Chipset: Dimensity 8300, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 10200mAh, Hệ điều hành: Android 14, CPU: 8C, 1x A715 @3.35 GHz + 3x A715@3.2 GHz + 4x A510@2.2 GHz, Tương thích: Tab Pen Plus, Keyboard Pack, Folio Case",
                2);

        insertProduct(db,
                "Lenovo Tab M11 4G (4GB 64GB) ZADB0134VN - Kèm bút, bao da",
                "4.567.000đ", "5.790.000đ",
                4.5f, 60, R.drawable.icon_tablet,
                "Helio G88, 4GB RAM, 64GB, IPS LCD 11 inch, Android 13",
                "Kích thước màn hình: 11 inches, Công nghệ màn hình: IPS LCD, Độ phân giải: 1920 x 1200, 400nits, 90Hz, Camera sau: 8MP, Camera trước: 8MP, Chipset: Helio G88, RAM: 4GB, Bộ nhớ trong: 64GB, Pin: 7040mAh, SIM: Nano-SIM, Hệ điều hành: Android 13, CPU: 8C, 2x A75 @2.0GHz + 6x A55 @1.8GHz, Tương thích: Tab Pen, Bao da Folio",
                2);

        insertProduct(db,
                "Lenovo Tab 4G 4GB 64GB ZAEJ0139VN kèm bao da",
                "3.970.000đ", "4.190.000đ",
                4.4f, 55, R.drawable.icon_tablet,
                "Helio G85, 4GB RAM, 64GB, TFT LCD 10.1 inch, Android 14",
                "Kích thước màn hình: 10.1 inches, Công nghệ màn hình: TFT LCD, Độ phân giải: 1920 x 1200, 400nits, 60Hz, Camera sau: 8MP, Camera trước: 5MP, Chipset: Helio G85, RAM: 4GB, Bộ nhớ trong: 64GB, Pin: 5100mAh, SIM: Nano-SIM, Hệ điều hành: Android 14, CPU: 8C, 2x A75 @2.0GHz + 6x A55 @1.8GHz, Tương thích: Bao da",
                2);
        // --- Thêm sản phẩm TV mới ---
        // ===================== SAMSUNG =====================
        insertProduct(db,
                "Smart Tivi Samsung 4K 65 inch UA65CU8000",
                "14.490.000₫",
                "25.400.000₫",
                4.9f, 50, R.drawable.samsung_ua65cu8000,
                "Tivi Samsung 65 inch, 4K, Smart Hub, Hệ điều hành Tizen™",
                "Kích thước màn hình: 65 inch, Độ phân giải: 4K UHD, Tần số quét: 60Hz, Công nghệ hình ảnh: Dynamic Crystal Color, HDR10+, HLG, Bộ xử lý: Crystal 4K, Loa: 20W, Hệ điều hành: Tizen™, Kết nối: Bluetooth, WiFi, HDMI x3, USB x2, Xuất xứ: Việt Nam",
                6
        );

        insertProduct(db,
                "Smart Tivi Samsung 55 inch Crystal UHD 4K UA55BU8000",
                "11.490.000₫",
                "16.900.000₫",
                4.8f, 40, R.drawable.samsung_ua65cu8000,
                "Tivi Samsung 55 inch, 4K UHD, Crystal Processor 4K, Motion Xcelerator",
                "Màn hình: 55 inch, Độ phân giải: 4K UHD, Tần số quét: 60Hz, Công nghệ hình ảnh: HDR10+, HLG, Bộ xử lý: Crystal 4K, Âm thanh: 20W, Công nghệ Q-Symphony, Tizen OS, Kết nối: HDMI x3, USB x2, Bluetooth, Xuất xứ: Việt Nam",
                6
        );

// ===================== LG =====================
        insertProduct(db,
                "Smart Tivi LG 4K 43 inch 43UQ8000PSC",
                "7.990.000₫",
                "11.400.000₫",
                4.6f, 60, R.drawable.lg_43uq8000psc,
                "Tivi LG 43 inch, 4K UHD, AI ThinQ, WebOS",
                "Màn hình: 43 inch, Độ phân giải: 4K UHD, Công nghệ hình ảnh: HDR10 Pro, HLG, Bộ xử lý: α5 Gen5 AI Processor 4K, AI Sound, Âm thanh 20W, Hệ điều hành: WebOS, Tính năng thông minh: LG ThinQ AI, Kết nối: HDMI x3, USB x2, Bluetooth, Xuất xứ: Indonesia",
                6
        );

        insertProduct(db,
                "Smart Tivi LG 4K 55 inch 55UQ7550PSF",
                "10.290.000₫",
                "16.900.000₫",
                4.8f, 40, R.drawable.lg_43uq8000psc,
                "Tivi LG 55 inch 4K, ThinQ AI, WebOS, Âm thanh vòm",
                "Màn hình: 55 inch, Độ phân giải: 4K UHD, HDR: Active HDR, HLG, HDR10 Pro, Bộ xử lý: α5 Gen5 AI Processor, Âm thanh: 20W, AI Sound, Hệ điều hành: WebOS, Tính năng: Voice Search, Kết nối: HDMI x3, USB x1, Bluetooth, Xuất xứ: Indonesia",
                6
        );

// ===================== SONY =====================
        insertProduct(db,
                "Google Tivi Sony 4K 43 inch KD-43X75K",
                "9.690.000₫",
                "13.900.000₫",
                4.7f, 50, R.drawable.sony_43x75k,
                "Google TV Sony 43 inch 4K, Triluminos Pro, Android 11",
                "Màn hình: 43 inch, Độ phân giải: 4K UHD, Tần số quét: 60Hz, HDR10, HLG, Bộ xử lý: X1 4K Processor, Âm thanh: 20W, Dolby Audio, DTS Digital Surround, Hệ điều hành: Google TV (Android 11), Trợ lý ảo: Google Assistant, Kết nối: HDMI x3, USB x2, Xuất xứ: Malaysia",
                6
        );

        insertProduct(db,
                "Google Tivi Sony 4K 55 inch KD-55X75K",
                "12.290.000₫",
                "18.400.000₫",
                4.9f, 30, R.drawable.sony_55x75x,
                "Sony 55 inch 4K UHD, Google TV, HDR10/HLG, X-Reality PRO",
                "Màn hình: 55 inch, Độ phân giải: 4K UHD, HDR: HDR10, HLG, Bộ xử lý: X1 4K Processor, Âm thanh: 20W, Dolby Audio, DTS Digital Surround, Hệ điều hành: Google TV, Voice Remote: Có, Kết nối: HDMI x3, USB x2, Bluetooth, Xuất xứ: Malaysia",
                6
        );

// ===================== TCL =====================
        insertProduct(db,
                "Google Tivi TCL 4K 43 inch 43P635",
                "6.590.000₫",
                "9.400.000₫",
                4.5f, 40, R.drawable.tlc_43p635,
                "Tivi TCL 43 inch 4K, Google TV, Dolby Audio, Micro Dimming",
                "Màn hình: 43 inch, Độ phân giải: 4K UHD, HDR10, Bộ xử lý: Quad-core, Âm thanh: Dolby Audio, Hệ điều hành: Google TV, Tính năng: Chromecast Built-in, Voice Search, Kết nối: HDMI x3, USB x1, Bluetooth 5.0, Xuất xứ: Việt Nam",
                6
        );

        insertProduct(db,
                "Google Tivi TCL 4K 55 inch 55P635",
                "8.790.000₫",
                "13.900.000₫",
                4.7f, 35, R.drawable.tcl_55p635,
                "TCL 55 inch, 4K Google TV, Dolby Vision, DTS-HD",
                "Màn hình: 55 inch, Độ phân giải: 4K UHD, HDR10, Bộ xử lý: Quad-core, Âm thanh: Dolby Audio, DTS-HD, Hệ điều hành: Google TV, Tính năng: AI-IN, Chromecast Built-in, Kết nối: HDMI x3, USB x1, Bluetooth 5.0, Xuất xứ: Việt Nam",
                6
        );



        // --- Sản phẩm đồng hồ mới cập nhật ---
        insertProduct(db, 
            "Apple Watch SE 2 2024 44mm (GPS) Viền Nhôm Dây Cao Su", 
            "5.761.000đ", 
            "6.790.000đ", 
            4.8f, 120, R.drawable.apple_watch_se_2_2024,
            "Retina LTPO OLED, Đường kính 44mm, Bluetooth, Pin 18h, Apple Chính hãng",
            "Công nghệ màn hình: Retina LTPO OLED, Độ sáng tối đa: 1000 nit, Đường kính mặt: 44 mm, Kích thước cổ tay phù hợp: 14 - 19 cm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đếm bước chân, Tính quãng đường chạy, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Theo dõi chu kỳ, Tương thích: iPhone Xs trở lên, iOS 18+, Thời lượng pin: 18 giờ, Hãng sản xuất: Apple Chính hãng",
            4);
        insertProduct(db, 
            "Apple Watch Series 10 42mm (GPS) Viền Nhôm Dây Cao Su", 
            "8.348.000đ", 
            "10.990.000đ", 
            4.9f, 100, R.drawable.apple_watch_s10,
            "Retina LTPO3 Always On, 42mm, Bluetooth, Pin 18-36h, Apple Chính hãng",
            "Công nghệ màn hình: Retina LTPO3 Always On, OLED góc rộng, Độ sáng tối đa: 2000 nit, Kích thước màn hình: 989 mm, Đường kính mặt: 42 mm, Kích thước cổ tay phù hợp: 13 – 18 cm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảm biến nhiệt độ, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Đo điện tâm đồ, Theo dõi chu kỳ, Tương thích: iPhone Xs trở lên, iOS 18+, Thời lượng pin: 18-36 giờ, Hãng sản xuất: Apple Chính hãng",
            4);
        insertProduct(db, 
            "Apple Watch Series 9 41mm (4G) viền nhôm dây cao su", 
            "7.950.000đ", 
            "13.090.000đ", 
            4.8f, 90, R.drawable.apple_watch_s9,
            "Retina, 41mm, eSim, Pin 18-36h, Apple Chính hãng",
            "Công nghệ màn hình: Retina, Kích thước màn hình: 1.6 inch, Đường kính mặt: 41 mm, Kích thước cổ tay phù hợp: 130-180mm, Nghe gọi: eSim, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảm biến nhiệt độ, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Đo điện tâm đồ, Tương thích: iOS, Thời lượng pin: 18-36 giờ, Hãng sản xuất: Apple Chính hãng",
            4);
        insertProduct(db, 
            "Apple Watch Series 10 46mm 4G Viền Titan Dây Thép", 
            "21.681.000đ", 
            "22.590.000đ", 
            5.0f, 50, R.drawable.apple_watch_s10_titan,
            "Retina LTPO3 Always On, 46mm, eSim, Pin 18-36h, Apple Chính hãng",
            "Công nghệ màn hình: Retina LTPO3 Always On, OLED góc rộng, Độ sáng tối đa: 2000 nit, Đường kính mặt: 46 mm, Kích thước cổ tay phù hợp: 13 - 18 cm, Nghe gọi: eSim, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảm biến nhiệt độ, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Đo điện tâm đồ, Theo dõi chu kỳ, Tương thích: iPhone Xs trở lên, iOS 18+, Thời lượng pin: 18-36 giờ, Hãng sản xuất: Apple Chính hãng",
            4);
        insertProduct(db, 
            "Đồng hồ thông minh Samsung Galaxy Watch Ultra", 
            "13.423.000đ", 
            "16.990.000đ", 
            4.7f, 60, R.drawable.samsung_watch_ultra,
            "Super AMOLED, 47mm, eSim, Pin 80-100h, Samsung Chính hãng",
            "Công nghệ màn hình: Super AMOLED, Kích thước màn hình: 1.5 inch, Đường kính mặt: 47 mm, Nghe gọi: eSim, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Đo chỉ số năng lượng cơ thể, Đo điện tâm đồ, Đo VO2 max, Đo huyết áp, Theo dõi chu kỳ, Nhắc nhở uống nước, Theo dõi chỉ số AGEs, Đo tải trọng mạch máu, Tương thích: Android 12+, Thời lượng pin: 80-100 giờ, Hãng sản xuất: Samsung Chính hãng",
            4);
        insertProduct(db, 
            "Samsung Galaxy Watch 7", 
            "4.826.000đ", 
            "7.850.000đ", 
            4.6f, 80, R.drawable.samsung_watch_7_,
            "Super AMOLED, 40mm, Bluetooth, Pin 40h, Samsung Chính hãng",
            "Công nghệ màn hình: Super AMOLED, Kích thước màn hình: 1.31 inch, Đường kính mặt: 40mm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đếm bước chân, Phát hiện té ngã, Đo chỉ số năng lượng cơ thể, Đo điện tâm đồ, Đo huyết áp, Tương thích: Android 11+, RAM 1.5GB+, Thời lượng pin: 40 giờ, Hãng sản xuất: Samsung Chính hãng",
            4);
        insertProduct(db, 
            "Đồng hồ thông minh Samsung Galaxy Watch8 44mm", 
            "9.940.000đ", 
            "9.990.000đ", 
            4.8f, 100, R.drawable.samsung_watch_8,
            "Super AMOLED, 1.47 inch, 44mm, Bluetooth, Pin 40h, Samsung Chính hãng",
            "Công nghệ màn hình: Super AMOLED, Độ sáng: 3000 nits, Kích thước màn hình: 1.47 inch, Đường kính mặt: 44 mm, Kích thước cổ tay phù hợp: Không công bố, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảnh báo nhịp tim bất thường, Đo điện tâm đồ, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Đo huyết áp, Theo dõi chu kỳ, Nhắc nhở uống nước, Phát hiện chứng ngưng thở khi ngủ, Phân tích thành phần cơ thể, Theo dõi chỉ số AGEs, Đo tải trọng mạch máu, Đo chất chống oxi hóa, Tương thích: Android 12.0 trở lên (RAM tối thiểu 1.5GB), Thời lượng pin: Khoảng 40 giờ (Tắt Always-On-Display), Khoảng 30 giờ (Bật Always-On Display), Hãng sản xuất: Samsung Chính hãng",
            4); // Thêm sản phẩm Galaxy Watch8 44mm
        insertProduct(db, 
            "Đồng hồ Samsung Galaxy Fit 3", 
            "955.000đ", 
            "1.360.000đ", 
            4.5f, 80, R.drawable.samsung_watch_fit3,
            "AMOLED, 1.6 inch, Pin 13 ngày, Samsung Chính hãng",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.6 inch, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đếm bước chân, Đo mức độ stress, Phát hiện té ngã, Thời lượng pin: Lên đến 13 ngày, Hãng sản xuất: Samsung Chính hãng",
            4); // Thêm sản phẩm Galaxy Fit 3
        insertProduct(db, 
            "Samsung Galaxy Watch6 Classic 43mm Bluetooth", 
            "4.766.000đ", 
            "8.990.000đ", 
            4.7f, 90, R.drawable.samsung_watch_6,
            "Super AMOLED, 1.3 inch, 43mm, Bluetooth, Pin 40h, Samsung Chính hãng",
            "Công nghệ màn hình: Super AMOLED, Kích thước màn hình: 1.3 inch, Đường kính mặt: 43mm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảm biến nhiệt độ, Cảnh báo nhịp tim bất thường, Tính tuổi thể chất, Đo điện tâm đồ, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Đo huyết áp, Tương thích: Android 10 trở lên và có bộ nhớ trên 1.5GB, Thời lượng pin: Lên đến 40 giờ (Tắt Always on Display), Lên đến 30 giờ (Bật Always on Display), Hãng sản xuất: Samsung Chính hãng",
            4);
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Redmi Watch 5 Active", 
            "741.000đ", 
            "890.000đ", 
            4.3f, 60, R.drawable.xiaomi_watch_5,
            "LCD, 2.0 inch, 49.1mm, Bluetooth, Pin 18 ngày, Xiaomi",
            "Công nghệ màn hình: LCD, Kích thước màn hình: 2.0 inch, Đường kính mặt: 49.1 mm, Kích thước cổ tay phù hợp: Hãng không công bố, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Đo mức độ stress, Tương thích: Android 6.0 trở lên, iOS 11 trở lên, Thời lượng pin: 18 ngày sử dụng liên tục (không có AOD), Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Redmi Watch 5 Active
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Watch S4", 
            "3.510.000đ", 
            "4.290.000đ", 
            4.5f, 50, R.drawable.xiaomi_watch_s4,
            "AMOLED, 1.43 inch, 2200 nit, Bluetooth, Pin 15 ngày, Xiaomi",
            "Công nghệ màn hình: AMOLED, Độ sáng tối đa: 2200 nit, Kích thước màn hình: 1.43 inch, Kích thước cổ tay phù hợp: 14 - 21 cm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đo mức độ stress, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Theo dõi chu kỳ, Bài tập thở, Tương thích: Android 8.0 hoặc iOS 12.0 trở lên, Thời lượng pin: 15 ngày sử dụng thông thường, Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Watch S4
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Redmi Watch 5 Lite", 
            "1.206.500đ", 
            "1.390.000đ", 
            4.4f, 55, R.drawable.xiaomi_watch_5_lite,
            "AMOLED, 1.96 inch, 48.2mm, Bluetooth, Pin 18 ngày, Xiaomi",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.96 inch, Đường kính mặt: 48.2 mm, Kích thước cổ tay phù hợp: Hãng không công bố, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Đo mức độ stress, Cảnh báo nhịp tim bất thường, Theo dõi chu kỳ, Tương thích: Android 6.0 trở lên, iOS 11 trở lên, Thời lượng pin: 18 ngày sử dụng liên tục (không có AOD), Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Redmi Watch 5 Lite
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Redmi Watch 4", 
            "1.558.000đ", 
            "2.690.000đ", 
            4.5f, 70, R.drawable.xiaomi_watch_4,
            "AMOLED, 1.97 inch, 47.5mm, Bluetooth, Pin 20 ngày, Xiaomi",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.97 inch, Đường kính mặt: 47.5 mm, Kích thước cổ tay phù hợp: Hãng không công bố, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảnh báo nhịp tim bất thường, Theo dõi chu kỳ, Thời lượng pin: Khoảng 20 ngày (Chế độ cơ bản), Khoảng 10 ngày (khi sử dụng chế độ Màn hình luôn bật), Dung lượng Pin: 470 mAh, Tiện ích: Màn hình cảm ứng, Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Redmi Watch 4
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Watch 2", 
            "2.800.000đ", 
            "4.690.000đ", 
            4.6f, 65, R.drawable.xiaomi_watch_2,
            "AMOLED, 1.43 inch, Bluetooth, Pin 65 giờ, Xiaomi",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.43 inch, Kích thước cổ tay phù hợp: 140 mm - 210 mm, Nghe gọi: Bluetooth, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Đo mức độ stress, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Theo dõi chu kỳ, Tương thích: Android 8.0 trở lên, có phiên bản GMS từ 21.30.12 trở lên, Thời lượng pin: 65 giờ sử dụng, Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Watch 2
        insertProduct(db, 
            "Đồng hồ thông minh Xiaomi Watch S1", 
            "2.890.000đ", 
            "5.990.000đ", 
            4.5f, 60, R.drawable.xiaomi_watch_s1,
            "AMOLED, 1.43 inch, 46.5mm, Pin 12-24 ngày, Xiaomi",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.43 inch, Đường kính mặt: 46.5 mm, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Tương thích: Android 6.0 và iOS 10.0 trở lên, Thời lượng pin: 12 ngày ở chế độ bình thường, 24 ngày ở chế độ tiết kiệm pin, Hãng sản xuất: Xiaomi",
            4); // Thêm Xiaomi Watch S1
        insertProduct(db, 
            "Đồng hồ thông minh Garmin Forerunner 165", 
            "4.596.000đ", 
            "6.690.000đ", 
            4.7f, 40, R.drawable.garmin_165,
            "AMOLED, 1.2 inch, 43mm, Không nghe gọi, Pin 11-20 ngày, Garmin",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.2 inch, Đường kính mặt: 43mm, Nghe gọi: Không, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Cảnh báo nhịp tim bất thường, Tính tuổi thể chất, Đo chỉ số năng lượng cơ thể, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Theo dõi chu kỳ, Tương thích: Android, iOS, Thời lượng pin: Chế độ đồng hồ thông minh Lên đến 11 ngày, Chế độ đồng hồ tiết kiệm pin Lên đến 20 ngày, Chế độ chỉ GPS Lên đến 19 giờ, Chế độ GNSS mọi hệ thống Lên đến 17 giờ, Hãng sản xuất: Garmin",
            4); // Thêm Garmin Forerunner 165
        insertProduct(db, 
            "Đồng hồ thông minh Garmin Forerunner 55", 
            "2.734.000đ", 
            "5.290.000đ", 
            4.5f, 35, R.drawable.garmin_55,
            "MIP, 1.04 inch, 42mm, Pin 20 ngày, Garmin",
            "Công nghệ màn hình: MIP, Kích thước màn hình: 1.04 inch, Đường kính mặt: 42 mm, Kích thước cổ tay phù hợp: 12 - 20 cm, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Tính tuổi thể chất, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Tương thích: Android 7.0 trở lên, iOS 14 trở lên, Thời lượng pin: Chế độ thông minh: 20 ngày, Chế độ GPS: 20 giờ, Hãng sản xuất: Garmin",
            4); // Thêm Garmin Forerunner 55
        insertProduct(db, 
            "Đồng hồ thông minh Garmin Forerunner 965", 
            "12.926.000đ", 
            "16.490.000đ", 
            4.8f, 20, R.drawable.garmin_965,
            "AMOLED, 1.4 inch, 47.2mm, 32GB, Pin 23 ngày, Garmin",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.4 inch, Đường kính mặt: 47.2 mm, Bộ nhớ trong: 32 GB, Kích thước cổ tay phù hợp: 13.5 - 20 cm, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Tương thích: Android 6.0 trở lên, iOS 11 trở lên, Thời lượng pin: Chế độ thông minh: 23 ngày, Bật GPS liên tục: 31 giờ, Chế độ GNSS SatIQ: 22 giờ, Chế độ GNSS mọi hệ thống và đa băng tần: 19 giờ, Chế độ GNSS chỉ GPS có nhạc: 10.5 giờ, Hãng sản xuất: Garmin",
            4); // Thêm Garmin Forerunner 965
        insertProduct(db, 
            "Đồng hồ thông minh Garmin Forerunner 165 Music", 
            "6.909.000đ", 
            "7.990.000đ", 
            4.7f, 25, R.drawable.garmin_165_music,
            "AMOLED, 1.2 inch, 43mm, Không nghe gọi, Pin 11-20 ngày, Garmin",
            "Công nghệ màn hình: AMOLED, Kích thước màn hình: 1.2 inch, Đường kính mặt: 43mm, Kích thước cổ tay phù hợp: 12.6 - 20.3 cm, Nghe gọi: Không, Tiện ích sức khoẻ: Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Tính quãng đường chạy, Đo mức độ stress, Phát hiện té ngã, Cảnh báo nhịp tim bất thường, Tính tuổi thể chất, Đo chỉ số năng lượng cơ thể, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Tương thích: Android, iOS, Thời lượng pin: Chế độ đồng hồ thông minh: 11 ngày, Chế độ đồng hồ tiết kiệm pin: 20 ngày, Chế độ chỉ GPS: 19 giờ, Chế độ GNSS mọi hệ thống: 17 giờ, Hãng sản xuất: Garmin",
            4); // Thêm Garmin Forerunner 165 Music
        insertProduct(db, 
            "Đồng hồ thông minh Garmin Fenix 7 Pro", 
            "15.670.000đ", 
            "23.490.000đ", 
            4.9f, 15, R.drawable.garmin_fenix_7,
            "MIP, 1.3 inch, 47mm, Không nghe gọi, Pin 18-57 ngày, Garmin",
            "Công nghệ màn hình: MIP, Kích thước màn hình: 1.3 inch, Đường kính mặt: 47 mm, Kích thước cổ tay phù hợp: 12.5 - 20.8 cm, Nghe gọi: Không, Tiện ích sức khoẻ: Chế độ luyện tập, Theo dõi giấc ngủ, Đo nhịp tim, Đo lượng oxy trong máu, Đếm bước chân, Tính calo tiêu thụ, Đo mức độ stress, Tính tuổi thể chất, Đo VO2 max (Đo lượng tiêu thụ oxy tối đa), Tương thích: Android, iOS, Thời lượng pin: Chế độ thông minh: 18 ngày / 22 ngày với solar, Chế độ tiết kiệm pin: 57 ngày / 173 ngày với solar, Bật GPS liên tục: 57 giờ, Tất cả các hệ thống vệ tinh: 40 giờ, Hãng sản xuất: Garmin",
            4); // Thêm Garmin Fenix 7 Pro
        // --- Sản phẩm điện thoại mới cập nhật ---
        insertProduct(db,
            "Realme C67 8GB 128GB",
            "4.075.500đ",
            "5.290.000đ",
            4.2f, 0, R.drawable.realme_c67, // Sửa tên ảnh nếu cần
            "Snapdragon 685, Camera 108MP, Pin 5000mAh, Màn hình 90Hz",
            "Kích thước màn hình: 6.72 inches, Công nghệ màn hình: IPS LCD, Camera sau: 108MP+2MP, Camera trước: 8MP, Chipset: Snapdragon 685 8 nhân, Công nghệ NFC: Không, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 1080 x 2400 pixels (FullHD+), Tần số quét: 90Hz, Độ sáng: 950 nits, Mặt kính: Panda, Loại CPU: 2.8 GHz",
            1);
        insertProduct(db,
            "Samsung Galaxy A15 LTE 8GB 128GB",
            "2.871.000đ",
            "4.990.000đ",
            4.3f, 0, R.drawable.samsung_a15, // Sửa tên ảnh nếu cần
            "Helio G99, Camera 50MP, Pin 5000mAh, Màn hình Super AMOLED 90Hz",
            "Kích thước màn hình: 6.5 inches, Công nghệ màn hình: Super AMOLED, Camera sau: 50MP+5MP+2MP, Camera trước: 13MP, Chipset: MediaTek Helio G99 8 nhân, Công nghệ NFC: Không, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 1080 x 2340 pixels (FullHD+), Tần số quét: 90Hz, Độ sáng: 800 nits, Mặt kính: Kính cường lực, Loại CPU: 2 GHz",
            1);
        insertProduct(db,
            "Xiaomi Redmi Note 13 Pro 5G",
            "6.790.000đ",
            "9.300.000đ",
            4.5f, 0, R.drawable.redmi_13, // Sửa tên ảnh nếu cần
            "Snapdragon 7s Gen 2, Camera 200MP, Pin 5100mAh, Màn hình AMOLED 120Hz",
            "Kích thước màn hình: 6.67 inches, Công nghệ màn hình: AMOLED, Camera sau: 200MP+8MP+2MP, Camera trước: 16MP, Chipset: Snapdragon 7s Gen 2 8 nhân, Công nghệ NFC: Có, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 5100mAh, Thẻ SIM: Dual nano-SIM hoặc 1 nano-SIM + 1 eSIM, Hệ điều hành: Android 13, Độ phân giải màn hình: 1220 x 2712 pixels, Tần số quét: 120Hz, Độ sáng: 1800 nits, Kính: Corning Gorilla Glass Victus, Loại CPU: 4x2.4 GHz & 4x1.95 GHz",
            1);
        insertProduct(db,
            "Samsung Galaxy S25 Ultra 12GB 256GB",
            "27.780.000đ",
            "33.380.000đ",
            4.9f, 0, R.drawable.samsung_s25_ultral, // Sửa tên ảnh nếu cần
            "Snapdragon 8 Elite, Camera 200MP, Màn hình 120Hz, Pin 5000mAh",
            "Kích thước màn hình: 6.9 inches, Công nghệ màn hình: Dynamic AMOLED 2X, Camera sau: 200MP+50MP+50MP+10MP, Camera trước: 12MP, Chipset: Snapdragon 8 Elite (3nm), Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM + eSIM, Hệ điều hành: Android 15, Độ phân giải màn hình: 3120 x 1440 pixels (Quad HD+), Tần số quét: 120Hz, Độ sáng: 2600 nits, Kính: Corning Gorilla Armor 2",
            1);
        insertProduct(db,
            "Samsung Galaxy M55 12GB 256GB",
            "7.510.600đ",
            "10.990.000đ",
            4.6f, 0, R.drawable.samsung_m55, // Sửa tên ảnh nếu cần
            "Snapdragon 7 Gen 1, Camera 50MP, Pin 5000mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.7 inches, Công nghệ màn hình: Super AMOLED Plus, Camera sau: 50MP+8MP+2MP, Camera trước: 50MP, Chipset: Qualcomm Snapdragon 7 Gen 1 (4nm), Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 1080 x 2400 pixels (FullHD+), Tần số quét: 120Hz, Độ sáng: 1000 nits, Loại CPU: 1x2.4 GHz Cortex-A710 & 3x2.36 GHz Cortex-A710 & 4x1.8 GHz Cortex-A510",
            1);
        // --- Sản phẩm iPhone mới cập nhật ---
        insertProduct(db,
            "iPhone 15 128GB | Chính hãng VN/A",
            "15.413.000đ",
            "19.990.000đ",
            4.8f, 0, R.drawable.iphone_15, // Sửa tên ảnh nếu cần
            "A16 Bionic, Super Retina XDR OLED, Camera 48MP, Dynamic Island",
            "Kích thước màn hình: 6.1 inches, Công nghệ màn hình: Super Retina XDR OLED, Camera sau: 48MP+12MP, Camera trước: 12MP f/1.9, Chipset: Apple A16 Bionic 6 nhân, Công nghệ NFC: Có, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 3349mAh, Thẻ SIM: 2 SIM (nano‑SIM và eSIM), Hệ điều hành: iOS 17, Độ phân giải màn hình: 2556 x 1179 pixels, Dynamic Island, HDR display, True Tone, Wide color (P3), Haptic Touch, Lớp phủ oleophobia chống dấu vân tay, Độ sáng tối đa: 2000 nits, Mặt kính: Ceramic Shield, Tần số quét: 60Hz, Loại CPU: 6 lõi (2 hiệu năng + 4 tiết kiệm điện)",
            1);
        insertProduct(db,
            "iPhone 14 128GB | Chính hãng VN/A",
            "12.527.000đ",
            "14.990.000đ",
            4.7f, 0, R.drawable.iphone_14, // Sửa tên ảnh nếu cần
            "A15 Bionic, OLED, Camera kép 12MP, Ceramic Shield",
            "Kích thước màn hình: 6.1 inches, Công nghệ màn hình: OLED, Camera sau: 12MP f/1.5 + 12MP f/2.4, Camera trước: 12MP f/1.9, Chipset: Apple A15 Bionic 6 nhân, Công nghệ NFC: Có, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 3279mAh, Thẻ SIM: 2 SIM (nano‑SIM và eSIM), Hệ điều hành: iOS 16, Độ phân giải màn hình: 2532 x 1170 pixels, Tần số quét: 60Hz, Độ sáng: 1200 nits, Kính: Ceramic Shield, Super Retina XDR, Loại CPU: 3.22 GHz",
            1);
        insertProduct(db,
            "iPhone 14 Pro Max 128GB | Chính hãng VN/A",
            "25.462.000đ",
            "29.990.000đ",
            4.9f, 0, R.drawable.iphone_14_pro_max, // Sửa tên ảnh nếu cần
            "A16 Bionic, Super Retina XDR OLED, Camera 48MP, 120Hz, Always-On",
            "Kích thước màn hình: 6.7 inches, Công nghệ màn hình: Super Retina XDR OLED, Camera sau: 48MP f/1.8 + 12MP f/2.2 + 12MP f/2.8 + TOF 3D LiDAR, Camera trước: 12MP f/1.9, Chipset: Apple A16 Bionic 6-core, Công nghệ NFC: Có, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 4323mAh, Thẻ SIM: 2 SIM (nano‑SIM và eSIM), Hệ điều hành: iOS 16, Độ phân giải màn hình: 2796 x 1290 pixels, Tần số quét: 120Hz, Always-On display, HDR, True Tone, Haptic Touch, Tỷ lệ tương phản: 2,000,000:1, Độ sáng tối đa: 2000 nits, Loại CPU: 6 lõi (2 hiệu năng + 4 tiết kiệm điện)",
            1);
        insertProduct(db,
            "iPhone 16 128GB | Chính hãng VN/A",
            "18.796.000đ",
            "22.990.000đ",
            5.0f, 0, R.drawable.iphone_16, // Sửa tên ảnh nếu cần
            "A18, Super Retina XDR OLED, Camera 48MP, Dynamic Island",
            "Kích thước màn hình: 6.1 inches, Công nghệ màn hình: Super Retina XDR OLED, Camera sau: 48MP f/1.6 + 12MP f/2.2, Camera trước: 12MP f/1.9, Chipset: Apple A18, Công nghệ NFC: Có, RAM: Không công bố, Bộ nhớ trong: 128GB, Pin: Không công bố, Thẻ SIM: Sim kép (nano-Sim và e-Sim, hỗ trợ 2 e-Sim), Hệ điều hành: iOS 18, Độ phân giải màn hình: 2556 x 1179 pixels, Dynamic Island, HDR, True Tone, Dải màu rộng (P3), Haptic Touch, Tỷ lệ tương phản: 2.000.000:1, Độ sáng tối đa: 1000 nit, 460 ppi, Lớp phủ kháng dầu, Hỗ trợ đa ngôn ngữ, Loại CPU: 6 lõi (2 hiệu năng + 4 tiết kiệm điện)",
            1);
        // --- Sản phẩm Xiaomi mới cập nhật ---
        insertProduct(db,
            "Xiaomi 14T Pro 12GB 512GB",
            "14.590.000đ",
            "17.670.000đ",
            4.7f, 0, R.drawable.xiaomi14ultra, // Sửa tên ảnh nếu cần
            "Dimensity 9300+, Camera 50MP, Màn hình 144Hz, Pin 5000mAh",
            "Kích thước màn hình: 6.67 inches, Công nghệ màn hình: AMOLED, Camera sau: 50MP+12MP+50MP, Camera trước: 32MP f/2.0, Chipset: MediaTek Dimensity 9300+, Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 512GB, Pin: 5000mAh, Thẻ SIM: Sim kép (nano-Sim và e-Sim, hỗ trợ 2 e-Sim), Độ phân giải màn hình: 1220 x 2712 pixels, Tần số quét: 144Hz, 446ppi, Độ sáng: 4000 nits, Độ sâu màu: 12 bit, Làm mờ PWM 3840Hz, HDR10+, Dolby Vision, Mặt sau cong 3D, Loại CPU: 1x Cortex-X4 3.4GHz + 3x Cortex-X4 2.85GHz + 4x Cortex-A720 2.0GHz",
            1);
        insertProduct(db,
            "Xiaomi POCO C71 4GB 128GB",
            "2.707.500đ",
            "3.150.000đ",
            4.2f, 0, R.drawable.xiaomi_poco_c71, // Sửa tên ảnh nếu cần
            "Unisoc T7250, Camera 32MP, Pin 5200mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.88 inches, Công nghệ màn hình: IPS LCD, Camera sau: 32MP f/1.8, Camera trước: 8MP f/2.0, Chipset: Unisoc T7250 (12nm), Công nghệ NFC: Không, RAM: 4GB, Bộ nhớ trong: 128GB, Pin: 5200mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 15, HyperOS, Độ phân giải màn hình: 720 x 1640 pixels, Tần số quét: 120Hz, Độ sáng: 450 nits, HD+, ~260ppi, 16 triệu màu, Loại CPU: 2x1.8 GHz Cortex-A75 + 6x1.6 GHz Cortex-A55",
            1);
        insertProduct(db,
            "Xiaomi Redmi Note 14 Pro Plus 5G 8GB 256GB",
            "9.300.000đ",
            "10.800.000đ",
            4.6f, 0, R.drawable.redmi_note14, // Sửa tên ảnh nếu cần
            "Snapdragon 7s Gen 3, Camera 200MP, Màn hình 120Hz, Pin 5110mAh",
            "Kích thước màn hình: 6.67 inches, Công nghệ màn hình: AMOLED, Camera sau: 200MP OIS f/1.65 + 8MP f/2.2 + 2MP f/2.4, Camera trước: f/2.2, Chipset: Snapdragon 7s Gen 3, Công nghệ NFC: Có, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 5110mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 2712 x 1220 pixels, Tần số quét: 120Hz, Độ sáng: 3000 nits, Độ sâu màu: 12-bit, Tỷ lệ tương phản: 5,000,000:1, Loại CPU: 8 nhân 2.5GHz",
            1);
        insertProduct(db,
            "Xiaomi 15 5G 12GB 256GB",
            "22.240.000đ",
            "24.540.000đ",
            4.9f, 0, R.drawable.xiaomi_15, // Sửa tên ảnh nếu cần
            "Snapdragon 8 Elite, Camera Leica 50MP, CrystalRes AMOLED, Pin 5240mAh",
            "Kích thước màn hình: 6.36 inches, Công nghệ màn hình: CrystalRes AMOLED, Camera sau: 50MP Leica f/1.62 OIS + 50MP Leica f/2.0 OIS + 50MP Leica f/2.2 115°, Camera trước: 32MP f/2.0 90° 21mm, Chipset: Snapdragon 8 Elite (3nm), Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 5240mAh, Thẻ SIM: Sim kép (nano-Sim và e-Sim, hỗ trợ 2 e-Sim), Hệ điều hành: Xiaomi HyperOS 2, Độ phân giải màn hình: 2670 x 1200 pixels, Độ sáng: 3200 nits, Tần số quét: 1-120Hz tự động, Tốc độ phản hồi cảm ứng: 300Hz, 68 tỷ màu, Dải màu DCI-P3, Dolby Vision, HDR10+, HDR10, Độ sáng DC, Công nghệ cảm ứng ướt, Loại CPU: 2x Lõi Prime 4.32GHz + 6x Lõi hiệu suất 3.53GHz",
            1);

        // --- Sản phẩm OPPO mới cập nhật ---
        insertProduct(db,
            "OPPO Find X8 16GB 512GB",
            "21.380.000đ",
            "22.990.000đ",
            4.8f, 0, R.drawable.oppo_find_x8, // Sửa tên ảnh nếu cần
            "Dimensity 9400, RAM 16GB, Camera 50MP bộ ba, Pin 5630mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.59 inches, Công nghệ màn hình: AMOLED, Camera sau: 50MP OIS (Chính) + 50MP (Chân dung Tele) + 50MP (Góc rộng), Camera trước: 32MP f/2.4, Chipset: MediaTek Dimensity 9400, Công nghệ NFC: Có, RAM: 16GB, Bộ nhớ trong: 512GB, Pin: 5630mAh, Thẻ SIM: Dual nano-SIM hoặc 1 nano-SIM + 1 eSIM, Hệ điều hành: ColorOS 15 (Android 15), Độ phân giải màn hình: 1256 x 2760 pixels, Tần số quét: 120Hz, Kính: Gorilla Glass 7i, Độ sáng: 1600 nits, 1 tỷ màu, Loại CPU: 8 nhân",
            1);
        insertProduct(db,
            "OPPO Reno14 5G 12GB 256GB",
            "15.500.000đ",
            "15.990.000đ",
            4.7f, 0, R.drawable.oppo_reno_14, // Sửa tên ảnh nếu cần
            "Dimensity 8350, RAM 12GB, Camera 50MP bộ ba, Pin 6000mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.59 inches, Công nghệ màn hình: AMOLED, Camera sau: 50MP OIS f/1.8 + 50MP OIS f/2.8 + 8MP f/2.2, Camera trước: 50MP f/2.0, Chipset: MediaTek Dimensity 8350 5G 3.35GHz, Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 6000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: ColorOS 15 (Android 15), Độ phân giải màn hình: 1256 x 2760 pixels, Tần số quét: 120Hz, Độ sáng: 1200 nits, Kính: Gorilla Glass 7i, Loại CPU: 8 nhân 3.35GHz",
            1);
        insertProduct(db,
            "OPPO Reno12 5G 12GB 256GB",
            "8.790.000đ",
            "12.990.000đ",
            4.6f, 0, R.drawable.oppo_reno_12, // Sửa tên ảnh nếu cần
            "Dimensity 7300, RAM 12GB, Camera 50MP, Pin 5000mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.7 inches, Công nghệ màn hình: AMOLED, Camera sau: 50MP OIS + 8MP + 2MP, Camera trước: 32MP, Chipset: MediaTek Dimensity 7300, Công nghệ NFC: Có, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Hệ điều hành: Android 14, Tần số quét: 120Hz",
            1);
        insertProduct(db,
            "OPPO A79 5G 8GB 256GB",
            "6.660.000đ",
            "7.490.000đ",
            4.3f, 0, R.drawable.oppo_a79, // Sửa tên ảnh nếu cần
            "Dimensity 6020, RAM 8GB, Camera 50MP, Pin 5000mAh, Màn hình 90Hz",
            "Kích thước màn hình: 6.72 inches, Camera sau: 50MP f/1.8 5P + 2MP f/2.4 3P, Camera trước: 8MP f/2.0 4P, Chipset: MediaTek Dimensity 6020 8 nhân, Công nghệ NFC: Có, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 13, Độ phân giải màn hình: 1080 x 2400 pixels (FullHD+), Tần số quét: 90Hz, 391 PPI, Loại CPU: 2x2.2 GHz & 6x2.0 GHz",
            1);
        insertProduct(db,
            "OPPO Reno12 F 5G 8GB 256GB",
            "6.920.000đ",
            "9.490.000đ",
            4.4f, 0, R.drawable.oppo_reno_12f, // Sửa tên ảnh nếu cần
            "Dimensity 6300, RAM 8GB, Camera 50MP, Pin 5000mAh, Màn hình 120Hz",
            "Kích thước màn hình: 6.7 inches, Công nghệ màn hình: AMOLED, Camera sau: 50MP + 8MP + 2MP, Camera trước: 32MP, Chipset: MediaTek Dimensity 6300, Công nghệ NFC: Có, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Hệ điều hành: Android 14, Tần số quét: 120Hz",
            1);
        insertProduct(db,
            "OPPO Find N3 16GB 512GB",
            "29.290.000đ",
            "44.990.000đ",
            4.9f, 0, R.drawable.oppo_find_n3, // Sửa tên ảnh nếu cần
            "Snapdragon 8 Gen 2, RAM 16GB, Camera 48+48+64MP, Màn hình gập 7.82 inch 2K+ 120Hz",
            "Kích thước màn hình: 7.82 inches, Công nghệ màn hình: OLED, Camera sau: 48MP f/1.7 OIS + 48MP f/2.2 + 64MP f/2.6, Camera trước: 20MP f/2.2 (chính) + 32MP f/2.4 (ngoài), Chipset: Snapdragon 8 Gen 2 8 nhân, Công nghệ NFC: Có, RAM: 16GB, Bộ nhớ trong: 512GB, Pin: 4805mAh, Thẻ SIM: Nano-SIM + eSIM, Hệ điều hành: Android 13, Độ phân giải màn hình: 2268 x 2440 pixels, Màn hình chính: 7.82 inch 2K+ OLED 120Hz 2800 nit 1 tỷ màu Kính UTG, Màn hình phụ: 6.31 inch FHD+ 120Hz Kính Nanocrystal, Loại CPU: 1x3.2 GHz Cortex-X3 & 2x2.8 GHz Cortex-A715 & 2x2.8 GHz Cortex-A710 & 3x2.0 GHz Cortex-A510",
            1);

        // --- Sản phẩm realme mới cập nhật ---
        insertProduct(db,
            "realme 13+ 5G 8GB 256GB",
            "6.260.500đ",
            "9.490.000đ",
            4.5f, 0, R.drawable.realme_13, // Sửa tên ảnh nếu cần
            "Dimensity 7300 Energy 5G, RAM 8GB, Camera 50MP OIS, Màn hình OLED 120Hz",
            "Kích thước màn hình: 6.67 inches, Công nghệ màn hình: OLED, Camera sau: 50MP 882 OIS + 2MP + Flicker, Camera trước: 16MP f/2.4, Chipset: Dimensity 7300 Energy 5G, Công nghệ NFC: Có, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 1080 x 2400 pixels (FullHD+), 16.7 triệu màu, 2000nits, 120Hz, AGC DT STAR2, Loại CPU: 4*A78 @2.5 GHz, 4*A55 @2.5 GHz",
            1);
        insertProduct(db,
            "realme C61 6GB 128GB",
            "3.505.500đ",
            "3.990.000đ",
            4.2f, 0, R.drawable.realme_c61, // Sửa tên ảnh nếu cần
            "Unisoc Tiger T612, RAM 6GB, Camera 50MP, Pin 5000mAh, Màn hình 90Hz",
            "Kích thước màn hình: 6.745 inches, Công nghệ màn hình: IPS LCD, Camera sau: 50MP f/1.8 1/2.5\" + 0.08MP, Camera trước: 5MP, Chipset: Unisoc Tiger T612, Công nghệ NFC: Không, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 14, Độ phân giải màn hình: 720 x 1600 pixel, Tốc độ làm tươi: 90Hz, 560nits, 16.7 triệu màu, Kính Panda MN228, Loại CPU: 2x1.8 GHz Cortex-A75 + 6x1.8 GHz Cortex-A55",
            1);
        insertProduct(db,
            "realme Note 60x 3GB 64GB",
            "2.555.500đ",
            "2.690.000đ",
            4.0f, 0, R.drawable.realme_60xx, // Sửa tên ảnh nếu cần
            "Unisoc T612, RAM 3GB, Camera 8MP, Pin 5000mAh, Màn hình 90Hz",
            "Kích thước màn hình: 6.74 inches, Công nghệ màn hình: LCD, Camera sau: 8MP, Camera trước: 5MP, Chipset: Unisoc T612, Công nghệ NFC: Không, RAM: 3GB, Bộ nhớ trong: 64GB, Pin: 5000mAh, Tần số quét: 90Hz",
            1);
        insertProduct(db,
            "Realme 9 4G 8GB 128GB",
            "6.640.000đ",
            "6.990.000đ",
            4.4f, 0, R.drawable.realme_9, // Sửa tên ảnh nếu cần
            "Snapdragon 680, RAM 8GB, Camera 108MP, Pin 5000mAh, Màn hình Super AMOLED 90Hz",
            "Kích thước màn hình: 6.4 inches, Công nghệ màn hình: Super AMOLED, Camera sau: 108MP + 8MP + 2MP, Camera trước: 16MP, Chipset: Snapdragon 680 8 nhân, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 5000mAh, Thẻ SIM: 2 Nano-SIM, Hệ điều hành: Android 12, Độ phân giải màn hình: 1080 x 2400 pixels (FullHD+), Tần số quét: 90Hz, Độ sáng: 1000 nits, Kính: Corning Gorilla Glass 5, Loại CPU: 4x2.4 GHz + 4x1.9 GHz",
            1);
        // --- Sản phẩm laptop mới cập nhật ---
        insertProduct(db,
            "Apple MacBook Air M2 2024 8CPU 8GPU 16GB 256GB",
            "19.290.000đ",
            "24.990.000đ",
            4.8f, 0, R.drawable.macbook_air_m2, // Sửa tên ảnh nếu cần
            "Apple M2 8CPU 8GPU, RAM 16GB, SSD 256GB, Liquid Retina, 13.6 inch",
            "Loại card đồ họa: 8 nhân GPU, 16 nhân Neural Engine, Dung lượng RAM: 16GB, Ổ cứng: 256GB SSD, Kích thước màn hình: 13.6 inches, Công nghệ màn hình: Liquid Retina Display, Pin: 52,6 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 2560 x 1664 pixels, Loại CPU: Apple M2 8 nhân, Cổng giao tiếp: 2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3",
            3);
        insertProduct(db,
            "MacBook Air M4 13 inch 2025 10CPU 8GPU 16GB 256GB",
            "24.490.000đ",
            "26.990.000đ",
            4.9f, 0, R.drawable.macbook_air_m4, // Sửa tên ảnh nếu cần
            "Apple M4 10CPU 8GPU, RAM 16GB, SSD 256GB, Liquid Retina, 13.6 inch",
            "Loại card đồ họa: GPU 8 lõi, Neural Engine 16 lõi, Công nghệ dò tia tốc độ cao, Băng thông bộ nhớ 120GB/s, Dung lượng RAM: 16GB, Ổ cứng: 256GB SSD, Kích thước màn hình: 13.6 inches, Công nghệ màn hình: Liquid Retina, LED, 224ppi, 500 nit, 1 tỷ màu, Dải màu rộng (P3), True Tone, Pin: 53.8 Wh, Hệ điều hành: macOS, Độ phân giải màn hình: 2560 x 1664 pixels, Loại CPU: 10 lõi (4 hiệu năng + 6 tiết kiệm điện), Cổng giao tiếp: MagSafe 3, Jack 3.5mm, 2 x Thunderbolt 4 (USB-C)",
            3);
        insertProduct(db,
            "MacBook Pro 14 M4 10CPU 10GPU 16GB 512GB",
            "37.490.000đ",
            "39.990.000đ",
            4.9f, 0, R.drawable.macbook_pro_m4, // Sửa tên ảnh nếu cần
            "Apple M4 10CPU 10GPU, RAM 16GB, SSD 512GB, Liquid Retina XDR, 14.2 inch",
            "Loại card đồ họa: 10 lõi, Neural Engine 16 lõi, Dung lượng RAM: 16GB, Ổ cứng: 512GB SSD, Kích thước màn hình: 14.2 inches, Công nghệ màn hình: Liquid Retina XDR, XDR 1000 nit, HDR 1600 nit, 1 tỷ màu, Dải màu rộng (P3), True Tone, 2 màn hình ngoài, Pin: 72.4 Wh, Hệ điều hành: macOS, Độ phân giải màn hình: 3024 x 1964 pixels, Loại CPU: Apple M4 10 lõi (4 hiệu năng + 6 tiết kiệm điện), Cổng giao tiếp: SDXC, HDMI, Jack 3.5mm, MagSafe 3, 3 x Thunderbolt 4 (USB‑C)",
            3);
        insertProduct(db,
            "MacBook Air M3 13 inch 2024 8CPU 10GPU 16GB 512GB",
            "27.990.000đ",
            "32.990.000đ",
            4.8f, 0, R.drawable.macbook_air_m2, // Sửa tên ảnh nếu cần
            "Apple M3 8CPU 10GPU, RAM 16GB, SSD 512GB, True Tone, 13.6 inch",
            "Loại card đồ họa: 10 lõi, Dung lượng RAM: 16GB, Ổ cứng: 512GB SSD, Kích thước màn hình: 13.6 inches, Công nghệ màn hình: True Tone, Hệ điều hành: MacOS, Loại CPU: Apple M3 8 lõi, Neural Engine 16 lõi, Cổng giao tiếp: MagSafe 3, 2 x Thunderbolt / USB 4",
            3);
        insertProduct(db,
            "Laptop ASUS Zenbook S 14 OLED UX5406SA-PV140WS",
            "38.790.000đ",
            "44.990.000đ",
            4.9f, 0, R.drawable.asus_rog_strix, // Sửa tên ảnh nếu cần
            "Intel Core Ultra 7-258V, AI Boost, RAM 32GB, SSD 1TB, OLED 14 inch",
            "Chip AI: Intel AI Boost NPU 47 TOPs, Tổng AI 120 TOPS, Loại card đồ họa: Intel Arc Graphics, Dung lượng RAM: 32GB LPDDR5X Onboard, Ổ cứng: 1TB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: 0.2ms, 500nits HDR, 100% DCI-P3, HDR True Black 500, 1.07 tỷ màu, Giảm ánh sáng xanh 70%, TÜV Rheinland, SGS Eye Care, Pin: 72WHrs, 2S2P, 4-cell Li-ion, Hệ điều hành: Windows 11 Home + Office, Độ phân giải: 2880 x 1800 pixels, Loại CPU: Intel Core Ultra 7-258V 1.8 GHz (12MB Cache, up to 4.8 GHz, 8 lõi, 8 luồng), Cổng giao tiếp: 1x USB 3.2 Gen 2 Type-A, 2x Thunderbolt 4, 1x HDMI 2.1 TMDS, 1x 3.5mm Combo Audio Jack",
            3);
        insertProduct(db,
            "Laptop ASUS Zenbook 14 UM3406KA-PP113WS",
            "25.990.000đ",
            "28.990.000đ",
            4.7f, 0, R.drawable.asus_rog_strix, // Sửa tên ảnh nếu cần
            "AMD Ryzen AI 7 350, AI XDNA, RAM 16GB, SSD 512GB, OLED 14 inch",
            "Chip AI: AMD XDNA NPU 50TOPS, Loại card đồ họa: AMD Radeon Graphics, Dung lượng RAM: 16GB LPDDR5X Onboard, Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: 400nits, HDR 600nits, 100% DCI-P3, HDR True Black 600, 1.07 tỷ màu, Giảm ánh sáng xanh 70%, TÜV Rheinland, SGS Eye Care, Pin: 75WHrs, 4S1P, 4-cell Li-ion, Hệ điều hành: Windows 11 Home + Office, Độ phân giải: 2880 x 1800 pixels, Loại CPU: AMD Ryzen AI 7 350 2.0GHz (24MB Cache, up to 5.0GHz, 8 lõi, 16 luồng), Cổng giao tiếp: 1x USB 3.2 Gen 1 Type-A, 1x USB 3.2 Gen 2 Type-C, 1x USB 4.0 Gen 3 Type-C, 1x HDMI 2.1 TMDS, 1x 3.5mm Combo Audio Jack",
            3);
        insertProduct(db,
            "Laptop ASUS Expertbook B1402CBA-NK2669W",
            "12.891.300đ",
            "19.990.000đ",
            4.5f, 0, R.drawable.asus_rog_strix, // Sửa tên ảnh nếu cần
            "Intel Core i5-1235U, RAM 16GB, SSD 512GB, 14 inch, Win 11",
            "Loại card đồ họa: Intel UHD Graphics, Dung lượng RAM: 16GB DDR4 Onboard, Ổ cứng: 512GB M.2 2280 NVMe PCIe 4.0 SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: 250 nits, NTSC 45%, Chống chói, Tương phản 500:1, Pin: 42WHrs, 3S1P, 3-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1235U (1.7 GHz, 12M Cache, up to 4.4 GHz, 12 lõi), Cổng giao tiếp: 1x USB 2.0 Type-A, 1x USB 3.2 Gen 1 Type-A, 1x USB 3.2 Gen 1 Type-C (Sạc), 1x USB 3.2 Gen 2 Type-C (display / Sạc), 1x HDMI 1.4, 1x 3.5mm Combo Audio Jack, 1x RJ45 Gigabit Ethernet",
            3);
        // --- Sản phẩm laptop HP và Dell mới cập nhật ---
        insertProduct(db,
            "Laptop HP 245 G10 B8PF8AT",
            "11.339.300đ",
            "15.590.000đ",
            4.5f, 0, R.drawable.hp_gaming_15, // Sửa tên ảnh nếu cần
            "Ryzen 5 7530U, RAM 16GB, SSD 256GB, Radeon Graphics, 14 inch",
            "Loại card đồ họa: AMD Radeon Graphics, Dung lượng RAM: 16GB DDR4-3200 (1 x 16 GB), Ổ cứng: 256GB PCIe NVMe SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: 45% NTSC, 250 nits, Chống chói, Pin: 41 Wh Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: AMD Ryzen 5 7530U (up to 4.5 GHz, 16 MB L3, 6 lõi, 12 luồng), Cổng giao tiếp: 1x USB Type-C 5Gbps, 2x USB Type-A 5Gbps, 1x AC, 1x HDMI 1.4b, 1x Headphone/mic combo",
            3);
        insertProduct(db,
            "Laptop HP 14-EP0112TU 8C5L1PA",
            "14.734.300đ",
            "19.590.000đ",
            4.6f, 0, R.drawable.hp_gaming_15, // Sửa tên ảnh nếu cần
            "Core i5-1335U, RAM 16GB, SSD 512GB, Iris Xe, 14 inch",
            "Loại card đồ họa: Intel Iris Xe Graphics, Dung lượng RAM: 16GB DDR4 3200MHz (2 khe 8GB), Ổ cứng: 512GB PCIe NVMe M.2 SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: Chống chói, 250 nits, 45% NTSC, Pin: 41 Wh Li-ion, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1335U (4.60 GHz, 10 lõi / 12 luồng, 12MB), Cổng giao tiếp: 1x USB Type-C, 2x USB Type-A, 1x AC smart pin, 1x HDMI 1.4b, 1x Headphone/mic combo",
            3);
        insertProduct(db,
            "Laptop HP Victus 16-R0376TX AY8Z2PA",
            "23.490.000đ",
            "32.690.000đ",
            4.8f, 0, R.drawable.hp_gaming_15, // Sửa tên ảnh nếu cần
            "Core i7-13700HX, RTX 3050, RAM 16GB, SSD 512GB, 16.1 inch",
            "Loại card đồ họa: NVIDIA GeForce RTX 3050 6GB, Dung lượng RAM: 16GB DDR5-4800 (2x8GB), Ổ cứng: 512GB PCIe Gen4 NVMe M.2 SSD, Kích thước màn hình: 16.1 inches, Công nghệ màn hình: 100% sRGB, 300 nits, 7ms, Ánh sáng xanh thấp, Pin: 70 Wh Li-ion polymer, Sạc nhanh 50%/30p, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i7-13700HX (up to 5.0 GHz, 30 MB L3, 16 lõi, 24 luồng), Cổng giao tiếp: 1x USB Type-A (Sleep/Charge), 1x USB Type-C (sạc nhanh, DP 1.4, Sleep/Charge), 2x USB Type-A, 1x HDMI 2.1, 1x RJ-45, 1x Headphone/mic combo",
            3);
        insertProduct(db,
            "Laptop HP Pavilion X360 14-EK2017TU 9Z2V5PA",
            "20.790.000đ",
            "24.990.000đ",
            4.7f, 0, R.drawable.hp_pavilion, // Sửa tên ảnh nếu cần
            "Core 5 120U, RAM 16GB, SSD 512GB, 14 inch, X360",
            "Dung lượng RAM: 16GB DDR4 3200MHz Onboard, Ổ cứng: 512GB SSD PCIe (M.2 2280), Kích thước màn hình: 14 inches, Pin: 43.3 Wh, Hệ điều hành: Windows 11 Home SL 64-bit, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core 5 120U (10 lõi, Turbo 5.00 GHz), Cổng giao tiếp: 2x USB 3.2 Gen 1 Type-A, 1x USB 3.2 Gen 2 Type-C (PD/DP/Sleep&Charge), 1x HDMI, 1x Headphone/mic combo",
            3);
        insertProduct(db,
            "Laptop Dell Inspiron 15 3520-5810BLK 102F0",
            "11.397.500đ",
            "13.990.000đ",
            4.4f, 0, R.drawable.dell_inspiron_15, // Sửa tên ảnh nếu cần
            "Core i5-1155G7, RAM 8GB, SSD 256GB, Iris Xe, 15.6 inch",
            "Loại card đồ họa: Intel Iris Xe Graphics, Dung lượng RAM: 8GB DDR4 2666MHz (2 khe SODIMM), Ổ cứng: 256GB, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: 250 nits, 45% NTSC, Chống chói, Pin: lithium-polymer, Hệ điều hành: Windows 11, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1155G7 (2.5-4.5 GHz, 4 lõi/8 luồng, 8MB), Cổng giao tiếp: 1x nguồn, 1x HDMI 1.4, 2x USB 3.2 Gen 1, 1x SD, 1x USB 2.0, 1x Headphone",
            3);
        insertProduct(db,
            "Laptop Dell Inspiron 15 3520 6R6NK V2 - Nhập khẩu chính hãng",
            "13.570.300đ",
            "14.490.000đ",
            4.5f, 0, R.drawable.dell_inspiron_15, // Sửa tên ảnh nếu cần
            "Core i5-1235U, RAM 16GB, SSD 512GB, UHD Graphics, 15.6 inch",
            "Loại card đồ họa: Intel UHD Graphics, Dung lượng RAM: 16GB DDR4 (2 khe), Ổ cứng: 512GB PCIE (1 Slot HDD tối đa 1TB), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: 250 nits, 45% NTSC, Chống chói, Pin: 41 Wh lithium-polymer, Hệ điều hành: Windows 11 Home, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1235U (1.3-4.4 GHz, 10 lõi/12 luồng, 12MB), Cổng giao tiếp: 1x nguồn, 1x HDMI 1.4, 2x USB 3.2 Gen 1, 1x SD, 1x USB 2.0, 1x Headphone",
            3);
        insertProduct(db,
            "Laptop Dell Latitude 3540 71038100",
            "16.092.300đ",
            "18.990.000đ",
            4.6f, 0, R.drawable.dell_latitude, // Sửa tên ảnh nếu cần
            "Core i5-1235U, RAM 16GB, SSD 512GB, Iris Xe, 15.6 inch",
            "Loại card đồ họa: Intel Iris Xe Graphics, Dung lượng RAM: 16GB DDR4 3200 (2x8GB, tối đa 64GB), Ổ cứng: 512GB M.2 2230 QLC PCIe Gen 4 NVMe, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Chống chói, 250 nit, 45% NTSC, Pin: 42 Wh, sạc nhanh, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1235U (12MB, 10 lõi, 12 luồng, up to 4.40 GHz), Cổng giao tiếp: 1x RJ45, 1x USB 3.2 Gen 2 Type-C, 1x USB 3.2 Gen 1 PowerShare, 2x USB 3.2 Gen 1, 1x Headphone, 1x HDMI 1.4, 1x microSIM, 1x khóa hình nêm",
            3);
        insertProduct(db,
            "Laptop Dell Vostro 3530 2H1TPI5 - Nhập khẩu chính hãng",
            "13.473.300đ",
            "18.990.000đ",
            4.5f, 0, R.drawable.dell_vostro_3530, // Sửa tên ảnh nếu cần
            "Core i5-1334U, RAM 16GB, SSD 512GB, UHD Graphics, 15.6 inch",
            "Loại card đồ họa: Intel UHD Graphics, Dung lượng RAM: 16GB DDR4 2666MHz (2x8GB, tối đa 16GB), Ổ cứng: 512GB SSD (1 Slot SSD tối đa 1TB), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: WVA, 45% NTSC, Chống chói, Pin: 41Wh 3Cell, Hệ điều hành: Free OS, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-1334U (10 nhân 12 luồng, 0.9-4.6 GHz, 12MB), Cổng giao tiếp: 1x nguồn, 1x HDMI 1.4, 1x USB 3.2 Gen 1, 1x SD, 1x USB 2.0, 1x Headphone 3.5mm, 1x Lan, 1x Type C",
            3);
        // --- Sản phẩm laptop Lenovo mới cập nhật ---
        insertProduct(db,
            "Laptop Lenovo LOQ 15ARP9 83JC007HVN",
            "18.990.000đ",
            "23.490.000đ",
            4.7f, 0, R.drawable.lenovo_loq_15arp9, // Sửa tên ảnh nếu cần
            "Ryzen 5 7235HS, RTX 3050 6GB, RAM 12GB, SSD 512GB, 15.6 inch",
            "Loại card đồ họa: NVIDIA GeForce RTX 3050 6GB GDDR6, Boost 1732MHz, TGP 95W, Dung lượng RAM: 12GB DDR5-4800 (2 khe, nâng cấp 32GB), Ổ cứng: 512GB SSD M.2 2242 PCIe 4.0x4 NVMe (2x M.2 SSD, tối đa 1TB), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: 300nits, Chống chói, 100% sRGB, G-SYNC, Pin: 60Wh, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: AMD Ryzen 5 7235HS (4C/8T, 3.2/4.2GHz, 2MB L2/8MB L3), Cổng giao tiếp: 3x USB-A, 1x USB-C (PD 140W, DP 1.4), 1x HDMI 2.1, 1x Headphone/mic, 1x Ethernet, 1x nguồn",
            3);
        insertProduct(db,
            "Laptop Lenovo IdeaPad Slim 5 OLED 15ARP10 83J3002SVN",
            "21.490.000đ",
            "23.090.000đ",
            4.8f, 0, R.drawable.lenovo_ideapad_slim_5, // Sửa tên ảnh nếu cần
            "Ryzen 7 7735HS, Radeon 680M, RAM 32GB, SSD 512GB, OLED 15.1 inch",
            "Loại card đồ họa: AMD Radeon 680M, Dung lượng RAM: 32GB LPDDR5x-6400 Onboard, Ổ cứng: 512GB SSD M.2 2242 PCIe 4.0x4 NVMe (2x M.2 SSD, tối đa 1TB), Kích thước màn hình: 15.1 inches, Công nghệ màn hình: OLED, HDR True Black 600, 100% DCI-P3, 500nits, Pin: 70Wh, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 2560 x 1600 (WQXGA), Loại CPU: AMD Ryzen 7 7735HS (8C/16T, 3.2/4.75GHz, 4MB L2/16MB L3), Cổng giao tiếp: 2x USB-A, 2x USB-C (PD 3.0, DP 1.4), 1x HDMI 2.1, 1x Headphone/mic, 1x microSD",
            3);
        insertProduct(db,
            "Laptop Lenovo IdeaPad Slim 3 15ABR8 82XM00MDVN",
            "11.727.300đ",
            "14.290.000đ",
            4.5f, 0, R.drawable.lenovo_slim3, // Sửa tên ảnh nếu cần
            "Ryzen 5 5625U, Radeon Graphics, RAM 16GB, SSD 512GB, 15.6 inch",
            "Loại card đồ họa: AMD Radeon Graphics, Dung lượng RAM: 16GB DDR4-3200 Onboard, Ổ cứng: 512GB SSD M.2 2242 PCIe 4.0x4 NVMe (1 ổ, tối đa 1TB), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: 300nits, Chống chói, Pin: 47Wh, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1080 pixels (FullHD), Loại CPU: AMD Ryzen 5 5625U (6C/12T, 2.3/4.3GHz, 3MB L2/16MB L3), Cổng giao tiếp: 2x USB 3.2 Gen 1, 1x USB-C 3.2 Gen 1 (PD, DP 1.2), 1x HDMI 1.4, 1x Headphone/mic, 1x đầu đọc thẻ, 1x nguồn",
            3);
        insertProduct(db,
            "Laptop Lenovo IdeaPad Slim 5 14Q8X9 83HL000KVN",
            "18.490.000đ",
            "24.990.000đ",
            4.7f, 0, R.drawable.lenovo_ideapad_slim_5, // Sửa tên ảnh nếu cần
            "Snapdragon X Plus, Adreno, RAM 16GB, SSD 512GB, 14 inch, AI NPU",
            "Chip AI: Qualcomm Hexagon NPU 45TOPS, Loại card đồ họa: Qualcomm Adreno, Dung lượng RAM: 16GB LPDDR5x-8448 Onboard, Ổ cứng: 512GB SSD M.2 2242 PCIe 4.0x4 NVMe (1 ổ, tối đa 1TB), Kích thước màn hình: 14 inches, Công nghệ màn hình: 400nits, 100% DCI-P3, Pin: 57Wh, Hệ điều hành: Windows 11 Home SL, Độ phân giải: 1920 x 1200 pixels (WUXGA), Loại CPU: Snapdragon X Plus X1P-42-100, 8C, Turbo 3.4GHz/3.2GHz, 30MB, Cổng giao tiếp: 2x USB-A, 2x USB-C (PD 3.0, DP 1.4), 1x HDMI 2.1, 1x Headphone/mic, 1x microSD",
            3);
        // --- Thêm sản phẩm tai nghe mới ---
        insertProduct(db,
            "Tai nghe Bluetooth True Wireless HUAWEI FreeClip",
            "3.322.000đ",
            "4.990.000đ",
            4.7f, 0, R.drawable.huawei_true_wireless,
            "Pin 8h (tai nghe), 36h (hộp sạc), IP54, Cảm ứng chạm",
            "Kích thước tai nghe: 25.3x22x26.7mm, Hộp sạc: 27.35x51.95x59.7mm, Trọng lượng: 5.6g (tai nghe), 45.5g (hộp sạc), Micro: Có, Phương thức điều khiển: Cảm ứng chạm, Hãng: Huawei",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth Apple AirPods Pro 2 2023 USB-C",
            "5.282.000đ",
            "6.190.000đ",
            4.8f, 0, R.drawable.airpods_pro2,
            "Active Noise Cancellation, Chip H2, Adaptive EQ, Cảm ứng chạm",
            "Công nghệ âm thanh: ANC, Chip Apple H2, Adaptive EQ, Micro: Có, Phương thức điều khiển: Cảm ứng chạm, Hãng: Apple Chính hãng",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth chụp tai Sony WH-1000XM6",
            "11.750.000đ",
            "11.990.000đ",
            4.9f, 0, R.drawable.sony_wh_1000xm6,
            "Adaptive NC Optimizer, Pin 40h, 3.5mm, Nút vật lý",
            "Trọng lượng: 254g, Công nghệ âm thanh: Adaptive NC Optimizer, Micro: Có, Cổng kết nối: 3.5mm, Pin: 40h (tắt chống ồn)/30h (bật), Hãng: Sony",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth chụp tai Sony WH-CH520",
            "1.011.700đ",
            "1.290.000đ",
            4.6f, 0, R.drawable.sony_wh_ch520,
            "Pin 50h, DSEE, ENC, 360 Reality Audio, Nút vật lý",
            "Kích thước: 22.8x17.2x4.4cm, Trọng lượng: 147g, Công nghệ âm thanh: DSEE, ENC, 360 Reality Audio, Equalizer, Micro: Có, Pin: 50h, Hãng: Sony",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth True Wireless Samsung Galaxy Buds 3 Pro",
            "4.596.000đ",
            "5.490.000đ",
            4.8f, 0, R.drawable.samsung_bud_3,
            "ANC, Hi-Fi 24bit, 360 Audio, 6 micro, Pin 7h/30h, Cảm ứng",
            "Kích thước tai nghe: 18.1x19.8x33.2mm, Hộp sạc: 58.9x48.7x24.4mm, Trọng lượng: 5.4g (tai nghe), 46.5g (hộp), Công nghệ âm thanh: ANC, Hi-Fi 24bit, 360 Audio, Micro: 6, Pin: 7h/30h, Hãng: Samsung Chính hãng",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth True Wireless SoundPeats T3 Pro",
            "378.000đ",
            "500.000đ",
            4.4f, 0, R.drawable.soundpeats_t3_pro,
            "Spatial Audio, ANC, Pin 7h/28h, Cảm ứng, IPX4",
            "Kích thước tai nghe: 33.4x22x25mm, Hộp sạc: 64.7x49.15x32.1mm, Trọng lượng: 140g, Công nghệ âm thanh: Spatial Audio, ANC, Micro: 4, Pin: 7h/28h, Chipset: BT8932F, Hãng: Soundpeats",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth True Wireless SoundPEATS Air4 Pro",
            "940.500đ",
            "1.380.000đ",
            4.5f, 0, R.drawable.soundpeats_air4_pro,
            "Snapdragon Sound, aptX Lossless, ANC, Pin 6h/26h, Cảm ứng, IPX4",
            "Kích thước tai nghe: 34.4x24x20.7mm, Hộp sạc: 64x24.2x48.3mm, Công nghệ âm thanh: Snapdragon Sound, aptX Lossless, ANC, Micro: 6, Pin: 6h/26h, Chipset: QCC3071, Hãng: Soundpeats",
            7);
        insertProduct(db,
            "Tai nghe Bluetooth thể thao SoundPEATS GoFree2",
            "1.000.000đ",
            "1.410.000đ",
            4.5f, 0, R.drawable.soundpeats_gofree_2,
            "HiRes Audio Wireless, ENC, LDAC, Pin 9h/35h, IPX5, Cảm ứng",
            "Kích thước tai nghe: 49.84x38.56x22.24mm, Hộp sạc: 84.4x65.3x28.9mm, Trọng lượng: 9g (tai nghe), 71g (hộp), Công nghệ âm thanh: HiRes, ENC, LDAC, Micro: 4, Pin: 9h/35h, Hãng: Soundpeats",
            7);
        insertProduct(db,
            "Tai nghe Gaming chụp tai Sony Inzone H9",
            "4.265.500đ",
            "5.850.000đ",
            4.7f, 0, R.drawable.sony_inzone_h9,
            "360 Spatial Sound, ECM, Pin 32h, Nút vật lý, Có thể vừa sạc vừa dùng",
            "Trọng lượng: ~330g, Công nghệ âm thanh: ECM, 360 Spatial Sound, Cảm biến tiếng ồn kép, Micro: Có, Pin: 32h, Hãng: Sony",
            7);
        insertProduct(db,
            "Tai nghe không dây Gaming Sony INZONE Buds",
            "3.467.500đ",
            "4.790.000đ",
            4.6f, 0, R.drawable.sony_inzone_buds,
            "Chống ồn, 360 Spatial Sound, Pin 12h/24h, Cảm ứng, IPX4",
            "Trọng lượng: ~6.5g x2, Công nghệ âm thanh: Chống ồn, 360 Spatial Sound, Micro: Có, Pin: 12h/24h, Hãng: Sony",
            7);
        // --- Sản phẩm phụ kiện ---
        insertProduct(db,
            "Sạc nhanh 20W Apple MHJE3ZA | Chính hãng Apple Việt Nam",
            "500.000đ",
            "890.000đ",
            4.7f, 0, R.drawable.apple_adapter,
            "Công suất 20W, Sạc nhanh, Bảo vệ quá dòng/quá áp/chập mạch/quá nhiệt, Công nghệ Power Delivery (PD), Chứng nhận ROSH, CE, FCC",
            "Công suất sạc: 20W, Sử dụng tối đa: 1 thiết bị, Đầu ra: USB-C, Tiện ích: Sạc nhanh, Bảo vệ sản phẩm tránh quá dòng, quá điện áp hay hiện tượng mạch điện bị chập và quá nhiệt trong quá trình sạc, Công nghệ/Đạt chứng nhận: Công nghệ Power Delivery (PD), Chứng nhận ROSH, CE, FCC, Hãng sản xuất: Apple Chính hãng",
            8);
        insertProduct(db,
            "Bút cảm ứng Apple Pencil Pro",
            "3.224.000đ",
            "3.490.000đ",
            4.8f, 0, R.drawable.apple_pencil_pro,
            "Kết nối Bluetooth, Gắn/ghép đôi/tích điện từ tính, Tương thích iPad Pro/Air mới, Apple Chính hãng",
            "Hãng sản xuất: Apple Chính hãng, Kết nối: Bluetooth, Tính năng khác: Gắn, ghép đôi và tích điện bằng từ tính, Hệ điều hành: iPadOS 17.5 trở lên, Tương thích: iPad Pro 13 inch (M4), iPad Pro 11 inch (M4), iPad Air 13 inch (M2), iPad Air 11 inch (M2)",
            8);
        insertProduct(db,
            "Apple AirTag",
            "735.000đ",
            "790.000đ",
            4.7f, 0, R.drawable.apple_airtag,
            "Chip U1 Ultra Wideband, Loa tích hợp, Kết nối Find My, Apple Chính hãng",
            "Chipset: Chip U1 (Ultra Wideband), Hãng sản xuất: Apple Chính hãng, Tính năng khác: Loa tích hợp phát âm thanh giúp định vị AirTag, Kết nối với ứng dụng Find My trên iPhone, Theo dõi vị trí đồ vật qua smartphone",
            8);
        insertProduct(db,
            "Chuột Apple Magic Mouse 3 2024",
            "1.852.000đ",
            "2.290.000đ",
            4.7f, 0, R.drawable.apple_mouse_3,
            "Kết nối Bluetooth, Dùng 1 tháng, Apple Chính hãng",
            "Tương thích: Mac (macOS 15.1+), iPad (iPadOS 18.1+), Kết nối: Bluetooth, Khoảng cách kết nối: 10m, Thời gian dùng: 1 tháng, Hãng sản xuất: Apple Chính hãng",
            8);
        insertProduct(db,
            "Apple TV 2022 4K 128GB Ethernet",
            "3.812.000đ",
            "5.990.000đ",
            4.8f, 0, R.drawable.apple_tv,
            "4K 60FPS, Dolby Vision, HDR 10, 128GB, TVOS, Apple Chính hãng",
            "Độ phân giải: 4K 60FPS, Công nghệ: Dolby Vision, HDR 10, Hệ điều hành: TVOS, Bộ nhớ trong: 128GB, Cổng kết nối: HDMI, LAN, nguồn, Kết nối bàn phím: Bluetooth, Hãng sản xuất: Apple Chính hãng, Tính năng khác: Xem phim, Chơi game, Nghe nhạc",
            8);
        insertProduct(db,
            "Cáp Aukey Type-C to Lightning MFi CB-CL5 1.2 mét",
            "247.000đ",
            "260.000đ",
            4.6f, 0, R.drawable.aukey_type_c,
            "Công suất 27W, Sạc nhanh, Chứng nhận MFi, Power Delivery, Aukey",
            "Công suất sạc: 27W, Sử dụng tối đa: 1 thiết bị, Đầu vào: Type-C, Đầu ra: Lightning, Tiện ích: Sạc nhanh, chịu uốn cong vượt trội, Truyền dữ liệu 480 Mbps, Công nghệ/Đạt chứng nhận: MFi, Power Delivery, Chiều dài dây: 1.2m, Loại cáp: Type-C to Lightning, Hãng sản xuất: Aukey",
            8);
        insertProduct(db,
            "Sạc nhanh Ugreen Robot CD361 3 cổng USB-C và USB-A GaN 65W",
            "475.000đ",
            "900.000đ",
            4.7f, 0, R.drawable.ugreen_robot,
            "Công suất 65W, 3 cổng, Công nghệ GaNFast, Màn hình LED, Ugreen",
            "Công suất sạc: 65W, Sử dụng tối đa: 3 thiết bị, Đầu ra: USB-C/USB-A, Tiện ích: GaNFast, Màn hình LED, Bảo vệ ngắn mạch/quá tải/nhiệt độ/quá áp, Phích cắm gập, Hãng sản xuất: Ugreen",
            8);
        insertProduct(db,
            "Sạc StarGO 2C1A PD Gan 65W PowerGO 65",
            "320.000đ",
            "490.000đ",
            4.5f, 0, R.drawable.icon_phukien,
            "Công suất 65W, 3 cổng, Công nghệ GaN, StarGO",
            "Công suất sạc: 65W, Sạc một cổng: USB-C (65W) + USB-A (30W), Sạc hai cổng: USB-C1+USB-C2 (45W+20W); USB-C1+USB-A(45W+18W); USB-C2+USB-A(15W), Sạc ba cổng: USB-C1 (45W) + USB-C2 (7.5W) + USB-A (7.5W), Sử dụng tối đa: 3 thiết bị, Đầu vào: 2 x USB-C, 1 x USB-A, Tiện ích: Bảo vệ quá dòng/quá áp/ngắn mạch/chống tĩnh điện/quá nhiệt/quá tải, Công nghệ: GaN, Hãng sản xuất: StarGO",
            8);
        insertProduct(db,
            "Sạc Anker Zolo 3C1A 140W kèm cáp USB-C B2697",
            "1.251.000đ",
            "1.700.000đ",
            4.8f, 0, R.drawable.icon_phukien,
            "Công suất 140W, 4 cổng, Power IQ 3.0, Màn hình thông minh, Anker",
            "Công suất sạc: 140W, Sử dụng tối đa: 4 thiết bị, Đầu ra: 3 x USB-C, 1 x USB-A, Tiện ích: Sạc nhanh, Màn hình thông minh kép hiển thị nhiệt độ, Khi nhiệt độ cao hơn ngưỡng hệ thống tự điều chỉnh, Công nghệ: Power IQ 3.0, Chiều dài dây: 1.5m, Hãng sản xuất: Anker",
            8);
    }

     // Thêm danh mục vào database
     private void insertCategory(SQLiteDatabase db, String name, int iconResource) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, name);
        values.put(COLUMN_CATEGORY_ICON, iconResource);
        db.insert(TABLE_CATEGORIES, null, values);
    }

    // Thêm sản phẩm vào database
    private void insertProduct(SQLiteDatabase db, String name, String price, String oldPrice,
                              float rating, int sold, int imageResource, String highlight, String detail, int categoryId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_OLD_PRICE, oldPrice);
        values.put(COLUMN_PRODUCT_RATING, rating);
        values.put(COLUMN_PRODUCT_SOLD, sold);
        values.put(COLUMN_PRODUCT_IMAGE, imageResource);
        values.put(COLUMN_PRODUCT_HIGHLIGHT, highlight);
        values.put(COLUMN_PRODUCT_SPECS, detail);
        values.put(COLUMN_PRODUCT_CATEGORY_ID, categoryId);
        db.insert(TABLE_PRODUCTS, null, values);
    }

    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CATEGORIES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME));
                int iconResource = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_ICON));
                Category category = new Category(name, iconResource);
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
                String highlight = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_HIGHLIGHT));
                String detail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SPECS));
                
                // Tạo danh sách thông số kỹ thuật từ chuỗi detail
                List<SpecItem> specs = new ArrayList<>();
                // Giả sử detail chứa thông tin dạng "Màn hình: 6.7\" OLED, RAM: 8GB, ROM: 256GB"
                String[] specPairs = detail.split(", ");
                for (String pair : specPairs) {
                    String[] keyValue = pair.split(": ");
                    if (keyValue.length == 2) {
                        specs.add(new SpecItem(keyValue[0], keyValue[1]));
                    }
                }
                
                Product product = new Product(name, price, oldPrice, rating, sold, 
                    imageResource, highlight, detail, specs);
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    // Lấy sản phẩm theo danh mục
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PRODUCT_CATEGORY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};
        Cursor cursor = db.query(TABLE_PRODUCTS, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
                String highlight = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_HIGHLIGHT));
                String detail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SPECS));
                // Tách detail thành nhiều SpecItem
                List<SpecItem> specs = new ArrayList<>();
                String[] specPairs = detail.split(", ");
                for (String pair : specPairs) {
                    String[] keyValue = pair.split(": ");
                    if (keyValue.length == 2) {
                        specs.add(new SpecItem(keyValue[0], keyValue[1]));
                    }
                }
                Product product = new Product(name, price, oldPrice, rating, sold, imageResource, highlight, detail, specs);
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    // Tìm kiếm sản phẩm theo tên, highlight, specs
    public List<Product> searchProducts(String searchQuery) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PRODUCT_NAME + " LIKE ? OR " + COLUMN_PRODUCT_HIGHLIGHT + " LIKE ? OR " + COLUMN_PRODUCT_SPECS + " LIKE ?";
        String likeQuery = "%" + searchQuery + "%";
        String[] selectionArgs = {likeQuery, likeQuery, likeQuery};
        Cursor cursor = db.query(TABLE_PRODUCTS, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
                String highlight = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_HIGHLIGHT));
                String detail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_SPECS));
                Product product = new Product(name, price, oldPrice, rating, sold, imageResource, highlight, detail,
                    Arrays.asList(new SpecItem("Thông số kỹ thuật", detail)));
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    // Thêm sản phẩm mới
    public long addProduct(Product product, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COLUMN_PRODUCT_OLD_PRICE, product.getOldPrice());
        values.put(COLUMN_PRODUCT_RATING, product.getRating());
        values.put(COLUMN_PRODUCT_SOLD, product.getSold());
        values.put(COLUMN_PRODUCT_IMAGE, product.getImageResId());
        values.put(COLUMN_PRODUCT_HIGHLIGHT, product.getHighlight());
        values.put(COLUMN_PRODUCT_SPECS, product.getDetail());
        values.put(COLUMN_PRODUCT_CATEGORY_ID, categoryId);
        return db.insert(TABLE_PRODUCTS, null, values);
    }

    // Cập nhật sản phẩm
    public int updateProduct(Product product, int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COLUMN_PRODUCT_OLD_PRICE, product.getOldPrice());
        values.put(COLUMN_PRODUCT_RATING, product.getRating());
        values.put(COLUMN_PRODUCT_SOLD, product.getSold());
        values.put(COLUMN_PRODUCT_IMAGE, product.getImageResId());
        values.put(COLUMN_PRODUCT_HIGHLIGHT, product.getHighlight());
        values.put(COLUMN_PRODUCT_SPECS, product.getDetail());
        String selection = COLUMN_PRODUCT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(productId)};
        return db.update(TABLE_PRODUCTS, values, selection, selectionArgs);
    }

    // Xóa sản phẩm
    public int deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_PRODUCT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(productId)};
        return db.delete(TABLE_PRODUCTS, selection, selectionArgs);
    }

    // Thêm user mới
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // Kiểm tra user đã tồn tại chưa
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Kiểm tra đăng nhập
    public boolean checkUserLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean success = cursor.moveToFirst();
        cursor.close();
        return success;
    }
} 
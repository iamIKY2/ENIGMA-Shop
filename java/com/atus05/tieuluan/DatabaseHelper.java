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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }

    // Thêm dữ liệu mẫu vào database
    private void insertSampleData(SQLiteDatabase db) {
        insertCategory(db, "Điện thoại", R.drawable.icon_phone);
        insertCategory(db, "Tablet", R.drawable.icon_tablet);
        insertCategory(db, "Laptop", R.drawable.icon_laptop);
        insertCategory(db, "Đồng hồ", R.drawable.icon_watch);
        insertCategory(db, "Màn Hình", R.drawable.icon_screen);
        insertCategory(db, "Tivi", R.drawable.icon_tv);
        insertCategory(db, "Âm Thanh", R.drawable.icon_tainghe);
        insertCategory(db, "Phụ Kiện", R.drawable.icon_phukien);
        // Thêm sản phẩm mẫu với highlight và specs
        insertProduct(db, "iPhone 15 Pro Max", "29.990.000đ", "32.990.000đ", 4.8f, 230, R.drawable.iphone_15_pro_max256, "Chip A17 Pro, Camera 48MP, Màn hình 120Hz, Pin 5000mAh", "Màn hình: 6.7\" OLED, RAM: 8GB, ROM: 256GB, Camera: 48MP+12MP+12MP, Pin: 5000mAh", 1);
        insertProduct(db, "Samsung Galaxy A15", "4.500.000đ", "5.500.000đ", 4.3f, 150, R.drawable.samsung_a15, "Pin 5000mAh, Màn hình 90Hz", "Màn hình: 6.5\" AMOLED, RAM: 6GB, ROM: 128GB, Camera: 50MP+5MP+2MP, Pin: 5000mAh", 1);
        insertProduct(db, "iPhone 13", "18.990.000đ", "20.990.000đ", 4.6f, 198, R.drawable.iphone13, "Chip A15 Bionic, Camera kép 12MP", "Màn hình: 6.1\" OLED, RAM: 4GB, ROM: 128GB, Camera: 12MP+12MP, Pin: 3240mAh", 1);
        insertProduct(db, "Redmi Note 13", "5.290.000đ", "6.290.000đ", 4.4f, 112, R.drawable.redmi13, "Pin 5000mAh, Sạc nhanh 33W", "Màn hình: 6.67\" AMOLED, RAM: 8GB, ROM: 128GB, Camera: 108MP+8MP+2MP, Pin: 5000mAh", 1);
        insertProduct(db, "OPPO Reno 11F", "7.990.000đ", "8.990.000đ", 4.5f, 121, R.drawable.oppo_reno11f, "Sạc nhanh 67W, Camera 64MP", "Màn hình: 6.7\" AMOLED, RAM: 8GB, ROM: 256GB, Camera: 64MP+8MP+2MP, Pin: 5000mAh", 1);
        insertProduct(db, "Realme C67", "4.990.000đ", "5.490.000đ", 4.2f, 95, R.drawable.realme_c67, "Pin 5000mAh, Camera 108MP", "Màn hình: 6.72\" IPS LCD, RAM: 8GB, ROM: 128GB, Camera: 108MP+2MP, Pin: 5000mAh", 1);
        insertProduct(db, "Xiaomi 14 Ultra", "18.990.000đ", "21.990.000đ", 4.6f, 140, R.drawable.xiaomi14ultra, "Snapdragon 8 Gen 3, Camera Leica", "Màn hình: 6.73\" AMOLED, RAM: 12GB, ROM: 512GB, Camera: 50MP+50MP+50MP+50MP, Pin: 5000mAh", 1);
        insertProduct(db, "Samsung S24 Ultra", "25.990.000đ", "28.990.000đ", 4.7f, 190, R.drawable.samsung_s24, "Snapdragon 8 Gen 3, S Pen", "Màn hình: 6.8\" AMOLED, RAM: 12GB, ROM: 256GB, Camera: 200MP+12MP+10MP, Pin: 5000mAh", 1);
        insertProduct(db, "Samsung Galaxy Tab A9+ WIFI 4GB 64GB", "4.070.000đ", "5.890.000đ", 4.5f, 231, R.drawable.samsung_galaxy_tab_a9_plus_1, "Màn hình lớn, Pin 7040mAh", "Màn hình: 11\" TFT LCD, RAM: 4GB, ROM: 64GB, Camera: 8MP, Pin: 7040mAh", 2);
        // --- Sản phẩm Macbook (ghi chú: chỉ dành cho Macbook) ---
        insertProduct(db, "Macbook Pro M4", "45.990.000₫", "49.990.000₫", 4.9f, 120, R.drawable.macbook_pro_m4, "Macbook, Chip Apple M4, 8GB RAM, SSD 512GB", "Màn hình: 14\" Liquid Retina, RAM: 8GB, SSD: 512GB, Loại card đồ họa: Apple M4 GPU, Dung lượng RAM: 8GB, Ổ cứng: 512GB SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: Liquid Retina, Pin: 52,6 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 3024 x 1964 pixels, Loại CPU: Apple M4, Cổng giao tiếp: 2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3", 3);
        insertProduct(db, "Macbook Air M2", "28.990.000₫", "32.990.000₫", 4.8f, 90, R.drawable.macbook_air_m2, "Macbook, Chip Apple M2, 8GB RAM, SSD 256GB", "Màn hình: 13.6\" Liquid Retina, RAM: 8GB, SSD: 256GB, Loại card đồ họa: Apple M2 GPU, Dung lượng RAM: 8GB, Ổ cứng: 256GB SSD, Kích thước màn hình: 13.6 inches, Công nghệ màn hình: Liquid Retina, Pin: 53,7 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 2560 x 1600 pixels, Loại CPU: Apple M2, Cổng giao tiếp: 2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3", 3);
        insertProduct(db, "Macbook Pro M3", "39.990.000₫", "43.990.000₫", 4.7f, 80, R.drawable.macbook_pro_m3, "Macbook, Chip Apple M3, 16GB RAM, SSD 512GB", "Màn hình: 14\" Liquid Retina, RAM: 16GB, SSD: 512GB, Loại card đồ họa: Apple M3 GPU, Dung lượng RAM: 16GB, Ổ cứng: 512GB SSD, Kích thước màn hình: 14 inches, Công nghệ màn hình: Liquid Retina, Pin: 54,3 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 3024 x 1964 pixels, Loại CPU: Apple M3, Cổng giao tiếp: 2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3", 3);
        insertProduct(db, "Macbook Air M1", "22.990.000₫", "26.990.000₫", 4.6f, 110, R.drawable.macbook_air_m1, "Macbook, Chip Apple M1, 8GB RAM, SSD 256GB", "Màn hình: 13.3\" Retina, RAM: 8GB, SSD: 256GB, Loại card đồ họa: Apple M1 GPU, Dung lượng RAM: 8GB, Ổ cứng: 256GB SSD, Kích thước màn hình: 13.3 inches, Công nghệ màn hình: Retina, Pin: 54 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 2560 x 1600 pixels, Loại CPU: Apple M1, Cổng giao tiếp: 2 x Thunderbolt 3, Jack tai nghe 3.5 mm, MagSafe 3", 3);
        insertProduct(db, "Macbook Pro 2022", "35.990.000₫", "39.990.000₫", 4.5f, 70, R.drawable.macbookpro2022, "Macbook, Chip Apple M2 Pro, 16GB RAM, SSD 512GB", "Màn hình: 16\" Liquid Retina, RAM: 16GB, SSD: 512GB, Loại card đồ họa: Apple M2 Pro GPU, Dung lượng RAM: 16GB, Ổ cứng: 512GB SSD, Kích thước màn hình: 16 inches, Công nghệ màn hình: Liquid Retina, Pin: 90 Wh, Hệ điều hành: MacOS, Độ phân giải màn hình: 3840 x 2400 pixels, Loại CPU: Apple M2 Pro, Cổng giao tiếp: 2 x Thunderbolt 4, Jack tai nghe 3.5 mm, MagSafe 3", 3);
        insertProduct(db, "Asus ROG Strix", "32.000.000đ", "35.000.000đ", 4.7f, 80, R.drawable.asus_rog_strix, "Intel i7, RTX 4060, 16GB RAM", "Màn hình: 15.6\" FHD 144Hz, RAM: 16GB, SSD: 512GB", 3);
        insertProduct(db, "Laptop ASUS Gaming Vivobook 16X", "17.490.000đ", "20.990.000đ", 4.6f, 60, R.drawable.asus_vv_16x, "I5-13420H, 16GB RAM, mỏng nhẹ", "Màn hình: 16 inches FHD, RAM: 16GB, SSD: 512GB", 3);
        insertProduct(db, "Laptop HP Gaming Victus 15", "16.190.000đ", "24.590.000đ", 5f, 120, R.drawable.hp_gaming_15, "I5-12450H, 16GB RAM, 512GB SSD", "Màn hình: 13.4\" FHD Touch, RAM: 16GB, SSD: 512GB", 3);
        insertProduct(db, "Laptop MSI Modern 14", "11.436.300đ", "15.990.000đ", 5f, 120, R.drawable.msi_modern14, "I5-1235U/16GB/512GB PCIE/14.0 FHD/WIN11/ĐEN", "Màn hình: 14.0\" FHD, RAM: 16GB, SSD: 512GB", 3);
        insertProduct(db, "Màn hình thông minh LG MyView 25SR50F 25 inch", "2.745.500đ", "5.990.000đ", 4.8f, 0, R.drawable.icon_screen, "Tấm nền IPS, Tần số quét 60Hz, Độ phân giải 1920x1080", "Kích thước thực tế (bao gồm viền): 24.5 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 60 Hz, Thời gian phản hồi: 8ms, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI 2.1, USB-A 2EA, Đầu ra tai nghe, Kích thước có chân đế: 731.8 x 521.2 x 209.9 mm, Kích thước không chân đế: 731.8 x 440.5 x 45.0 mm, Trọng lượng có chân đế: 6.6 kg, Trọng lượng không chân đế: 5.4 kg, Độ phân giải màn hình: 1920 x 1080 pixels", 5);
        insertProduct(db, "Màn hình Gaming ASUS TUF VG249Q3A 24 inch", "2.840.500đ", "3.990.000đ", 4.8f, 0, R.drawable.icon_screen, "Tấm nền IPS, Tần số quét 180Hz, Độ phân giải 1920x1080", "Kích thước thực tế (bao gồm viền): 24 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 180 Hz, Thời gian phản hồi: 1ms, Treo tường: 100 x 100 mm, Cổng kết nối: DisplayPort 1.2 x 1, HDMI (v2.0) x 2, Jack tai nghe, Kích thước có chân đế: 541 x 394 x 174 mm, Kích thước không chân đế: 541 x 323 x 58 mm, Trọng lượng có chân đế: 3.5 kg, Trọng lượng không chân đế: 2.9 kg, Độ phân giải màn hình: 1920 x 1080", 5);
        insertProduct(db, "Màn hình Gaming LG UltraGear 24GS60F-B 24 inch", "2.897.500đ", "3.990.000đ", 4.7f, 0, R.drawable.icon_screen, "Tấm nền IPS, Tần số quét 180Hz, Độ phân giải 1920x1080", "Kích thước thực tế (bao gồm viền): 23.8 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 180 Hz, Thời gian phản hồi: 1ms, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI 2.0 x 1, DisplayPort 1.4, Cổng âm thanh, Kích thước có chân đế: 540.8 x 408.9 x 180.5 mm, Kích thước không chân đế: 540.8 x 323.8 x 42.8 mm, Trọng lượng có chân đế: 4 kg, Trọng lượng không chân đế: 3.5 kg, Độ phân giải màn hình: 1920 x 1080 pixels", 5);
        insertProduct(db, "Màn hình EDRA EGM27F120H 27 inch", "2.244.200đ", "2.490.000đ", 4.6f, 0, R.drawable.icon_screen, "Tấm nền IPS, Tần số quét 120Hz, Độ phân giải 1920x1080", "Kích thước thực tế (bao gồm viền): 27 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 120 Hz, Thời gian phản hồi: 1ms, Cổng kết nối: 1 x HDMI, 1 x VGA + DC, Kích thước vỏ hộp: 613 x 453.4 x 200.9 mm, Trọng lượng: 3.7 kg, Độ phân giải màn hình: 1920 x 1080 pixels", 5);
        // --- Thêm sản phẩm màn hình mới ---
        insertProduct(db, "Màn hình E-Dra EGM24F100H 24 inch", "1.605.500đ", "2.490.000đ", 4.6f, 0, R.drawable.icon_screen, "Tấm nền IPS, 100Hz, FHD", "Kích thước thực tế (bao gồm viền): 23.8 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 100 Hz, Thời gian phản hồi: 1ms, Treo tường: 75 x 75 mm, Cổng kết nối: 1xVGA + 1xHDMI + Audio out + DC, Độ phân giải màn hình: 1920 x 1080", 5);
        insertProduct(db, "Màn hình Viewsonic VA2708-2K-MHD 27 inch", "3.518.000đ", "4.290.000đ", 4.7f, 0, R.drawable.icon_screen, "IPS, 2K, 100Hz, nhiều cổng kết nối", "Kích thước thực tế (bao gồm viền): 27 inches, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 100 Hz, Thời gian phản hồi: 1ms, Độ tương phản động: 50M:1, Treo tường: 75 x 75 mm, Cổng kết nối: 1x Đầu ra âm thanh 3,5 mm, 2x HDMI 2.0, 1x DisplayPort, DC Socket (Center Positive), Kích thước có chân đế: 613.19 x 449.89 x 190 mm, Kích thước không chân đế: 613.19 x 362.99 x 52.74 mm, Trọng lượng có chân đế: 4.1 kg, Trọng lượng không chân đế: 3.3 kg, Độ phân giải màn hình: 2560 x 1440 pixels", 5);
        insertProduct(db, "Màn hình đồ họa ASUS ProArt PA248QV 24.1 inch", "4.740.500đ", "6.490.000đ", 4.8f, 0, R.drawable.icon_screen, "Chuyên đồ họa, 16:10, nhiều cổng USB", "Kích thước thực tế (bao gồm viền): 24.1 inches, Tỉ lệ màn hình: 16:10, Tần số quét: 75 Hz, Thời gian phản hồi: 5ms, Độ tương phản động: 100000000:1, Treo tường: 100 x 100 mm, Cổng kết nối: HDMI, D-Sub, DisplayPort, PC Audio Input: 3.5mm Mini-Jack, Earphone jack: 3.5mm Mini-Jack, USB Port(s): 3.0x4, Kích thước kèm với kệ: 533 x (375~505) x 211 mm, Kích thước không kèm kệ: 533 x 360 x 47 mm, Trọng lượng tịnh với chân đế: 6.1 Kg, Trọng lượng tịnh không có chân đế: 3.9 Kg, Độ phân giải màn hình: 1920 x 1200 pixels, Tính năng khác: 2W x 2 Stereo RMS, VESA Wall Mounting, Kensington lock", 5);
        insertProduct(db, "Màn hình di động MSI Pro MP161 E2 15.6 inch", "2.840.500đ", "4.290.000đ", 4.6f, 0, R.drawable.icon_screen, "Di động, IPS, 15.6 inch, nhẹ", "Kích thước thực tế (bao gồm viền): 15.6 inches, Độ phân giải màn hình: 1920 x 1080, Tấm nền: IPS, Tỉ lệ màn hình: 16:9, Tần số quét: 60 Hz, Thời gian phản hồi: 4ms, Treo tường: Không hỗ trợ, Cổng kết nối: 2 x USB Type-C, 1 x Mini HDMI, Kích thước: 362.26 x 232.92 x 17.8 mm, Trọng lượng: 0.75 Kg", 5);
        // --- Thêm sản phẩm laptop mới ---
        insertProduct(db, "Laptop ASUS Vivobook 15 X1502VA-BQ885W", "13.085.300đ", "15.990.000đ", 4.6f, 0, R.drawable.asus_rog_strix, "i5-13420H, 16GB, 512GB SSD, 15.6\" FHD", "Loại card đồ họa: Intel HD Graphics, Dung lượng RAM: 16GB, Loại RAM: DDR4, Số khe ram: 2 khe (8GB DDR4 on board + 8GB DDR4 SO-DIMM, Có thể nâng cấp; Cần tháo vỏ dưới/trên), Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Độ sáng 250nits, Độ phủ màu 45% NTSC, Màn hình chống chói, TÜV Rheinland-certified, Pin: 42WHrs, 3S1P, 3-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i5-13420H 2.1 GHz (12MB Cache, up to 4.6 GHz, 8 lõi, 12 luồng), Cổng giao tiếp: 1x USB 2.0 Type-A (480Mbps), 1x USB 3.2 Gen 1 Type-C (5Gbps), 2x USB 3.2 Gen 1 Type-A (5Gbps), 1x HDMI 1.4, 1x Giắc cắm âm thanh 3.5mm, 1x DC-in", 3);
        insertProduct(db, "Laptop ASUS VivoBook S 16 OLED S5606MA-MX051W", "24.190.000đ", "29.990.000đ", 4.8f, 0, R.drawable.asus_rog_strix, "Ultra 7 155H, OLED 16 inch, 16GB, 512GB SSD", "Loại card đồ họa: Intel Arc Graphics, Dung lượng RAM: 16GB, Loại RAM: LPDDR5X Onboard, Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 16 inches, Công nghệ màn hình: Độ sáng tối đa 600nits HDR, Gam màu 100% DCI-P3, Tỷ lệ tương phản 1.000.000:1, Ánh sáng xanh ít gây hại hơn 70%, TÜV Rheinland-certified, 1.07 tỷ màu, Pin: 75WHrs, 4S1P, 4-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 3200 x 2000 pixel (WQHD+), Loại CPU: Intel Core Ultra 7 155H 1.4 GHz (24MB Cache, up to 4.8 GHz, 16 lõi, 22 luồng), Cổng giao tiếp: 2x USB 3.2 Gen 1 Type-A, 2x Thunderbolt 4, 1x HDMI 2.1 TMDS, 1x 3.5mm Combo Audio Jack, Đầu đọc thẻ Micro SD", 3);
        insertProduct(db, "Laptop ASUS Vivobook 15 OLED A1505VA-L1688W", "17.990.000đ", "20.990.000đ", 4.7f, 0, R.drawable.asus_rog_strix, "i7-13620H, OLED 15.6 inch, 16GB, 512GB SSD", "Loại card đồ họa: Intel UHD Graphics, Dung lượng RAM: 16GB, Loại RAM: DDR4, Số khe ram: 2 khe (8GB DDR4 on board + 8GB DDR4 SO-DIMM), Ổ cứng: 512GB M.2 NVMe PCIe 4.0 SSD, Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Thời gian phản hồi 0.2ms, Độ sáng tối đa 600nits HDR, Độ phủ màu 100% DCI-P3, Gamut mapping, HDR True Black 600, 1.07 tỷ màu, Giảm 70% ánh sáng xanh có hại, TÜV Rheinland, SGS, Pin: 50WHrs, 3S1P, 3-cell Li-ion, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i7-13620H 2.4 GHz (24MB Cache, up to 4.9 GHz, 10 lõi, 16 luồng), Cổng giao tiếp: 1x USB 2.0 Type-A, 1x USB 3.2 Gen 1 Type-C, 2x USB 3.2 Gen 1 Type-A, 1x HDMI 1.4, 1x Giắc cắm âm thanh 3.5mm, 1x DC-in", 3);
        insertProduct(db, "Laptop MSI Katana 15 B13VEK-2256VN", "23.990.000đ", "26.990.000đ", 4.7f, 0, R.drawable.msi_modern14, "i7-13620H, RTX 4050, 16GB, 512GB SSD", "Loại card đồ họa: Intel UHD Graphics + NVIDIA GeForce RTX 4050 6GB GDDR6, Dung lượng RAM: 16GB, Loại RAM: DDR5 5200MHz, Số khe ram: 2 khe (1x 16GB, Hỗ trợ nâng cấp tối đa 64GB), Ổ cứng: 1x 512GB M.2-2280 SSD slot (NVMe PCIe Gen4x4), Kích thước màn hình: 15.6 inches, Công nghệ màn hình: Độ phủ màu 45% NTSC, Màn hình chống chói, Pin: 3-cell, 53.5WHr, Hệ điều hành: Windows 11 Home, Độ phân giải màn hình: 1920 x 1080 pixels (FullHD), Loại CPU: Intel Core i7-13620H, Cổng giao tiếp: 1x Type-C (USB3.2 Gen1 / DisplayPort), 2x Type-A USB3.2 Gen1, 1x Type-A USB2.0, 1x HDMI (4K @ 30Hz), 1x RJ45, 1x Mic-in/Headphone-out Combo Jack", 3);
        insertProduct(db, "Laptop MSI Prestige 13 AI Evo A1MG-240VN", "25.990.000đ", "30.990.000đ", 4.8f, 0, R.drawable.msi_modern14, "Ultra 5 125H, 32GB, 512GB SSD, 13.3\" 2.8K", "Chip AI: Intel AI Boost, Loại card đồ họa: Intel Arc Graphics, Dung lượng RAM: 32GB, Loại RAM: LPDDR5X/ 8533MHz, Số khe ram: Onboard, Ổ cứng: 512GB SSD PCIe (M.2 2280), Kích thước màn hình: 13.3 inches, Công nghệ màn hình: Màn hình bóng, Pin: 4 Cell Int (75Wh), Hệ điều hành: Windows 11 Home Single Language 64-bit, Độ phân giải màn hình: 2880 x 1800 pixels, Loại CPU: Intel Core Ultra 5 125H (14 lõi) - Max Turbo Frequency: 4.50 GHz, Cổng giao tiếp: 1 x USB 3.2 Gen 1 Type-A, 2 x Thunderbolt 4, 1 x HDMI, 1 x Headphone/Microphone combo audio jack", 3);
        // --- Thêm sản phẩm tablet mới ---
        insertProduct(db, "iPad A16 Wifi 128GB 2025", "8.526.300đ", "9.990.000đ", 4.8f, 120, R.drawable.icon_tablet, "Chip A16, Màn hình 11 inches, 128GB, iPadOS 18", "Kích thước màn hình: 11 inches, Công nghệ màn hình: Liquid Retina, Camera sau: 12MP, Camera trước: 12MP, Chipset: A16, Bộ nhớ trong: 128GB, Pin: 28,93Wh, Hệ điều hành: iPadOS 18, Độ phân giải: 2360 x 1640 pixel, Tính năng: Multi-Touch, True Tone, Độ sáng 500 nit, Lớp phủ kháng dầu, CPU: 5 lõi, Tương thích: Apple Pencil (USB‑C, Gen 1)", 2);
        insertProduct(db, "iPad mini 7 2024 Wifi 128GB", "11.436.300đ", "13.990.000đ", 4.7f, 90, R.drawable.icon_tablet, "Chip A17 Pro, Màn hình 8.3 inches, 128GB, iPadOS 18", "Kích thước màn hình: 8.3 inches, Công nghệ màn hình: Liquid Retina, Camera sau: 12MP, Camera trước: 12MP, Chipset: A17 Pro, Bộ nhớ trong: 128GB, Pin: 19,3Wh, Hệ điều hành: iPadOS 18, Độ phân giải: 2266 x 1488 pixels, Tính năng: Multi-Touch, True Tone, Độ sáng 500 nit, CPU: 6 lõi, Tương thích: Apple Pencil Pro/USB‑C", 2);
        insertProduct(db, "Xiaomi Redmi Pad 2 WiFi 6GB 128GB", "4.930.500đ", "5.690.000đ", 4.6f, 80, R.drawable.icon_tablet, "Helio G100-Ultra, 6GB RAM, 128GB, HyperOS 2", "Kích thước màn hình: 11 inches, Camera sau: 8MP, Camera trước: 5MP, Chipset: MediaTek Helio G100-Ultra, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 9000mAh, Hệ điều hành: HyperOS 2, Độ phân giải: 2560 x 1600, Tính năng: 90Hz, 500 nits, CPU: A76+A55, Tương thích: Redmi Smart Pen", 2);
        insertProduct(db, "Xiaomi Pad 7 8GB 128GB", "9.200.000đ", "10.300.000đ", 4.8f, 110, R.drawable.icon_tablet, "Snapdragon 7+ Gen 3, 8GB RAM, 128GB, HyperOS 2", "Kích thước màn hình: 11.2 inches, Công nghệ màn hình: IPS LCD, Camera sau: 13MP, Camera trước: 8MP, Chipset: Snapdragon 7+ Gen 3, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 8850mAh, Hệ điều hành: Android 15, HyperOS 2, Độ phân giải: 2136 x 3200, Tính năng: 144Hz, HDR10, Dolby Vision, CPU: Cortex-X4/A720/A520", 2);
        insertProduct(db, "Xiaomi Redmi Pad Pro wifi 8GB 128GB", "6.750.000đ", "7.850.000đ", 4.7f, 70, R.drawable.icon_tablet, "Snapdragon 7s Gen 2, 8GB RAM, 128GB, HyperOS", "Kích thước màn hình: 12.1 inches, Công nghệ màn hình: IPS LCD, Camera sau: 8MP, Camera trước: 8MP, Chipset: Snapdragon 7s Gen 2, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 10.000mAh, Hệ điều hành: HyperOS, Android U, Độ phân giải: 2560 x 1600, Tính năng: 120Hz, Dolby Vision, CPU: Octa-core 2.4GHz", 2);
        insertProduct(db, "Samsung Galaxy Tab S10 Plus Wifi 12GB 256GB", "21.520.000đ", "25.520.000đ", 4.9f, 150, R.drawable.samsung_galaxy_tab_a9_plus_1, "Dimensity 9300+, 12GB RAM, 256GB, Android 14", "Kích thước màn hình: 12.4 inches, Công nghệ màn hình: Dynamic AMOLED 2X, Camera sau: 13+8MP, Camera trước: 12MP, Chipset: Dimensity 9300+, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 10,090mAh, Hệ điều hành: Android 14, Độ phân giải: 1752 x 2800, Tính năng: 120Hz, 650 nits, CPU: 8 nhân 3.4GHz, Tương thích: S Pen", 2);
        insertProduct(db, "Samsung Galaxy Tab S10 FE Plus Wifi 8GB 128GB", "12.401.500đ", "15.700.000đ", 4.7f, 100, R.drawable.samsung_galaxy_tab_a9_plus_1, "Exynos 1580, 8GB RAM, 128GB, Android 15", "Kích thước màn hình: 13.1 inches, Công nghệ màn hình: TFT LCD, Camera sau: 13MP, Camera trước: 12MP, Chipset: Exynos 1580, RAM: 8GB, Bộ nhớ trong: 128GB, Pin: 10,090mAh, Hệ điều hành: Android 15, Độ phân giải: 2880 x 1800, Tính năng: 90Hz, 800 nits, CPU: 2.9/2.6/1.9GHz, Tương thích: S Pen", 2);
        insertProduct(db, "Samsung Galaxy Tab S10 Ultra Wifi 12GB 256GB", "26.430.000đ", "30.430.000đ", 4.9f, 160, R.drawable.samsung_galaxy_tab_a9_plus_1, "Dimensity 9300+, 12GB RAM, 256GB, Android 14", "Kích thước màn hình: 14.6 inches, Công nghệ màn hình: Dynamic AMOLED 2X, Camera sau: 13+8MP, Camera trước: 12+12MP, Chipset: Dimensity 9300+, RAM: 12GB, Bộ nhớ trong: 256GB, Pin: 11,200mAh, Hệ điều hành: Android 14, Độ phân giải: 1848 x 2960, Tính năng: 120Hz, 930 nits, CPU: 8 nhân 3.4GHz, Tương thích: S Pen", 2);
        insertProduct(db, "Máy Tính Bảng Huawei Matepad 11.5''S 8GB 256GB", "11.550.000đ", "14.490.000đ", 4.8f, 85, R.drawable.icon_tablet, "Kirin 9000WL, 8GB RAM, 256GB, HarmonyOS 4.2", "Kích thước màn hình: 11.5 inches, Công nghệ màn hình: TFT LCD, Camera sau: 13MP, Camera trước: 8MP, Chipset: Kirin 9000WL, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 8800mAh, Hệ điều hành: HarmonyOS 4.2, Độ phân giải: 2800 x 1840, Tính năng: 144Hz, 500 nit, CPU: Octa-core 3.2GHz, Tương thích: M-Pencil", 2);
        insertProduct(db, "Huawei MatePad SE 11 inch 6GB 128GB", "4.590.000đ", "5.490.000đ", 4.6f, 60, R.drawable.icon_tablet, "6GB RAM, 128GB, HarmonyOS 2.0", "Kích thước màn hình: 11 inches, Công nghệ màn hình: TFT LCD, Camera sau: 8MP, Camera trước: 5MP, RAM: 6GB, Bộ nhớ trong: 128GB, Pin: 7700mAh, Hệ điều hành: HarmonyOS 2.0, Độ phân giải: 1920 x 1200, Tính năng: 400 nits, Tương thích: M-Pen lite4", 2);
        insertProduct(db, "Lenovo Idea Tab Pro Wifi 8GB 256GB ZAE40190VN kèm bút, bàn phím", "11.433.000đ", "11.990.000đ", 4.7f, 75, R.drawable.icon_tablet, "Dimensity 8300, 8GB RAM, 256GB, LCD 12.7 inch, Android 14", "Công nghệ màn hình: LCD, Kích thước: 12.7 inch, Độ phân giải: 2944 x 1840, Tần số quét: 144Hz, 273 PPI, 400 nits, Paper-like-anti-glare-matte, Camera sau: 13MP, Camera trước: 8MP, Chipset: Dimensity 8300, RAM: 8GB, Bộ nhớ trong: 256GB, Pin: 10200mAh, Hệ điều hành: Android 14, CPU: 8C, 1x A715 @3.35 GHz + 3x A715@3.2 GHz + 4x A510@2.2 GHz, Tương thích: Tab Pen Plus, Keyboard Pack, Folio Case", 2);
        insertProduct(db, "Lenovo Tab M11 4G (4GB 64GB) ZADB0134VN - Kèm bút, bao da", "4.567.000đ", "5.790.000đ", 4.5f, 60, R.drawable.icon_tablet, "Helio G88, 4GB RAM, 64GB, IPS LCD 11 inch, Android 13", "Kích thước màn hình: 11 inches, Công nghệ màn hình: IPS LCD, Độ phân giải: 1920 x 1200, 400nits, 90Hz, Camera sau: 8MP, Camera trước: 8MP, Chipset: Helio G88, RAM: 4GB, Bộ nhớ trong: 64GB, Pin: 7040mAh, SIM: Nano-SIM, Hệ điều hành: Android 13, CPU: 8C, 2x A75 @2.0GHz + 6x A55 @1.8GHz, Tương thích: Tab Pen, Bao da Folio", 2);
        insertProduct(db, "Lenovo Tab 4G 4GB 64GB ZAEJ0139VN kèm bao da", "3.970.000đ", "4.190.000đ", 4.4f, 55, R.drawable.icon_tablet, "Helio G85, 4GB RAM, 64GB, TFT LCD 10.1 inch, Android 14", "Kích thước màn hình: 10.1 inches, Công nghệ màn hình: TFT LCD, Độ phân giải: 1920 x 1200, 400nits, 60Hz, Camera sau: 8MP, Camera trước: 5MP, Chipset: Helio G85, RAM: 4GB, Bộ nhớ trong: 64GB, Pin: 5100mAh, SIM: Nano-SIM, Hệ điều hành: Android 14, CPU: 8C, 2x A75 @2.0GHz + 6x A55 @1.8GHz, Tương thích: Bao da", 2);
        // --- Thêm sản phẩm TV mới ---
        insertProduct(db, "Smart Tivi Samsung UHD 4K 55 INCH 2024 (55DU8000)", "9.940.000đ", "15.400.000đ", 4.8f, 120, R.drawable.smart_tivi_samsung_uhd_4k, "Samsung, 55 inch, 4K, Crystal Processor 4K, Tizen OS", "Kích cỡ màn hình: 55 inch, Độ phân giải: 4K, Loại màn hình: LED, Tần số quét: 50Hz, Công nghệ hình ảnh: Crystal Processor 4K, HDR 10+, UHD Dimming, Motion Xcelerator, HDR, Nâng cấp Tương phản, 4K Upscaling, Loại tivi: LED, Smart tivi, Công nghệ âm thanh: Adaptive Sound, Q-Symphony, OTS Lite, Hệ điều hành: Tizen OS, Tiện ích: Điều khiển qua ứng dụng, Thương hiệu: Samsung, Sản xuất tại: Việt Nam", 6);
        insertProduct(db, "Samsung OLED 4K 55 inch S95C", "32.990.000đ", "39.990.000đ", 4.9f, 100, R.drawable.samsung_oled_4k,
            "OLED, 55 inch, 4K, Tizen OS, Dolby Atmos, Quantum HDR, 120Hz, Ambient Mode+, FreeSync, Q-Symphony, Việt Nam, 2022", 
            "Kích cỡ màn hình: 55 inch, Công nghệ hình ảnh: Ambient Mode+, Brightness/Color Detection, HDR10+, HLG, Màu sắc chuẩn chứng nhận PANTONE, Quantum Dot hiển thị 100% dải màu, Quantum HDR, Real Depth Enhancer, Cân chỉnh màu sắc Expert Calibration, Chống chói Anti Reflection, Màn hình OLED, Chuyển động mượt Motion Xcelerator Turbo+, Chuyển động ảnh mượt mà Auto Motion Plus, Chống xé hình FreeSync Premium, LED Clear Motion, Góc nhìn siêu rộng Ultra Viewing Angle, Super Ultra Wide Game View & Game Bar, Chế độ Game Motion Plus, Căn chỉnh hình ảnh tự động bảo vệ mắt EyeComfort, FilmMaker Mode, Giảm độ trễ chơi game Auto Low Latency Mode (ALLM), Độ phân giải: 4K, Loại màn hình: OLED, Tần số quét: 120Hz, Loại tivi: Tivi OLED, Smart tivi, Công nghệ âm thanh: Dolby Atmos, Âm thanh chuyển động theo hình ảnh OTS Lite, Adaptive Sound+, Khuếch đại âm thoại chủ động AVA, Q-Symphony kết hợp loa tivi với loa thanh, Hệ điều hành: Tizen OS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Tích hợp trợ lí ảo Google Assistant, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Chơi game trên tivi, Thương hiệu: Samsung, Sản xuất tại: Việt Nam, Năm ra mắt: 2022", 6);
        insertProduct(db, "LG 4K 55 inch HDR10 WebOS 2025", "10.990.000đ", "14.900.000đ", 4.8f, 50, R.drawable.smart_tivi_lg_uhd_4k,
            "LG, 55 inch, 4K, HDR10, WebOS, Clear Voice Pro, AI Sound, Việt Nam, 2025",
            "Kích cỡ màn hình: 55 inch, Công nghệ hình ảnh: HDR10, Tăng cường biểu cảm 4K, HDR (High Dynamic Range), FILMMAKER MODE™ Auto Calibration, Hiệu chuẩn tự động Auto Calibration, Độ phân giải: 4K, Loại màn hình: LED, Tần số quét: 60Hz, Loại tivi: Tivi LED, Smart tivi, Công nghệ âm thanh: LG Sound Sync, Clear Voice Pro, AI Sound (Âm thanh AI), Sound Mode Share, WOW Orchestra, Hệ điều hành: WebOS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: LG, Sản xuất tại: Việt Nam, Năm ra mắt: 2025", 6);
        insertProduct(db, "LG LCD QHD 27 inch AI α8 webOS 24", "18.990.000đ", "22.990.000đ", 4.7f, 20, R.drawable.man_hinh_thong_minh_lg,
            "LG, 27 inch, LCD, QHD, AI α8, webOS 24, 60Hz, HDMI, USB-C, Pin 4 giờ, 2024",
            "Kích thước thực tế (bao gồm viền): 27 inches, Tấm nền: LCD, Tần số quét: 60 Hz, Cổng kết nối: 1 x HDMI 1.4, 2 x USB-C, Kích thước: 622 x 1083 - 1283 x 362 mm, Trọng lượng: 15.9kg, Chip AI: Bộ xử lý AI α8 AI Processor Gen2, Pin: Tối đa 4 giờ, Hệ điều hành: webOS 24, Độ phân giải màn hình: QHD (2560 x 1440)",
            5);
        insertProduct(db, "LG NanoCell 4K 75 inch HDR10 Pro WebOS", "20.990.000đ", "36.990.000đ", 4.8f, 30, R.drawable.smart_tivi_lg_nano_cell_4k,
            "LG, 75 inch, 4K, NanoCell, HDR10 Pro, WebOS, AI Sound Pro, Indonesia, 2024",
            "Kích cỡ màn hình: 75 inch, Công nghệ hình ảnh: 4K Upscaling, Điều chỉnh độ sáng AI, HDR10 Pro/HLG, Dimming Technology, FILMMAKER MODE™, 9 chế độ (Vivid, Standard, Eco, Cinema, Sports, Game, Filmmaker, (ISF)Expert(Bright Room), (ISF)Expert(Dark Room)), Độ phân giải: 4K, Loại màn hình: LED, Tần số quét: 60Hz, Loại tivi: Tivi LED, Tivi NanoCell, Smart tivi, Công nghệ âm thanh: AI Sound Pro (Virtual 9.1.2 Up-mix), Điều chỉnh âm thanh AI, Clear Voice Pro, LG Sound Sync, Sound Mode Share, Bluetooth Surround Ready, Hệ điều hành: WebOS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: LG, Sản xuất tại: Indonesia",
            6);
        insertProduct(db, "LG UHD 4K 55 inch HDR10 AI 2023", "9.590.000đ", "13.900.000đ", 4.7f, 25, R.drawable.smart_tivi_lg_uhd,
            "LG, 55 inch, 4K, HDR10, AI Brightness, WebOS, 2023",
            "Kích cỡ màn hình: 55 inch, Công nghệ hình ảnh: Chế độ game HGiG, Chế độ nhà làm phim FilmMaker Mode, HDR Dynamic Tone Mapping, HDR10, HLG, Điều chỉnh độ sáng tự động AI Brightness, Nâng cấp độ phân giải 4K AI Upscaling, Chế độ hình ảnh phù hợp nội dung, Giảm độ trễ chơi game Auto Low Latency Mode (ALLM), Tương thích bộ mã hóa Video decoder (VP9, AV1), Tương thích HEVC, Độ phân giải: 4K, Loại màn hình: LED, Tần số quét: 60Hz, Loại tivi: Tivi LED, Smart tivi, Công nghệ âm thanh: Bluetooth Surround Ready, Chế độ lọc thoại Clear Voice Pro, Hệ điều hành: WebOS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: LG, Năm ra mắt: 2023",
            6);
        insertProduct(db, "LG QNED 4K >85 inch α8 AI 2025", "58.990.000đ", "95.000.000đ", 4.9f, 10, R.drawable.smart_tivi_lg_qned,
            "LG, >85 inch, 4K, QNED, α8 AI, Dolby Vision, 120Hz, WebOS, Indonesia, 2025",
            "Kích cỡ màn hình: > 85 inch, Công nghệ hình ảnh: HLG, HDR10, Dolby Vision, AI Picture Pro, FilmMaker Mode, 10 chế độ hình ảnh, 4K Expression Enhancer, α8 AI Super Upscaling 4K, Dynamic Tone Mapping Pro, Dynamic QNED Color, Kiểm soát đèn nền - Local Dimming, Điều chỉnh độ sáng tự động AI Brightness, Motion Pro, Độ phân giải: 4K, Loại màn hình: QNED, Tần số quét: 120Hz, Loại tivi: Smart tivi, QNED tivi, Công nghệ âm thanh: WISA ready, Dolby Atmos, WOW Orchestra, TV Sound Mode Share, AI Acoustic Tuning, LG Sound Sync, Chế độ lọc thoại Clear Voice Pro, α8 AI Sound Pro (Virtual 9.1.2 Up-mix), Hệ điều hành: WebOS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: LG, Sản xuất tại: Indonesia, Năm ra mắt: 2025",
            6);
        insertProduct(db, "Samsung Smart TV 32 inch HDR 10+ Tizen OS", "5.990.000đ", "6.400.000đ", 4.5f, 40, R.drawable.smart_tivi_samsung,
            "Samsung, 32 inch, HD, HDR 10+, Tizen OS, OTS Lite, Q-Symphony",
            "Kích cỡ màn hình: 32 inch, Công nghệ hình ảnh: HDR, HDR 10+, Mega Contrast, Micro Dimming Pro, Tăng Cường Tương Phản, Độ phân giải: HD, Loại màn hình: LED, Tần số quét: 50Hz, Loại tivi: Tivi LED, Smart tivi, Công nghệ âm thanh: OTS Lite, Q-Symphony, Adaptive Sound, Hệ điều hành: Tizen OS, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Tích hợp trợ lí ảo Google Assistant, Thương hiệu: Samsung",
            6);
        insertProduct(db, "Sony Google TV 4K 43 inch Dolby Vision 2024", "13.390.000đ", "15.690.000đ", 4.6f, 35, R.drawable.google_tivi_sony,
            "Sony, 43 inch, 4K, Triluminos Pro, Dolby Vision, Google TV, Malaysia, 2024",
            "Kích cỡ màn hình: 43 inch, Công nghệ hình ảnh: Tăng cường màu sắc Triluminos Pro, HLG, HDR10, Dolby Vision, Nâng cấp hình ảnh 4K X-Reality PRO, Tinh năng Game Menu, Chuyển động mượt Motionflow XR 200, Giảm độ trễ chơi game Auto Low Latency Mode (ALLM), Độ phân giải: 4K, Loại màn hình: LED, Tần số quét: 50Hz / 60Hz, Loại tivi: Tivi LED, Smart tivi, Google tivi, Công nghệ âm thanh: Dolby Atmos, Bộ khuếch đại âm thanh S-Master Digital Amplifier, Loa X-Balanced tăng chất lượng âm thanh, Hệ điều hành: Google TV, Tiện ích nổi bật: Tích hợp trợ lí ảo Google Assistant, Điều khiển bằng giọng nói không cần remote, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Chơi game trên tivi, Thương hiệu: Sony, Sản xuất tại: Malaysia, Năm ra mắt: 2024",
            6);
        insertProduct(db, "TCL QD-Mini LED Google TV 4K 75 inch 144Hz 2025", "43.990.000đ", "74.990.000", 4.7f, 15, R.drawable.google_tivitcl_qd_miniled,
            "TCL, 75 inch, 4K, Mini LED, Quantum Dot, Dolby Vision IQ, Google TV, Việt Nam, 2025",
            "Kích cỡ màn hình: 75 inch, Công nghệ hình ảnh: VRR 144Hz, HLG, HDR10+, HDR, Dolby Vision IQ, Quantum Dot, Công nghệ giảm ánh sáng xanh, bảo vệ mắt Low Blue Light, Full Array Local Dimming, Game Master, Chống xé hình FreeSync Premium Pro, Tương thích chuẩn IMAX Enhanced, HDR High Brightness, Công nghệ 288 Hz DLG, Độ phân giải: 4K, Loại màn hình: Mini LED, Tần số quét: 144Hz, Loại tivi: Google tivi, QD-Mini LED, Công nghệ âm thanh: Dolby Atmos, DTS:X, Hệ thống loa Bang & Olufsen, Hệ điều hành: Google TV, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Tích hợp trợ lí ảo Google Assistant, Điều khiển bằng giọng nói không cần remote, Điều khiển qua ứng dụng, Thương hiệu: TCL, Sản xuất tại: Việt Nam, Năm ra mắt: 2025",
            6);
        insertProduct(db, "TCL QLED Google TV 43 inch FullHD 2025", "6.190.000đ", "8.490.000đ", 4.5f, 18, R.drawable.google_tivi_tcl_qled,
            "TCL, 43 inch, FullHD, QLED, Quantum Dot, Google TV, Việt Nam, 2025",
            "Kích cỡ màn hình: 43 inch, Công nghệ hình ảnh: HDR10, Quantum Dot, Công nghệ giảm ánh sáng xanh, bảo vệ mắt Low Blue Light, Độ phân giải: FullHD, Loại màn hình: QLED, Tần số quét: 60Hz, Loại tivi: Tivi QLED, Google tivi, Công nghệ âm thanh: Dolby Audio, Hệ điều hành: Google TV, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Tích hợp trợ lí ảo Google Assistant, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: TCL, Sản xuất tại: Việt Nam, Năm ra mắt: 2025",
            6);
        insertProduct(db, "Sony Google TV 4K 55 inch Dolby Vision 2023", "29.890.000đ", "", 4.8f, 22, R.drawable.google_tivi_sony_4k,
            "Sony, 55 inch, 4K, Dolby Vision, XR Contrast Booster 10, Google TV, Malaysia, 2023",
            "Kích cỡ màn hình: 55 inch, Công nghệ hình ảnh: Dolby Vision, HDR10, HLG, Tăng cường tương phản XR Contrast Booster 10, Nâng cấp độ phân giải XR 4K Upscaling, Tăng cường màu sắc XR Triluminos Pro, Giảm nhiễu XR Clear Image, Làm mượt chuyển động XR Motion Clarity, Độ phân giải: 4K, Loại màn hình: LED, Loại tivi: Google tivi, Công nghệ âm thanh: Dolby Atmos, DTS Digital Surround, Nâng cấp âm thanh vòm 3D, Âm thanh vòm S-Force Front Surround, Bộ khuếch đại âm thanh S-Master Digital Amplifier, Âm thanh từ màn hình Acoustic Multi Audio, Tăng chất lượng âm thanh với loa X-Balanced, Hệ điều hành: Google TV, Tiện ích nổi bật: Tìm kiếm giọng nói bằng tiếng Việt, Tích hợp trợ lí ảo Google Assistant, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Thương hiệu: Sony, Sản xuất tại: Malaysia, Năm ra mắt: 2023",
            6);
        insertProduct(db, "Sony Google TV Mini LED 4K 65 inch 2024", "39.990.000đ", "51.190.000đ", 4.9f, 12, R.drawable.google_tivi_sony_mini_led,
            "Sony, 65 inch, 4K, Mini LED, XR Contrast Booster, Dolby Vision, 120Hz, Google TV, Malaysia, 2024",
            "Kích cỡ màn hình: 65 inch, Công nghệ hình ảnh: HLG, HDR10, Dolby Vision, XR Contrast Booster, XR Backlight Master Drive, XR Triluminos Pro, XR 4K Upscaling, XR Motion Clarity, XR Clear Image, IMAX Enhanced, Netflix Calibrated Mode, Độ phân giải: 4K, Loại màn hình: Mini LED, Tần số quét: 120Hz, Loại tivi: Google tivi, Mini LED, Công nghệ âm thanh: Dolby Atmos, Acoustic Center Sync, Acoustic Multi Audio, DTS Digital Surround, 360 Spatial Sound Mapping, S-Master Digital Amplifier, Nâng cấp âm thanh vòm 3D, Âm thanh vòm S-Force Front Surround, Hệ điều hành: Google TV, Tiện ích nổi bật: Tích hợp trợ lí ảo Google Assistant, Điều khiển bằng giọng nói không cần remote, Chiếu hình ảnh từ điện thoại lên TV, Điều khiển qua ứng dụng, Chơi game trên tivi, Thương hiệu: Sony, Sản xuất tại: Malaysia, Năm ra mắt: 2024",
            6);
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

    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchProducts(String searchQuery) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PRODUCT_NAME + " LIKE ?";
        String[] selectionArgs = {"%" + searchQuery + "%"};
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
} 
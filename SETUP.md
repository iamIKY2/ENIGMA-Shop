# Hướng dẫn Setup Project

## Yêu cầu hệ thống

- **Android Studio**: Phiên bản mới nhất (Arctic Fox trở lên)
- **JDK**: Version 11 hoặc cao hơn
- **Android SDK**: API Level 28 trở lên
- **RAM**: Tối thiểu 4GB (khuyến nghị 8GB)

## Các bước setup

### 1. Clone project
```bash
git clone <repository-url>
cd tieuluan2
```

### 2. Mở project trong Android Studio
- Mở Android Studio
- Chọn "Open an existing Android Studio project"
- Chọn thư mục `tieuluan2`

### 3. Sync project
- Đợi Android Studio sync Gradle files
- Nếu có thông báo update Gradle, chọn "Update"

### 4. Cấu hình SDK
- Vào `File > Project Structure`
- Trong tab `SDK Location`, đảm bảo:
  - Android SDK location đúng
  - JDK location đúng (version 11+)

### 5. Build project
- Chọn `Build > Make Project` hoặc nhấn `Ctrl+F9`
- Đợi build hoàn tất

### 6. Chạy ứng dụng
- Kết nối thiết bị Android hoặc tạo emulator
- Nhấn nút "Run" (▶️) hoặc `Shift+F10`

## Cấu trúc project

```
tieuluan2/
├── app/
│   ├── src/main/
│   │   ├── java/com/atus05/tieuluan/
│   │   │   ├── MainActivity.java
│   │   │   ├── DatabaseHelper.java
│   │   │   ├── ProductManagementActivity.java
│   │   │   ├── CategorySpinnerAdapter.java
│   │   │   ├── Product.java
│   │   │   ├── Category.java
│   │   │   └── [Các Adapter khác]
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Troubleshooting

### Lỗi thường gặp

#### 1. Gradle sync failed
- Kiểm tra kết nối internet
- Xóa thư mục `.gradle` và sync lại
- Update Android Studio

#### 2. SDK not found
- Vào `File > Settings > Appearance & Behavior > System Settings > Android SDK`
- Cài đặt SDK platforms và build tools cần thiết

#### 3. JDK not found
- Cài đặt JDK 11 hoặc cao hơn
- Trong `File > Project Structure > SDK Location`, chọn JDK location đúng

#### 4. Build failed
- Clean project: `Build > Clean Project`
- Rebuild project: `Build > Rebuild Project`
- Invalidate caches: `File > Invalidate Caches and Restart`

### Lỗi SQLite
- Database sẽ được tạo tự động khi chạy app lần đầu
- Nếu có lỗi database, xóa app và cài lại

## Tính năng chính

1. **Hiển thị sản phẩm**: Danh mục, sản phẩm nổi bật, tất cả sản phẩm
2. **Quản lý SQLite**: Thêm, sửa, xóa sản phẩm
3. **Tìm kiếm**: Tìm kiếm sản phẩm theo tên
4. **Lọc danh mục**: Xem sản phẩm theo danh mục

## Đóng góp

Khi đóng góp code:
1. Tạo branch mới: `git checkout -b feature/ten-tinh-nang`
2. Commit changes: `git commit -m "Mô tả thay đổi"`
3. Push branch: `git push origin feature/ten-tinh-nang`
4. Tạo Pull Request

## Liên hệ

Nếu có vấn đề, tạo Issue trên GitHub hoặc liên hệ team. 
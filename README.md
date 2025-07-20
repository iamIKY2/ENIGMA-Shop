# Ứng dụng Shop với SQLite

## Mô tả
Ứng dụng Android mô phỏng cửa hàng điện tử với khả năng quản lý sản phẩm sử dụng SQLite database.

## 📋 Mục lục
- [Mô tả](#mô-tả)
- [Tính năng chính](#tính-năng-chính)
- [Cài đặt](#cài-đặt)
- [Cấu trúc Database](#cấu-trúc-database)
- [Cách sử dụng](#cách-sử-dụng)
- [Cấu trúc code](#cấu-trúc-code)
- [Đóng góp](#đóng-góp)
- [Dữ liệu mẫu](#dữ-liệu-mẫu)
- [Mở rộng tính năng](#mở-rộng-tính-năng)
- [Lưu ý kỹ thuật](#lưu-ý-kỹ-thuật)

## Tính năng chính

### 1. Hiển thị sản phẩm
- **Danh mục sản phẩm**: Hiển thị các danh mục như Điện thoại, Tablet, Laptop, v.v.
- **Sản phẩm nổi bật**: Hiển thị các sản phẩm nổi bật theo chiều ngang
- **Tất cả sản phẩm**: Hiển thị tất cả sản phẩm theo dạng lưới 2 cột

### 2. Quản lý sản phẩm (SQLite)
- **Thêm sản phẩm mới**: Nhập thông tin sản phẩm và lưu vào database
- **Cập nhật sản phẩm**: Chỉnh sửa thông tin sản phẩm hiện có
- **Xóa sản phẩm**: Xóa sản phẩm khỏi database
- **Tìm kiếm sản phẩm**: Tìm kiếm theo tên sản phẩm
- **Lọc theo danh mục**: Hiển thị sản phẩm theo danh mục được chọn

## Cấu trúc Database

### Bảng Categories
- `id`: ID danh mục (Primary Key)
- `name`: Tên danh mục
- `icon_resource`: ID icon của danh mục

### Bảng Products
- `id`: ID sản phẩm (Primary Key)
- `name`: Tên sản phẩm
- `price`: Giá hiện tại
- `old_price`: Giá cũ
- `rating`: Đánh giá (0.0 - 5.0)
- `sold`: Số lượng đã bán
- `image_resource`: ID hình ảnh sản phẩm
- `category_id`: ID danh mục (Foreign Key)

## Cài đặt

### Yêu cầu hệ thống
- **Android Studio**: Phiên bản mới nhất (Arctic Fox trở lên)
- **JDK**: Version 11 hoặc cao hơn
- **Android SDK**: API Level 28 trở lên
- **RAM**: Tối thiểu 4GB (khuyến nghị 8GB)

### Các bước cài đặt
1. **Clone repository**
   ```bash
   git clone <repository-url>
   cd tieuluan2
   ```

2. **Mở project trong Android Studio**
   - Mở Android Studio
   - Chọn "Open an existing Android Studio project"
   - Chọn thư mục `tieuluan2`

3. **Sync project**
   - Đợi Android Studio sync Gradle files
   - Nếu có thông báo update Gradle, chọn "Update"

4. **Build và chạy**
   - Build project: `Build > Make Project`
   - Chạy ứng dụng: Nhấn nút "Run" (▶️)

### Troubleshooting
Nếu gặp lỗi, xem file [SETUP.md](SETUP.md) để biết hướng dẫn chi tiết.

## Cách sử dụng

### 1. Chạy ứng dụng
- Build và chạy ứng dụng trên thiết bị Android hoặc emulator
- Database sẽ được tạo tự động với dữ liệu mẫu

### 2. Quản lý sản phẩm
- Nhấn nút "Quản lý" ở header để mở màn hình quản lý sản phẩm
- Nhập thông tin sản phẩm:
  - Tên sản phẩm (bắt buộc)
  - Giá sản phẩm (bắt buộc)
  - Giá cũ (tùy chọn)
  - Đánh giá (0.0 - 5.0)
  - Số lượng đã bán
  - Chọn danh mục
- Nhấn "Thêm" để thêm sản phẩm mới
- Nhấn "Cập nhật" để cập nhật sản phẩm hiện có
- Nhấn "Xóa sản phẩm" để xóa sản phẩm

### 3. Tương tác với danh mục
- Nhấn vào danh mục để xem thông báo (có thể mở rộng để lọc sản phẩm)

## Cấu trúc code

### DatabaseHelper.java
- Quản lý SQLite database
- Các method CRUD cho sản phẩm và danh mục
- Tạo dữ liệu mẫu khi khởi tạo database

### MainActivity.java
- Màn hình chính hiển thị sản phẩm
- Sử dụng RecyclerView để hiển thị danh sách
- Kết nối với ProductManagementActivity

### ProductManagementActivity.java
- Màn hình quản lý sản phẩm
- Form nhập liệu và các nút thao tác
- Tương tác với DatabaseHelper

### CategorySpinnerAdapter.java
- Adapter cho Spinner hiển thị danh mục
- Sử dụng trong ProductManagementActivity

## Dữ liệu mẫu

Ứng dụng được tạo sẵn với các dữ liệu mẫu:

### Danh mục
- Điện thoại
- Tablet
- Laptop
- Đồng hồ
- Màn Hình
- Tivi
- Âm Thanh
- Phụ Kiện

### Sản phẩm
- iPhone 15 Pro Max
- Samsung Galaxy A15
- iPhone 13
- Redmi Note 13
- OPPO Reno 11F
- Realme C67
- Xiaomi 14 Ultra
- Samsung S24 Ultra
- Samsung Galaxy Tab A9+

## Đóng góp

Chúng tôi rất hoan nghênh mọi đóng góp! Nếu bạn muốn đóng góp vào project này:

1. **Fork** repository này
2. Tạo **branch** mới cho tính năng của bạn (`git checkout -b feature/AmazingFeature`)
3. **Commit** thay đổi của bạn (`git commit -m 'Add some AmazingFeature'`)
4. **Push** lên branch (`git push origin feature/AmazingFeature`)
5. Mở **Pull Request**

Xem file [CONTRIBUTING.md](CONTRIBUTING.md) để biết hướng dẫn chi tiết về quy trình đóng góp.

### Tính năng có thể đóng góp
- [ ] Giỏ hàng và đơn hàng
- [ ] Quản lý người dùng (đăng ký/đăng nhập)
- [ ] Đánh giá và bình luận sản phẩm
- [ ] Yêu thích sản phẩm
- [ ] Tìm kiếm nâng cao
- [ ] Dark mode
- [ ] Animation chuyển cảnh

## Mở rộng tính năng

Có thể mở rộng thêm các tính năng:
- Giỏ hàng và đơn hàng
- Quản lý người dùng
- Đánh giá và bình luận
- Tìm kiếm nâng cao
- Lọc theo giá, đánh giá
- Sắp xếp sản phẩm
- Backup/Restore dữ liệu

## Lưu ý kỹ thuật

- Sử dụng SQLite database local
- Dữ liệu được lưu trữ bền vững
- Có thể thêm/sửa/xóa sản phẩm
- Giao diện Material Design
- Responsive layout



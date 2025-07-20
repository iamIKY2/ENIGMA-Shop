# Hướng dẫn đóng góp

## Quy tắc chung

### 1. Code Style
- Sử dụng **camelCase** cho tên biến và method
- Sử dụng **PascalCase** cho tên class
- Comment bằng **tiếng Việt** cho các đoạn code phức tạp
- Mỗi method không quá 50 dòng
- Tên biến phải có ý nghĩa

### 2. Commit Message
```
feat: thêm tính năng giỏ hàng
fix: sửa lỗi crash khi xóa sản phẩm
docs: cập nhật README
style: format code
refactor: tối ưu DatabaseHelper
test: thêm unit test cho Product
```

### 3. Branch Naming
- `feature/ten-tinh-nang`: Tính năng mới
- `fix/ten-loi`: Sửa lỗi
- `docs/ten-tai-lieu`: Cập nhật tài liệu
- `refactor/ten-refactor`: Tối ưu code

## Quy trình đóng góp

### 1. Fork repository
- Fork project về GitHub account của bạn
- Clone về máy local

### 2. Tạo branch mới
```bash
git checkout -b feature/ten-tinh-nang
```

### 3. Code và test
- Code theo quy tắc đã định
- Test kỹ trước khi commit
- Đảm bảo không có lỗi build

### 4. Commit changes
```bash
git add .
git commit -m "feat: thêm tính năng tìm kiếm nâng cao"
```

### 5. Push và tạo Pull Request
```bash
git push origin feature/ten-tinh-nang
```

### 6. Tạo Pull Request
- Mô tả rõ ràng tính năng/thay đổi
- Đính kèm screenshot nếu cần
- Tag người review

## Cấu trúc code

### Database
```java
// Thêm method mới vào DatabaseHelper
public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
    // Implementation
}
```

### Activity
```java
// Tạo Activity mới
public class NewFeatureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feature);
        // Implementation
    }
}
```

### Layout
```xml
<!-- Tạo layout mới -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <!-- Content -->
</LinearLayout>
```

## Tính năng có thể đóng góp

### 1. Tính năng cơ bản
- [ ] Giỏ hàng và đơn hàng
- [ ] Quản lý người dùng (đăng ký/đăng nhập)
- [ ] Đánh giá và bình luận sản phẩm
- [ ] Yêu thích sản phẩm
- [ ] Lịch sử mua hàng

### 2. Tính năng nâng cao
- [ ] Tìm kiếm nâng cao (theo giá, đánh giá)
- [ ] Sắp xếp sản phẩm
- [ ] Lọc theo nhiều tiêu chí
- [ ] Phân trang sản phẩm
- [ ] Offline mode

### 3. UI/UX
- [ ] Dark mode
- [ ] Animation chuyển cảnh
- [ ] Pull to refresh
- [ ] Swipe to delete
- [ ] Material Design 3

### 4. Backend
- [ ] API integration
- [ ] Firebase integration
- [ ] Push notification
- [ ] Analytics

## Review Process

### 1. Code Review
- Kiểm tra code style
- Kiểm tra logic
- Kiểm tra performance
- Kiểm tra security

### 2. Testing
- Unit test cho logic phức tạp
- UI test cho flow chính
- Manual test trên device

### 3. Documentation
- Cập nhật README nếu cần
- Comment code phức tạp
- Tạo screenshot cho UI mới

## Lỗi thường gặp

### 1. Build failed
- Kiểm tra Gradle sync
- Kiểm tra dependencies
- Clean và rebuild project

### 2. Database error
- Kiểm tra SQL syntax
- Kiểm tra foreign key constraints
- Test trên device thật

### 3. UI crash
- Kiểm tra null pointer
- Kiểm tra resource không tồn tại
- Test trên nhiều screen size

## Liên hệ

- **Issues**: Tạo issue trên GitHub
- **Discussions**: Sử dụng GitHub Discussions
- **Email**: [your-email@example.com]

## License

Project này sử dụng MIT License. Xem file `LICENSE` để biết thêm chi tiết. 
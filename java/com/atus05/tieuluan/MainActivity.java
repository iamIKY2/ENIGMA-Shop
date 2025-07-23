    package com.atus05.tieuluan;

    import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

        RecyclerView rvProducts;
        RecyclerView rvFeaturedProducts;
        RecyclerView rvCategories;
        DatabaseHelper databaseHelper;
        Button btnManageProducts;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            // Khởi tạo DatabaseHelper
            databaseHelper = new DatabaseHelper(this);

            rvProducts = findViewById(R.id.rv_products);
            rvFeaturedProducts = findViewById(R.id.rv_featured_products);
            rvCategories = findViewById(R.id.rv_categories);

            // Thiết lập GridLayoutManager cho RecyclerView chính (2 cột)
            rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
            
            // Thiết lập LinearLayoutManager horizontal cho RecyclerView nổi bật
            rvFeaturedProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            // Thiết lập LinearLayoutManager horizontal cho RecyclerView danh mục
            rvCategories.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));

            // Lấy danh sách danh mục từ database
            List<Category> categoryList = databaseHelper.getAllCategories();

            // Thiết lập adapter cho RecyclerView danh mục
            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, this);
            rvCategories.setAdapter(categoryAdapter);

            // Lấy danh sách sản phẩm từ database
            List<Product> productList = databaseHelper.getAllProducts();

            // Lấy sản phẩm nổi bật random 3 sản phẩm từ danh sách sản phẩm
            List<Product> featuredProductList = new ArrayList<>();
            if (productList.size() > 0) {
                // Sử dụng java.util.Collections để trộn ngẫu nhiên danh sách
                java.util.Collections.shuffle(productList);
                // Lấy tối đa 3 sản phẩm đầu tiên sau khi đã random
                int featuredCount = Math.min(5, productList.size());
                for (int i = 0; i < featuredCount; i++) {
                    featuredProductList.add(productList.get(i));
                }
            }

            // Thiết lập adapter cho RecyclerView chính
            ProductAdapter adapter = new ProductAdapter(productList);
            rvProducts.setAdapter(adapter);

            // Thiết lập adapter cho RecyclerView nổi bật
            FeaturedProductAdapter featuredAdapter = new FeaturedProductAdapter(featuredProductList);
            rvFeaturedProducts.setAdapter(featuredAdapter);



            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        @Override
        public void onCategoryClick(Category category) {
            // Xử lý sự kiện click vào danh mục
            String catName = category.getName();
            if (catName.equalsIgnoreCase("Laptop")) {
                // Nếu là danh mục Laptop thì chuyển sang trang Laptop
                Intent intent = new Intent(this, LaptopActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Màn Hình")) {
                // Nếu là danh mục Màn Hình thì chuyển sang activity man_hinh
                Intent intent = new Intent(this, man_hinh.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Điện thoại")) {
                // Nếu là danh mục Điện thoại thì chuyển sang PhoneActivity
                Intent intent = new Intent(this, PhoneActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Tablet")) {
                // Nếu là danh mục Tablet thì chuyển sang TabletActivity
                Intent intent = new Intent(this, TabletActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Tivi")) {
                // Nếu là danh mục Tivi thì chuyển sang TVActivity
                Intent intent = new Intent(this, TVActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Âm Thanh")) {
                // Nếu là danh mục Âm Thanh thì chuyển sang AudioActivity
                Intent intent = new Intent(this, AudioActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Phụ Kiện")) {
                // Nếu là danh mục Phụ Kiện thì chuyển sang AccessoryActivity
                Intent intent = new Intent(this, AccessoryActivity.class);
                startActivity(intent);
            } else if (catName.equalsIgnoreCase("Đồng hồ")) {
                // Nếu là danh mục Đồng hồ thì chuyển sang WatchActivity
                Intent intent = new Intent(this, WatchActivity.class);
                startActivity(intent);
            } else {
                // TODO: Có thể lọc sản phẩm theo danh mục được chọn
                // List<Product> filteredProducts = databaseHelper.getProductsByCategory(categoryId);
                // adapter.updateProducts(filteredProducts);
            }
        }
        
        @Override
        protected void onDestroy() {
            super.onDestroy();
            // Đóng database khi activity bị hủy
            if (databaseHelper != null) {
                databaseHelper.close();
            }
        }
    }

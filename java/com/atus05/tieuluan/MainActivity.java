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

            // Lấy sản phẩm nổi bật (3 sản phẩm đầu tiên)
            List<Product> featuredProductList = new ArrayList<>();
            if (productList.size() >= 4) {
                featuredProductList.add(productList.get(0)); // iPhone 15 Pro Max
                featuredProductList.add(productList.get(7)); // Samsung S24 Ultra
                featuredProductList.add(productList.get(6)); // Xiaomi 14 Ultra
                featuredProductList.add(productList.get(2)); // iPhone 13
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
            if (category.getName().equalsIgnoreCase("Laptop")) {
                // Nếu là danh mục Laptop thì chuyển sang trang Laptop
                Intent intent = new Intent(this, LaptopActivity.class);
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

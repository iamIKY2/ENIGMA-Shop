    package com.atus05.tieuluan;

    import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
    import android.widget.AutoCompleteTextView;
    import android.widget.ArrayAdapter;
    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import android.view.inputmethod.EditorInfo;
    import android.view.KeyEvent;
    import android.widget.TextView;
    import com.atus05.tieuluan.SimpleStringAdapter;

    import java.util.ArrayList;
    import java.util.List;
    import android.view.Menu;
    import android.view.MenuItem;

    public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

        RecyclerView rvProducts;
        RecyclerView rvFeaturedProducts;
        RecyclerView rvCategories;
        RecyclerView rvSuggestions;
        TextView tvSuggestedProductsTitle;
        SuggestionAdapter suggestionAdapter;
        List<String> keywordSuggestions;
        List<Product> allProducts;
        DatabaseHelper databaseHelper;
        Button btnManageProducts;
        List<Product> productList; // Danh sách gốc
        ProductAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            // Khôi phục logic cũ: chuyển Activity khi bấm tab
            findViewById(R.id.nav_home).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            });
            findViewById(R.id.nav_cart).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            });
            findViewById(R.id.nav_order_history).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            });
            findViewById(R.id.nav_profile).setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            });

            // Thêm sự kiện click cho giỏ hàng và tài khoản
            View navCart = findViewById(R.id.nav_cart);
            View navCartIcon = navCart.findViewById(android.R.id.icon);
            View navCartText = null;
            if (navCart instanceof android.view.ViewGroup) {
                android.view.ViewGroup vg = (android.view.ViewGroup) navCart;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    if (child instanceof android.widget.TextView && ((android.widget.TextView) child).getText().toString().contains("Giỏ hàng")) {
                        navCartText = child;
                    }
                }
            }
            View.OnClickListener cartListener = v -> {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            };
            navCart.setOnClickListener(cartListener);
            if (navCartIcon != null) navCartIcon.setOnClickListener(cartListener);
            if (navCartText != null) navCartText.setOnClickListener(cartListener);

            View navProfile = findViewById(R.id.nav_profile);
            View navProfileIcon = navProfile.findViewById(android.R.id.icon);
            View navProfileText = null;
            if (navProfile instanceof android.view.ViewGroup) {
                android.view.ViewGroup vg = (android.view.ViewGroup) navProfile;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    if (child instanceof android.widget.TextView && ((android.widget.TextView) child).getText().toString().contains("Tài khoản")) {
                        navProfileText = child;
                    }
                }
            }
            View.OnClickListener profileListener = v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            };
            navProfile.setOnClickListener(profileListener);
            if (navProfileIcon != null) navProfileIcon.setOnClickListener(profileListener);
            if (navProfileText != null) navProfileText.setOnClickListener(profileListener);

            View navOrderHistory = findViewById(R.id.nav_order_history);
            navOrderHistory.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            });

            // Khởi tạo DatabaseHelper
            databaseHelper = new DatabaseHelper(this);

            rvProducts = findViewById(R.id.rv_products);
            rvFeaturedProducts = findViewById(R.id.rv_featured_products);
            rvCategories = findViewById(R.id.rv_categories);
            EditText etSearch = findViewById(R.id.et_search);
            Button btnSearch = findViewById(R.id.btn_search);

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

            productList = databaseHelper.getAllProducts();
            adapter = new ProductAdapter(productList);
            rvProducts.setAdapter(adapter);

            // Lấy sản phẩm nổi bật random 3 sản phẩm từ danh sách sản phẩm
            List<Product> featuredProductList = new ArrayList<>();
            if (productList.size() > 0) {
                // Sử dụng java.util.Collections để trộn ngẫu nhiên danh sách
                java.util.Collections.shuffle(productList);
                // Lấy tối đa 3 sản phẩm đầu tiên sau khi đã random
                int featuredCount = Math.min(3, productList.size());
                for (int i = 0; i < featuredCount; i++) {
                    featuredProductList.add(productList.get(i));
                }
            }

            // Thiết lập adapter cho RecyclerView chính
            // Đã gán adapter ở trên, không cần gán lại ở đây

            // Thiết lập adapter cho RecyclerView nổi bật
            FeaturedProductAdapter featuredAdapter = new FeaturedProductAdapter(featuredProductList);
            rvFeaturedProducts.setAdapter(featuredAdapter);

            rvSuggestions = findViewById(R.id.rv_suggestions);
            tvSuggestedProductsTitle = findViewById(R.id.tv_suggested_products_title);
            rvSuggestions.setLayoutManager(new LinearLayoutManager(this));
            // Gợi ý từ khóa mẫu (hãng, danh mục, từ khóa phổ biến)
            keywordSuggestions = new ArrayList<>();
            keywordSuggestions.add("Tivi LG");
            keywordSuggestions.add("Màn hình LG");
            keywordSuggestions.add("Laptop LG Gram");
            keywordSuggestions.add("Loa LG");
            keywordSuggestions.add("Tivi LG 65 inch");
            // Adapter cho gợi ý từ khóa (dùng SimpleStringAdapter hoặc custom adapter)
            SimpleStringAdapter keywordAdapter = new SimpleStringAdapter(keywordSuggestions);
            rvSuggestions.setAdapter(keywordAdapter);
            // Ẩn gợi ý khi chưa nhập
            rvSuggestions.setVisibility(View.GONE);
            tvSuggestedProductsTitle.setVisibility(View.GONE);
            // Lắng nghe nhập liệu
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    String query = s.toString().trim().toLowerCase();
                    List<Product> result;
                    if (query.isEmpty()) {
                        result = databaseHelper.getAllProducts();
                        tvSuggestedProductsTitle.setVisibility(View.GONE);
                    } else if (query.equals("laptop")) {
                        result = databaseHelper.getProductsByCategory(3);
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else if (query.equals("tablet")) {
                        result = databaseHelper.getProductsByCategory(2);
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else if (query.equals("tivi") || query.equals("tv")) {
                        result = databaseHelper.getProductsByCategory(6);
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else if (query.equals("iphone")) {
                        result = new ArrayList<>();
                        for (Product p : databaseHelper.getAllProducts()) {
                            if (p.getName().toLowerCase().contains("iphone") || p.getHighlight().toLowerCase().contains("iphone")) {
                                result.add(p);
                            }
                        }
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else if (query.equals("samsung")) {
                        result = new ArrayList<>();
                        for (Product p : databaseHelper.getAllProducts()) {
                            if (p.getName().toLowerCase().contains("samsung") || p.getHighlight().toLowerCase().contains("samsung")) {
                                result.add(p);
                            }
                        }
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else if (query.equals("macbook")) {
                        result = new ArrayList<>();
                        for (Product p : databaseHelper.getAllProducts()) {
                            if (p.getName().toLowerCase().contains("macbook") || p.getHighlight().toLowerCase().contains("macbook")) {
                                result.add(p);
                            }
                        }
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    } else {
                        result = databaseHelper.searchProducts(query);
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    }
                    ProductAdapter adapter = new ProductAdapter(result);
                    rvProducts.setAdapter(adapter);
                }
            });
            // Xử lý click cho nút tìm kiếm
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = etSearch.getText().toString().trim();
                    List<Product> result;
                    if (query.isEmpty()) {
                        result = databaseHelper.getAllProducts();
                        tvSuggestedProductsTitle.setVisibility(View.GONE);
                    } else {
                        result = databaseHelper.searchProducts(query);
                        tvSuggestedProductsTitle.setVisibility(View.VISIBLE);
                    }
                    adapter = new ProductAdapter(result);
                    rvProducts.setAdapter(adapter);
                    rvSuggestions.setVisibility(View.GONE);
                }
            });

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.action_order_history) {
                startActivity(new Intent(this, OrderHistoryActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
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

        // Thêm hàm filterProducts vào cuối class MainActivity
        private void filterProducts(String query) {
            if (query.isEmpty()) {
                // adapter.updateProducts(productList); // productList is removed
            } else {
                List<Product> filtered = new ArrayList<>();
                for (Product p : productList) { // productList is removed
                    if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                        filtered.add(p);
                    }
                }
                // adapter.updateProducts(filtered); // adapter is removed
            }
        }
    }

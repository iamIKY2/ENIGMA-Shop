package com.example.tieuluan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {

    private EditText etProductName, etProductPrice, etProductOldPrice, etProductRating, etProductSold;
    private Spinner spinnerCategory;
    private Button btnAddProduct, btnUpdateProduct, btnDeleteProduct;
    private DatabaseHelper databaseHelper;
    private Product currentProduct;
    private int currentProductId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        // Khởi tạo DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Khởi tạo các view
        initViews();

        // Thiết lập spinner categories
        setupCategorySpinner();

        // Thiết lập sự kiện
        setupListeners();

        // Kiểm tra xem có sản phẩm được truyền từ MainActivity không
        Intent intent = getIntent();
        if (intent.hasExtra("product_id")) {
            currentProductId = intent.getIntExtra("product_id", -1);
            loadProductData(currentProductId);
        }
    }

    private void initViews() {
        etProductName = findViewById(R.id.et_product_name);
        etProductPrice = findViewById(R.id.et_product_price);
        etProductOldPrice = findViewById(R.id.et_product_old_price);
        etProductRating = findViewById(R.id.et_product_rating);
        etProductSold = findViewById(R.id.et_product_sold);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnAddProduct = findViewById(R.id.btn_add_product);
        btnUpdateProduct = findViewById(R.id.btn_update_product);
        btnDeleteProduct = findViewById(R.id.btn_delete_product);
    }

    private void setupCategorySpinner() {
        // Lấy danh sách categories từ database
        List<Category> categories = databaseHelper.getAllCategories();

        // Tạo adapter cho spinner
        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this, categories);
        spinnerCategory.setAdapter(adapter);
    }

    private void setupListeners() {
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });
    }

    private void addProduct() {
        // Lấy dữ liệu từ các EditText
        String name = etProductName.getText().toString().trim();
        String price = etProductPrice.getText().toString().trim();
        String oldPrice = etProductOldPrice.getText().toString().trim();
        String ratingStr = etProductRating.getText().toString().trim();
        String soldStr = etProductSold.getText().toString().trim();

        // Kiểm tra dữ liệu
        if (name.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và giá sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float rating = ratingStr.isEmpty() ? 0 : Float.parseFloat(ratingStr);
            int sold = soldStr.isEmpty() ? 0 : Integer.parseInt(soldStr);
            int categoryId = spinnerCategory.getSelectedItemPosition() + 1; // +1 vì ID bắt đầu từ 1

            // Tạo đối tượng Product
            Product product = new Product(
                name,
                price,
                oldPrice,
                rating,
                sold,
                R.drawable.iphone_16_pr,
                "", // highlight
                "", // detail
                new java.util.ArrayList<SpecItem>() // specifications
            );

            // Thêm vào database
            long result = databaseHelper.addProduct(product, categoryId);

            if (result != -1) {
                Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                clearForm();
            } else {
                Toast.makeText(this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho đánh giá và số lượng bán", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateProduct() {
        if (currentProductId == -1) {
            Toast.makeText(this, "Không có sản phẩm nào được chọn để cập nhật", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy dữ liệu từ các EditText
        String name = etProductName.getText().toString().trim();
        String price = etProductPrice.getText().toString().trim();
        String oldPrice = etProductOldPrice.getText().toString().trim();
        String ratingStr = etProductRating.getText().toString().trim();
        String soldStr = etProductSold.getText().toString().trim();

        // Kiểm tra dữ liệu
        if (name.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và giá sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float rating = ratingStr.isEmpty() ? 0 : Float.parseFloat(ratingStr);
            int sold = soldStr.isEmpty() ? 0 : Integer.parseInt(soldStr);

            // Tạo đối tượng Product
            Product product = new Product(
                name,
                price,
                oldPrice,
                rating,
                sold,
                R.drawable.iphone_16_pr,
                "", // highlight
                "", // detail
                new java.util.ArrayList<SpecItem>() // specifications
            );

            // Cập nhật trong database
            int result = databaseHelper.updateProduct(product, currentProductId);

            if (result > 0) {
                Toast.makeText(this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cập nhật sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho đánh giá và số lượng bán", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct() {
        if (currentProductId == -1) {
            Toast.makeText(this, "Không có sản phẩm nào được chọn để xóa", Toast.LENGTH_SHORT).show();
            return;
        }

        // Xóa sản phẩm khỏi database
        int result = databaseHelper.deleteProduct(currentProductId);

        if (result > 0) {
            Toast.makeText(this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
            clearForm();
            currentProductId = -1;
        } else {
            Toast.makeText(this, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProductData(int productId) {
        // TODO: Implement method để load dữ liệu sản phẩm từ database
        // Hiện tại chưa có method getProductById trong DatabaseHelper
        Toast.makeText(this, "Tính năng load dữ liệu sản phẩm sẽ được implement sau", Toast.LENGTH_SHORT).show();
    }

    private void clearForm() {
        etProductName.setText("");
        etProductPrice.setText("");
        etProductOldPrice.setText("");
        etProductRating.setText("");
        etProductSold.setText("");
        spinnerCategory.setSelection(0);
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

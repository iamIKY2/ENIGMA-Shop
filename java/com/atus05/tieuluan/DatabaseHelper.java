package com.atus05.tieuluan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    // Thông tin database
    private static final String DATABASE_NAME = "ShopDatabase";
    private static final int DATABASE_VERSION = 1;
    
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
        COLUMN_PRODUCT_CATEGORY_ID + " INTEGER, " +
        "FOREIGN KEY(" + COLUMN_PRODUCT_CATEGORY_ID + ") REFERENCES " + 
        TABLE_CATEGORIES + "(" + COLUMN_CATEGORY_ID + "))";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo các bảng
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        
        // Thêm dữ liệu mẫu
        insertSampleData(db);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ và tạo lại nếu có thay đổi version
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }
    
    // Thêm dữ liệu mẫu vào database
    private void insertSampleData(SQLiteDatabase db) {
        // Thêm danh mục
        insertCategory(db, "Điện thoại", R.drawable.icon_phone);
        insertCategory(db, "Tablet", R.drawable.icon_tablet);
        insertCategory(db, "Laptop", R.drawable.icon_laptop);
        insertCategory(db, "Đồng hồ", R.drawable.icon_watch);
        insertCategory(db, "Màn Hình", R.drawable.icon_screen);
        insertCategory(db, "Tivi", R.drawable.icon_tv);
        insertCategory(db, "Âm Thanh", R.drawable.icon_tainghe);
        insertCategory(db, "Phụ Kiện", R.drawable.icon_phukien);
        
        // Thêm sản phẩm
        insertProduct(db, "iPhone 15 Pro Max", "29.990.000đ", "32.990.000đ", 4.8f, 230, R.drawable.iphone_16_pr, 1);
        insertProduct(db, "Samsung Galaxy A15", "4.500.000đ", "5.500.000đ", 4.3f, 150, R.drawable.samsung_a15, 1);
        insertProduct(db, "iPhone 13", "18.990.000đ", "20.990.000đ", 4.6f, 198, R.drawable.iphone13, 1);
        insertProduct(db, "Redmi Note 13", "5.290.000đ", "6.290.000đ", 4.4f, 112, R.drawable.redmi13, 1);
        insertProduct(db, "OPPO Reno 11F", "7.990.000đ", "8.990.000đ", 4.5f, 121, R.drawable.oppo_reno11f, 1);
        insertProduct(db, "Realme C67", "4.990.000đ", "5.490.000đ", 4.2f, 95, R.drawable.realme_c67, 1);
        insertProduct(db, "Xiaomi 14 Ultra", "18.990.000đ", "21.990.000đ", 4.6f, 140, R.drawable.xiaomi14ultra, 1);
        insertProduct(db, "Samsung S24 Ultra", "25.990.000đ", "28.990.000đ", 4.7f, 190, R.drawable.samsung_s24, 1);
        insertProduct(db, "Samsung Galaxy Tab A9+ WIFI 4GB 64GB", "4.070.000đ", "5.890.000đ", 4.5f, 231, R.drawable.samsung_galaxy_tab_a9_plus_1, 2);
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
                             float rating, int sold, int imageResource, int categoryId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_OLD_PRICE, oldPrice);
        values.put(COLUMN_PRODUCT_RATING, rating);
        values.put(COLUMN_PRODUCT_SOLD, sold);
        values.put(COLUMN_PRODUCT_IMAGE, imageResource);
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
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME));
                int iconResource = cursor.getInt(cursor.getColumnIndex(COLUMN_CATEGORY_ICON));
                
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
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE));
                
                Product product = new Product(name, price, oldPrice, rating, sold, imageResource);
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
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE));
                
                Product product = new Product(name, price, oldPrice, rating, sold, imageResource);
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
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
                String oldPrice = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_OLD_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRODUCT_RATING));
                int sold = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_SOLD));
                int imageResource = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE));
                
                Product product = new Product(name, price, oldPrice, rating, sold, imageResource);
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
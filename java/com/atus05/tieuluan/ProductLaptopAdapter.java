package com.atus05.tieuluan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Adapter cho danh sách sản phẩm laptop (dạng dọc)
public class ProductLaptopAdapter extends RecyclerView.Adapter<ProductLaptopAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductLaptopAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_laptop, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        // Gán dữ liệu từ Product vào View
        holder.name.setText(product.getName()); // Tên sản phẩm
        holder.price.setText(product.getPrice()); // Giá hiện tại
        holder.oldPrice.setText(product.getOldPrice()); // Giá cũ
        holder.rating.setText(product.getRating() + " ⭐"); // Đánh giá
        holder.image.setImageResource(product.getImageResId()); // Hình ảnh
        // Gạch ngang giá cũ
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        // Sự kiện click giữ nguyên
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            if (product.getSpecifications() == null || product.getSpecifications().isEmpty()) {
                List<SpecItem> specs = new ArrayList<>();
                String[] specPairs = product.getDetail().split(", ");
                for (String pair : specPairs) {
                    String[] keyValue = pair.split(": ");
                    if (keyValue.length == 2) {
                        specs.add(new SpecItem(keyValue[0], keyValue[1]));
                    }
                }
                product.setSpecifications(specs);
            }
            intent.putExtra("product", product);
            context.startActivity(intent);
        });
        ImageButton btnAddToCart = holder.itemView.findViewById(R.id.btn_add_to_cart);
        btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(context, product);
            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, oldPrice, rating;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_product_image);
            name = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_product_price);
            oldPrice = itemView.findViewById(R.id.tv_product_old_price);
            rating = itemView.findViewById(R.id.tv_rating);
        }
    }
} 
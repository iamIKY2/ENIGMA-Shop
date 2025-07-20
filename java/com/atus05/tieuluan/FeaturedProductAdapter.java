package com.atus05.tieuluan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.FeaturedProductViewHolder> {

    private List<Product> productList;

    public FeaturedProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public FeaturedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_horizontal, parent, false);
        return new FeaturedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.rating.setText(product.getRating() + " ⭐");
        holder.image.setImageResource(product.getImageResId());

        // Xử lý click cho item sản phẩm
        holder.itemView.setOnClickListener(v -> {
            // TODO: Xử lý khi click vào sản phẩm nổi bật
            // Toast.makeText(v.getContext(), "Đã chọn " + product.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class FeaturedProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, rating;

        public FeaturedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_product_image);
            name = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_product_price);
            rating = itemView.findViewById(R.id.tv_rating);
        }
    }
} 
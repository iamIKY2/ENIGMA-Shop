package com.atus05.tieuluan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Adapter đơn giản cho danh sách chuỗi (hãng, nhu cầu)
public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.ViewHolder> {
    private List<String> items;
    private boolean isBrand;
    private boolean isNeed;

    // Thêm biến isNeed để xác định adapter dùng cho nhu cầu laptop
    public SimpleStringAdapter(List<String> items) {
        this(items, false, false);
    }
    public SimpleStringAdapter(List<String> items, boolean isBrand) {
        this(items, isBrand, false);
    }
    public SimpleStringAdapter(List<String> items, boolean isBrand, boolean isNeed) {
        this.items = items;
        this.isBrand = isBrand;
        this.isNeed = isNeed;
    }

    // Interface lắng nghe sự kiện click vào item
    public interface OnItemClickListener {
        void onItemClick(String item);
    }
    private OnItemClickListener listener;

    // Hàm thiết lập listener từ bên ngoài
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isBrand) {
            // Nếu là adapter cho hãng laptop thì dùng layout có viền xanh dương
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        } else if (isNeed) {
            // Nếu là adapter cho nhu cầu laptop thì dùng layout có viền xanh lá
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_need, parent, false);
        } else {
            // Nếu là adapter mặc định
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        return new ViewHolder(view, isBrand, isNeed);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(items.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder cho item chuỗi
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        boolean isBrand, isNeed;
        ViewHolder(View itemView, boolean isBrand, boolean isNeed) {
            super(itemView);
            this.isBrand = isBrand;
            this.isNeed = isNeed;
            if (isBrand) {
                textView = itemView.findViewById(R.id.tv_brand_name);
            } else if (isNeed) {
                textView = itemView.findViewById(R.id.tv_need_name);
            } else {
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
        void bind(String text) {
            textView.setText(text);
        }
    }
} 
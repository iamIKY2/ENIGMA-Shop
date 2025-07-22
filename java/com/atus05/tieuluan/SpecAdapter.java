package com.atus05.tieuluan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.SpecViewHolder> {
    private List<SpecItem> specList;

    public SpecAdapter(List<SpecItem> specList) {
        this.specList = specList;
    }

    @NonNull
    @Override
    public SpecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_spec, parent, false);
        return new SpecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecViewHolder holder, int position) {
        SpecItem item = specList.get(position);
        holder.tvName.setText(item.name);
        holder.tvValue.setText(item.value);

        // Thiết lập mô tả trợ năng
        holder.itemView.setContentDescription(item.name + ": " + item.value);
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }

    static class SpecViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvValue;

        public SpecViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_spec_name);
            tvValue = itemView.findViewById(R.id.tv_spec_value);

            // Cấu hình accessibility
            itemView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
            tvName.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
            tvValue.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        }
    }
}
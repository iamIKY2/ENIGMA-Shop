package com.atus05.tieuluan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> allProducts;
    private List<Product> filteredProducts;

    public SuggestionAdapter(Context context, List<Product> products) {
        super(context, 0, products);
        this.context = context;
        this.allProducts = new ArrayList<>(products);
        this.filteredProducts = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false);
        }
        Product product = getItem(position);
        ImageView img = convertView.findViewById(R.id.imgProduct);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        if (product != null) {
            img.setImageResource(product.getImageResId());
            tvName.setText(product.getName());
            tvPrice.setText(product.getPrice());
        }
        return convertView;
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return filteredProducts.get(position);
    }

    @Override
    public int getCount() {
        return filteredProducts.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Product> suggestions = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    suggestions.addAll(allProducts);
                } else {
                    String query = constraint.toString().toLowerCase();
                    for (Product p : allProducts) {
                        if (p.getName().toLowerCase().contains(query)) {
                            suggestions.add(p);
                        }
                    }
                }
                results.values = suggestions;
                results.count = suggestions.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredProducts.clear();
                if (results.values != null) {
                    filteredProducts.addAll((List<Product>) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }
} 
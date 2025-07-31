package com.atus05.tieuluan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);

        SharedPreferences prefs = getSharedPreferences("order_prefs", MODE_PRIVATE);
        String json = prefs.getString("orders", null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Order>>(){}.getType();
        List<Order> orderList = json != null ? gson.fromJson(json, type) : new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        // Custom adapter
        listView.setAdapter(new android.widget.BaseAdapter() {
            @Override
            public int getCount() { return orderList.size(); }
            @Override
            public Object getItem(int position) { return orderList.get(position); }
            @Override
            public long getItemId(int position) { return position; }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout layout = new LinearLayout(OrderHistoryActivity.this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setPadding(24, 24, 24, 24);
                layout.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
                ImageView img = new ImageView(OrderHistoryActivity.this);
                Order o = orderList.get(position);
                if (o.productImageResId > 0) {
                    img.setImageResource(o.productImageResId);
                } else {
                    img.setImageResource(android.R.drawable.ic_menu_report_image);
                }
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(160, 160);
                imgParams.setMargins(0, 0, 32, 0);
                img.setLayoutParams(imgParams);
                layout.addView(img);
                // Thông tin đơn hàng
                TextView tv = new TextView(OrderHistoryActivity.this);
                String info = "Sản phẩm: " + o.productName +
                        "\nTên: " + o.name +
                        "\nSĐT: " + o.phone +
                        "\nEmail: " + o.email +
                        "\nĐịa chỉ: " + o.address +
                        (o.note.isEmpty() ? "" : "\nGhi chú: " + o.note) +
                        "\nThời gian: " + sdf.format(new Date(o.timestamp));
                tv.setText(info);
                tv.setTextSize(16f);
                tv.setPadding(0, 0, 0, 0);
                layout.addView(tv);
                return layout;
            }
        });
    }
} 
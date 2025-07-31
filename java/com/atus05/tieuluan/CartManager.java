package com.atus05.tieuluan;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "cart_prefs";
    private static final String KEY_CART = "cart_items";
    private static Gson gson = new Gson();
    private static android.app.Activity cartActivityInstance;

    public static void addToCart(Context context, Product product) {
        List<Product> cart = getCartItems(context);
        cart.add(product);
        saveCart(context, cart);
    }

    public static List<Product> getCartItems(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CART, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Product>>(){}.getType();
        List<Product> cart = gson.fromJson(json, type);
        return (cart != null) ? cart : new ArrayList<>();
    }

    public static void clearCart(Context context) {
        saveCart(context, new ArrayList<>());
    }

    public static int getTotalPrice(Context context) {
        int total = 0;
        for (Product p : getCartItems(context)) {
            try {
                String priceStr = p.getPrice().replaceAll("[^\\d]", "");
                int price = Integer.parseInt(priceStr);
                total += price;
            } catch (Exception e) {}
        }
        return total;
    }

    public static void saveCart(Context context, List<Product> cart) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_CART, gson.toJson(cart)).apply();
    }

    public static void setCartActivityInstance(android.app.Activity activity) {
        cartActivityInstance = activity;
    }
    public static void clearCartActivityInstance() {
        cartActivityInstance = null;
    }
    public static android.app.Activity getCartActivityInstance() {
        return cartActivityInstance;
    }
} 
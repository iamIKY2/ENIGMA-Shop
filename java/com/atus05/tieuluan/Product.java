package com.atus05.tieuluan;
public class Product {
    private String name;
    private String price;
    private String oldPrice;
    private float rating;
    private int sold;
    private int imageResId;

    public Product(String name, String price, String oldPrice, float rating, int sold, int imageResId) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.rating = rating;
        this.sold = sold;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getOldPrice() { return oldPrice; }
    public float getRating() { return rating; }
    public int getSold() { return sold; }
    public int getImageResId() { return imageResId; }
}



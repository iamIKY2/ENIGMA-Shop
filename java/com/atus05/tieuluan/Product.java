package com.atus05.tieuluan;
public class Product {
    private String name;
    private String price;
    private String oldPrice;
    private float rating;
    private int sold;
    private int imageResId;
    private String highlight;
    private String specs;

    public Product(String name, String price, String oldPrice, float rating, int sold, int imageResId, String highlight, String specs) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.rating = rating;
        this.sold = sold;
        this.imageResId = imageResId;
        this.highlight = highlight;
        this.specs = specs;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getOldPrice() { return oldPrice; }
    public float getRating() { return rating; }
    public int getSold() { return sold; }
    public int getImageResId() { return imageResId; }
    public String getHighlight() { return highlight; }
    public String getSpecs() { return specs; }
}



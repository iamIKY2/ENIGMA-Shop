package com.atus05.tieuluan;
import java.util.List;
import java.io.Serializable;
public class Product implements Serializable {
    private String name;
    private String price;
    private String oldPrice;
    private float rating;
    private int sold;
    private int imageResId;
    private String highlight;
    private String detail;
    private List<SpecItem> specifications;

    public Product(String name, String price, String oldPrice, float rating, int sold, int imageResId, String highlight, String detail, List<SpecItem> specifications) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.rating = rating;
        this.sold = sold;
        this.imageResId = imageResId;
        this.highlight = highlight;
        this.detail = detail;
        this.specifications = specifications;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getOldPrice() { return oldPrice; }
    public float getRating() { return rating; }
    public int getSold() { return sold; }
    public int getImageResId() { return imageResId; }
    public String getHighlight() { return highlight; }
    public String getDetail() { return detail; }
    public List<SpecItem> getSpecifications() {
        return specifications;
    }
    public void setSpecifications(List<SpecItem> specifications) {
        this.specifications = specifications;
    }
}



package com.atus05.tieuluan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public Product(String name, String price, String oldPrice, float rating, int sold,
                   int imageResId, String highlight, String detail, List<SpecItem> specifications) {
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.rating = rating;
        this.sold = sold;
        this.imageResId = imageResId;
        this.highlight = highlight;
        this.detail = detail;
        this.specifications = (specifications != null) ? new ArrayList<>(specifications) : new ArrayList<>();

        // Nếu specifications rỗng nhưng có detail, tự động parse từ detail
        if (this.specifications.isEmpty() && detail != null && !detail.isEmpty()) {
            parseSpecificationsFromDetail();
        }
    }

    private void parseSpecificationsFromDetail() {
        if (detail == null || detail.isEmpty()) return;

        String[] specPairs = detail.split(", ");
        for (String pair : specPairs) {
            String[] keyValue = pair.split(": ");
            if (keyValue.length == 2) {
                addSpecification(keyValue[0].trim(), keyValue[1].trim());
            }
        }
    }

    public void addSpecification(String name, String value) {
        if (specifications == null) {
            specifications = new ArrayList<>();
        }
        specifications.add(new SpecItem(name, value));
    }

    // Getter methods
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getOldPrice() { return oldPrice; }
    public float getRating() { return rating; }
    public int getSold() { return sold; }
    public int getImageResId() { return imageResId; }
    public String getHighlight() { return highlight; }
    public String getDetail() { return detail; }
    public List<SpecItem> getSpecifications() {
        if (specifications == null) {
            specifications = new ArrayList<>();
            parseSpecificationsFromDetail();
        }
        return specifications;
    }

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setPrice(String price) { this.price = price; }
    public void setOldPrice(String oldPrice) { this.oldPrice = oldPrice; }
    public void setRating(float rating) { this.rating = rating; }
    public void setSold(int sold) { this.sold = sold; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
    public void setHighlight(String highlight) { this.highlight = highlight; }
    public void setDetail(String detail) {
        this.detail = detail;
        // Khi detail thay đổi, tự động cập nhật specifications
        if (detail != null && !detail.isEmpty()) {
            parseSpecificationsFromDetail();
        }
    }
    public void setSpecifications(List<SpecItem> specifications) {
        this.specifications = (specifications != null) ? new ArrayList<>(specifications) : new ArrayList<>();
    }

    // Phương thức hỗ trợ serialization
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.writeObject(name);
        out.writeObject(price);
        out.writeObject(oldPrice);
        out.writeFloat(rating);
        out.writeInt(sold);
        out.writeInt(imageResId);
        out.writeObject(highlight);
        out.writeObject(detail);
        out.writeObject(specifications);
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        name = (String) in.readObject();
        price = (String) in.readObject();
        oldPrice = (String) in.readObject();
        rating = in.readFloat();
        sold = in.readInt();
        imageResId = in.readInt();
        highlight = (String) in.readObject();
        detail = (String) in.readObject();
        specifications = (List<SpecItem>) in.readObject();

        // Đảm bảo specifications không null sau khi deserialize
        if (specifications == null) {
            specifications = new ArrayList<>();
        }
    }
}
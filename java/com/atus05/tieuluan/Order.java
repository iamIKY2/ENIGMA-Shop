package com.atus05.tieuluan;

import java.io.Serializable;

public class Order implements Serializable {
    public String name;
    public String phone;
    public String email;
    public String address;
    public String note;
    public String productName;
    public int productImageResId;
    public long timestamp;

    public Order(String name, String phone, String email, String address, String note, String productName, int productImageResId, long timestamp) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.productName = productName;
        this.productImageResId = productImageResId;
        this.timestamp = timestamp;
    }
} 
package com.example.tieuluan;


import java.io.Serializable;

public class SpecItem implements Serializable {
    public String name;
    public String value;

    public SpecItem(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
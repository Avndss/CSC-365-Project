package com.example.projectv1;

public class DataPoint {
    private String category;
    private double value;

    public DataPoint(String category, double value) {
        this.category = category;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }
}

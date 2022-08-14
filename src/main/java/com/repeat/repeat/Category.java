package com.repeat.repeat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

    private String category;
    private List<Subcategory> subcategoryList = new ArrayList<>();

    public Category(String category) {
        this.category = category;
        subcategoryList.add(new Subcategory("ALL"));
    }
    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    @Override
    public String toString() {
        return category;
    }
}

package com.repeat.repeat.data;

import com.repeat.repeat.Category;
import com.repeat.repeat.Subcategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Database implements Serializable {
    EXAMPLE;
    protected static List<Category> listOfAllCategory = new ArrayList<>();

    public final static List<Category> getListOfAllCategory() {
        return listOfAllCategory;
    }

    protected static void setListOfAllCategory(List<Category> list) {
        listOfAllCategory = list;
    }

    public final static List<Subcategory> getListOfSubcategory() {
        List<Subcategory> list = listOfAllCategory.stream()
                .flatMap(n -> n.getSubcategoryList().stream())
                .collect(Collectors.toList());
        return list;
    }
}

package com.repeat.repeat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subcategory implements Serializable {

    private String subcategory;
    private List<String> listOfQuestions = new ArrayList<>();

    public Subcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<String> getListOfQuestions() {
        return listOfQuestions;
    }

    public String getSubcategory() {
        return subcategory;
    }
    @Override
    public String toString() {
        return subcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcategory)) return false;

        Subcategory that = (Subcategory) o;

        return subcategory.equals(that.subcategory);
    }
}

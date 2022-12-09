package com.example.englishquestion.model;

public class Catalog {
    private String catID;

    private String catName;

    private int background;

    public Catalog() {
    }

    public Catalog(String catID, String catName, int background) {
        this.catID = catID;
        this.catName = catName;
        this.background = background;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}

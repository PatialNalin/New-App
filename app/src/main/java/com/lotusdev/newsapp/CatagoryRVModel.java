package com.lotusdev.newsapp;

public class CatagoryRVModel {

    private String category;
    private String catagoryImageUrl;

    public CatagoryRVModel(String category, String catagoryImageUrl) {
        this.category = category;
        this.catagoryImageUrl = catagoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCatagoryImageUrl() {
        return catagoryImageUrl;
    }

    public void setCatagoryImageUrl(String catagoryImageUrl) {
        this.catagoryImageUrl = catagoryImageUrl;
    }
}

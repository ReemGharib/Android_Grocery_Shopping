package com.example.firstapplication.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("categoryName")
    String categoryName;
    @SerializedName("nbOfProducts")
    Integer nbOfProducts;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("productEntityList")
    List<Product> productList;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setNbOfProducts(Integer nbOfProducts) {
        this.nbOfProducts = nbOfProducts;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getNbOfProducts() {
        return nbOfProducts;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

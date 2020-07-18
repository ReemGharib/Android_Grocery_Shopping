package com.example.firstapplication.Entities;

import com.google.gson.annotations.SerializedName;

public class OrderProduct {
    @SerializedName("id")
    String id;
    @SerializedName("quantity")
    Integer quantity;
    @SerializedName("totalPrice")
    float totalPrice;
    @SerializedName("customerPaid")
    String customerPaid;
    @SerializedName("productEntity")
    Product product;
    @SerializedName("orderEntity")
    Order order;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerPaid() {
        return customerPaid;
    }

    public void setCustomerPaid(String customerPaid) {
        this.customerPaid = customerPaid;
    }
}

package com.example.firstapplication.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    @SerializedName("id")
    String id;
    @SerializedName("orderDate")
    Date orderDate;
    @SerializedName("shipmentDate")
    Date shipmentDate;
    @SerializedName("shipmentStatus")
    String shipmentStatus;
    @SerializedName("totalPrice")
    float totalPrice;
    @SerializedName("customerEntity")
    Customer customer;
    @SerializedName("orderProductEntityList")
    List<OrderProduct> orderProductArrayList;

    int isSelected=0;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderProduct> getOrderProductArrayList() {
        return orderProductArrayList;
    }

    public void setOrderProductArrayList(ArrayList<OrderProduct> orderProductArrayList) {
        this.orderProductArrayList = orderProductArrayList;
    }
}

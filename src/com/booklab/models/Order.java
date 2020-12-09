package com.booklab.models;

import java.sql.Date;

public class Order {

    private int orderID;
    private ShoppingCart SC;
    private boolean orderStatus;
    private Date orderDate;
    private String orderref;

    public Order(int orderID) {
        this.orderID = orderID;
    }

 
    //CONSTRUCTEUR
    public Order(int orderID, ShoppingCart SC, boolean orderStatus, Date orderDate, String orderref) {
        this.orderID = orderID;
        this.SC = SC;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderref = orderref;
    }

    public Order(ShoppingCart SC, boolean orderStatus, Date orderDate) {
        this.SC = SC;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(int orderID, ShoppingCart SC, boolean orderStatus, Date orderDate) {
        this.orderID = orderID;
        this.SC = SC;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
    //GETTERS & SETTERS
    
     public String getOrderref() {
        return orderref;
    }

    public void setOrderref(String orderref) {
        this.orderref = orderref;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public ShoppingCart getCart() {
        return SC;
    }

    public void setCart(ShoppingCart SC) {
        this.SC = SC;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    //EQUALS & HASH
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + orderID;
        result = prime * result + (orderStatus ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        if (orderID != other.orderID) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", SC=" + SC + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + '}';
    }

}

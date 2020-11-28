package com.booklab.models;

import java.sql.Date;

public class Order {
	private int orderID;
	private int cartID;
	private boolean orderStatus;
	private Date orderDate;
	
	
	//CONSTRUCTEUR
	public Order(int cartID, boolean orderStatus, Date orderDate) {
		this.cartID = cartID;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}
	
	public Order(int orderID, int cartID, boolean orderStatus, Date orderDate) {
		this.orderID = orderID;
		this.cartID = cartID;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}
	//GETTERS & SETTERS
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
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
		result = prime * result + cartID;
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderID;
		result = prime * result + (orderStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (cartID != other.cartID)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderID != other.orderID)
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", cartID=" + cartID + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + '}';
    }
	
	
}

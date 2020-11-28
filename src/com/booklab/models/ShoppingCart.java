package com.booklab.models;

import java.util.ArrayList;

public class ShoppingCart {
	private int cartID;
	private int userID;
	private ArrayList<Integer> itemsID = new ArrayList<>();
        private ArrayList<Integer> amounts = new ArrayList<>();
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.cartID;
        hash = 59 * hash + this.userID;
        return hash;
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
        final ShoppingCart other = (ShoppingCart) obj;
        if (this.cartID != other.cartID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        
        return true;
    }



    public ShoppingCart(int userID) {
        this.userID = userID;
    }

    public ShoppingCart(int cartID, int userID) {
        this.cartID = cartID;
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItem(int index){
        return itemsID.get(index);
    }
    public int getItemAmount(int index){
        return amounts.get(index);
    }
    
    public ArrayList<Integer> getItemsID() {
        return itemsID;
    }

    public void setItemsID(ArrayList<Integer> itemsID) {
        this.itemsID = itemsID;
    }
    
    public void addItem(int itemID, int amount) {
        this.itemsID.add(itemID);
        this.amounts.add(amount);
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "cartID=" + cartID + ", userID=" + userID + ", itemsID=" + itemsID + ", amounts=" + amounts + '}';
    }

    
}

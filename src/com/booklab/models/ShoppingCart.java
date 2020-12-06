package com.booklab.models;

import java.util.ArrayList;

public class ShoppingCart {
	private int cartID;
	private int userID;
	private ArrayList<Item> items = new ArrayList<>();
        private ArrayList<Integer> amounts = new ArrayList<>();

    public ShoppingCart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
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

    public Item getItem(int index){
        return items.get(index);
    }
    public int getItemAmount(int index){
        return amounts.get(index);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    public void addItem(Item item, int amount) {
        for(int i=0; i<items.size(); i++){
            if (items.get(i).equals(item)){
                amounts.set(i, amounts.get(i)+amount);
                return;
            }
        }
            this.items.add(item);
            this.amounts.add(amount);
    }   

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }
    
    
    
    public double calculateMontantTotal(){
        double montant = 0;
        for(int i=0; i<items.size(); i++){
            System.out.println("(*) "+items.get(i).getName()+"  "+items.get(i).getPrice()+"$ x "+amounts.get(i)+"   "+(items.get(i).getPrice() * amounts.get(i))+"$");
            montant += items.get(i).getPrice() * amounts.get(i);
        }
        System.out.println("-------------------------------------");
        return montant;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "cartID=" + cartID + ", userID=" + userID + ", itemsID=" + items + ", amounts=" + amounts + '}';
    }

    
}

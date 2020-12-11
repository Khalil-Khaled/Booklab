/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.ArrayList;

/**
 *
 * @author radhw
 */
public class Wishlist {

    private int wishlistID;
    private int customerID;
    private ArrayList<Item> books = new ArrayList<>();

    public Wishlist(int wishlistID, int customerID) {
        this.wishlistID = wishlistID;
        this.customerID = customerID;
    }
    
    public Wishlist(int customerID) {
        this.customerID = customerID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Item getBook(int index) {
        return books.get(index);
    }

    public ArrayList<Item> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Item> books) {
        this.books = books;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "wishlistID=" + wishlistID + ", customerID=" + customerID + '}';
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
        final Wishlist other = (Wishlist) obj;
        if (this.customerID != other.customerID) {
            return false;
        }
        return true;
    }

    public void ajouterBook(Item  book) {
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(book)) {
                found = true;
                return;
            }
        }
        this.books.add(book);
    }

    public void supprimerBook(Item  book) {
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(book)) {
                found = true;
                this.books.remove(book);
            }
        }
        return;
    }
}

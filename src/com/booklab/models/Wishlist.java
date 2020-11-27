/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

/**
 *
 * @author radhw
 */
public class Wishlist {
    private int customerID ;
    private int bookID ;
    private boolean favorite ;

    public Wishlist(int customerID, int bookID, boolean favorite) {
        this.customerID = customerID;
        this.bookID = bookID;
        this.favorite = favorite;
    }

    public Wishlist(int bookID, boolean favorite) {
        this.bookID = bookID;
        this.favorite = favorite;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getBookID() {
        return bookID;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "customerID=" + customerID + ", bookID=" + bookID + ", favorite=" + favorite + '}';
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
        if (this.bookID != other.bookID) {
            return false;
        }
        return true;
    }}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

/**
 *
 * @author steam
 */
public class wishlistBooks {
    
    private int wishlistBooksId;
    private int wishlistId;
    private int BookId;
    private String bookName;
    private String bookDescription;

    public wishlistBooks(int wishlistBooksId, int wishlistId, int BookId,String bookName, String bookDescription) {
        this.wishlistBooksId = wishlistBooksId;
        this.wishlistId = wishlistId;
        this.BookId = BookId;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
    }

    public wishlistBooks(String bookName, String bookDescription) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
    }

    public wishlistBooks(int BookId, String bookName, String bookDescription) {
        this.BookId = BookId;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
    }

    public int getWishlistBooksId() {
        return wishlistBooksId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public int getBookId() {
        return BookId;
    }


    public String getBookName() {
        return bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }
    
    public void setWishlistBooksId(int wishlistBooksId) {
        this.wishlistBooksId = wishlistBooksId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
    
    @Override
    public String toString() {
        return "wishlistBooks{" + "wishlistBooksId=" + wishlistBooksId + ", wishlistId=" + wishlistId + ", BookId=" + BookId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final wishlistBooks other = (wishlistBooks) obj;
        if (this.wishlistBooksId != other.wishlistBooksId) {
            return false;
        }
        return true;
    }
    
    
    
}

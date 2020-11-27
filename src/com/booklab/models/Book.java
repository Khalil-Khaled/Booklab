/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author Naoures Hidri
 */
public class Book extends Item{
   
   private int bookId;
   private String bookName;
   private int categoryId;
   private String bookDescription;
   private Date publishDate;
   private String author;
   private int ageRatingBook;

   public Book(int id, String name, float price, Image image,int bookId, String bookName, int categoryId, String bookDescription, Date publishDate, String author, int ageRatingBook){
       super(id, name, price, image);
       this.bookId=bookId;
       this.bookName=bookName;
       this.categoryId=categoryId;
       this.bookDescription=bookDescription;
       this.publishDate=publishDate;
       this.ageRatingBook=ageRatingBook;
       
   }

    /**
     *
     */
   
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAgeRatingBook() {
        return ageRatingBook;
    }

    public void setAgeRatingBook(int ageRatingBook) {
        this.ageRatingBook = ageRatingBook;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", bookName=" + bookName + ", categoryId=" + categoryId + ", bookDescription=" + bookDescription + ", publishDate=" + publishDate + ", author=" + author + ", ageRatingBook=" + ageRatingBook + '}';
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
        final Book other = (Book) obj;
        if (this.bookId != other.bookId) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (this.ageRatingBook != other.ageRatingBook) {
            return false;
        }
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.bookDescription, other.bookDescription)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publishDate, other.publishDate)) {
            return false;
        }
        return true;
    }

    
}

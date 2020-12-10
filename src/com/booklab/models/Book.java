/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.awt.Image;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Naoures Hidri
 */
public class Book extends Item{
   
   private Date publishDate;
   private String author;
   private int pageNumber;
   


    public Book(int id) {
        super(id);
    }

    public Book(Date publishDate, String author, int pageNumber, int id, String name, int categoryId, String description, float price, String image, int quantity, int rating) {
        super(id, name, categoryId, description, price, image, quantity, rating);
        this.publishDate = publishDate;
        this.author = author;
        this.pageNumber = pageNumber;
    }

    public Book(Date publishDate, String author, int pageNumber, String name, int categoryId, String description, float price, String image, int quantity, int rating) {
        super(name, categoryId, description, price, image, quantity, rating);
        this.publishDate = publishDate;
        this.author = author;
        this.pageNumber = pageNumber;
    }

 

   

    public Date getPublishDate() {
        return publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return super.toString()+"Book{" + "publishDate=" + publishDate + ", author=" + author + ", pageNumber=" + pageNumber + '}';
    }
    
    
   
   

   

    
}

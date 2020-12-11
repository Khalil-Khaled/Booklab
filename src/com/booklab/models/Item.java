/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.awt.Image;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Naoures Hidri
 */
public class Item {
 
    private int id;
    private String name;
    private String categoryName;
    private String description;
    private double price;
    private String image;
    private int quantity;
    private int rating;
    private Date publishDate;
    private String author;
    private int pageNumber;
    private String type;
    
    public Item(int id){
        this.id=id;
    }

    public Item(int id, String name, String categoryName, String description, double price, String image, int quantity, int rating, Date publishDate, String author, int pageNumber, String type) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.rating = rating;
        this.publishDate = publishDate;
        this.author = author;
        this.pageNumber = pageNumber;
        this.type = type;
    }

    public Item(String name, String categoryName, String description, double price, String image, int quantity, int rating, Date publishDate, String author, int pageNumber, String type) {
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.rating = rating;
        this.publishDate = publishDate;
        this.author = author;
        this.pageNumber = pageNumber;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.name);
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
        final Item other = (Item) obj;
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", categoryName=" + categoryName + ", description=" + description + ", price=" + price + ", image=" + image + ", quantity=" + quantity + ", rating=" + rating + ", publishDate=" + publishDate + ", author=" + author + ", pageNumber=" + pageNumber + ", type=" + type + '}';
    }

    



    

}

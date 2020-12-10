/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.awt.Image;
import java.util.Objects;

/**
 *
 * @author Naoures Hidri
 */
public class Item {
 
    private int id;
    private String name;
    private int categoryId;
    private String description;
    private float price;
    private String image;
    private int quantity;
    private int rating;
    
    public Item(int id){
        this.id=id;
    }

    public Item(int id, String name, int categoryId, String description, float price, String image, int quantity, int rating) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.rating = rating;
    }
       public Item(String name, int categoryId, String description, float price, String image, int quantity, int rating) {
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", description=" + description + ", price=" + price + ", image=" + image + ", quantity=" + quantity + ", rating=" + rating + '}';
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    

}

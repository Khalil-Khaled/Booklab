/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.awt.Image;

/**
 *
 * @author Naoures Hidri
 */
public class Accessory extends Item{

    
    private int accessoryId;
    private String accessoryName;
    private int categoryId;
    private String accessoryDescription;
    private Image accessoryImage;
    private int ageRatingAccessory;

    public Accessory(int id, String name, float price, Image image, int accessoryId, String accessoryName, int categoryId, String accessoryDescription, Image accessoryImage,int ageRatingBook){
        super(id, name, price, image);
        this.accessoryId=accessoryId;
        this.accessoryName=accessoryName;
        this.categoryId=categoryId;
        this.accessoryDescription=accessoryDescription;
        this.accessoryImage=accessoryImage;
        this.ageRatingAccessory=ageRatingBook;
    }
    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAccessoryDescription() {
        return accessoryDescription;
    }

    public void setAccessoryDescription(String accessoryDescription) {
        this.accessoryDescription = accessoryDescription;
    }

    public Image getAccessoryImage() {
        return accessoryImage;
    }

    public void setAccessoryImage(Image accessoryImage) {
        this.accessoryImage = accessoryImage;
    }

    public int getAgeRatingAccessory() {
        return ageRatingAccessory;
    }

    public void setAgeRatingAccessory(int ageRatingAccessory) {
        this.ageRatingAccessory = ageRatingAccessory;
    }

    @Override
    public String toString() {
        return "Accessory{" + "accessoryId=" + accessoryId + ", accessoryName=" + accessoryName + ", categoryId=" + categoryId + ", accessoryDescription=" + accessoryDescription + ", accessoryImage=" + accessoryImage + ", ageRatingAccessory=" + ageRatingAccessory + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.accessoryId;
        hash = 71 * hash + Objects.hashCode(this.accessoryName);
        hash = 71 * hash + this.categoryId;
        hash = 71 * hash + Objects.hashCode(this.accessoryDescription);
        hash = 71 * hash + Objects.hashCode(this.accessoryImage);
        hash = 71 * hash + this.ageRatingAccessory;
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
        final Accessory other = (Accessory) obj;
        if (this.accessoryId != other.accessoryId) {
            return false;
        }
        if (this.ageRatingAccessory != other.ageRatingAccessory) {
            return false;
        }
        if (!Objects.equals(this.accessoryName, other.accessoryName)) {
            return false;
        }
        if (!Objects.equals(this.accessoryDescription, other.accessoryDescription)) {
            return false;
        }
        if (!Objects.equals(this.accessoryImage, other.accessoryImage)) {
            return false;
        }
        return true;
    }
    




}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
/**
 *
 * @author radhw
 */
public class Customer extends User{
private int customerId;
private FileInputStream profileImage;
private int rate;
private int wishId;
private int cardId;

    public Customer(FileInputStream profileImage, int rate, int wishId, int cardId, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif);
        this.profileImage = profileImage;
        this.rate = rate;
        this.wishId = wishId;
        this.cardId = cardId;
    }

    public Customer() {
    
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProfileImage(FileInputStream profileImage) {
        this.profileImage = profileImage;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Customer(int customerId, FileInputStream profileImage, int rate, int wishId, int cardId, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif);
        this.customerId = customerId;
        this.profileImage = profileImage;
        this.rate = rate;
        this.wishId = wishId;
        this.cardId = cardId;
    }
 public Customer(int customerId, int rate, int wishId, int cardId, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif);
        this.customerId = customerId;
        this.rate = rate;
        this.wishId = wishId;
        this.cardId = cardId;
 }
    public FileInputStream getProfileImage() {
        return profileImage;
    }

    public int getRate() {
        return rate;
    }

    public int getWishId() {
        return wishId;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.customerId;
        hash = 61 * hash + Objects.hashCode(this.profileImage);
        hash = 61 * hash + this.rate;
        hash = 61 * hash + this.wishId;
        hash = 61 * hash + this.cardId;
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
        final Customer other = (Customer) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        if (this.wishId != other.wishId) {
            return false;
        }
        if (this.cardId != other.cardId) {
            return false;
        }
        if (!Objects.equals(this.profileImage, other.profileImage)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Customer{" + "customerId=" + customerId + ", rate=" + rate + ", wishId=" + wishId + ", cardId=" + cardId + '}';
    }

    

}

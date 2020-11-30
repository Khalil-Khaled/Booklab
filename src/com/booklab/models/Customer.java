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
private int rate;
private int wishId;
private int cardId;

    public Customer(int userid, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif,String profilimage,int rate, int wishId, int cardId) {
        super(userid, userName, firstName, profilimage, lastName, email, password, questionVerif, answerVerif);
        this.rate = rate;
        this.wishId = wishId;
        this.cardId = cardId;
    }

    public Customer(String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif,String profilimage,int rate, int wishId, int cardId) {
        super(userName, firstName, profilimage, lastName, email, password, questionVerif, answerVerif);
        this.rate = rate;
        this.wishId = wishId;
        this.cardId = cardId;
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

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.rate;
        hash = 19 * hash + this.wishId;
        hash = 19 * hash + this.cardId;
        return hash;
    }



    @Override
    public String toString() {
        return super.toString()+"Customer{" + "rate=" + rate + ", wishId=" + wishId + ", cardId=" + cardId + '}';
    }

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
        if (!Objects.equals(this.getEmail(), other.getEmail())) {
            return false;
        }
        return true;
    }

}

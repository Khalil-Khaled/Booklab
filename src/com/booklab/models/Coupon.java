/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Coupon {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private int couponID;
    private String codeCoupon;
    private boolean validity;
    private Date expirationDate;
    
    public Coupon(int couponID, String codeCoupon, boolean validity,Date expirationDate ) {
        this.couponID = couponID;
        this.codeCoupon = codeCoupon;
        this.validity = validity;
        this.expirationDate=expirationDate;
    }

    public Coupon(String codeCoupon, boolean validity,Date expirationDate) {
        this.codeCoupon = codeCoupon;
        this.validity = validity;
        this.expirationDate=expirationDate;
    }


    public int getCouponID() {
        return couponID;
    }

    public String getCodeCoupon() {
        return codeCoupon;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public void setCodeCoupon(String codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
        final Coupon other = (Coupon) obj;
        if (!Objects.equals(this.codeCoupon, other.codeCoupon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coupon{" + "couponID=" + couponID + ", codeCoupon=" + codeCoupon + ", validity=" + validity + ", expirationDate=" + expirationDate + '}';
    }


}

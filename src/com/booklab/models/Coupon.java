/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Coupon {

    private int couponID;
    private String codeCoupon;
    private boolean validity;

    public Coupon(int couponID, String codeCoupon, boolean validity) {
        this.couponID = couponID;
        this.codeCoupon = codeCoupon;
        this.validity = validity;
    }

    public Coupon(String codeCoupon, boolean validity) {
        this.codeCoupon = codeCoupon;
        this.validity = validity;
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
        if (this.couponID != other.couponID) {
            return false;
        }
        if (this.validity != other.validity) {
            return false;
        }
        if (!Objects.equals(this.codeCoupon, other.codeCoupon)) {
            return false;
        }
        return true;
    }

}

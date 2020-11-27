/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.Objects;

/**
 *
 * @author radhw
 */
public class Coupon {
    private int couponId;
    private String codeCoupon;
    private Boolean validity;

    public Coupon(int couponId, String codeCoupon, Boolean validity) {
        this.couponId = couponId;
        this.codeCoupon = codeCoupon;
        this.validity = validity;
    }

    public Coupon(String codeCoupon, Boolean validity) {
        this.codeCoupon = codeCoupon;
        this.validity = validity;
    }

    public int getCouponId() {
        return couponId;
    }

    public String getCodeCoupon() {
        return codeCoupon;
    }

    public Boolean getValidity() {
        return validity;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public void setCodeCoupon(String codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.couponId;
        hash = 53 * hash + Objects.hashCode(this.codeCoupon);
        hash = 53 * hash + Objects.hashCode(this.validity);
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
        final Coupon other = (Coupon) obj;
        if (this.couponId != other.couponId) {
            return false;
        }
        if (!Objects.equals(this.codeCoupon, other.codeCoupon)) {
            return false;
        }
        if (!Objects.equals(this.validity, other.validity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coupon{" + "couponId=" + couponId + ", codeCoupon=" + codeCoupon + ", validity=" + validity + '}';
    }
    
}

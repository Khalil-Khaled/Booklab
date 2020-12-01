/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

/**
 *
 * @author Khalil
 */
public class Offer {
    private int idOffer;
    private String typeOffer;
    private float priceOffer;
    private String descriptionOffer;
    private boolean offerStatus=false;
    private int idCustomer;

    public Offer(String typeOffer, float priceOffer, String descriptionOffer, int idCustomer) {
        this.typeOffer = typeOffer;
        this.priceOffer = priceOffer;
        this.descriptionOffer = descriptionOffer;
        this.idCustomer=idCustomer;
        
    }

    public Offer(int idOffer, String typeOffer, float priceOffer, String descriptionOffer, boolean offerStatus,int idCustomer) {
        this.idOffer = idOffer;
        this.typeOffer = typeOffer;
        this.priceOffer = priceOffer;
        this.descriptionOffer = descriptionOffer;
        this.offerStatus = offerStatus;
        this.idCustomer=idCustomer;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public String getTypeOffer() {
        return typeOffer;
    }

    public void setTypeOffer(String typeOffer) {
        this.typeOffer = typeOffer;
    }

    public float getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(float priceOffer) {
        this.priceOffer = priceOffer;
    }

    public String getDescriptionOffer() {
        return descriptionOffer;
    }

    public void setDescriptionOffer(String descriptionOffer) {
        this.descriptionOffer = descriptionOffer;
    }

    public String getOfferStatus() {
        return offerStatus? "TerminÃ©" : "En cours";
    }

    public boolean isOfferStatus() {
        return offerStatus;
    }
    
    public void setOfferStatus(boolean offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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
        final Offer other = (Offer) obj;
        if (this.idOffer != other.idOffer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offer{" + "idOffer=" + idOffer + ", typeOffer=" + typeOffer + ", priceOffer=" + priceOffer + ", descriptionOffer=" + descriptionOffer + ", offerStatus=" + offerStatus + '}';
    }
    
    
}

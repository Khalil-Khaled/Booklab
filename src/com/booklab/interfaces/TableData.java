/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booklab.interfaces;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class TableData {
    private int item_id;
    private String item_name;
    private int amount;
    private double prix_unitaire;

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
    private double total;
    
    
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public TableData(int item_id, String item_name, int amount, double prix_unitaire, double total) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.amount = amount;
        this.prix_unitaire = prix_unitaire;
        this.total = total;
    }

   

    @Override
    public String toString() {
        return "TableData{" + "item_id=" + item_id + ", item_name=" + item_name + ", amount=" + amount + ", total=" + total + '}';
    }

  

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TableData(String item_name, int amount, double total) {
        this.item_name = item_name;
        this.amount = amount;
        this.total = total;
    }

    
}
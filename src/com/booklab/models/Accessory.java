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
public class Accessory extends Item{

    public Accessory(int id, String name, int categoryId, String description, float price, String image, int quantity, int rating) {
        super(id, name, categoryId, description, price, image, quantity, rating);
    }

    public Accessory(int id) {
        super(id);
    }
  
}

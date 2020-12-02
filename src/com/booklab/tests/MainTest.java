/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import com.booklab.Utils.DataSource;
import com.booklab.models.Accessory;
import com.booklab.models.Admin;
import com.booklab.models.Book;
import com.booklab.models.Customer;
import com.booklab.models.Item;
import com.booklab.models.Offer;
import com.booklab.models.Order;
import com.booklab.models.ShoppingCart;
import com.booklab.services.AdminServices;
import com.booklab.services.CustomerServices;
import com.booklab.services.OfferServices;
import com.booklab.services.ServicesOrder;
import com.booklab.services.ServicesShoppingCart;
import java.io.FileInputStream;
import java.sql.Date;

/**
 *
 * @author radhw
 */
public class MainTest {
    
    public static void main(String args[]){
        ServicesShoppingCart ssc = new ServicesShoppingCart();
        ServicesOrder so = new ServicesOrder();

        Item b = new Book(new Date(2020-1900, 7, 21), "Mehdi", 50, 2, "Mehdi", 0, "N/A", 5, "", 5, 5);
        Item b1 = new Book(new Date(2020-1900, 7, 21), "SALEM", 50, 2, "Mehdi", 0, "N/A", 5, "", 5, 5);
        Item b2 = new Book(new Date(2020-1900, 7, 21), "TEST", 50, 2, "Mehdi", 0, "N/A", 5, "", 5, 5);
        ShoppingCart SC = new ShoppingCart(1,1);
            SC.addItem(b, 5);
            SC.addItem(b1, 1);
            SC.addItem(b2, 4);
        ssc.addItemsToCart(SC);

        
                
        Order O = new Order(SC, false, new Date(2020-1900, 7, 21));
        //so.insertOrder(O);
        System.out.println(SC);
       

         //System.out.println(SC);
        //ssc.createCart(SC);
        //ssc.addItemsToCart(SC);
        
        
        
       /* System.out.println(SC);
        System.out.println("-------------------------------------");
        System.out.println("Montant Total:              "+SC.calculateMontantTotal()+"$");
        System.out.println("-------------------------------------");
        */
        //Order O = new Order(SC, true, new Date(2020, 5, 5));
        //so.insertOrder(O);
        
        //ssc.clearCart(SC);
        //ssc.removeCart(SC);
    }
}

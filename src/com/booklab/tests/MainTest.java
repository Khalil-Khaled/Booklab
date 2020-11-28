/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import com.booklab.Utils.DataSource;
import com.booklab.models.Admin;
import com.booklab.models.Customer;
import com.booklab.models.Offer;
import com.booklab.models.ShoppingCart;
import com.booklab.services.AdminServices;
import com.booklab.services.CustomerServices;
import com.booklab.services.OfferServices;
import com.booklab.services.ServicesShoppingCart;
import java.io.FileInputStream;

/**
 *
 * @author radhw
 */
public class MainTest{
    public static void main(String [] args)throws Exception{
//    FileInputStream fin = new FileInputStream("E:\\images\\javafx_logo.jpg");
//    Customer c = new Customer(2,fin,1,235,456,"","","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//    CustomerServices s = new CustomerServices();
//        Admin a = new Admin("ra","raaa","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//               Admin a1 = new Admin("ra","raaa","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//
//        AdminServices aa=new AdminServices();
//       aa.delete(a1);
//       System.out.println(aa.show());
       //Offer offer = new Offer ("vente", 100, "vente de livre",1);
       //OfferServices sss=new OfferServices();
//       sss.createOffer(offer);
       //sss.removeOffer(new Offer (1,"vente", 100, "vente de livre",false,1));
       
       testCart();
    }
    

    
    public static void testCart(){
        ServicesShoppingCart ssc = new ServicesShoppingCart();
        //ServicesOrder so = new ServicesOrder();
        
        /*ShoppingCart SC = new ShoppingCart(1);
            SC.addItem(1, 5);
            SC.addItem(2, 1);
            SC.addItem(3, 4);
        ssc.insert(SC);

        
                
        Order O = new Order(SC.getCartID(), false, new Date(2020-1900, 7, 21));
        so.insert(O);
        System.out.println(SC);
        System.out.println(O);*/
        
        ShoppingCart SC = new ShoppingCart(1,1);
            SC.addItem(55, 1);
            SC.addItem(45, 3);
        
        ssc.createCart(SC);
        ssc.addItemsToCart(SC);
        
        
        
        System.out.println(ssc.getCartItems(SC));
        
        ssc.clearCart(SC);
        ssc.removeCart(SC);
    }
}

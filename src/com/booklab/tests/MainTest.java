/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import com.booklab.Utils.DataSource;
import com.booklab.models.Admin;
import com.booklab.models.Book;
import com.booklab.models.Customer;
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

    public static void main(String[] args) throws Exception {

//      Customer c = new Customer("radhw.05","radhwene","elhafi","radhwene@emai.com","-----66","the pet?","samouel","pic.jpg",1,2,3);
////      Customer c1 = new Customer(17,"radhw.05","","","radhwene@emai.com","-----66","the pet?","samouel","pic.jpg",1,2,3);
     CustomerServices s = new CustomerServices();
//      s.create(c);
////      s.modify(c);
////      s.delete(c1);
//      System.out.println(s.show());
//     Admin a = new Admin("radhwene05","radhwene","elhafi","elhafi@gmail.com","radhwej134564","radhwene??","byrsa","pige.png");   
//      Admin a1 = new Admin(16," ","","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou","pig.png");   
////        
//      AdminServices aa=new AdminServices();
//      aa.create(a);
////      aa.modify(a1);
////      aa.delete(a1);
////      System.out.println(aa.show());  
//     System.out.println(aa.show());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////// radhwene test dont toutch
////       Offer offer = new Offer ("vente", 100, "vente de livre",1);
//        //OfferServices sss=new OfferServices();
////       sss.createOffer(offer);
//        //sss.removeOffer(new Offer (1,"vente", 100, "vente de livre",false,1));
//        //testCart();
    }

//    public static void testCart(){
//        ServicesShoppingCart ssc = new ServicesShoppingCart();
//        ServicesOrder so = new ServicesOrder();
//        
//        /*ShoppingCart SC = new ShoppingCart(1);
//            SC.addItem(1, 5);
//            SC.addItem(2, 1);
//            SC.addItem(3, 4);
//        ssc.insert(SC);
//
//        
//                
//        Order O = new Order(SC.getCartID(), false, new Date(2020-1900, 7, 21));
//        so.insert(O);
//        System.out.println(SC);
//        System.out.println(O);*/
//        
//        ShoppingCart SC = new ShoppingCart(1,1);
//        Book b = new Book(0, "KTEB 9RA2A", 5, 35, "", 0, "", 0, "1er Année", new Date(2020-1900, 3, 1), "Mehdi", 3);
//            SC.addItem(b, 1);
//            SC.addItem(b, 3);
//        Book b1 = new Book(1, "KTEB FALSFA", 3, 20, "", 0, "", 0, "2eme Année", new Date(2020-1900, 3, 1), "Rami", 5);
//            SC.addItem(b1, 1);
//        //ssc.createCart(SC);
//        //ssc.addItemsToCart(SC);
//        
//        
//        
//       /* System.out.println(SC);
//        System.out.println("-------------------------------------");
//        System.out.println("Montant Total:              "+SC.calculateMontantTotal()+"$");
//        System.out.println("-------------------------------------");
//        */
//        Order O = new Order(SC, true, new Date(2020, 5, 5));
//        //so.insertOrder(O);
//        
//        //ssc.clearCart(SC);
//        ssc.removeCart(SC);
//    }
}

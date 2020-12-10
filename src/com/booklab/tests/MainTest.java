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

import com.booklab.models.*;

import com.booklab.services.*;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author radhw
 */
public class MainTest {

    public static void main(String[] args) throws Exception {


//      Customer c = new Customer("radhw.05","radhwene","elhafi","radhwene@emai.com","-----66","the pet?","samouel","pic.jpg",1,2,3);
////      Customer c1 = new Customer(17,"radhw.05","","","radhwene@emai.com","-----66","the pet?","samouel","pic.jpg",1,2,3);
//     CustomerServices s = new CustomerServices();
//     CategoryServices cs = new CategoryServices();
//     Category c =new Category("Action and Adventure");
//     Category c1 =new Category("Classics");
//     Category c2 =new Category("Comic Book or Graphic Novel");
//     Category c3 =new Category("Detective and Mystery");
//   
     
     
//        System.out.println(s.showemail("sinda.sghair@esprit.tn"));
//      s.create(c);
////      s.modify(c);
////      s.delete(c1);
//      System.out.println(s.show());
//String hashed = BCrypt.hashpw("radhwen", BCrypt.gensalt());
//if (BCrypt.checkpw("radhwen", hashed))
//	System.out.println("It matches");
//else
//	System.out.println("It does not match");

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

//    FileInputStream fin = new FileInputStream("E:\\images\\javafx_logo.jpg");
//    Customer c = new Customer(2,fin,1,235,456,"","","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//    CustomerServices s = new CustomerServices();
//        Admin a = new Admin("ra","raaa","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//               Admin a1 = new Admin("ra","raaa","elhafi","elahfi@radhwene.com","RADH123","the pet of mmy child","kangou");
//
//        AdminServices aa=new AdminServices();
//       aa.delete(a1);
//       System.out.println(aa.show());
//       Offer offer = new Offer ("vente", 100, "vente de livre",1);
//       OfferServices sss=new OfferServices();
////       sss.createOffer(offer);
//       sss.removeOffer(new Offer (1,"vente", 100, "vente de livre",false,1));
    /*   Complaint complaint1 = new Complaint("Wrong order ", "ORDER", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Complaint complaint2 = new Complaint("Wrong offer ", "OFFER", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        Complaint complaint3 = new Complaint(4, "Wrong order ", "ORDER", "cccccccccccccccccccccccccccccccccccccccccc");
        ComplaintServices complaintService = new ComplaintServices();
        complaintService.add(complaint1);
        complaintService.add(complaint2);
        complaintService.add(complaint3);
        System.out.println(complaintService.showAll()); */
        //complaintService.delete(complaint3);
        /*String sDate1 = "31/12/1998";
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        System.out.println(date1);
         */
        // complaintService.show(1);
        /*Coupon c1 = new Coupon("12ee2a", true, new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2020"));
        Coupon c2 = new Coupon("857e2a", false, new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2020"));

        CouponServices couponServices = new CouponServices();*/
        // couponServices.add(c1);
        //couponServices.add(c2);
        // couponServices.show(17);
        //couponServices.showAll();
//        ComplaintResponse cr1 = new ComplaintResponse(1, 2, "aaaaaaaaaaaaaaa", "COMPLETED");
//        ComplaintResponseServices complaintResponseServices = new ComplaintResponseServices();
//        //  complaintResponseServices.show(1);
//

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

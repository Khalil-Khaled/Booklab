/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import com.booklab.Utils.DataSource;
import com.booklab.models.*;

import com.booklab.services.*;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;

/**
 *
 * @author radhw
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
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
        ComplaintResponse cr1 = new ComplaintResponse(1, 2, "aaaaaaaaaaaaaaa", "COMPLETED");
        ComplaintResponseServices complaintResponseServices = new ComplaintResponseServices();
        //  complaintResponseServices.show(1);

    }
}

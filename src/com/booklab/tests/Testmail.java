/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import com.booklab.views.JavaMailUtil;

/**
 *
 * @author radhw
 */
public class Testmail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JavaMailUtil.sendMail("khalil.khaled@esprit.tn");
    }
    
}

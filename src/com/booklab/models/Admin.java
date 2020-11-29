/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

/**
 *
 * @author radhw
 */
public class Admin extends User{ 
    public Admin(int userid, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif, String profilimage) {
        super(userid, userName, firstName, lastName, email, password, questionVerif, answerVerif, profilimage);
    }

    public Admin(String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif, String profilimage) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif, profilimage);
    }

    @Override
    public String toString() {
        return "Admin"+super.toString();
    }

  
  
}

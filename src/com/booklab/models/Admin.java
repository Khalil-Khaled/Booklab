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
    private int adminId;

    public Admin(int adminId, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif);
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.adminId;
        return hash;
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
        final Admin other = (Admin) obj;
        if (this.adminId != other.adminId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Admin{" + "adminId=" + adminId + '}';
    }
    
    public Admin(String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif) {
        super(userName, firstName, lastName, email, password, questionVerif, answerVerif);
    }

    public Admin() {
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.Objects;

/**
 *
 * @author radhw
 */
public abstract class User {

    private int userid;
    private String userName;
    private String firstName;
    private String profilimage;
    private String lastName;
    private String email;
    private String password;
    private String questionVerif;
    private String answerVerif;
    public User(int userid, String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif, String profilimage) {
        this.userid = userid;
        this.userName = userName;
        this.firstName = firstName;
        this.profilimage = profilimage;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.questionVerif = questionVerif;
        this.answerVerif = answerVerif;
    }

    public User(String userName, String firstName, String lastName, String email, String password, String questionVerif, String answerVerif, String profilimage) {
        this.userName = userName;
        this.firstName = firstName;
        this.profilimage = profilimage;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.questionVerif = questionVerif;
        this.answerVerif = answerVerif;
    }

    public User() {
    }



    public int getUserid() {
        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getProfilimage() {
        return profilimage;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getQuestionVerif() {
        return questionVerif;
    }

    public String getAnswerVerif() {
        return answerVerif;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setProfilimage(String profilimage) {
        this.profilimage = profilimage;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQuestionVerif(String questionVerif) {
        this.questionVerif = questionVerif;
    }

    public void setAnswerVerif(String answerVerif) {
        this.answerVerif = answerVerif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.userid;
        hash = 79 * hash + Objects.hashCode(this.userName);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.profilimage);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.questionVerif);
        hash = 79 * hash + Objects.hashCode(this.answerVerif);
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
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", userName=" + userName + ", firstName=" + firstName + ", profilimage=" + profilimage + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", questionVerif=" + questionVerif + ", answerVerif=" + answerVerif + '}';
    }
    
}

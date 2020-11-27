/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.*; 

/**
 *
 * @author steam
 */
public class Event {
    private int id ;
    private int organizerID ;
    private String organizerName ;
    private String name ;
    private String desciption ;
    private Date date  ; 
    private String location ;
    private int customerID ;
    
    public Event(String name,int organizerID , String organizerName ,String desciption,Date date, String location) {
        this.name = name;
        this.organizerID = organizerID ;
        this.organizerName = organizerName;
        this.desciption = desciption;
        this.date = date ;
        this.location = location ;
    }

    public Event(String organizerName, String name, String desciption, Date date , String location) {
        this.organizerName = organizerName;
        this.name = name;
        this.desciption = desciption;
        this.date = date ;
        this.location = location;
    }
    
    public Event(int id, String name,String organizerName, String description, String location) {
        this.id = id;
        this.name = name;
        this.organizerName = organizerName;
        this.desciption = description ;
        this.location = location;
    }
    
    public int getId() {
        return id;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getOrganizerID() {
        return organizerID;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setCustomerID(int customerID){
    this.customerID = customerID ;
    }

    public void setOrganizerID(int organizerID) {
        this.organizerID = organizerID;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", organizerName=" + organizerName + ", name=" + name + ", desciption=" + desciption + ", date=" + date + ", location=" + location + ", events=" + customerID + '}';
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
        final Event other = (Event) obj;
        return Objects.equals(this.date, other.date);
    }
}

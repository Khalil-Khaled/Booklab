/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.Date;
import java.util.Objects;




/**
 *
 * @author user
 */
public class Complaint {

    private int complaintId;
    private String topic;
    private String message;
    private String type;
    private int userId;
    private   String status;

    public Complaint(int complaintId,int userId, String topic, String type, String message,String status ) {
        this.complaintId = complaintId;
        this.topic = topic;
        this.type = type;
        this.message = message;
        this.userId=userId;
        this.status=status;
    }

    public Complaint(int userId,String topic, String type, String message,String status) {
        this.topic = topic;
        this.type = type;
        this.message = message;
        this.userId=userId;
        this.status=status;
    }

    public Complaint(int userId,String topic, String message, String type) {
        this.topic = topic;
        this.message = message;
        this.type = type;
        this.userId = userId;
    }



    public int getComplaintId() {
        return complaintId;
    }

    public String getTopic() {
        return topic;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
        final Complaint other = (Complaint) obj;
        if (this.complaintId != other.complaintId) {
            return false;
        }
        if (!Objects.equals(this.topic, other.topic)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "Complaint{" + "complaintId=" + complaintId + ", topic=" + topic + ", type=" + type + ", message=" + message + '}';
    }

}

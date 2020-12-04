/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.util.Objects;

/**
 *
 * @author user
 */
public class ComplaintResponse {
   private int  complaintResponseID;
    private String response ;
    private String complaintStatus ;
    private int userId;
     private int complaintId;
     
    public ComplaintResponse(int complaintResponseID, int userId,int complaintId, String Response, String complaintStatus) {
        this.complaintResponseID = complaintResponseID;
        this.userId=userId;
        this.complaintId=complaintId;
        this.response = Response;
        this.complaintStatus = complaintStatus;
    }

    public ComplaintResponse(int userId,int complaintId,String Response, String complaintStatus) {
        this.userId=userId;
        this.complaintId=complaintId;
        this.response = Response;
        this.complaintStatus = complaintStatus;
    }

    public int getComplaintIResponseID() {
        return complaintResponseID;
    }

    public String getResponse() {
        return response;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setComplaintIResponseID(int complaintIResponseID) {
        this.complaintResponseID = complaintIResponseID;
    }

    public void setResponse(String Response) {
        this.response = Response;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getComplaintResponseID() {
        return complaintResponseID;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintResponseID(int complaintResponseID) {
        this.complaintResponseID = complaintResponseID;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
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
        final ComplaintResponse other = (ComplaintResponse) obj;
        if (this.complaintResponseID != other.complaintResponseID) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.complaintId != other.complaintId) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        if (this.complaintStatus != other.complaintStatus) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComplaintResponse{" + "complaintResponseID=" + complaintResponseID + ", response=" + response + ", complaintStatus=" + complaintStatus + ", userId=" + userId + ", complaintId=" + complaintId + '}';
    }

  

    
}

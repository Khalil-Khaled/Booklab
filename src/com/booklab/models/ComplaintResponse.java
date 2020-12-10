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

    private int complaintResponseID;
    private String response;
    private String complaintStatus;
  
    private int complaintId;
    private String customerInfo;

    public ComplaintResponse(int complaintResponseID, int complaintId,String customerInfo, String Response, String complaintStatus) {
        this.complaintResponseID = complaintResponseID;
        this.complaintId = complaintId;
        this.response = Response;
        this.complaintStatus = complaintStatus;
        this.customerInfo = customerInfo;
    }

    public ComplaintResponse(int complaintId, String customerInfo, String Response, String complaintStatus) {
        this.complaintId = complaintId;
        this.response = Response;
        this.complaintStatus = complaintStatus;
        this.customerInfo = customerInfo;

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

    public void setComplaintIResponseID(int complaintIResponseID) {
        this.complaintResponseID = complaintIResponseID;
    }

    public void setResponse(String Response) {
        this.response = Response;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
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

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ComplaintResponse other = (ComplaintResponse) obj;
        if (this.complaintResponseID != other.complaintResponseID) {
            return false;
        }
        if (this.complaintId != other.complaintId) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        if (!Objects.equals(this.complaintStatus, other.complaintStatus)) {
            return false;
        }
        if (!Objects.equals(this.customerInfo, other.customerInfo)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "ComplaintResponse{" + "complaintResponseID=" + complaintResponseID + ", response=" + response + ", complaintStatus=" + complaintStatus + ", complaintId=" + complaintId + ", customerInfo=" + customerInfo + '}';
    }

    

}

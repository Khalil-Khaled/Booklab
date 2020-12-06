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
public class Invitation {

    private int invitationID;
    private int senderID;
    private int receiverID;
    private boolean invitationStatus;

    public Invitation(int invitationID, int senderID, int receiverID, boolean invitationStatus) {
        this.invitationID = invitationID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.invitationStatus = invitationStatus;
    }

    public Invitation(int senderID, int receiverID, boolean invitationStatus) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.invitationStatus = invitationStatus;
    }

    public int getInvitationID() {
        return invitationID;
    }

    public int getSernderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public boolean isInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationID(int invitationID) {
        this.invitationID = invitationID;
    }

    public void setSernderID(int senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public void setInvitationStatus(boolean invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    @Override
    public String toString() {
        return "Invitation{" + "invitationID=" + invitationID + ", senderID=" + senderID + ", receiverID=" + receiverID + ", invitationStatus=" + invitationStatus + '}';
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
        final Invitation other = (Invitation) obj;
        if (this.senderID != other.senderID) {
            return false;
        }
        if (this.receiverID != other.receiverID) {
            return false;
        }
        return true;
    }
}

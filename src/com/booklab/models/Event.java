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

    private int idEvent;
    private int eventOrganizerID;
    private String eventName;
    private String eventDesciption;
    private Date eventDate;
    private String eventLocation;
    private ArrayList<Customer> participants = new ArrayList<>();

    public Event(String eventName, int eventOrganizerID, String eventDesciption, Date eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventOrganizerID = eventOrganizerID;
        this.eventDesciption = eventDesciption;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public Event(String eventName, String eventDesciption, Date eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public Event(int idEvent, String eventName, String eventDesciption, String eventLocation) {
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.eventLocation = eventLocation;
    }

    public int getId() {
        return idEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDesciption() {
        return eventDesciption;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public int getEventOrganizerID() {
        return eventOrganizerID;
    }

    public Customer getParticipant(int index) {
        return participants.get(index);
    }

    public ArrayList<Customer> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Customer> participants) {
        this.participants = participants;
    }

    public void setId(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDesciption(String eventDesciption) {
        this.eventDesciption = eventDesciption;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventOrganizerID(int eventOrganizerID) {
        this.eventOrganizerID = eventOrganizerID;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", eventOrganizerID=" + eventOrganizerID + ", eventName=" + eventName + ", eventDesciption=" + eventDesciption + ", eventDate=" + eventDate + ", eventLocation=" + eventLocation + '}';
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
        if (this.eventOrganizerID != other.eventOrganizerID) {
            return false;
        }
        if (!Objects.equals(this.eventLocation, other.eventLocation)) {
            return false;
        }
        if (!Objects.equals(this.eventDate, other.eventDate)) {
            return false;
        }
        return true;
    }

    public void ajouterParticipant(Customer participant) {
        boolean found = false;
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).equals(participant)) {
                found = true;
                return;
            }
        }
        this.participants.add(participant);
    }

    public void supprimerBook(Book book) {
        boolean found = false;
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).equals(book)) {
                found = true;
                this.participants.remove(book);
            }
        }
        return;
    }

}

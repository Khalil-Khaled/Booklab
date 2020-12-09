/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author steam
 */
public class Event {

    private int idEvent;
    private int eventOrganizerID;
    private String organizerName;
    private String eventName;
    private String eventDesciption;
    private Date eventStartDate;
    private Date eventEndDate;
    private String eventLocation;
    private int participantID;
    private String participantName;
    private String participantEmail;
    private ArrayList<Customer> participants = new ArrayList<>();

    public Event(int idEvent) {
        this.idEvent = idEvent;
    }
    
    public Event(int idEvent ,String organizerName,String eventName, String eventDesciption, Date eventStartDate,Date eventEndDate ,String eventLocation) {
        this.idEvent = idEvent;
        this.organizerName=organizerName;
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventLocation = eventLocation;
    }

    public Event(String eventName, String eventDesciption, Date eventStartDate, Date eventEndDate ,String eventLocation) {
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventLocation = eventLocation;
    }

    public Event(int idEvent,String organizerName, String eventName, String eventDesciption, String eventLocation) {
        this.idEvent = idEvent;
        this.organizerName=organizerName;
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.eventLocation = eventLocation;
    }

    public Event(String participantName, String participantEmail) {
        this.participantName = participantName;
        this.participantEmail = participantEmail;
    }
    
    public int getIdEvent() {
        return idEvent;
    }

    public String getOrganizerName() {
        return organizerName;
    }
    
    public String getEventName() {
        return eventName;
    }

    public String getEventDesciption() {
        return eventDesciption;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
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

    public int getParticipantID() {
        return participantID;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getParticipantEmail() {
        return participantEmail;
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

    public void setEventStartDate(Date eventDate) {
        this.eventStartDate = eventDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
    
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }
    
    public void setEventOrganizerID(int eventOrganizerID) {
        this.eventOrganizerID = eventOrganizerID;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", eventOrganizerName=" + organizerName + ", eventName=" + eventName + ", eventDesciption=" + eventDesciption + ", eventStartDate=" + eventStartDate +", evenEndDate"+ eventEndDate + ", eventLocation=" + eventLocation + '}';
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
        if (this.idEvent != other.idEvent) {
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

    public void supprimerParticipant(Customer participant) {
        boolean found = false;
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).equals(participant)) {
                found = true;
                this.participants.remove(participant);
            }
        }
        return;
    }

}

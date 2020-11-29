/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Event;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steam
 */
public class EventServices {

    Connection cnx = DataSource.getInstance().getCnx();

    //Ajouter Event
    public void creerEvent(Event e) {
        try {
            String req = "INSERT INTO event (eventOrganizerID,eventName,eventDescription,eventDate,eventLocation) VALUES (null,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, e.getEventOrganizerID());
            st.setString(2, e.getEventName());
            st.setString(3, e.getEventDesciption());
            st.setDate(4, (Date) e.getEventDate());
            st.setString(5, e.getEventLocation());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            System.out.println("Event ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Supprimer Event
    public void supprimerEvent(Event e) {
        try {
            String req = "DELETE FROM event WHERE eventID=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getId());
            st.executeUpdate();
            System.out.println("Event supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Modifier le nom, la description et le lieu
    public void modifierEvent(Event e) {
        try {
            String req = "UPDATE event set eventName=?,eventDescription =?,eventLocation=? where eventID=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, e.getEventName());
            st.setString(2, e.getEventDesciption());
            st.setString(3, e.getEventLocation());
            st.setInt(4, e.getId());
            st.executeUpdate();
            System.out.println("Event modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //AFFICHER EVET
    public Event afficherEvent(int idEvent) {
        Event event = null;
        try {
            String req = "SELECT * FROM event where eventID=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idEvent);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                event = new Event(res.getString(1), res.getString(2), res.getDate(4), res.getString(5));
            }
            System.out.println("Event récuperé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return event;
    }

    //afficher events
    public List<Event> afficherEvents() {

        List<Event> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM event where eventDate < (SELECT current_timestamp) ";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Event(res.getString(1), res.getInt(2), res.getString(3), res.getDate(5), res.getString(6)));
            }
            System.out.println("Events récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void ajouterParticipantEvent(Event event) {
        PreparedStatement st;
        String REQ = "";
        try {

            for (int i = 0; i < event.getParticipants().size(); i++) {
                REQ = "INSERT INTO event_participant values (null, ?, ?, ?)";
                st = cnx.prepareStatement(REQ);
                st.setInt(1, event.getIdEvent());
                st.setInt(2, event.getParticipant(i).getCustomerId());
                st.setInt(3, event.getParticipants().size());
                st.executeUpdate();
                System.out.println("Participant ajouté");
            }

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

}

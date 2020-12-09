/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Customer;
import com.booklab.models.Event;
import com.booklab.views.UserloginController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author steam
 */
public class EventServices {

    Connection cnx = DataSource.getInstance().getCnx();

    //Ajouter Event
    public void creerEvent(Event e) {
        try {
            String req = "INSERT INTO event (eventOrganizerID,eventName,eventDescription,eventStartDate,eventEndDate,eventLocation) VALUES (null,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, e.getEventName());
            st.setString(2, e.getEventDesciption());
            st.setDate(3, (Date) e.getEventStartDate());
            st.setDate(4, (Date) e.getEventEndDate());
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
            st.setInt(1, e.getIdEvent());
            st.executeUpdate();
            System.out.println("Event supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void organizerSupprimerEvent(Event e) {
        try {
            String req = "DELETE FROM event WHERE eventID=? and eventorganizerid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getIdEvent());
            st.setInt(2, UserloginController.idlogin);
            st.executeUpdate();
            System.out.println("Event supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierEvent(Event e) {
        try {
            String req = "UPDATE event set eventName=?,eventDescription =?,eventLocation=? where eventID=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, e.getEventName());
            st.setString(2, e.getEventDesciption());
            st.setString(3, e.getEventLocation());
            st.setInt(4, e.getIdEvent());
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
                event = new Event(res.getString(1), res.getString(2), res.getDate(4), res.getDate(5), res.getString(6));
            }
            System.out.println("Event récuperé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return event;
    }

    public void ajouterParticipantEvent(Event event) {
        PreparedStatement st;
        String REQ = "";
        try {

            for (int i = 0; i < event.getParticipants().size(); i++) {
                REQ = "INSERT INTO event_participant values (null, ?, ?)";
                st = cnx.prepareStatement(REQ);
                st.setInt(1, event.getIdEvent());
                st.setInt(2, event.getParticipantID());
                st.executeUpdate();
                System.out.println("Participant ajouté");
            }

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

//    //afficher events
//    public ObservableList<Event> afficherEvents() {
//        Connection cnx = DataSource.getInstance().getCnx();
//        ObservableList<Event> list = FXCollections.observableArrayList();
//        try {
//            String req = "SELECT * FROM event ";
//            PreparedStatement st = cnx.prepareStatement(req);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                list.add(new Event(res.getInt(1), res.getString(3), res.getString(4),res.getString(7)));
//            }
//            System.out.println("Events récuperés");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;
//    }
//    afficher events
//    public ObservableList<Event> afficherEvents() {
//        Connection cnx = DataSource.getInstance().getCnx();
//        ObservableList<Event> list = FXCollections.observableArrayList();
//        try {
//            String req = "SELECT e.*,c.firstname FROM event e join customer c on e.eventorganizerid=c.userId ";
//            PreparedStatement st = cnx.prepareStatement(req);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                list.add(new Event(res.getInt(1), res.getString(3), res.getString(4),res.getString(7)));
//            }
//            System.out.println("Events récuperés");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;
//    }
    public ObservableList<Event> afficherEvents() {
        Connection cnx = DataSource.getInstance().getCnx();
        ObservableList<Event> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT e.*,concat(c.firstname,' ',c.lastname) as orgname FROM event e join customer c on e.eventorganizerid=c.userId";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Event(res.getInt("eventID"), res.getString("orgname"), res.getString("eventName"), res.getString("eventDescription"), res.getDate("eventStartDate"), res.getDate("eventEndDate"), res.getString("eventLocation")));
            }
            System.out.println("Events récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<Customer> afficherEventParticipants(Event e) {
        Connection cnx = DataSource.getInstance().getCnx();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT distinct concat(c.firstname,' ',c.lastname) as ParticipantFullName,c.email FROM event_participant ep join customer c on ep.participantID=c.userID join event e on ep.eventid=e.eventid where e.eventID=78";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            //st.setInt(1, e.getIdEvent());
            while (res.next()) {
                list.add(new Customer(res.getString("ParticipantFullName"), res.getString("email")));
            }
            System.out.println("Events récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}

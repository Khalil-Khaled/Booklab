/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Customer;
import com.booklab.models.Event;
import java.sql.Connection;
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
    public void ajouter (Event e){
        try {
        String req ="INSERT INTO event (organizerID,name,organizerName,description,location) VALUES (?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, e.getOrganizerID());
            st.setString(2, e.getOrganizerName());
            st.setString(3, e.getName());
            st.setString(4, e.getDesciption());
            st.setString(5, e.getLocation());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
            System.out.println("Event ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Supprimer Event
    public void supprimer (Event e){
        try {
        String req ="DELETE FROM event WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getId());
            st.executeUpdate();
            System.out.println("Event supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Modifier le nom, la description et le lieu
    public void modifier (Event e){
        try {
        String req ="UPDATE event set name=?,description =?,location=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, e.getName());
            st.setString(2, e.getDesciption());
            st.setString(3, e.getLocation());
            st.setInt(4, e.getId());
            st.executeUpdate();
            System.out.println("Event modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //AFFICHER EVET
    public Event afficherEvent(int idEvent){
        Event event = null ;
       try{ 
           String req = "SELECT * FROM event where id=?";
           PreparedStatement st = cnx.prepareStatement(req) ;
           st.setInt(1, idEvent);
           ResultSet res = st.executeQuery();
           while (res.next()){
               event = new Event(res.getString(1),res.getString(2),res.getString(3),res.getDate(4),res.getString(5)) ;
           }
           System.out.println("Event récuperé");
       }catch(SQLException ex){   
                System.out.println(ex.getMessage());
            }  
       return event ;
    }
    
    //afficher events
    public List<Event> afficher(){
        
        List<Event> list = new ArrayList<>();
           try{
                String req ="SELECT * FROM event where date < (SELECT current_timestamp) ";
                PreparedStatement st = cnx.prepareStatement(req);
                ResultSet res = st.executeQuery();
                while(res.next()){
                    list.add(new Event(res.getString(1),res.getInt(2),res.getString(3),res.getString(4),res.getDate(5) ,res.getString(6)));
                }
                System.out.println("Events récuperé");
            }catch(SQLException ex){   
                System.out.println(ex.getMessage());
            }   
            return list ;
    }
    
    //ajouter un participant à un event
    public void ajouterParticipant (Customer c){
        try {
            
        String req ="INSERT INTO event (customerID) SELECT id from customer where id=1";
        PreparedStatement st = cnx.prepareStatement(req);
        st.executeUpdate();
            System.out.println("Participant ajouté ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
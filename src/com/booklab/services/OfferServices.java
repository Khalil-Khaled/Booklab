/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Offer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Khalil
 */
public class OfferServices {
    Connection cnx= DataSource.getInstance().getCnx();
    
    public Offer createOffer(Offer o) {
        try {
            String req = "INSERT INTO offer VALUES (null,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, o.getTypeOffer());
            st.setFloat(2,o.getPriceOffer());
            st.setString(3, o.getDescriptionOffer());
            st.setBoolean(4, o.isOfferStatus());
            st.setInt(5,o.getIdCustomer());
            st.executeUpdate();
            ResultSet key = st.getGeneratedKeys();
            while (key.next()) {
                o.setIdOffer(key.getInt(1));
            }
            System.out.println("Offre ajoutÃ©e");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return o;
    }
    
    public void removeOffer (Offer o) {
        try {
            String req = "DELETE FROM offer where idOffer="+o.getIdOffer();
            PreparedStatement st = cnx.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Offre supprimÃ©e");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    public Offer getOffer (int idOffer) {
        Offer offer = null;
        try {
            String req = "SELECT * from offer where idOffer=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idOffer);
            ResultSet res = st.executeQuery();
            
            while (res.next()) {
                offer = new Offer(res.getInt(1),res.getString(2),res.getFloat(3),res.getString(4),res.getBoolean(5),res.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return offer;
    }
    
    public ArrayList<Offer> getOffers () {
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            String req = "SELECT * from offer";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                offers.add(new Offer(res.getInt(1),res.getString(2),res.getFloat(3),res.getString(4),res.getBoolean(5),res.getInt(6)));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return offers;
    }
    
    public ArrayList<Offer> getValidatedOffers () {
        ArrayList<Offer> offers = new ArrayList<>();
        try {
            String req = "SELECT * from offer where offerStatus = true";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                offers.add(new Offer(res.getInt(1),res.getString(2),res.getFloat(3),res.getString(4),res.getBoolean(5),res.getInt(6)));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return offers;
    }
    
    public void updateOffers(Offer c) {
        String req = "UPDATE offer SET offerStatus = ?  WHERE idOffer=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setBoolean(1, c.isOfferStatus());
            st.setInt(2, c.getIdOffer());
            st.executeUpdate();
            System.out.println("Offer modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

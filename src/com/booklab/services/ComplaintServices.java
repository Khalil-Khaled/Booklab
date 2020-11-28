/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.models.Complaint;
import com.booklab.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class ComplaintServices implements Iservices<Complaint> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(Complaint c) {
        try {
            String req = "INSERT INTO complaint(topic,type,message) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getTopic());
            st.setString(2, c.getType().name());
            st.setString(3, c.getMessage());
            st.executeUpdate();
            System.out.println("Added Complaint !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Complaint c) {
        try {

            String req = "DELETE FROM complaint WHERE complaintId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getComplaintId());
            st.executeUpdate();
            System.out.println("Deleted Complaint !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modify(Complaint c) {
        String req = "UPDATE complaint SET topic=? , type=? , message=?  WHERE complaintId=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(4, c.getComplaintId());
            st.setString(1, c.getTopic());
            st.setString(2, c.getType().name());
            st.setString(3, c.getMessage());
            st.executeUpdate();
            System.out.println("Complaint modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

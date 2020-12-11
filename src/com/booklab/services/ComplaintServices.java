/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.*;
import com.booklab.views.UserloginController;
import java.sql.*;
import java.util.*;

/**
 *
 * @author user
 */
public class ComplaintServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void add(Complaint c) {
        int loginId = UserloginController.idlogin;

        try {
            String req = "INSERT INTO complaint(userId,topic,type,message) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, loginId);
            st.setString(2, c.getTopic());
            st.setString(3, c.getType());
            st.setString(4, c.getMessage());

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            System.out.println("Added Complaint !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

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

    public void modify(Complaint c) {
        try {
            String req = "UPDATE complaint SET topic=? , type=? , message=?  WHERE complaintId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(4, c.getComplaintId());
            st.setString(1, c.getTopic());
            st.setString(2, c.getType());
            st.setString(3, c.getMessage());
            st.executeUpdate();
            System.out.println("Complaint modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

//    public Complaint show(int complaintId) {
//        Complaint complaint = null;
//
//        try {
//            String req = "SELECT * FROM complaint WHERE complaintId=?";
//            PreparedStatement st = cnx.prepareStatement(req);
//            st.setInt(1, complaintId);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                complaint = new Complaint(res.getInt(1), res.getString(2), (res.getString(3)), res.getString(4));
//            }
//            System.out.println(complaint);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return complaint;
//    }
    public List<Complaint> showAll() {
        List<Complaint> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM complaint";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Complaint(res.getInt(1), 0, res.getString(2), res.getString(3), res.getString(4), res.getString(5)));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
//        

    public List<Complaint> showComplaintsByUser() {
        List<Complaint> list = new ArrayList<>();
        int loginId = UserloginController.idlogin;
        try {
            String req = "SELECT * FROM complaint WHERE userId=?";//where id =loginId 
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, loginId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Complaint(0, res.getString(3), res.getString(4), res.getString(5)));
            }
            System.out.println("List of complaints :");
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

//      public List<Complaint> showAll(int id) {
//        List<Complaint> list = new ArrayList<>();
//        int loginId=UserloginController.idlogin;
//        try {
//            String req = "SELECT * FROM complaint WHERE id=?";
//            PreparedStatement st = cnx.prepareStatement(req);
//            st.setInt(1, loginId);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                list.add(new Complaint(res.getInt(1), res.getString(2), res.getString(3), res.getString(4)));
//            }
//            System.out.println("List of complaints :");
//            System.out.println(list);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
}

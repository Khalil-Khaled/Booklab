/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.ComplaintResponse;
import com.booklab.models.ComplaintStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ComplaintResponseServices implements Iservices<ComplaintResponse> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(ComplaintResponse c) {
        try {
            String req = "INSERT INTO complaint_response(userId,complaintId,response,complaintStatus) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getUserId());
            st.setInt(2, c.getComplaintId());
            st.setString(3, c.getResponse());
            st.setString(4, c.getComplaintStatus().name());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            System.out.println("Response added !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void delete(ComplaintResponse c) {
        try {
            String req = "DELETE FROM complaint_response WHERE complaintResponseId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getComplaintIResponseID());
            st.executeUpdate();
            System.out.println("complaint response deleted !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modify(ComplaintResponse c) {
        try {
            String req = "UPDATE complaint_response SET userId=? ,complaintId=?,response=? , complaintStatus=? WHERE complaintResponseId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(5, c.getComplaintResponseID());
             st.setInt(5, c.getUserId());
              st.setInt(5, c.getComplaintId());
            st.setString(2, c.getResponse());
            st.setString(4, c.getComplaintStatus().name());
            st.executeUpdate();
            System.out.println("ComplaintResponse modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ComplaintResponse show(int complaintResponseId) {
        ComplaintResponse complaintResponse = null;

        try {
            String req = "SELECT * FROM complaint_response WHERE complaintResponseId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, complaintResponseId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                complaintResponse = new ComplaintResponse(res.getInt(1), res.getInt(2),res.getInt(3),res.getString(4), (ComplaintStatus.valueOf(res.getString(5))));
            }
            System.out.println(complaintResponse);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return complaintResponse;
    }

    @Override
    public List<ComplaintResponse> showAll() {
        List<ComplaintResponse> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM complaint_response";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new ComplaintResponse(res.getInt(1), res.getInt(2),res.getInt(3),res.getString(4), (ComplaintStatus.valueOf(res.getString(5)))));
            }
            System.out.println("List of complaints responses  :");
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}

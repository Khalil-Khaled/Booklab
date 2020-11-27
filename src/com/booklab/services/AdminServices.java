/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Admin;
import com.booklab.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radhw
 */
public class AdminServices {
        Connection cnx= DataSource.getInstance().getCnx();
    
    public void create(Admin a) {
		try {
			String req="INSERT INTO Admin VALUES(null,?,?,?,?,?,?,?)";
			
                        PreparedStatement st = cnx.prepareStatement(req);
   
			st.setString(1,a.getUserName());
			st.setString(2,a.getFirstName());
			st.setString(3,a.getLastName());
			st.setString(4,a.getEmail());
			st.setString(5,a.getPassword());
			st.setString(6,a.getQuestionVerif());
			st.setString(7,a.getAnswerVerif());
			st.executeUpdate();
			
			System.out.println("Admin ADD ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
            public void delete(Admin a) {
		try {
			String req="DELETE FROM admin WHERE adminId=?";
			PreparedStatement st = cnx.prepareStatement(req);
			st.setInt(1,a.getAdminId());
		
			st.executeUpdate();
			
			System.out.println("customer delete ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public void modify(Admin a) {
		try {
			String req="UPDATE admin set userName=?,firstName=?,lastName=?,email=?,password=?,questionVerif=? ,answerVerif=? WHERE adminId=? ";
			PreparedStatement st = cnx.prepareStatement(req);

			
			st.setString(1,a.getUserName());
			st.setString(2,a.getFirstName());
			st.setString(3,a.getLastName());
			st.setString(4,a.getEmail());
			st.setString(5,a.getPassword());
			st.setString(6,a.getQuestionVerif());
			st.setString(7,a.getAnswerVerif());
                        st.setInt(8,a.getAdminId());
			st.executeUpdate();
			
			System.out.println("customer updatedd ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public List<Admin>show(){
		List<Admin> list=new ArrayList<>();
		try {
			String req="select * from Admin";
			PreparedStatement st = cnx.prepareStatement(req);
			ResultSet res =st.executeQuery(req);
			while (res.next()) {
                                
				list.add(new Admin(res.getInt("adminId"),res.getString("userName"),res.getString("firstName"),res.getString("lastName"),res.getString("email"),res.getString("password"),res.getString("questionVerif"),res.getString("answerVerif")));
                                //list sans image
                        }
			
			System.out.println("Admin listed");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}

}

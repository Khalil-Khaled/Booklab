/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booklab.Utils.DataSource;
import com.booklab.models.Customer;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author radhw
 */
public class CustomerServices {
    Connection cnx= DataSource.getInstance().getCnx();
    
    public void create(Customer c) {
		try {
			String req="INSERT INTO Customer VALUES(null,?,?,?,?,?,?,?,?,?,?,?)";
			
                        PreparedStatement st = cnx.prepareStatement(req);
                       
                        st.setBinaryStream(1, c.getProfileImage());
                        st.setInt(2,c.getRate());
                        st.setInt(3,c.getWishId());
                        st.setInt(4,c.getCardId());
			st.setString(5,c.getUserName());
			st.setString(6,c.getFirstName());
			st.setString(7,c.getLastName());
			st.setString(8,c.getEmail());
			st.setString(9,c.getPassword());
			st.setString(10,c.getQuestionVerif());
			st.setString(11,c.getAnswerVerif());
			st.executeUpdate();
			
			System.out.println("customer ADD ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
            public void delete(Customer c) {
		try {
			String req="DELETE FROM Customer WHERE customerId=?";
			PreparedStatement st = cnx.prepareStatement(req);
			st.setInt(1,c.getCustomerId());
		
			st.executeUpdate();
			
			System.out.println("customer delete ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public void modify(Customer c) {
		try {
			String req="UPDATE customer set profileImage=?,rate=?,wishId=?,cardId=?,userName=?,firstName=?,lastName=?,email=?,password=?,questionVerif=? ,answerVerif=? WHERE customerId=? ";
			PreparedStatement st = cnx.prepareStatement(req);

			st.setBinaryStream(1, c.getProfileImage());
                        st.setInt(2,c.getRate());
                        st.setInt(3,c.getWishId());
                        st.setInt(4,c.getCardId());
			st.setString(5,c.getUserName());
			st.setString(6,c.getFirstName());
			st.setString(7,c.getLastName());
			st.setString(8,c.getEmail());
			st.setString(9,c.getPassword());
			st.setString(10,c.getQuestionVerif());
			st.setString(11,c.getAnswerVerif());
                        st.setInt(12,c.getCustomerId());
			st.executeUpdate();
			
			System.out.println("customer updatedd ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public List<Customer>show(){
		List<Customer> list=new ArrayList<>();
		try {
			String req="select * from Customer";
			PreparedStatement st = cnx.prepareStatement(req);
			ResultSet res =st.executeQuery(req);
			while (res.next()) {
                                System.out.println(res.getBlob("profileImage"));
				list.add(new Customer(res.getInt("customerId"),res.getInt("rate"),res.getInt("wishId"),res.getInt("cardId"),res.getString("userName"),res.getString("firstName"),res.getString("lastName"),res.getString("email"),res.getString("password"),res.getString("questionVerif"),res.getString("answerVerif")));
                                //list sans image
                        }
			
			System.out.println("customer listed");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}

}

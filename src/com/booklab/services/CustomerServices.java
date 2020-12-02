/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// lezem constructeur men ghir password
// lezem el affichage men ghir password, question réponse secrete, clés étrangeres

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
                       
                        st.setString(1,c.getUserName());
			st.setString(2,c.getFirstName());
			st.setString(3,c.getLastName());
			st.setString(4,c.getEmail());
			st.setString(5,c.getPassword());
			st.setString(6,c.getQuestionVerif());
			st.setString(7,c.getAnswerVerif());
                        st.setString(8, c.getProfilimage());
                        st.setInt(9,c.getRate());
                        st.setInt(10,c.getWishId());
                        st.setInt(11,c.getCardId());
		
			st.executeUpdate();
			
			System.out.println("customer ADD ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
            public void delete(Customer c) {
		try {
			String req="DELETE FROM Customer WHERE userId=?";
			PreparedStatement st = cnx.prepareStatement(req);
			st.setInt(1,c.getUserid());
		
			st.executeUpdate();
			
			System.out.println("customer delete ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public void modify(Customer c) {
		try {
			String req="UPDATE customer set userName=?,firstName=?,lastName=?,email=?,password=?,questionVerif=?,answerVerif=?,profilImage=?,rate=?,wishlistId=?,cardId=? WHERE userId=? ";
			PreparedStatement st = cnx.prepareStatement(req);

			
                      
			st.setString(1,c.getUserName());
			st.setString(2,c.getFirstName());
			st.setString(3,c.getLastName());
			st.setString(4,c.getEmail());
			st.setString(5,c.getPassword());
			st.setString(6,c.getQuestionVerif());
			st.setString(7,c.getAnswerVerif());
                        st.setString(8, c.getProfilimage());
                        st.setInt(9,c.getRate());
                        st.setInt(10,c.getWishId());
                        st.setInt(11,c.getCardId());
                        st.setInt(12,c.getUserid());
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
				list.add(new Customer(res.getInt("userId"),res.getString("userName"),res.getString("firstName"),res.getString("lastName"),res.getString("email"),res.getString("password"),res.getString("questionVerif"),res.getString("answerVerif"),res.getString("profilimage"),res.getInt("rate"),res.getInt("wishlistId"),res.getInt("cardId")));
                                
                        }
			
			System.out.println("customer listed");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}

}

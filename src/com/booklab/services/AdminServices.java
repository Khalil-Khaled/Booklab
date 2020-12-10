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
			String req="INSERT INTO Admin VALUES(null,?,?,?,?,?,?,?,?)";
			
                        PreparedStatement st = cnx.prepareStatement(req);
   
			st.setString(1,a.getUserName());
			st.setString(2,a.getFirstName());
			st.setString(3,a.getLastName());
			st.setString(4,a.getEmail());
			st.setString(5,a.getPassword());
			st.setString(6,a.getQuestionVerif());
			st.setString(7,a.getAnswerVerif());
                        st.setString(8,a.getProfilimage());
			st.executeUpdate();
			
			System.out.println("Admin ADD ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
            public void delete(Admin a) {
		try {
			String req="DELETE FROM admin WHERE userid=?";
			PreparedStatement st = cnx.prepareStatement(req);
			st.setInt(1,a.getUserid());
		
			st.executeUpdate();
			
			System.out.println("admin delete ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public void modify(Admin a) {
		try {
			String req="UPDATE admin set userName=?,firstName=?,lastName=?,email=?,password=?,questionVerif=? ,answerVerif=?,Profilimage=? WHERE userId=? ";
			PreparedStatement st = cnx.prepareStatement(req);

			
			st.setString(1,a.getUserName());
			st.setString(2,a.getFirstName());
			st.setString(3,a.getLastName());
			st.setString(4,a.getEmail());
			st.setString(5,a.getPassword());
			st.setString(6,a.getQuestionVerif());
			st.setString(7,a.getAnswerVerif());
                        st.setString(8,a.getProfilimage());
                        st.setInt(9,a.getUserid());
			st.executeUpdate();
			
			System.out.println("admin updatedd ok!!");
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
                                
				list.add(new Admin(res.getInt("userid"),res.getString("userName"),res.getString("firstName"),res.getString("lastName"),res.getString("email"),res.getString("password"),res.getString("questionVerif"),res.getString("answerVerif"),res.getString("profilimage")));
                                //list sans image
                        }
			
			System.out.println("Admin listed");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
            public Admin showadmin(int id) {
            Admin c = null;
            try {
                String req = "select * from admin where userid=" + id;
                PreparedStatement st = cnx.prepareStatement(req);
                ResultSet res = st.executeQuery(req);
                while (res.next()) {
                    c = new Admin(res.getInt("userId"), res.getString("userName"), res.getString("firstName"), res.getString("lastName"), res.getString("email"), res.getString("password"), res.getString("questionVerif"), res.getString("answerVerif"), res.getString("profilimage"));

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return c;
    }
                public void modify(Admin c, int id) {
        try {
            String req = "UPDATE admin set userName=?,firstName=?,lastName=?,email=?,password=?,questionVerif=?,answerVerif=?,profilImage=? WHERE userId=" + id;
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, c.getUserName());
            st.setString(2, c.getFirstName());
            st.setString(3, c.getLastName());
            st.setString(4, c.getEmail());
            st.setString(5, c.getPassword());
            st.setString(6, c.getQuestionVerif());
            st.setString(7, c.getAnswerVerif());
            st.setString(8, c.getProfilimage());

            st.executeUpdate();

            System.out.println("admin updatedd ok!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public void updatepass(String pass, int id) {
        try {
            String req = "UPDATE admin set password='" + pass + "' WHERE userId=" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            st.executeUpdate();

            System.out.println("admin updatedd ok!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

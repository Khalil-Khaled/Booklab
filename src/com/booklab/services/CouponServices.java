/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Admin;
import com.booklab.models.Coupon;
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
public class CouponServices {
     Connection cnx= DataSource.getInstance().getCnx();
    
    public void create(Coupon c) {
		try {
			String req="INSERT INTO coupon VALUES(null,?,?)";
			
                        PreparedStatement st = cnx.prepareStatement(req);
   
			st.setString(1,c.getCodeCoupon());
                        st.setBoolean(2,c.getValidity());
			st.executeUpdate();
			
			System.out.println("coupon ADD ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
    public void delete(Coupon c) {
		try {
			String req="DELETE FROM coupon WHERE couponId=?";
			PreparedStatement st = cnx.prepareStatement(req);
			st.setInt(1,c.getCouponId());
		
			st.executeUpdate();
			
			System.out.println("Coupon delete ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
    public void modify(Coupon c) {
		try {
			String req="UPDATE coupon set couponCode=?,validity=? WHERE couponId=? ";
			PreparedStatement st = cnx.prepareStatement(req);

			
			st.setString(1,c.getCodeCoupon());
                        st.setBoolean(2,c.getValidity());
                        st.setInt(3,c.getCouponId());
			st.executeUpdate();
			
			System.out.println("customer updatedd ok!!");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}	
	}
        public List<Coupon>show(){
		List<Coupon> list=new ArrayList<>();
		try {
			String req="select * from coupon";
			PreparedStatement st = cnx.prepareStatement(req);
			ResultSet res =st.executeQuery(req);
			while (res.next()) {
                                
				list.add(new Coupon(res.getInt("adminId"),res.getString("codecoupon"),res.getBoolean("validity")));
                                //list sans image
                        }
			
			System.out.println("Coupon listed");
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}

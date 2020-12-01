/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Complaint;
import com.booklab.models.Coupon;
import com.booklab.models.Type;
import java.sql.Connection;
import java.sql.Date;
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
public class CouponServices implements Iservices<Coupon> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(Coupon c) {
        try {
            String req = "INSERT INTO coupon (codeCoupon,validity,expirationDate) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, c.getCodeCoupon());
            st.setBoolean(2, c.isValidity());
            java.util.Date utilDate = c.getExpirationDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setDate(3, sqlDate);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            System.out.println("Added Coupon !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Coupon c) {
        try {
            String req = "DELETE FROM coupon  WHERE couponId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getCouponID());
            st.executeUpdate();
            System.out.println("Deleted Coupon !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modify(Coupon c) {
        try {
            String req = "UPDATE coupon SET codeCoupon=? , validity=? , expirationDate=?  WHERE couponId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(4, c.getCouponID());
            st.setString(1, c.getCodeCoupon());
            st.setBoolean(2, c.isValidity());
            st.setDate(3, (Date) c.getExpirationDate());
            st.executeUpdate();
            System.out.println("Coupon modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Coupon show(int couponId) {
        Coupon coupon = null;

        try {
            String req = "SELECT * FROM coupon  WHERE couponId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, couponId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                coupon = new Coupon(res.getInt(1), res.getString(2), res.getBoolean(3), res.getDate(4));
            }
            System.out.println(coupon);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return coupon;
    }

    @Override
    public List<Coupon> showAll() {
        List<Coupon> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM coupon";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Coupon(res.getInt(1), res.getString(2), res.getBoolean(3), res.getDate(4)));
            }
            System.out.println("List of coupons :");
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}

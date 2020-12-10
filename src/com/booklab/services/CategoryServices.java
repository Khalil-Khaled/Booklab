/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Naoures Hidri
 */
public class CategoryServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void create(Category c) {
        try {
            String req = "INSERT INTO Category VALUES(null,?)";

            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, c.getCategoryName());

            st.executeUpdate();
            System.out.println(" A category is added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Category c) {
        try {
            String req = "DELETE FROM Category WHERE categoryId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getCategoryId());

            st.executeUpdate();

            System.out.println("A category is deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Category c) {
        try {
            String req = "UPDATE Category set  categoryName=? WHERE id=? ";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setString(1, c.getCategoryName());

            st.executeUpdate();

            System.out.println("category updatedd !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Category> show() {
        List<Category> list = new ArrayList<>();
        try {
            String req = "select * from category";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {

                list.add(new Category(res.getInt("categoryId"), res.getString("categoryName")));

            }

            System.out.println("category listed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<String> showname() {
        List<String> list = new ArrayList<>();
        try {
            String req = "select categoryname from category";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {

                list.add(res.getString(1));

            }

            System.out.println("category name listed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}


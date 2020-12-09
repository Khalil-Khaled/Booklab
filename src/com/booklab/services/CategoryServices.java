/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Category;
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
public class CategoryServices {
    Connection cnx = DataSource.getInstance().getCnx();
    public void create(Category c) {
        try {
            String req = "INSERT INTO category VALUES(null,?)";

            PreparedStatement st = cnx.prepareStatement(req);

  
            st.setString(1, c.getCategoryName());
    

            st.executeUpdate();

            System.out.println("Category ADD ok!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public void delete(Category c) {
        try {
            String req = "DELETE FROM category WHERE categoryId=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getCategoryId());

            st.executeUpdate();

            System.out.println("customer delete ok!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public List<Category> show() {
        List<Category> list = new ArrayList<>();
        try {
            String req = "select * from Category";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                list.add(new Category(res.getInt("CategoryId"),res.getString("CategoryName")));

            }

            System.out.println("Category listed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}


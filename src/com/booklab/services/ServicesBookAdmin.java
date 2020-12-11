/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ServicesBookAdmin {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void insertItem(Item item){
        String REQ="";
        PreparedStatement st;
        try {
                REQ="INSERT INTO item values (?, ?, ?, ?, ?, ?, ?, ?,?,?,?, ?)";
                st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
                    st.setInt(1, item.getId());
                    st.setString(2, item.getName());
                    st.setString(3, item.getCategoryName());
                    st.setString(4, item.getDescription());
                    st.setDouble(5, item.getPrice());
                    st.setString(6, item.getImage());
                    st.setInt(7, item.getQuantity());
                    st.setInt(8, item.getRating());
                    st.setDate(9, item.getPublishDate());
                    st.setString(10, item.getAuthor());
                    st.setInt(11, item.getPageNumber());
                    st.setString(12, item.getType());
 
                System.out.println("ITEM INSERTION STATUS: "+(st.executeUpdate()>0));
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()){
                item.setId(rs.getInt(1));
            }
           

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
    public void deleteItem(Item item){
        String REQ="";
        PreparedStatement st;
        try {
                REQ="DELETE from item where id = ?";
                st = cnx.prepareStatement(REQ);
                    st.setInt(1, item.getId());
                System.out.println("ITEM DELETION STATUS: "+(st.executeUpdate()>0));
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    public void updateItem(Item item){
            deleteItem(item);
            insertItem(item);
                System.out.println("ITEM HAS BEEN UPDATED");
    }
    
    
    public ArrayList<Item> selectItems(){
        ArrayList<Item> items= new ArrayList<>();
        String REQ="";
        PreparedStatement st;
        try {
                REQ="SELECT * from item";
                st = cnx.prepareStatement(REQ);
                ResultSet rs = st.executeQuery();
                
                while (rs.next()){
                    items.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
                }
                
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return items;
    }
 
}
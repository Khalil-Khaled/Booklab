/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.models.ShoppingCart;
import com.booklab.Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ServicesShoppingCart {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void createCart(ShoppingCart SC){
        try {           
            String REQ = "INSERT INTO shoppingcart values (?, ?)";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1, SC.getCartID());
                st.setInt(2, SC.getUserID());
                st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()){
                SC.setCartID(rs.getInt(1));
            }
            System.out.println("ADD STATUS : CART CREATED");       

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
    public void addItemsToCart(ShoppingCart SC){
        PreparedStatement st;
        String REQ = "";
        try {
            
            for(int i=0; i<SC.getItemsID().size(); i++){
            REQ = "INSERT INTO cart_actions values (null, ?, ?, ?)";
            st = cnx.prepareStatement(REQ);
                
                st.setInt(1, SC.getCartID());
                st.setInt(2, SC.getItem(i));
                st.setInt(3, SC.getItemAmount(i));
                System.out.println("("+i+")INSERT STATUS: "+(st.executeUpdate()>0));
            }
            

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
    public void removeItemsFromCart(ShoppingCart SC){
        PreparedStatement st;
        String REQ = "";
        try {
            
            for(int i=0; i<SC.getItemsID().size(); i++){
            REQ = "DELETE FROM cart_actions WHERE cartID = ? and itemID = ?";
            st = cnx.prepareStatement(REQ);
                
                st.setInt(1, SC.getCartID());
                st.setInt(2, SC.getItem(i));
                System.out.println("("+i+")DELETE STATUS: "+(st.executeUpdate()>0));
            }
            

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    public void clearCart(ShoppingCart SC){
        PreparedStatement st;
        String REQ = "";
        try {
            
            REQ = "DELETE FROM cart_actions WHERE cartID = ?";
            st = cnx.prepareStatement(REQ);
            st.setInt(1, SC.getCartID());
 
                System.out.println("DELETE STATUS: "+(st.executeUpdate()>0));


        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
        
        
    public void removeCart(ShoppingCart SC){
        try {
            String REQ = "DELETE FROM shoppingcart where cartID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, SC.getCartID());
            
            System.out.println("DELETE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
    public ShoppingCart getCartItems(ShoppingCart SC){
        ShoppingCart list = new ShoppingCart(SC.getCartID(), SC.getUserID());
        try {
            String REQ = "select DISTINCT itemID, sum(amount) from cart_actions where cartID = ? group by itemID;";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, SC.getCartID());
            
            ResultSet result = st.executeQuery();
           
            while (result.next()){
                list.addItem(result.getInt(1), result.getInt(2));
            }
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return list;
    }
}

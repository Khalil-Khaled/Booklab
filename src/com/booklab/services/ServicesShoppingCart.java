/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.models.ShoppingCart;
import com.booklab.Utils.DataSource;
import com.booklab.models.Book;
import com.booklab.models.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.booklab.interfaces.TableData;

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
            
            for(int i=0; i<SC.getItems().size(); i++){
            REQ = "INSERT INTO cart_actions values (null, ?, ?, ?)";
            st = cnx.prepareStatement(REQ);
                
                st.setInt(1, SC.getCartID());
                st.setInt(2, SC.getItem(i).getId());
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
            
            for(int i=0; i<SC.getItems().size(); i++){
            REQ = "DELETE FROM cart_actions WHERE cartID = ? and itemID = ?";
            st = cnx.prepareStatement(REQ);
                
                st.setInt(1, SC.getCartID());
                st.setInt(2, SC.getItem(i).getId());
                System.out.println("("+i+")DELETE STATUS: "+(st.executeUpdate()>0));
            }
            

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
 
    
    public void removeOneItem(TableData item){
        PreparedStatement st;
        String REQ = "";
        try {
            
            REQ = "DELETE FROM cart_actions WHERE itemID = ?";
            st = cnx.prepareStatement(REQ);
            st.setInt(1, item.getItem_id());
 
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
    
        public void updateCartItem(TableData item){
        try {
            /*String REQ = "DELETE from cart_actions where itemID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ);
               st.setInt(1, item.getId());
            st.executeUpdate();*/
            
            String REQ = "update cart_actions set amount = ? where itemID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ);
                st.setInt(2, item.getItem_id());
                st.setInt(1, item.getAmount());
            st.executeUpdate();
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            System.out.println(item.getItem_id());
            System.out.println(item.getAmount());
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
        
     public float getCartTotal(ShoppingCart SC){
         try{
             String REQ = "select sum(amount * price) from item join cart_actions on ItemID = id where cartID = ? ";
   
            PreparedStatement st = cnx.prepareStatement(REQ);
            st.setInt(1, SC.getCartID());
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getFloat(1);
            }catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
             }
         return 0;
    }
    
    
    public ShoppingCart getCartItems(ShoppingCart SC){
        ShoppingCart list = new ShoppingCart(SC.getCartID(), SC.getUserID());
        try {
            String REQ = "select DISTINCT itemID, name, price, quantity, sum(amount) from cart_actions inner join item on itemID = id where cartID = ? group by itemID;";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, SC.getCartID());
            
            ResultSet result = st.executeQuery();
           
            while (result.next()){//getInt(1), result.getInt(2)
                Book tmp = new Book(result.getInt(1));
                    tmp.setName(result.getString(2));
                    tmp.setPrice(result.getFloat(3));
                    tmp.setQuantity(result.getInt(4));

                list.addItem(tmp, result.getInt(5));
            }
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return list;
    }

    public ShoppingCart setActiveCart(ShoppingCart SC) {
        try {
            String REQ = "select max(cartID) from shoppingcart where userID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, SC.getUserID());
            
            ResultSet result = st.executeQuery();
           
            while (result.next()){//getInt(1), result.getInt(2)
                SC.setCartID(result.getInt(1));
            }
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return SC;
    }
}

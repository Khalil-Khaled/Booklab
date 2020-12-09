package com.booklab.services;

import com.booklab.models.Order;
import com.booklab.Utils.DataSource;
import com.booklab.views.OrderController;
import com.booklab.views.OrderHistoryController;
import com.booklab.models.Book;
import com.booklab.models.Item;
import com.booklab.models.ShoppingCart;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class ServicesOrder {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void insertOrder(Order O){
        try {
            String REQ = "INSERT INTO Orders values (?, ?, ?, ?, ?)";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1, O.getOrderID());
                st.setInt(2, O.getCart().getCartID());
                st.setBoolean(3, O.isOrderStatus());
                st.setDate(4, O.getOrderDate());
                st.setString(5, getRandomString());
                System.out.println("ORDER INSERTION STATUS: "+(st.executeUpdate()>0));
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()){
                O.setOrderID(rs.getInt(1));
            }
           

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    //update status of order
    public void removeOrder(Order O){
        try {
            String REQ = "DELETE FROM Orders where orderID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, O.getOrderID());
            
            System.out.println("ORDER DELETE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    public void updateOrderStatus(Order O){
        try {
            String REQ = "UPDATE Orders Set orderStatus = ? where orderID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(2, O.getOrderID());
            st.setBoolean(1, O.isOrderStatus());
            
            System.out.println("ORDER UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
   //
  
    
    public Order getOrderInfo(Order O){
        try {
            String REQ = "select cartID, userID, orderID, orderStatus, orderdate, orderref from orders natural join shoppingcart natural join cart_actions where orderID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, O.getOrderID());
            
            ResultSet result = st.executeQuery();
           
            if (result.next()){
                O = new Order(result.getInt(3), new ShoppingCart(result.getInt(1), result.getInt(2)), result.getBoolean(4), result.getDate(5), result.getString(6));
            }
            ServicesShoppingCart ssc = new ServicesShoppingCart();
            O.setCart(ssc.getCartItems(O.getCart()));
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return O;
    }
      public ArrayList<Order> getAllOrders(int user_id){
        //select orderID from orders natural join shoppingcart join customer on shoppingcart.userID = customer.userID where shoppingcart.userID = 1;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String REQ = "select orderID from orders natural join shoppingcart join customer on shoppingcart.userID = customer.userID where shoppingcart.userID = ? order by orderID DESC";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, user_id);
            
            ResultSet result = st.executeQuery();
           ServicesOrder so= new ServicesOrder();
            while (result.next()){
                Order o = new Order(result.getInt(1));
                o = so.getOrderInfo(o);
               orders.add(o);
            }
            
            
            System.out.println("UPDATE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
        return orders;
        }
      
      
      protected String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
      
     
}


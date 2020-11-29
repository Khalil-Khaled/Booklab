package com.booklab.services;

import com.booklab.models.Order;
import com.booklab.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServicesOrder {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void insertOrder(Order O){
        try {
            String REQ = "INSERT INTO Orders values (null, ?, ?, ?)";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1, O.getCart().getCartID());
                st.setBoolean(2, O.isOrderStatus());
                st.setDate(3, O.getOrderDate());
                st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()){
                O.setOrderID(rs.getInt(1));
            }
            

        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
    
    
    public void removeOrder(Order O){
        try {
            String REQ = "DELETE FROM Orders where orderID = ?";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, O.getOrderID());
            
            System.out.println("DELETE STATUS: "+(st.executeUpdate()>0));
            
        } catch (SQLException ex) {
            System.out.println("Query Failed: "+ ex.getMessage());
        }
    }
}

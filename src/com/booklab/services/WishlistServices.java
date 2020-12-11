/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Customer;
import com.booklab.models.Item;
import com.booklab.models.wishlistBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author steam
 */
public class WishlistServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public int createWishlist(int userid) {
        int wishid = 0;

        System.out.println(userid);
        PreparedStatement st;
        String REQ = "";
        try {
            REQ = "INSERT INTO wishlist(customerid) values (?) ";

            st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, userid);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                wishid = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
        return wishid;

    }

    public int getWishlistId(int userid) {
        int wishid = 0;
        PreparedStatement st;
        String REQ = "";
        try {
            REQ = "Select wishlistid from wishlist where customerid=? ";

            st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, userid);
            st.executeQuery();
            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                wishid = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
        return wishid;
    }

    public void ajouterDansWishlist(Customer c, Item b) {
        PreparedStatement st;
        String REQ = "";
        try {

            REQ = "INSERT INTO wishlistbooks(itemid,wishlistid) values (?,?) ";
            st = cnx.prepareStatement(REQ);
            st.setInt(1, b.getId());
            st.setInt(2, c.getWishId());
            System.out.println(c.getWishId());
            st.executeUpdate();
            System.out.println("Book added");

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    //Supprimer book from wishlist
    public void supprimerBook(wishlistBooks wb) {
        try {
            String req = "DELETE FROM wishlistbooks WHERE itemid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, wb.getBookId());
            st.executeUpdate();
            System.out.println("Book deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void viderWishlist(wishlistBooks wb) {
        PreparedStatement st;
        String REQ = "";
        try {
            REQ = "DELETE FROM wishlistbooks";
            st = cnx.prepareStatement(REQ);
            st.executeUpdate();
            System.out.println("wishlist is empty now");
        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    public ObservableList<wishlistBooks> afficherWishlistBooks(int id ) {
        ObservableList<wishlistBooks> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT distinct b.itemName,b.itemdescription,b.itemid FROM wishlistbooks wb join item b on b.itemid=wb.itemid join customer c on c.wishlistid=wb.wishlistid where c.userid="+id;
            PreparedStatement st = cnx.prepareStatement(req);
            
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new wishlistBooks(res.getInt("itemID"), res.getString("itemName"), res.getString("itemDescription")));
            }
            System.out.println("Wishlist récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

   
    public Integer nmbreBookDansWishlist(int id) {
        int nbr = 0;
        try {
            String req = "SELECT count(*) as num from wishlistbooks wb join customer c on wb.wishlistid=c.wishlistid where c.userid="+id;
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                nbr = res.getInt("num");
            }
            System.out.println("Wishlist récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbr;
    }

    public int searchbookInWishlist(int itemid, int wishid) {
        int count = 0;
        System.out.println(itemid);
        try {
            String req = "select count(*) from wishlistbooks where wishlistid=? and itemid=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, wishid);
            st.setInt(2, itemid);
            ResultSet res = st.executeQuery(req);
            if (res.next()) { // just in case
                count = res.getInt(1); // note that indexes are one-based
            }
            //System.out.println(count);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

}
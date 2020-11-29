/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Book;
import com.booklab.models.Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author steam
 */
public class WishlistServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterDansWishlist(Wishlist wishlist) {
        PreparedStatement st;
        String REQ = "";
        try {

            for (int i = 0; i < wishlist.getBooks().size(); i++) {
                REQ = "INSERT INTO wishlist_actions values (null, ?, ?, ?)";
                st = cnx.prepareStatement(REQ);

                st.setInt(1, wishlist.getWishlistID());
                st.setInt(2, wishlist.getBook(i).getId());
                st.setInt(3, wishlist.getBooks().size());
                System.out.println("(" + i + ")INSERT STATUS: " + (st.executeUpdate() > 0));
            }

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    public void supprimerDeWishlist(Wishlist wishlist) {
        PreparedStatement st;
        String REQ = "";
        try {

            for (int i = 0; i < wishlist.getBooks().size(); i++) {
                REQ = "DELETE FROM wishlist_actions WHERE wihlistID = ? and bookID = ?";
                st = cnx.prepareStatement(REQ);

                st.setInt(1, wishlist.getWishlistID());
                st.setInt(2, wishlist.getBook(i).getId());
                System.out.println("(" + i + ")DELETE STATUS: " + (st.executeUpdate() > 0));
            }

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    public void viderWishlist(Wishlist wishlist) {
        PreparedStatement st;
        String REQ = "";
        try {

            REQ = "DELETE FROM wishlist_actions WHERE cartID = ?";
            st = cnx.prepareStatement(REQ);
            st.setInt(1, wishlist.getWishlistID());

            System.out.println("DELETE STATUS: " + (st.executeUpdate() > 0));

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    public Wishlist afficherWishlist(Wishlist wishlist) {
        Wishlist list = new Wishlist(wishlist.getWishlistID(), wishlist.getCustomerID());
        try {
            String REQ = "select DISTINCT bookID, sum(amount) from wishlist_actions where wishlistID = ? group by bookID;";
            PreparedStatement st = cnx.prepareStatement(REQ, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, wishlist.getWishlistID());

            ResultSet result = st.executeQuery();

            while (result.next()) {//getInt(1), result.getInt(2)
                list.ajouterBook(new Book(result.getInt(1)));
            }

            System.out.println("UPDATE STATUS: " + (st.executeUpdate() > 0));

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
        return list;
    }

}

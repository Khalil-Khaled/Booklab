/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Book;
import com.booklab.models.wishlistBooks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author steam
 */
public class WishlistServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterDansWishlist(int userid, Book b) {
        PreparedStatement st;
        String REQ = "";
        try {

            REQ = "INSERT INTO wishlistbooks values (?, ?) where UserID=?";
            st = cnx.prepareStatement(REQ);
            st.setInt(1, b.getBookId());
            st.setInt(2, userid);

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

    //Supprimer book from wishlist
    public void supprimerBook(wishlistBooks wb) {
        try {
            String req = "DELETE FROM wishlistbooks WHERE wishlistbooksid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, wb.getWishlistBooksId());
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
            REQ = "DELETE FROM wishlistBooks";
            st = cnx.prepareStatement(REQ);
            System.out.println("DELETE STATUS: " + (st.executeUpdate() > 0));

        } catch (SQLException ex) {
            System.out.println("Query Failed: " + ex.getMessage());
        }
    }

//    public ObservableList<wishlistBooks> afficherWishlistBooks() {
//        ObservableList<wishlistBooks> list = FXCollections.observableArrayList();
//        try {
//            String req = "SELECT b.bookName,b.bookdescription,wb.wishlistbooksID  FROM wishlistbooks wb join book b on b.bookid=wb.bookID ";
//            PreparedStatement st = cnx.prepareStatement(req);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                list.add(new wishlistBooks(res.getString("bookName"), res.getString("bookDescription")));
//            }
//            System.out.println("Wishlist récuperés");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;
//    }
    public ObservableList<wishlistBooks> afficherWishlistBooks() {
        ObservableList<wishlistBooks> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT distinct b.bookName,b.bookdescription,wb.* FROM wishlistbooks wb join customer c on wb.wishlistid=c.wishlistid join wishlist w on c.wishlistID=w.wishlistID join book b on b.bookid=wb.bookID  ";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new wishlistBooks(res.getInt("wishlistbooksID"), res.getInt("wishlistID"), res.getInt("bookID"), res.getString("bookName"), res.getString("bookDescription")));
            }
            System.out.println("Wishlist récuperés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

//SELECT b.bookName,b.bookdescription,wb.wishlistid,c.firstname,c.lastname,c.userID
//FROM wishlistbooks wb join customer c
//on wb.wishlistid=c.wishlistid 
//join book b on b.bookid=wb.bookID
    public Integer nmbreBookDansWishlist() {
        int nbr = 0;
        try {
            String req = "SELECT count(*) as num from wishlistbooks wb join wishlist w on w.wishlistid=wb.wishlistid join customer c on c.wishlistid=w.wishlistid and c.userID=w.customerID";
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

}
//SELECT count(*) as num from SELECT distinct b.bookName,b.bookdescription,wb.* FROM wishlistbooks wb join customer c on wb.wishlistid=c.wishlistid join wishlist w on c.wishlistID=w.wishlistID join book b on b.bookid=wb.bookID";

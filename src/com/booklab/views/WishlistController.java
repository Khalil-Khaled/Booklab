/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Wishlist;
import com.booklab.models.wishlistBooks;
import com.booklab.services.WishlistServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author steam
 */
public class WishlistController implements Initializable {

    @FXML
    private TableView<wishlistBooks> wishlist;
    @FXML
    private TableColumn<Wishlist, Integer> col_book;
    @FXML
    private TableColumn<Wishlist, Integer> col_price;
    @FXML
    Label quantity;

    WishlistServices ws = new WishlistServices();
    ObservableList<wishlistBooks> listM;

    Stage dialogStage = new Stage();
    Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listM = ws.afficherWishlistBooks();
        wishlist.setItems(listM);
        col_book.setCellValueFactory(new PropertyValueFactory<Wishlist, Integer>("BookName"));
        col_price.setCellValueFactory(new PropertyValueFactory<Wishlist, Integer>("BookDescription"));
        UpdateQuantity();
    }

    @FXML
    public void Delete(ActionEvent event) throws IOException {        
        wishlistBooks wishSelected = wishlist.getSelectionModel().getSelectedItem();
        if (wishSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Book to Remove!");
        } else {
            WishlistServices ws = new WishlistServices();
            ws.supprimerBook(wishSelected);
            UpdateTable();
            UpdateQuantity();
        }
    }

    @FXML
    public void RemoveAll(ActionEvent event) throws IOException {
        wishlistBooks wishSelected = wishlist.getSelectionModel().getSelectedItem();
        System.out.println(wishSelected);
        //ws.viderWishlist(wishSelected);
        UpdateTable();
        UpdateQuantity();
        JOptionPane.showMessageDialog(null, "Removed All Books!!!");
//        Node node = (Node) event.getSource();
//        dialogStage = (Stage) node.getScene().getWindow();
//        dialogStage.close();
//        scene = new Scene(FXMLLoader.load(getClass().getResource("CreateEvent.fxml")));
//        dialogStage.setScene(scene);
//        dialogStage.show();
    }

    private void UpdateTable() {
        listM = ws.afficherWishlistBooks();
        wishlist.setItems(listM);
    }
    
    private void UpdateQuantity() {
        listM = ws.afficherWishlistBooks();
        wishlist.setItems(listM);
        quantity.setText(ws.nmbreBookDansWishlist().toString());
    }

}

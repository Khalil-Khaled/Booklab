/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.interfaces;

import com.booklab.models.Book;
import com.booklab.models.Item;
import com.booklab.models.ShoppingCart;
import com.booklab.services.ServicesShoppingCart;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ShoppingCartController implements Initializable {

    @FXML
    private TableView<Item> list_cart;
    @FXML
    private TableColumn<Item, String> article;
    @FXML
    private TableColumn<Item, Integer> quantity;
    @FXML
    private TableColumn<Item, Float> total;
    @FXML
    private Button order_btn;   
    @FXML
    private Button cancel_btn;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        article.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        total.setCellValueFactory(new PropertyValueFactory<Item, Float>("price"));
       // list_cart.getItems().set(0, new Book(new Date(2020-1900,11,11), "Mehdi", 5, 10, "Mehdi", 15, "Mehdi", 20, "", 25, 30));
        list_cart.setItems(getItems());
     
        order_btn.setOnAction(e -> {
            JOptionPane.showMessageDialog(null, "ORDER NOW");
        });
        cancel_btn.setOnAction(e -> {
            JOptionPane.showMessageDialog(null, "CANCELED");
        });
    }    
    
    
    public ObservableList<Item> getItems(){
        ObservableList<Item> list = FXCollections.observableArrayList();
        Book book = new Book(new Date(2020-1900,11,11), "Mehdi", 5, 10, "Mehdi", 15, "Mehdi", 20, "", 25, 30);
        ShoppingCart sc=new ShoppingCart(1,1);
        

       
       
        ServicesShoppingCart so = new ServicesShoppingCart();
        list.addAll(so.getCartItems(sc).getItems());
        System.out.println(so.getCartItems(sc).getItems());
    return list;
    }
}

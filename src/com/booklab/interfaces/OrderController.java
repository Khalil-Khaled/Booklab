/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.interfaces;

import com.booklab.models.Order;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OrderController implements Initializable {

    @FXML
    private TableColumn<Order, String> article;
    @FXML
    private TableColumn<Order, Integer> quantity;
    @FXML
    private TableColumn<Order, Float> total;
    @FXML
    private Label total_value;
    @FXML
    private Label orderid_value;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        article.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCart().getItem(0).getName())
       );
       //quantity.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getCart().getItemAmount(0)));
       //total.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getCart().getItem(0).getId()));
       
    }    
    
}

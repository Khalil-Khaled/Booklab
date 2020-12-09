/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.interfaces;

import com.booklab.models.Order;
import com.booklab.models.ShoppingCart;
import com.booklab.services.ServicesOrder;
import com.booklab.services.ServicesShoppingCart;
import com.booklab.tests.NewFXMain;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OrderController implements Initializable {
    @FXML
    private TableView<TableData> order_table;
    @FXML
    private TableColumn<TableData, String> article;
    @FXML
    private TableColumn<TableData, Integer> quantity;
     @FXML
    private TableColumn<TableData, Float> prix_unitaire;
    @FXML
    private TableColumn<TableData, Float> total;
    @FXML
    private Label total_value;
    @FXML
    private Label orderid_value;
    
    @FXML
    private Label orderstatus_value;
    
    private Order selectedOrder;

    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        article.setCellValueFactory(new PropertyValueFactory<TableData, String>("item_name"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("amount"));
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<TableData, Float>("prix_unitaire"));
        total.setCellValueFactory(new PropertyValueFactory<TableData, Float>("total"));
  
        
        //loadItems();
        //loadTotal();
        
 /*new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
            loadItems();
        }
    }, 10, 1000);*/
        order_table.setOnMouseEntered(e -> loadItems());
 
       
    }  
    
     public void loadTotal(ShoppingCart SC){
        ServicesShoppingCart so = new ServicesShoppingCart();
        DecimalFormat df = new DecimalFormat("#.###");
        total_value.setText(String.valueOf(df.format(so.getCartTotal(SC)))+" DT");
    }
    
    
    public void loadItems(){
        order_table.setItems(getItems());
    }
    
    public ObservableList<TableData> getItems(){
        ServicesOrder SO = new ServicesOrder();
        Order O = selectedOrder;
        O = SO.getOrderInfo(O);
        ObservableList<TableData> list = FXCollections.observableArrayList();

        for(int i =0; i<O.getCart().getItems().size(); i++){
            list.add(new TableData(O.getCart().getItems().get(i).getId(), O.getCart().getItems().get(i).getName(), O.getCart().getItemAmount(i),O.getCart().getItems().get(i).getPrice(), O.getCart().getItems().get(i).getPrice() * O.getCart().getItemAmount(i)));
        }
        loadTotal(O.getCart());
        orderid_value.setText(String.valueOf(O.getOrderref()));
        orderstatus_value.setText(O.isOrderStatus() == true ? "Payed":"Pending");
       return list;
    }
    
    public void setSelectedOrder(Order selectedOrder) throws IOException {
        //System.out.println(selectedOrder);
        this.selectedOrder = selectedOrder;
    }
    
    
    
}

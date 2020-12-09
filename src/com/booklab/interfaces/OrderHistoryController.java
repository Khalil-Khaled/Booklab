/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.interfaces;

import com.booklab.models.Order;
import com.booklab.services.ServicesOrder;
import static com.booklab.tests.NewFXMain.userID;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OrderHistoryController implements Initializable {

    @FXML
    private TableView<Order> orderlist_table;
    @FXML
    private TableColumn<Order, Date> date_col;
   // @FXML
   // private TableColumn<Order, Boolean> status_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_col.setCellValueFactory(new PropertyValueFactory<Order, Date>("orderDate"));
        //status_col.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("orderStatus"));
        
        setHistoryItems();
     

   }
    
    
    
   
    
    
    public void setHistoryItems(){
        ServicesOrder so = new ServicesOrder();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.addAll(so.getAllOrders(userID));
        
        orderlist_table.setItems(orders);
    }

    @FXML
    private void setSelectedItem(MouseEvent e) {
        Order o = orderlist_table.getSelectionModel().getSelectedItem();
            if (e.getClickCount() == 2 && !(o==null) ) { 
                try {                    
                    sendData(o);
                } catch (IOException ex) {
                    Logger.getLogger(OrderHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    public void sendData(Order o) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Order.fxml"));
        Parent parent = loader.load();
        
        OrderController c = loader.getController();
   
        c.setSelectedOrder(o);
        
        Scene scene = new Scene(parent);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();
    }
    

    
}

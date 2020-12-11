/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Category;
import com.booklab.models.Item;
import com.booklab.services.ServicesBookAdmin;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class ItemsViewController implements Initializable {

    @FXML
    private TableView<Item> itemsTable;
    @FXML
    private TableColumn<Item, String> itemName;
    @FXML
    private TableColumn<Item, String> type;
    @FXML
    private TableColumn<Category, String> category;
    @FXML
    private TableColumn<Item, String> description;
    @FXML
    private TableColumn<Item, Double> price;
    @FXML
    private TableColumn<Item, Integer> quantity;
    @FXML
    private TableColumn<Item, Integer> rating;
    @FXML
    private TableColumn<Item, String> author;
    @FXML
    private TableColumn<Item, Date> publishDate;
    @FXML
    private TableColumn<Item, Integer> pageNumber;
    
    ServicesBookAdmin sba = new ServicesBookAdmin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemName.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        type.setCellValueFactory(new PropertyValueFactory<Item, String>("Type"));
        category.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryName"));
        description.setCellValueFactory(new PropertyValueFactory<Item, String>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<Item, Double>("Price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Quantity"));
        rating.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Rating"));
        author.setCellValueFactory(new PropertyValueFactory<Item, String>("Author"));
        publishDate.setCellValueFactory(new PropertyValueFactory<Item, Date>("PublishDate"));
        pageNumber.setCellValueFactory(new PropertyValueFactory<Item, Integer>("PageNumber"));
        
        ArrayList<Item> items = sba.selectItems();
        
        
        ObservableList<Item> data = FXCollections.<Item>observableArrayList(items);
        itemsTable.setItems(data);
    }    


    @FXML
    private void addToCart(ActionEvent event) {
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
    }

   

    
}

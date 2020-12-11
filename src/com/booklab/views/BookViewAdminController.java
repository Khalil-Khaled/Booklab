/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Category;
import com.booklab.models.Item;
import com.booklab.models.Offer;
import com.booklab.services.OfferServices;
import com.booklab.services.ServicesBookAdmin;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

/**
 *
 * @author steam
 */
   
 
public class BookViewAdminController implements Initializable {

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
    
    @FXML
    private AnchorPane AdminItemActions;
    
    static AnchorPane addBook,addAccessory;
    
    ServicesBookAdmin sba = new ServicesBookAdmin();
    
     //ObservableList<Event> listM;
    //ItemServices is=new ItemServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {

            addBook = FXMLLoader.load(getClass().getResource("CreateBookView.fxml"));
            addAccessory = FXMLLoader.load(getClass().getResource("CreateAccessoryAdmin.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(OffersViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        
        
        UpdateTable();
    }    

    @FXML
    private void createBook(ActionEvent event) {
        setNode(addBook);
    }
    


    
   

    @FXML
     public void UpdateTable() {
        
        ArrayList<Item> items = sba.selectItems();
        
        
        ObservableList<Item> data = FXCollections.<Item>observableArrayList(items);
        itemsTable.setItems(data);
        
    }
     
     private void setNode(Node node) {

        AdminItemActions.getChildren().clear();
        AdminItemActions.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        node.setVisible(true);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }

    @FXML
    private void createAccessory(ActionEvent event) {
        setNode(addAccessory);
    }

    @FXML
    private void removeItem(ActionEvent event) {
        
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
        if (itemSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            sba.deleteItem(itemSelected);
            
            UpdateTable();
        }
    }

    @FXML
    private void updatePrice(TableColumn.CellEditEvent edittedCell) {
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
        itemSelected.setPrice(Double.parseDouble(edittedCell.getNewValue().toString()));
        sba.updateItem(itemSelected);
    }

    @FXML
    private void updateQuantity(TableColumn.CellEditEvent edittedCell) {
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
        System.out.println(itemSelected);
        itemSelected.setQuantity(Integer.valueOf(edittedCell.getNewValue().toString()));
        sba.updateItem(itemSelected);
    }
    
    
     
     

}
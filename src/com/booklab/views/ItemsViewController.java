/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.Utils.NotificationAPI;
import com.booklab.models.Category;
import com.booklab.models.Item;
import com.booklab.models.ShoppingCart;
import com.booklab.services.CustomerServices;
import com.booklab.services.ServicesBookAdmin;
import com.booklab.services.ServicesShoppingCart;
import com.booklab.services.WishlistServices;
import static com.booklab.views.UserloginController.idlogin;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

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

    WishlistServices ws = new WishlistServices();

    CustomerServices cs = new CustomerServices();
    @FXML
    private Button btnWishlist;

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

        if (ws.getWishlistId(cs.showcustomer(idlogin).getUserid()) == 0) {
            cs.showcustomer(idlogin).setWishId((ws.createWishlist(cs.showcustomer(idlogin).getUserid())));
        }

    }

    @FXML
    private void addToCart(ActionEvent event) throws IOException {
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
        if (itemSelected == null) {
            JOptionPane.showMessageDialog(null, "No book selected!");
        } else {
            ServicesShoppingCart ssc = new ServicesShoppingCart();
            ShoppingCart SC = new ShoppingCart(idlogin);

            SC = new ShoppingCart(idlogin);
            SC = ssc.setActiveCart(SC);
            if (SC.getCartID() == 0) {
                ssc.createCart(SC);
            }

            SC.addItem(itemSelected, 1);
            ssc.addItemsToCart(SC);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShoppingCart.fxml"));
            Parent parent = loader.load();

            ShoppingCartController c = loader.getController();
            c.loadItems();

            JOptionPane.showMessageDialog(null, "Item added!");
        }
    }

    @FXML
    private void ajouterDansWs(ActionEvent event) {
        Item itemSelected = itemsTable.getSelectionModel().getSelectedItem();
        ws.ajouterDansWishlist(cs.showcustomer(idlogin), itemSelected);
        NotificationAPI.notifConfirm("you have added a book to your wishlist", "go check it");
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("book added");

    }
}

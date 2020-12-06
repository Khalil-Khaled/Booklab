/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.interfaces;

import com.booklab.models.Book;
import com.booklab.models.Item;
import com.booklab.models.Order;
import com.booklab.models.ShoppingCart;
import com.booklab.services.ServicesOrder;
import com.booklab.services.ServicesShoppingCart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ShoppingCartController implements Initializable {

    @FXML
    private TableView<TableData> list_cart;
    @FXML
    private TableColumn<TableData, String> article;
    @FXML
    private TableColumn<TableData, Integer> quantity;
    @FXML
    private TableColumn<TableData, Float> total;
    @FXML
    private TableColumn<TableData, Float> prix_unitaire;
    @FXML
    private Button order_btn;   
    @FXML
    private Button cancel_btn;
    @FXML
    private Button remove_btn;

    
    @FXML
    private Text sum_total;
    @FXML
    private TextField card_number;
    @FXML
    private TextField exp_month;
    @FXML
    private TextField exp_year;
    @FXML
    private TextField cvc;
 
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicesShoppingCart ssc = new ServicesShoppingCart();
        /*ArrayList<Item> i = ssc.getCartItems(new ShoppingCart(1,1)).getItems();
        for(Item item : i)
            System.out.println(item.getName());*/
       
        ShoppingCart SC = new ShoppingCart(1, 1);
        SC = ssc.getCartItems(SC);
            SC.addItem(new Book(1), 1);
            SC.addItem(new Book(2), 1);
            SC.addItem(new Book(3), 1);
         ssc.addItemsToCart(SC);
            
        
  
            
        
        article.setCellValueFactory(new PropertyValueFactory<TableData, String>("item_name"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("amount"));
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<TableData, Float>("prix_unitaire"));
        total.setCellValueFactory(new PropertyValueFactory<TableData, Float>("total"));
        
       // list_cart.getItems().set(0, new Book(new Date(2020-1900,11,11), "Mehdi", 5, 10, "Mehdi", 15, "Mehdi", 20, "", 25, 30));
       
       loadItems();
     
        order_btn.setOnAction(e -> {
            //createOrder();
            //JOptionPane.showMessageDialog(null, "Order Created!");
            pay();
        });
        cancel_btn.setOnAction(e -> {
            JOptionPane.showMessageDialog(null, "Shopping Canceled!");
            clearOrder();
            loadItems();
            loadTotal();
        });
        
        list_cart.setEditable(true);
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        remove_btn.setOnAction(e -> {
            deleteRow();
            loadItems();
        });
        loadTotal();
        
 
    } 
    
    public void loadTotal(){
        ServicesShoppingCart so = new ServicesShoppingCart();
        DecimalFormat df = new DecimalFormat("#.###");
        sum_total.setText(String.valueOf(df.format(so.getCartTotal(new ShoppingCart(1,1))))+" DT");
    }
    
    public void loadItems(){
        list_cart.setItems(getItems());
    }
    
    public ObservableList<TableData> getItems(){
        ShoppingCart sc=new ShoppingCart(1,1);
        ObservableList<TableData> list = FXCollections.observableArrayList();
            ServicesShoppingCart so = new ServicesShoppingCart();
            sc = so.getCartItems(sc);
        
 
        for(int i =0; i<sc.getItems().size(); i++){
            list.add(new TableData(sc.getItems().get(i).getId(), sc.getItems().get(i).getName(), sc.getItemAmount(i),sc.getItems().get(i).getPrice(), sc.getItems().get(i).getPrice() * sc.getItemAmount(i)));
        }
       return list;
    }
    
    @FXML
    public void changeQuantity(CellEditEvent editedCell) {
        TableData eventSelected = list_cart.getSelectionModel().getSelectedItem();
        eventSelected.setAmount((int) editedCell.getNewValue());
        ServicesShoppingCart ssc = new ServicesShoppingCart();
        ssc.updateCartItem(eventSelected);
        loadTotal();
        loadItems();
    }
    
    public void deleteRow() {
        TableData eventSelected = list_cart.getSelectionModel().getSelectedItem();
        if (eventSelected == null)
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        else{
            ServicesShoppingCart ssc = new ServicesShoppingCart();
            ssc.removeOneItem(eventSelected);
            loadTotal();
        }
    }

    private void createOrder() {
        ServicesShoppingCart sso = new ServicesShoppingCart();
        ShoppingCart sc = new ShoppingCart(1,1);
        sc = sso.getCartItems(sc);
 
        ServicesOrder so = new ServicesOrder();
        Order o = new Order(sc, true, new Date(2020-1900,10,10));
        so.insertOrder(o);
        sso.removeItemsFromCart(sc);
       
        
    }

    private void clearOrder() {
        ServicesShoppingCart sso = new ServicesShoppingCart();
        ShoppingCart sc = new ShoppingCart(1,1);
        sc = sso.getCartItems(sc);
        sso.removeItemsFromCart(sc);
    }

    private void pay() {
        try{
            Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
            Customer a = Customer.retrieve("sk_test_4eC39HqLyjWDarjtT1zdp7dc");
            Map <String, Object> cardParam = new HashMap<String, Object>();
            if(!(card_number.getText().equals("") || exp_month.getText().equals("") || exp_year.getText().equals("") || cvc.getText().equals(""))){
                cardParam.put("number", card_number.getText());
                cardParam.put("exp_month", Integer.parseInt(exp_month.getText()));
                cardParam.put("exp_year", Integer.parseInt(exp_year.getText()));
                cardParam.put("cvc", cvc.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please check your card information!");
                return;
            }
            Map <String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);
            
            Token token = Token.create(tokenParam);
            
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());
            
            a.getSources().create(source);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(token));
            
            Map<String,Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", sum_total.getText());
            chargeParam.put("currency", "tnd");
            chargeParam.put("source", token.getId());
            
            Charge.create(chargeParam);
        }catch(StripeException e){
            System.out.println(e.getMessage());            
        }
    }
}

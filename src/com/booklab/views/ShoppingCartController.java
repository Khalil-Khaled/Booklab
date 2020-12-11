/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.Utils.NotificationAPI;

import com.booklab.models.Item;
import com.booklab.models.Order;
import com.booklab.models.ShoppingCart;
import com.booklab.services.ServicesOrder;
import com.booklab.services.ServicesShoppingCart;
import static com.booklab.views.UserloginController.idlogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML
    private Button history;
     private double xOffset = 0;
    private double yOffset = 0;
     Stage dialogStage = new Stage();
    Scene scene;
    private ShoppingCart SC = new ShoppingCart(idlogin);
    /**
     * Initializes the controller class.
     */
    private ServicesShoppingCart ssc = new ServicesShoppingCart();
    @FXML
    private Button coupon;
    @FXML
    private Button refreshBtn;
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*ArrayList<Item> i = ssc.getCartItems(new ShoppingCart(1,1)).getItems();
        for(Item item : i)
            System.out.println(item.getName());*/

verifInput();
            
        
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
        
        history.setOnAction(e ->{
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderHistory.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
        
        
        newWindow.setTitle("Purchase History");
        newWindow.setScene(scene);
        newWindow.show();
            }catch(IOException Ex){
                System.out.println(Ex.getMessage());
            }
        });
        
 
    } 
    
    public void addbookfortests(){
        SC.addItem(new Item(1), 1);
            SC.addItem(new Item(2), 1);
           SC.addItem(new Item(3), 1);
          ssc.addItemsToCart(SC);
          loadItems();
          loadTotal();
    }
    
    public void initCart(){
        SC = ssc.setActiveCart(SC);
        if (SC.getCartID() == 0)
            ssc.createCart(SC);
        else
        SC = ssc.getCartItems(SC);
    }
    
    public void loadTotal(){
        DecimalFormat df = new DecimalFormat("#.###");
        sum_total.setText(String.valueOf(df.format(ssc.getCartTotal(SC)))+" DT");
    }
    
    public void loadItems(){
        initCart();
        list_cart.setItems(getItems());
    }
    
    public ObservableList<TableData> getItems(){
        ObservableList<TableData> list = FXCollections.observableArrayList();
 
        for(int i =0; i<SC.getItems().size(); i++){
            list.add(new TableData(SC.getItems().get(i).getId(), SC.getItems().get(i).getName(), SC.getItemAmount(i),SC.getItems().get(i).getPrice(), SC.getItems().get(i).getPrice() * SC.getItemAmount(i)));
        }
       return list;
    }
    
    @FXML
    public void changeQuantity(CellEditEvent editedCell) {
        TableData eventSelected = list_cart.getSelectionModel().getSelectedItem();
        eventSelected.setAmount((int) editedCell.getNewValue());
        
        ssc.updateCartItem(eventSelected);
        loadTotal();
        loadItems();
    }
    
    public void deleteRow() {
        TableData eventSelected = list_cart.getSelectionModel().getSelectedItem();
        if (eventSelected == null)
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        else{
            ssc.removeOneItem(eventSelected);
            loadTotal();
        }
    }

    private void createOrder() {
        ServicesOrder so = new ServicesOrder();
        
        Order o = new Order(SC, true, new Date(2020-1900,10,10));
        so.insertOrder(o);
        
        // Create new Cart for that user
            SC.setCartID(0);
            ssc.createCart(SC);
            
            loadItems();
            JOptionPane.showMessageDialog(null, "Your order was Created! Check your purchase history.");
        
       
        
    }
    @FXML  
    private void coupon(ActionEvent event) throws IOException{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("UserCouponView.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                Stage newWindow = new Stage();
                newWindow.setScene(scene);
                newWindow.show();
            
              
            }
      
    
    
    
    private void clearOrder() {
        SC = ssc.getCartItems(SC);
        ssc.removeItemsFromCart(SC);
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
            
            //a.getSources().create(source);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(token));
            
            Map<String,Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", Integer.parseInt(sum_total.getText().substring(0, sum_total.getText().length()-3)));
            chargeParam.put("currency", "usd");
            chargeParam.put("source", token.getId());
            
            Charge.create(chargeParam);
            NotificationAPI.notifInfo("Payment", "Your payment was successful!");
            // Create Order
            ServicesOrder so = new ServicesOrder();
            
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Order O = new Order(SC, true, date);
            createOrder();
            
            
        }catch(StripeException e){
            System.out.println(e.getMessage());   
            NotificationAPI.notif("Payment", "An error has occured with your Payment!");
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        loadItems();
        loadTotal();
    }

    private void verifInput() {
        card_number.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,16}?")) {
                    card_number.setText(oldValue);
                }
            }
        });

        exp_month.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}?")) {
                    exp_month.setText(oldValue);
                }
            }
        });

        exp_year.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    exp_year.setText(oldValue);
                }
            }
        });

        cvc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    cvc.setText(oldValue);
                }
            }
        });
    }

 
}

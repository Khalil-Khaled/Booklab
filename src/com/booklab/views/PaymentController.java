/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField ccNumTF;
    @FXML
    private Button payBtn;
    @FXML
    private TextField expMMTF;
    @FXML
    private TextField expYYYYTF;
    @FXML
    private TextField cvvTF;
    @FXML
    private Label amountLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ccNumTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,16}?")) {
                    ccNumTF.setText(oldValue);
                }
            }
        });
        
        expMMTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}?")) {
                    expMMTF.setText(oldValue);
                }
            }
        });
        
        expYYYYTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    expYYYYTF.setText(oldValue);
                }
            }
        });
        
        cvvTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    cvvTF.setText(oldValue);
                }
            }
        });
    }    



    @FXML
    private void pay(ActionEvent event) {
        try{
            Stripe.apiKey = "sk_test_51Hvo1xFV1bZqTpffmYhD2zfiW0CPKWJSvjd7M3XQxBCDbeawYGBjWh7fsXc0svFwtJ2IG5vRSftoC9H9uWbe6FOL00rzNhFj2z";
            Customer a = Customer.retrieve("cus_IWrnkhfWKKOesh");
            Map <String, Object> cardParam = new HashMap<String, Object>();
            if(!(ccNumTF.getText().equals("") || expMMTF.getText().equals("") || expYYYYTF.getText().equals("") || cvvTF.getText().equals(""))){
                cardParam.put("number", ccNumTF.getText());
                cardParam.put("exp_month", Integer.parseInt(expMMTF.getText()));
                cardParam.put("exp_year", Integer.parseInt(expYYYYTF.getText()));
                cardParam.put("cvc", cvvTF.getText());
                System.out.println(cardParam);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please check your card information!");
                return;
            }
            float amountFloat =  Float.parseFloat(amountLabel.getText())*100;
            int amount = (int) amountFloat;
            
            
            Map <String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);

            Token token = Token.create(tokenParam);
            
            Map<String,Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount",amount );
            chargeParam.put("currency", "usd");
            chargeParam.put("source", token.getId());
             System.out.println(chargeParam);

            Charge.create(chargeParam);

            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());

//            a.getSources().create(source);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(token));

            
        }catch(StripeException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}

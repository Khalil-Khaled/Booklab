/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.services.CustomerServices;
import static com.booklab.views.UserloginController.idlogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import com.stripe.model.PaymentIntent;
import com.booklab.models.Customer;
import com.booklab.services.OfferServices;
import com.stripe.model.Account;
import com.stripe.model.AccountCollection;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
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
    
    private static Charge charge;
    @FXML
    private TextField payName;
    @FXML
    private TextField payEmail;
    @FXML
    private TextField payAddress;
    @FXML
    private TextField payCity;
    @FXML
    private TextField payZIP;
    @FXML
    private Label offerDetail;

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
    private void pay(ActionEvent event) throws Exception {
        try {
            Stripe.apiKey = "sk_test_51Hvo1xFV1bZqTpffmYhD2zfiW0CPKWJSvjd7M3XQxBCDbeawYGBjWh7fsXc0svFwtJ2IG5vRSftoC9H9uWbe6FOL00rzNhFj2z";
            
            Map<String, Object> cardParam = new HashMap<String, Object>();
            if (!(ccNumTF.getText().equals("") || expMMTF.getText().equals("") || expYYYYTF.getText().equals("") || cvvTF.getText().equals(""))) {
                cardParam.put("number", ccNumTF.getText());
                cardParam.put("exp_month", Integer.parseInt(expMMTF.getText()));
                cardParam.put("exp_year", Integer.parseInt(expYYYYTF.getText()));
                cardParam.put("cvc", cvvTF.getText());
                cardParam.put("name", payName.getText());
                cardParam.put("address_line1", payAddress.getText());
                cardParam.put("address_city", payCity.getText());
                cardParam.put("address_zip", payZIP.getText());
                
                
                System.out.println("card params : "+cardParam);
            } else {
                JOptionPane.showMessageDialog(null, "Please check your card information!");
                return;
            }
            float amountFloat = Float.parseFloat(amountLabel.getText()) * 100;
            int amount = (int) amountFloat;
            
            OffersViewController ovc = new OffersViewController();
            String seller_id = ovc.getSellerId();
            System.out.println(seller_id);
            
            Map<String, Object> params = new HashMap<>();
            AccountCollection accounts = Account.list(params);
            System.out.println(accounts.getData());

            Map<String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);

            Token token = Token.create(tokenParam);
            
            Map<String, Object> addressParam = new HashMap<String, Object>();
            addressParam.put("city", payCity.getText());
            addressParam.put("line1", payAddress.getText());
            addressParam.put("postal_code", payZIP.getText());
            
            Map<String, Object> shippingParam = new HashMap<String, Object>();
            shippingParam.put("address", addressParam);
            shippingParam.put("name", payName.getText());

            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", amount);
            chargeParam.put("currency", "usd");
            chargeParam.put("source", token.getId());
            chargeParam.put("receipt_email",payEmail.getText() );
            chargeParam.put("shipping", shippingParam);
            chargeParam.put("description", offerDetail.getText());
            chargeParam.put("on_behalf_of", seller_id);
            
            Map<String, Object> transferDataParams = new HashMap<>();
            transferDataParams.put("destination", seller_id);
            chargeParam.put("transfer_data", transferDataParams);
            
            
            System.out.println("charge params : "+chargeParam);

            charge = Charge.create(chargeParam);
            System.out.println("charge"+charge);
            
            //send confirmation email
            if (charge.getStatus().equals("succeeded")) {
                CustomerServices cs = new CustomerServices();
                Customer c = cs.showcustomer(idlogin);
                JavaMailTransaction.sendMail(c.getEmail());
                JOptionPane.showMessageDialog(null, "Your payment is completed !");
                JavaMailTransaction.sendMail(charge.getReceiptEmail());
            }

//            Map<String, Object> source = new HashMap<String, Object>();

//            a.getSources().create(source);
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(token));
//            System.out.println(token);

        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Charge getCharge (){
        return charge;
    }

}

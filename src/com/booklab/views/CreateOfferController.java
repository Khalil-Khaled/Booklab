/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;



import com.booklab.models.Offer;
import com.booklab.services.OfferServices;
import com.booklab.tests.logintest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountCollection;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class CreateOfferController implements Initializable {

    @FXML
    private TextField priceTF;
    @FXML
    private TextArea DescriptionTF;
    @FXML
    private RadioButton venteRadio;
    @FXML
    private RadioButton pretRadio;
    @FXML
    private Button createOfferBtn;
    @FXML
    private ToggleGroup ventePret;

    @FXML
    private AnchorPane createOfferAnchor;
    @FXML
    private TextField stripeID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        createOfferAnchor.setVisible(true);
        priceTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,2})?")) {
                    priceTF.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void createOffer(ActionEvent event) throws StripeException {
        String radioText;
        if (venteRadio.isSelected()) {
            radioText = venteRadio.getText();
        } else {
            radioText = pretRadio.getText();
        }

        Offer offer = new Offer(radioText, Float.parseFloat(priceTF.getText()), DescriptionTF.getText(), 50, stripeID.getText());

        OfferServices os = new OfferServices();
        os.createOffer(offer);
        JOptionPane.showMessageDialog(null, "Offer successfully added !");
        createConnectedAccount();

        
        createOfferAnchor.setVisible(false);
        priceTF.setText("");
        DescriptionTF.setText("");
        venteRadio.setSelected(false);
        pretRadio.setSelected(false);
        stripeID.setText("");
    }

    private void createConnectedAccount() throws StripeException {
        Stripe.apiKey = "sk_test_51Hvo1xFV1bZqTpffmYhD2zfiW0CPKWJSvjd7M3XQxBCDbeawYGBjWh7fsXc0svFwtJ2IG5vRSftoC9H9uWbe6FOL00rzNhFj2z";

        Map<String, Object> params = new HashMap<>();
        AccountCollection accounts = Account.list(params);
//        System.out.println(accounts.getData());

        boolean exist = false;
        for (int i = 0; i < accounts.getData().size(); i++) {
            System.out.println(accounts.getData().get(i).getExternalAccounts());
            if (accounts.getData().get(i).getId() == stripeID.getText()) {
                exist = true;
            }
        }
        if (exist == false) {
//            Map<String, Object> params1 = new HashMap<>();
//            params1.put("account", stripeID.getText());
//            params1.put(
//                    "refresh_url",
//                    "https://example.com/reauth"
//            );
//            params1.put(
//                    "return_url",
//                    "https://example.com/return"
//            );
//            params1.put("type", "account_onboarding");
//
//            AccountLink accountLink = AccountLink.create(params1);

            logintest a = new logintest();
            a.getHostServices().showDocument("https://connect.stripe.com/express/oauth/authorize?redirect_uri=https://connect.stripe.com/hosted/oauth&client_id=ca_IXEy7KYcmtDwbwNXJS2AnWYZ4d6dyewh&state=onbrd_IXegYfqVzfJfv5yKQoeVl7o3WM&stripe_user[country]=US");

//        AccountLinkCreateParams params2
//                = AccountLinkCreateParams.builder()
//                        .setAccount(stripeID.getText())
//                        .setRefreshUrl("https://example.com/reauth")
//                        .setReturnUrl("https://example.com/return")
//                        .setType(AccountLinkCreateParams.Type.CUSTOM_ACCOUNT_VERIFICATION)
//                        .build();
//        AccountLink accountLink = AccountLink.create(params2);
//            System.out.println("accountLink : "+accountLink);
        }

    }

}

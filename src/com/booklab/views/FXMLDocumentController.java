/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import com.booklab.tests.logintest;
import static com.booklab.views.UserloginController.idlogin;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnPricing;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnAlerts;
    @FXML
    private Label user;
    @FXML
     private ImageView imageuser;
    
    AnchorPane contacts,alerts,pricing,profiles,complaints,controls,offers;
    @FXML
    private JFXButton btnControls;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
//        CustomerServices s=new CustomerServices();
//        Customer c=s.showcustomer(UserloginController.idlogin);
//         File file = new File(c.getProfilimage());
//        Image image = new Image(file.toURI().toString());
//        imageuser.setImage(image);
//        user.setText("User : "+c.getUserName());
//image and user in the dashboard
        try {
            
            
             contacts = FXMLLoader.load(getClass().getResource("Contacts.fxml"));
             alerts = FXMLLoader.load(getClass().getResource("Alerts.fxml"));
             pricing = FXMLLoader.load(getClass().getResource("Pricing.fxml"));
             profiles = FXMLLoader.load(getClass().getResource("Profiles.fxml"));
             complaints = FXMLLoader.load(getClass().getResource("complaints.fxml"));
             controls = FXMLLoader.load(getClass().getResource("Controls.fxml"));
             offers = FXMLLoader.load(getClass().getResource("offersView.fxml"));
            setNode(pricing);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
           
    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void switchPricing(ActionEvent event) {
        setNode(offers);
    }

    @FXML
    private void switchContacts(ActionEvent event) {
        setNode(contacts);
    }

    @FXML
    private void switchWidget(ActionEvent event) {
        setNode(complaints);
    }

    @FXML
    private void switchProfile(ActionEvent event) {
        setNode(profiles);
    }

    @FXML
    private void switchAlert(ActionEvent event) {
        setNode(alerts);
    }

    @FXML
    private void switchControls(ActionEvent event) {
        setNode(controls);
    }
    

}

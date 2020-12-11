/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Naoures Hidri
 */
public class AdminController implements Initializable {
    
    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUser;
    @FXML
    private JFXButton btnCategory;
    @FXML
    private JFXButton btnEvent;
    @FXML
    private JFXButton btnOffers;
    @FXML
    private JFXButton btnComplaint;
    Stage dialogStage = new Stage();
    Scene scene;
    AnchorPane profile, users, category, events,offers, complaint,coupon;
    /**1
     * Initializes the controller class.
     */
     private void close_app(MouseEvent event) {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            profile=FXMLLoader.load(getClass().getResource("Contacts.fxml"));
            users= FXMLLoader.load(getClass().getResource("UsersView.fxml"));
            category=FXMLLoader.load(getClass().getResource("BookViewAdmin.fxml"));
            events=FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
            offers=FXMLLoader.load(getClass().getResource("offersViewAdmin.fxml"));
            complaint=FXMLLoader.load(getClass().getResource("AdminComplaints.fxml"));
            coupon=FXMLLoader.load(getClass().getResource("adminCouponView.fxml"));
            setNode(category);
        }catch(IOException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
       //Set selected node to a content holder
    private void setNode(Node node) {
        menu.getChildren().clear();
        menu.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    @FXML
     private void switchEvent(ActionEvent event) {
        setNode(profile);
    }
    @FXML
     private void switchUser(ActionEvent event) {
        setNode(profile);
    }
      @FXML
     private void switchComplaint(ActionEvent event) {
        setNode(profile);
    }
     @FXML
     private void switchItem(ActionEvent event) {
        setNode(profile);
    }
     @FXML
     private void switchProfile(ActionEvent event) {
        setNode(profile);
    }
     @FXML
     private void switchUsers(ActionEvent event) {
        setNode(users);
    }
     @FXML
     private void switchCategory(ActionEvent event) {
        setNode(category);
    }
     @FXML
     private void switchEvents(ActionEvent event) {
        setNode(events);
    }
     @FXML
     private void switchOffers(ActionEvent event) {
        setNode(offers);
    }
     @FXML
      private void switchComplaints(ActionEvent event) {
        setNode(complaint);
    }
      @FXML
      private void switchcoupon(ActionEvent event) {
        setNode(coupon);
    }
}


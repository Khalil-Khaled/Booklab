/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import static com.booklab.views.UserloginController.idlogin;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Stage dialogStage = new Stage();
    Scene scene;
    AnchorPane profile, users, items, events,offers, complaint,coupon;
    @FXML
    private AnchorPane anchorAdmin;
    @FXML
    private Button logout;
      private double xOffset = 0;
    private double yOffset = 0;
    
    /**1
     * 
     * Initializes the controller class.
     */
     private void close_app(MouseEvent event) {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            
            users= FXMLLoader.load(getClass().getResource("UsersView.fxml"));
            items=FXMLLoader.load(getClass().getResource("BookViewAdmin.fxml"));
            events=FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
            offers=FXMLLoader.load(getClass().getResource("offersViewAdmin.fxml"));
            complaint=FXMLLoader.load(getClass().getResource("AdminComplaints.fxml"));
            coupon=FXMLLoader.load(getClass().getResource("adminCouponView.fxml"));
            setNode(items);
        }catch(IOException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     @FXML
    private void logout(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("adminlogin.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
                
                scene.setOnMousePressed(new EventHandler<MouseEvent>() {
   
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
                });
                scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                dialogStage.setX(event.getScreenX() - xOffset);
                dialogStage.setY(event.getScreenY() - yOffset);
                } });
                idlogin=0;
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
        setNode(events);
    }
    @FXML
     private void switchUser(ActionEvent event) {
        setNode(users);
    }
      @FXML
     private void switchComplaint(ActionEvent event) {
        setNode(complaint);
    }
     
     private void switchProfile(ActionEvent event) {
        setNode(profile);
    }
  
    @FXML
     private void switchOffers(ActionEvent event) {
        setNode(offers);
    }
     
      @FXML
      private void switchcoupon(ActionEvent event) {
        setNode(coupon);
    }

    @FXML
    private void switchItems(ActionEvent event) {
        setNode(items);
    }

  
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import com.booklab.tests.logintest;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class UserloginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    
    Stage dialogStage = new Stage();
    Scene scene;
   
    /**
     * Initializes the controller class.
     */
 
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeStageDrageable();
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }
    private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logintest.stage.setX(event.getScreenX() - xOffset);
                logintest.stage.setY(event.getScreenY() - yOffset);
                logintest.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            logintest.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            logintest.stage.setOpacity(1.0f);
        });

    }
    public static void infoBox(String infoMessage,String title,String herdertext){
     Alert alert = new Alert(AlertType.CONFIRMATION);
     alert.setContentText(infoMessage);
     alert.setTitle(title);
     alert.setHeaderText(herdertext);
     alert.showAndWait();
     
    }
  @FXML
    private void authentification(ActionEvent event) throws IOException {
      CustomerServices s=new CustomerServices();
      String s1=username.getText().toString();
      String s2=password.getText().toString();
      boolean passauth=s.authentification(s1,s2);
      if(!passauth)
          infoBox("Please enter correct username and password",null,"FAILED");
      else{
             Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
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
      }
     
 }      
    @FXML
        private void registert(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("register.fxml")));
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
            }
        });
    
     }

}
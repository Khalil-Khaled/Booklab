/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import com.booklab.tests.logintest;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class AdminloginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    public static int idlogin;
    Stage dialogStage = new Stage();
    Scene scene;
    public static String mail;
    @FXML
    private JFXButton register;
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
      String s1=username.getText();
      String s2=password.getText();
      
      boolean passauth=s.authentification(s1,s2);
      if((s1.length()==0)||(s2.length()==0))
          infoBox("Please enter correct username and password",null,"FAILED");
      else if((!passauth)){

          infoBox("Please enter correct username and password",null,"FAILED");}
      else{
                idlogin=s.idlogin(s1,s2);
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Admin.fxml")));
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
     
 }  @FXML

 
    
    
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
        @FXML
        private void loginCustomer (ActionEvent event) throws IOException, Exception {
          Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("userlogin.fxml")));
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
        @FXML
        private void forgetpassword(ActionEvent event) throws IOException, Exception {
         TextInputDialog dialog = new TextInputDialog("Email");
         dialog.setTitle("forget password");
         
         dialog.setContentText("give the email :");
         
         // Traditional way to get the response value.
         Optional<String> result = dialog.showAndWait();
         if (!(result.get().length()==0)){
            JavaMailUtil.sendMail(result.get());
            TextInputDialog dialogi = new TextInputDialog("code");
            dialogi.setTitle("new password");
            dialogi.setHeaderText("Look, a Text Input Dialog");
            dialogi.setContentText("code verification :");
        
            // Traditional way to get the response value.
            Optional<String> result1 = dialogi.showAndWait();
            mail=result.get();
            if (result1.get().equals(JavaMailUtil.code)){
                   Node node = (Node)event.getSource();
                   dialogStage = (Stage) node.getScene().getWindow();
                   dialogStage.close();
                   scene = new Scene(FXMLLoader.load(getClass().getResource("forgetpassword.fxml")));
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
        }

    @FXML
    private void registert(InputMethodEvent event) {
    }
}
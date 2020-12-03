/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author radhwene
 */
public class RegisterController implements Initializable {
    
    @FXML
    private Label btn_exit;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    
    @FXML
    private TextField username;

    @FXML
    private TextField email;
    @FXML
    private TextField questionverif;
    @FXML
    private TextField answerverif;
    @FXML 
    private Button image;
    @FXML
    private Label labSingleFile;
    private String imagelink;
   private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    
    Stage dialogStage = new Stage();
    Scene scene;
   
    
    public String getImagelink() {
        return imagelink;
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
      System.exit(0);
    }
    @FXML
    private void returns(MouseEvent event) throws IOException {
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
     private void image(ActionEvent event) {
     FileChooser fc = new FileChooser(); 
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
     File f = fc.showOpenDialog(null);
        if (f!=null){
          labSingleFile.setText("selected file:"+f.getAbsolutePath());
          imagelink=f.getAbsolutePath();
          System.out.println(imagelink);
        }
        
    }
     
     @FXML
     private void adduser(ActionEvent event) {
         CustomerServices s = new CustomerServices();
         
         s.create(new Customer(username.getText(),firstname.getText(),lastname.getText(),email.getText(),password.getText(),questionverif.getText(),answerverif.getText(),this.getImagelink(),1,2,3));
        JOptionPane.showMessageDialog(null,"customer added");
        
         
         
    }
     
}

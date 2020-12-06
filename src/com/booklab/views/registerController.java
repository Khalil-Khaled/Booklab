/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author proxc
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
    private TextField image;
    @FXML
    private void handleButtonAction(MouseEvent event) {
      System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void adduser(ActionEvent event) {
         CustomerServices s = new CustomerServices();
         s.create(new Customer(username.getText(),firstname.getText(),lastname.getText(),email.getText(),password.getText(),questionverif.getText(),answerverif.getText(),image.getText(),1,2,3));
        JOptionPane.showMessageDialog(null,"customer added");
        
         
         
    }
}

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Button btn;
    @FXML
    private TextField username;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private Label questionverif;
    @FXML
    private Label answerverif;
    @FXML
    private Label image;
    @FXML
    private TextField email1;
    @FXML
    private TextField password1;
    @FXML
    private TextField questionverif1;
    @FXML
    private TextField answerverif1;
    @FXML
    private TextField image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouteruser(ActionEvent event) {
         CustomerServices s = new CustomerServices();
         s.create(new Customer(username.getText(),nom.getText(),prenom.getText(),email1.getText(),password1.getText(),questionverif1.getText(),answerverif1.getText(),image1.getText(),1,2,3));
        JOptionPane.showMessageDialog(null,"customer added");
    }
    
}

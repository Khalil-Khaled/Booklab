/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Offer;
import com.booklab.models.User;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Naoures Hidri
 */
public class UsersViewController implements Initializable {
    
    @FXML
    private TableColumn<User, String> nameUser;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, Image> imageUser;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> emailUser;
    @FXML
    private AnchorPane anchorUser;

    @FXML
    private Button btnUser;
    
    @FXML
    private TableView<?> userTable;
    @FXML
    private TableColumn<?, ?> quesionVerif;
    @FXML
    private JFXButton bntUsr;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void refreshUser(ActionEvent event) {
    }

    @FXML
    private void RemoveUser(ActionEvent event) {
    }
    
}

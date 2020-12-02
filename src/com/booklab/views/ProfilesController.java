/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class ProfilesController implements Initializable {
    private String imagelink;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//    @FXML
//    private void updatecustomer(ActionEvent event ) {
//        CustomerServices s=new CustomerServices();
//        Customer c = new Customer();
//        s.modify(c);
//    }
        @FXML
     
        private void image(ActionEvent event) {
     FileChooser fc = new FileChooser(); 
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
     File f = fc.showOpenDialog(null);
        if (f!=null){
          
          imagelink=f.getAbsolutePath();
          System.out.println(imagelink);
        }
     
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.services.ComplaintServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import com.booklab.models.*;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddComplaintController implements Initializable {

    @FXML
    private TextField tfTopic;

    
    @FXML
    private RadioButton orderId;

    @FXML
    private ToggleGroup complaintType;
  
    @FXML
    private RadioButton offerId;

    @FXML
    private TextArea tfComplaintDetails;
    @FXML
    private Button submitId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addComplaint(ActionEvent event) {
        ComplaintServices sp = new ComplaintServices();
        String complaintTypeText;
        if (orderId.isSelected() )
                complaintTypeText = orderId.getText();
        else complaintTypeText = offerId.getText();
        sp.add(new Complaint(tfTopic.getText(),complaintTypeText, tfComplaintDetails.getText()));
        JOptionPane.showMessageDialog(null, "Complaint ajoutée");
    }
}

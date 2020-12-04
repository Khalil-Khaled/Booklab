/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Offer;
import com.booklab.services.OfferServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class CreateOfferController implements Initializable {

    @FXML
    private TextField priceTF;
    @FXML
    private TextArea DescriptionTF;
    @FXML
    private RadioButton venteRadio;
    @FXML
    private RadioButton pretRadio;
    @FXML
    private Button createOfferBtn;
    @FXML
    private ToggleGroup ventePret;
    
    
    @FXML
    private AnchorPane createOfferAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        priceTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,2})?")) {
                    priceTF.setText(oldValue);
                }
            }
        });
    }   

    @FXML
    private void createOffer(ActionEvent event) {
        String radioText;
        if (venteRadio.isSelected() )
                radioText = venteRadio.getText();
        else radioText = pretRadio.getText();
        
        Offer offer = new Offer(radioText, Float.parseFloat(priceTF.getText()), DescriptionTF.getText() , 50);
        
        OfferServices os = new OfferServices();
        os.createOffer(offer);
        JOptionPane.showMessageDialog(null,"Offer successfully added !");
        createOfferAnchor.getChildren().clear();
    }
    
}

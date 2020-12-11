/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Item;
import com.booklab.services.ServicesBookAdmin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class CreateAccessoryAdminController implements Initializable {

    @FXML
    private TextField accessoryNameTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField quantityTF;
    @FXML
    private TextField ratingTF;
    @FXML
    private ImageView accessoryImage;

    public String imagelink;

    private ServicesBookAdmin sba = new ServicesBookAdmin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        if (f != null) {

            imagelink = f.getAbsolutePath();
            File file = new File(imagelink);
            Image image1 = new Image(file.toURI().toString());
            accessoryImage.setImage(image1);
        }
    }

    @FXML
    private void createAccessory(ActionEvent event) throws IOException {
        Item item = new Item(accessoryNameTF.getText(), "", descriptionTF.getText(), Float.parseFloat(priceTF.getText()), imagelink, Integer.valueOf(quantityTF.getText()), Integer.valueOf(ratingTF.getText()), new Date(0, 0, 0), "", 0, "accessory");
        ArrayList<Item> items = sba.selectItems();
        for (Item item1 : items) {
            if (item1.equals(item)) {
                JOptionPane.showMessageDialog(null, "Item already exists !");
                return;
            }
        }
        sba.insertItem(item);

        JOptionPane.showMessageDialog(null, "Accessory Successfully added !");

        accessoryNameTF.setText("");

        descriptionTF.setText("");

        priceTF.setText("");

        quantityTF.setText("");

        ratingTF.setText("");

        accessoryImage.setImage(null);

    }

}

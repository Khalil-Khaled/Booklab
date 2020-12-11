/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Item;
import com.booklab.services.ServicesBookAdmin;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class CreateBookViewController implements Initializable {

    @FXML
    private TextField bookNameTF;
    @FXML
    private TextField categoryTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField quantityTF;
    @FXML
    private TextField ratingTF;
    @FXML
    private TextField authorTF;
    @FXML
    private TextField pageNumberTF;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView image;

    public String imagelink;

    private ServicesBookAdmin sba = new ServicesBookAdmin();

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

        quantityTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityTF.setText(oldValue);
                }
            }
        });

        ratingTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,1}?")) {
                    ratingTF.setText(oldValue);
                }
            }
        });

        pageNumberTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    pageNumberTF.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void createBook(ActionEvent event) throws ParseException {

        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        java.util.Date publishDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date date1 = new java.sql.Date(publishDate.getTime());

        Item item = new Item(bookNameTF.getText(), categoryTF.getText(), descriptionTF.getText(), Float.parseFloat(priceTF.getText()), imagelink, Integer.valueOf(quantityTF.getText()), Integer.valueOf(ratingTF.getText()), date1, authorTF.getText(), Integer.valueOf(pageNumberTF.getText()), "book");
        ArrayList<Item> items = sba.selectItems();
        for (Item item1 : items) {
            if (item1.equals(item)) {
                JOptionPane.showMessageDialog(null, "Item already exists !");
                return;
            }
        }
        sba.insertItem(item);
        JOptionPane.showMessageDialog(null, "Book Successfully added !");

        bookNameTF.setText("");

        categoryTF.setText("");

        descriptionTF.setText("");

        priceTF.setText("");

        quantityTF.setText("");

        ratingTF.setText("");

        authorTF.setText("");

        pageNumberTF.setText("");

        datePicker.setValue(null);

        image.setImage(null);

    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);
        if (f != null) {

            imagelink = f.getAbsolutePath();
            File file = new File(imagelink);
            Image image1 = new Image(file.toURI().toString());
            image.setImage(image1);
        }

    }
}

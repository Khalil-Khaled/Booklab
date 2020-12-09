/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Offer;
import com.booklab.services.OfferServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class OffersViewController implements Initializable {

    @FXML
    private TableView<Offer> offersTable;
    @FXML
    private TableColumn<Offer, Integer> idOffer;
    @FXML
    private TableColumn<Offer, String> typeOffer;
    @FXML
    private TableColumn<Offer, Float> priceOffer;
    @FXML
    private TableColumn<Offer, String> descriptionOffer;
    @FXML
    private TableColumn<Offer, String> statusOffer;
    @FXML
    private Button createOfferBtn;
    @FXML
    private AnchorPane holderPane;

    static AnchorPane offersView, createOffer, pay;
    @FXML
    private Button payBtn;
    
    private static String sellerId="";

    public String getSellerId() {
        return sellerId;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Load all fxmls in a cache
        try {

            createOffer = FXMLLoader.load(getClass().getResource("createOffer.fxml"));
            pay = FXMLLoader.load(getClass().getResource("payment.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(OffersViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Offer> offers = new ArrayList<>();
        OfferServices os = new OfferServices();
        offers = os.getOffers();

//        ObservableList<Offer> data = FXCollections.<Offer>observableArrayList(offers);
//        data.addAll(offers);
        //update table to allow modification 
//        idOffer.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("IdOffer"));
        typeOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("typeOffer"));
        priceOffer.setCellValueFactory(new PropertyValueFactory<Offer, Float>("priceOffer"));
        descriptionOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("descriptionOffer"));
        statusOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("OfferStatus"));
        statusOffer.setCellFactory(ComboBoxTableCell.forTableColumn("En cours", "Termine"));
//        idUser.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("idUser"));

        offersTable.getItems().addAll(offers);
        
        

    }

    @FXML
    private void createOfferView(ActionEvent event) {
        setNode(createOffer);
    }

    private void setNode(Node node) {

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        node.setVisible(true);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }

    @FXML
    private void refreshOffers(ActionEvent event) {
        update();
    }

    @FXML
    private void removeOffer(ActionEvent event) {
        Offer offerSelected = offersTable.getSelectionModel().getSelectedItem();
        if (offerSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            OfferServices os = new OfferServices();
            os.removeOffer(offerSelected);
            update();
        }
    }

    @FXML
    public void updateOfferStatus(TableColumn.CellEditEvent edittedCell) {
        Offer offerSelected = offersTable.getSelectionModel().getSelectedItem();
        offerSelected.setOfferStatus(edittedCell.getNewValue().toString());
        OfferServices os = new OfferServices();
        os.updateOffers(offerSelected);
    }

    private void update() {
        ArrayList<Offer> offers = new ArrayList<>();
        OfferServices os = new OfferServices();
        offers = os.getOffers();
        ObservableList<Offer> data = FXCollections.<Offer>observableArrayList(offers);
        offersTable.setItems(data);
    }

    @FXML
    public void payView(ActionEvent event) {
        Offer eventSelected = offersTable.getSelectionModel().getSelectedItem();
        if (eventSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row !");
        } else {
            Label amountLabel = (Label) pay.lookup("#amountLabel");
            amountLabel.setText(Float.toString(eventSelected.getPriceOffer()));
            
            Label descriptionLabel = (Label) pay.lookup("#offerDetail");
            descriptionLabel.setText(eventSelected.getDescriptionOffer());
            
            OfferServices os = new OfferServices();
            sellerId = os.getStripeUserID(eventSelected);
            System.out.println(sellerId);
            setNode(pay);
        }
        

    }

}

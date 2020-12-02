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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class OffersViewController implements Initializable {

    @FXML
    private TableView offersTable;
    @FXML
    private TableColumn<Offer,Integer> idOffer;
    @FXML
    private TableColumn<Offer, String> typeOffer;
    @FXML
    private TableColumn<Offer, Float> priceOffer;
    @FXML
    private TableColumn<Offer, String> descriptionOffer;
    @FXML
    private TableColumn<Offer, Boolean> statusOffer;
    @FXML
    private TableColumn<Offer, Integer> idUser;
    @FXML
    private Button createOfferBtn;
    @FXML
    private AnchorPane holderPane;
    
    static AnchorPane offersView, createOffer;
    @FXML
    private Button refreshBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Load all fxmls in a cache
        try {
             
             createOffer = FXMLLoader.load(getClass().getResource("createOffer.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(OffersViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Offer> offers = new ArrayList<>();
        OfferServices os = new OfferServices();
        offers = os.getOffers();

        ObservableList<Offer> data = FXCollections.<Offer>observableArrayList(offers);
//        data.addAll(offers);

        
        
        
        idOffer.setCellValueFactory(new PropertyValueFactory<Offer,Integer>("idOffer"));
        typeOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("typeOffer"));
        priceOffer.setCellValueFactory(new PropertyValueFactory<Offer, Float>("priceOffer"));
        descriptionOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("descriptionOffer"));
        statusOffer.setCellValueFactory(new PropertyValueFactory<Offer, Boolean>("statusOffer"));
        idUser.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("idUser"));
        
        offersTable.setItems(data);

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
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();}

    @FXML
    private void refreshOffers(ActionEvent event) {
        ArrayList<Offer> offers = new ArrayList<>();
        OfferServices os = new OfferServices();
        offers = os.getOffers();
        ObservableList<Offer> data = FXCollections.<Offer>observableArrayList(offers);
        offersTable.setItems(data);
    }


}

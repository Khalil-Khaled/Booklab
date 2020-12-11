/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import com.booklab.tests.logintest;
import static com.booklab.views.UserloginController.idlogin;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnPricing;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
     @FXML
    private JFXButton btncarts;
    @FXML
    private ImageView imageView;
     Stage dialogStage = new Stage();
    Scene scene;
    AnchorPane contacts,alerts,pricing,profiles,complaints,controls,offers,cart,wishlist,events,items;
    @FXML
    private JFXButton btnControls;
    @FXML
    private Label user1;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
          CustomerServices s=new CustomerServices();
          Customer c=s.showcustomer(UserloginController.idlogin);
           File file = new File(c.getProfilimage());
           
           Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            user1.setText("Hello "+c.getUserName());
            
            
           
            //Load all fxmls in a cache
//        CustomerServices s=new CustomerServices();
//        Customer c=s.showcustomer(UserloginController.idlogin);
//         File file = new File(c.getProfilimage());
//        Image image = new Image(file.toURI().toString());
//        imageuser.setImage(image);
//        user.setText("User : "+c.getUserName());
//image and user in the dashboard
        try {
            
            
             
             
             
             profiles = FXMLLoader.load(getClass().getResource("Profiles.fxml"));
             complaints = FXMLLoader.load(getClass().getResource("Complaints.fxml"));
             offers = FXMLLoader.load(getClass().getResource("offersView.fxml"));
             cart= FXMLLoader.load(getClass().getResource("ShoppingCart.fxml"));
             wishlist= FXMLLoader.load(getClass().getResource("Wishlist.fxml"));
             events=FXMLLoader.load(getClass().getResource("ShowEvent.fxml"));
             items= FXMLLoader.load(getClass().getResource("ItemsView.fxml"));
            setNode(items);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
           
    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void switchPricing(ActionEvent event) {
        setNode(offers);
    }

    @FXML
    private void switchevents(ActionEvent event) {
        setNode(events);
    }
     @FXML
    private void logout(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("userlogin.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
                
                scene.setOnMousePressed(new EventHandler<MouseEvent>() {
   
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
                });
                scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                dialogStage.setX(event.getScreenX() - xOffset);
                dialogStage.setY(event.getScreenY() - yOffset);
                } });
                idlogin=0;
    }

    
    @FXML
    private void switchWidget(ActionEvent event) {
        setNode(complaints);
    }

    @FXML
    private void switchProfile(ActionEvent event) {
        setNode(profiles);
    }

    private void switchAlert(ActionEvent event) {
        setNode(alerts);
    }

    private void switchControls(ActionEvent event) {
        setNode(controls);
    }
    @FXML
     private void switchcarts(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShoppingCart.fxml"));
            Parent parent = loader.load();

            ShoppingCartController c = loader.getController();
            c.loadItems();
        setNode(cart);
    }

        @FXML
     private void switchwishlist(ActionEvent event) {
        setNode(wishlist);
    }

    @FXML
    private void switchItems(ActionEvent event) {
        setNode(items);
        
    }
}

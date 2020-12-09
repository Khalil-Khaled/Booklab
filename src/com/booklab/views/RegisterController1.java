/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import static com.booklab.views.ProfilesController.infoBox;
import static com.booklab.views.UserloginController.idlogin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author radhwene
 */
public class RegisterController1 implements Initializable {
    
    @FXML
    private Label btn_exit;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    
    @FXML
    private TextField username;

    @FXML
    private TextField email;
    @FXML
    private TextField questionverif;
    @FXML
    private TextField answerverif;
    @FXML 
    private Button image;
     @FXML
    private ImageView imageView;
    @FXML
    private Label labSingleFile;
    private String imagelink;
   private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    @FXML
    private PasswordField confirmationpassword;
    Stage dialogStage = new Stage();
    Scene scene;
   
    
    public String getImagelink() {
        return imagelink;
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
      System.exit(0);
    }
    @FXML
    private void returns(MouseEvent event) throws IOException {
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
                    }
        });
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
     private void image(ActionEvent event) {
     FileChooser fc = new FileChooser(); 
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
     File f = fc.showOpenDialog(null);
        if (f!=null){
          imagelink=f.getAbsolutePath();
            File file = new File(imagelink);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }else
          imagelink=".";
        
    }
     
        private boolean validateEmaill(){
        CustomerServices s = new CustomerServices();
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (s.showemaildispo(email.getText())){
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("this Email exist ");
                alert.showAndWait();
            
            return false;    
            } 
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Email");
                alert.showAndWait();
            
            return false;            
        }
    }
        private boolean validateusername(){
        CustomerServices s = new CustomerServices();
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(username.getText());
        if (s.showeuserdispo(username.getText())){
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate username");
                alert.setHeaderText(null);
                alert.setContentText("this username exist ");
                alert.showAndWait();
            
            return false;    
            } 
        if(m.find() && m.group().equals(username.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate username");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter valide username only number and and password");
                alert.showAndWait();
            
            return false;            
        }
    }
         private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(password.getText());
         if(m.matches()){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
                alert.showAndWait();
            
            return false;            
        }
    }
         private boolean validateLastName(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(lastname.getText());
        if(m.find() && m.group().equals(lastname.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Last Name");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Last Name only alphabet");
                alert.showAndWait();
            
            return false;            
        }
    }
        private boolean validateFirstName(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(firstname.getText());
        if(m.find() && m.group().equals(firstname.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate first Name");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid First Name only alphabet");
                alert.showAndWait();
            
            return false;            
        }
    }
        private boolean validatepasswordverification(){
        if(password.getText().equals(confirmationpassword.getText())){
           return true; 
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the same password");
                alert.showAndWait();
            
            return false;            
        }
    }
     
     
     @FXML
     private void adduser(ActionEvent event) throws IOException, Exception {
         CustomerServices s = new CustomerServices();
       {
         String passwordcrypt=BCrypt.hashpw(password.getText(),BCrypt.gensalt());
         JavaMailUtil.sendMail(email.getText());
         TextInputDialog dialog = new TextInputDialog("Verification");
         dialog.setTitle("VERIFICATION");
         dialog.setHeaderText("Look, a Text Input Dialog");
         dialog.setContentText("Please enter the code of verification sent in your mail:");
         
         // Traditional way to get the response value.
         Optional<String> result = dialog.showAndWait();
         if (result.get().equals(JavaMailUtil.code)){
            
            

         s.create(new Customer(username.getText(),firstname.getText(),lastname.getText(),email.getText(),passwordcrypt,questionverif.getText(),answerverif.getText(),this.getImagelink(),1,2,3));
        JOptionPane.showMessageDialog(null,"customer added");
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
         
         
    }
    } 
    }
     }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.services.CustomerServices;
import static com.booklab.tests.logintest.stage;
import static com.booklab.views.UserloginController.idlogin;
import static com.booklab.views.UserloginController.infoBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class ProfilesController implements Initializable {
    private String imagelink;
    @FXML
    private PasswordField oldpassword;
    @FXML
    private PasswordField newpassword;
    @FXML
    private PasswordField confirmationpassword;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               CustomerServices s=new CustomerServices();
               Customer c=s.showcustomer(UserloginController.idlogin);
                    firstname.setText(c.getFirstName());
                    lastname.setText(c.getLastName());
                    username.setText(c.getUserName());
                    email.setText(c.getEmail());
                    questionverif.setText(c.getQuestionVerif());
                    answerverif.setText(c.getAnswerVerif());
                    
                    
                    
                        File file = new File(c.getProfilimage());
                       Image image = new Image(file.toURI().toString());
                       imageView.setImage(image);
//                                    //creating the image object
//                         InputStream stream;
//                        try {
//                            stream = new FileInputStream(c.getProfilimage());
//                        } catch (FileNotFoundException ex) {
//                            Logger.getLogger(ProfilesController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                         Image image = new Image(stream);
//                         //Creating the image view
//                         ImageView imageView = new ImageView();
//                         //Setting image to the image view
//                         imageView.setImage(image);
//                         //Setting the image view parameters
//                         imageView.setX(10);
//                         imageView.setY(10);
//                         imageView.setFitWidth(575);
//                         imageView.setPreserveRatio(true);
//                         //Setting the Scene object
//                         Group root = new Group(imageView);
//                         Scene scene = new Scene(root, 595, 370);
//                         stage.setTitle("Displaying Image");
//                         stage.setScene(scene);
//                         stage.show();
        
        
        
        
        
    }    
//    @FXML
//    private void updatecustomer(ActionEvent event ) {
//        CustomerServices s=new CustomerServices();
//        Customer c = new Customer();
//        s.modify(c);
//    }
     private boolean validateEmaill(){
        CustomerServices s = new CustomerServices();
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (s.showemaildispo(email.getText())){
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("this Email exist ");
                alert.showAndWait();
            
            return false;    
            } 
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
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
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate username");
                alert.setHeaderText(null);
                alert.setContentText("this username exist ");
                alert.showAndWait();
            
            return false;    
            } 
        if(m.find() && m.group().equals(username.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate username");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter valide username only number and and password");
                alert.showAndWait();
            
            return false;            
        }
    }
         private boolean validatePassword(){
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher m = p.matcher(newpassword.getText());
         if(m.matches()){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate first Name");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid First Name only alphabet");
                alert.showAndWait();
            
            return false;            
        }
    }
        private boolean validatepasswordverification(){
        if(newpassword.getText().equals(confirmationpassword.getText())){
           return true; 
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the same password");
                alert.showAndWait();
            
            return false;            
        }
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
            
        }
        
    }
        @FXML
        private void updated(ActionEvent event){
            CustomerServices s = new CustomerServices();
             
                s.modify(new Customer(username.getText(),firstname.getText(),lastname.getText(),email.getText(),"123456",questionverif.getText(),answerverif.getText(),"1235",1,2,3),idlogin);
                JOptionPane.showMessageDialog(null,"updated");
             
        
        }
            public static void infoBox(String infoMessage,String title,String herdertext){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(infoMessage);
                alert.setTitle(title);
                alert.setHeaderText(herdertext);
                alert.showAndWait();

               }
         @FXML
        private void updatedpassword1(ActionEvent event){
            CustomerServices s = new CustomerServices();
            if(!BCrypt.checkpw(oldpassword.getText(),s.showcustomer(idlogin).getPassword()))
                infoBox("Please enter the old password",null,"FAILED");
            else if(!newpassword.getText().equals(confirmationpassword.getText()))
                infoBox("Please enter the same password ",null,"FAILED");
            else {
                String hashed = BCrypt.hashpw(newpassword.getText(), BCrypt.gensalt());
                s.updatepass(hashed,idlogin);
                JOptionPane.showMessageDialog(null,"password changed");
            }
        }
          @FXML
     private void image1(ActionEvent event) {
     CustomerServices s = new CustomerServices();
     FileChooser fc = new FileChooser(); 
     fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
     File f = fc.showOpenDialog(null);
              System.out.println(imagelink=f.getAbsolutePath());
        if (f!=null){
          imagelink=f.getAbsolutePath();
            File file = new File(imagelink);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
          s.updateimage(imagelink,idlogin);
          
        }
        
    }
}

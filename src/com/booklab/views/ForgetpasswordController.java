/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.services.CustomerServices;
import static com.booklab.views.ProfilesController.infoBox;
import static com.booklab.views.UserloginController.idlogin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author radhw
 */
public class ForgetpasswordController implements Initializable {

    @FXML
    private PasswordField newpassword;
    @FXML
    private PasswordField confirmationpassword;
     Stage dialogStage = new Stage();
    Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

           @FXML
        private void updatedpassword1(ActionEvent event){
            CustomerServices s = new CustomerServices();
            if(!newpassword.getText().equals(confirmationpassword.getText()))
                infoBox("Please enter the same password ",null,"FAILED");
            else {
                String hashed = BCrypt.hashpw(newpassword.getText(), BCrypt.gensalt());
                s.updatepassemail(hashed,UserloginController.mail);
                JOptionPane.showMessageDialog(null,"password changed");
            }
        }
     @FXML
    private void close_app(MouseEvent event) {
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
}

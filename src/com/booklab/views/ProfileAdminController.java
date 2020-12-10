/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Admin;
import com.booklab.services.AdminServices;
import com.booklab.services.CustomerServices;
import static com.booklab.tests.logintest.stage;
import static com.booklab.views.ProfilesController.infoBox;
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
 * @author Naoures Hidri
 */
public class ProfileAdminController implements Initializable {

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
    private TextField adminname;

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


    public void initialize(URL url, ResourceBundle rb) {

        AdminServices s = new AdminServices();
        Admin a = s.showadmin(UserloginController.idlogin);
        firstname.setText(a.getFirstName());
        lastname.setText(a.getLastName());
        adminname.setText(a.getUserName());
        email.setText(a.getEmail());
        questionverif.setText(a.getQuestionVerif());
        answerverif.setText(a.getAnswerVerif());

        File file = new File(a.getProfilimage());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @FXML
    private void image(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            imagelink = f.getAbsolutePath();
            System.out.println(imagelink);
        }

    }

    @FXML
    private void updated(ActionEvent event) {
        AdminServices s = new AdminServices();

        s.modify(new Admin(adminname.getText(), firstname.getText(), lastname.getText(), email.getText(), "123456", questionverif.getText(), answerverif.getText(), "1235"), idlogin);
        JOptionPane.showMessageDialog(null, "updated");

    }

    public static void infoBox(String infoMessage, String title, String herdertext) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(herdertext);
        alert.showAndWait();

    }

    @FXML
    private void updatedpassword1(ActionEvent event) {
        AdminServices s = new AdminServices();
        if (BCrypt.checkpw(oldpassword.getText(), s.showadmin(idlogin).getPassword())) {
            if (!newpassword.getText().equals(confirmationpassword.getText())) {
                infoBox("Please enter the same password ", null, "FAILED");
            } else {
                String hashed = BCrypt.hashpw(newpassword.getText(), BCrypt.gensalt());
                s.updatepass(hashed, idlogin);
                JOptionPane.showMessageDialog(null, "password changed");
            }
        } else {
            infoBox("Please enter the old password", null, "FAILED");
        }
    }

    @FXML
    private void image1(ActionEvent event) {
        CustomerServices s = new CustomerServices();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File f = fc.showOpenDialog(null);
        System.out.println(imagelink = f.getAbsolutePath());
        if (f != null) {
            imagelink = f.getAbsolutePath();
            s.updateimage(imagelink, idlogin);

        }

    }

}

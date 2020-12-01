/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author radhw
 */
public class NewFXMain extends Application {
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws IOException {
     // FXMLLoader loader=new FXMLLoader(getClass().getResource("../views/FXML.fxml")); 
//      Parent root = FXMLLoader.load(getClass().getResource("../views/userlogin.fxml"));
//      Scene scene = new Scene(root);
//    
//        primaryStage.setTitle("userlogin");
//        primaryStage.setScene(scene);
//        primaryStage.show();       
        Parent root = FXMLLoader.load(getClass().getResource("../views/userlogin.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
       

    }

    

    public static void main(String[] args) {
        launch(args);
    }
    
}

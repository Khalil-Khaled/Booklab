/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.Utils;

/**
 *
 * @author radhw
 */
import static com.booklab.views.UserloginController.infoBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class DataSource {
 private static DataSource instance; 	
 private Connection cnx;
 private final String URL="jdbc:mysql://localhost:3306/booklab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 private final String USERNAME="root";
 private final String PASSWORD="";
 
   public static void infoBox(String infoMessage,String title,String herdertext){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(infoMessage);
                alert.setTitle(title);
                alert.setHeaderText(herdertext);
                alert.showAndWait();

               }
 private DataSource() {
	  
        try {
	cnx = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	System.out.println("Connected to Database");
	 }catch(SQLException ex) {
		 System.out.println(ex.getMessage());
                infoBox("bad connexion database",null,"FAILED");
                System.exit(0);
	 }
}
 public static DataSource getInstance() {//only one instance 
	 if (instance==null)
		 instance=new DataSource();
	return instance;
 }
public Connection getCnx() {
	return cnx;
}

}
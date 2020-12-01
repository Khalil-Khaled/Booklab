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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
 private static DataSource instance; 	
 private Connection cnx;
 private final String  URL="jdbc:mysql://localhost:3306/booklab3";
 private final String USERNAME="root";
 private final String PASSWORD="";
 

 private DataSource() {
	 try {
	cnx = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	System.out.println("connected to BD");
	 }catch(SQLException ex) {
		 System.out.println(ex.getMessage());
	 }
}
 public static DataSource getInstance() {//only one instance 
	 if (instance ==null)
		 instance=new DataSource();
	return instance;
 }
public Connection getCnx() {
	return cnx;
}

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Complaint;
import com.booklab.services.ComplaintServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class AdminComplaintsController implements Initializable {

 
    @FXML
    private AnchorPane compAnchor;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private TableView<Complaint> tab_admin;
    @FXML
    private TableColumn<Complaint, String> col_topic;
    @FXML
    private TableColumn<Complaint, String> col_details;
    @FXML
    private TableColumn<Complaint, String> col_customer;
    @FXML
    private TableColumn<Complaint, String> col_type;
    @FXML
    private TableColumn<Complaint, String> col_status;
    @FXML
    private Button btn_respond;
    @FXML
    private Label lb_complaints;

    @FXML
    private Button btn_remove;

    static AnchorPane AdminComplaints, AdminRespondToComplaint;
    /**
     * Initializes the controller class.
     */
    ComplaintServices complaintServices = new ComplaintServices();
    private ObservableList<Complaint> oList = FXCollections.observableArrayList(complaintServices.showAll());
    private int index = -1;
     //Complaint oc = tab_admin.getSelectionModel().getSelectedItem();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            AdminRespondToComplaint = FXMLLoader.load(getClass().getResource("AdminRespondToComplaint.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(AdminComplaintsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Complaint> complaints = new ArrayList<>();
        ComplaintServices cs = new ComplaintServices();

        //set up the columns in the able
        col_topic.setCellValueFactory(new PropertyValueFactory<Complaint, String>("topic"));
        col_details.setCellValueFactory(new PropertyValueFactory<Complaint, String>("message"));
        col_customer.setCellValueFactory(new PropertyValueFactory<Complaint, String>("customer"));
        col_type.setCellValueFactory(new PropertyValueFactory<Complaint, String>("type"));
        col_status.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
        //load the data
        tab_admin.setItems(oList);
        //update table to allow modification 
        tab_admin.setEditable(true);
        col_topic.setCellFactory(TextFieldTableCell.forTableColumn());
        col_details.setCellFactory(TextFieldTableCell.forTableColumn());
        col_customer.setCellFactory(TextFieldTableCell.forTableColumn());
        col_type.setCellFactory(TextFieldTableCell.forTableColumn());
        col_status.setCellFactory(TextFieldTableCell.forTableColumn());
        tab_admin.getItems().addAll(complaints);
    }
    @FXML
    private void respond(ActionEvent event) {
        Complaint o = tab_admin.getSelectionModel().getSelectedItem();
      //  int id=  o.getComplaintId();
          if(o != null ){
                try {
                sendData(o);
            } catch (IOException ex) {
                Logger.getLogger(AdminComplaintsController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          
        setNode(AdminRespondToComplaint);
          

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
    
 

    public void showAllComplaints() {
        index = tab_admin.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        col_topic.setText(col_topic.getCellData(index).toString());
        col_details.setText(col_details.getCellData(index).toString());
        col_type.setText(col_type.getCellData(index).toString());
        col_status.setText(col_type.getCellData(index).toString());
        tab_admin.setItems(oList);

    }

    @FXML
    private void removeComplaint(ActionEvent event) {
        Complaint complaintSelected = tab_admin.getSelectionModel().getSelectedItem();
        if (complaintSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            complaintServices.delete(complaintSelected);
            oList.remove(complaintSelected);
            complaintServices.showAll();
        }
    }
private  void setSelectedItem(MouseEvent e) {
        Complaint o = tab_admin.getSelectionModel().getSelectedItem();
            if (e.getClickCount() == 2 && !(o==null) ) { 
            try {
                sendData(o);
            } catch (IOException ex) {
                Logger.getLogger(AdminComplaintsController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            }
    }

    public void sendData(Complaint o) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminRespondToComplaint.fxml"));
        Parent parent = loader.load();

        AdminRespondToComplaintController c = loader.getController();

        c.setComplaint(o);
       
    }

}

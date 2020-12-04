/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Complaint;
import com.booklab.services.ComplaintServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ComplaintsViewController implements Initializable {

    @FXML
    private TableView<Complaint> table_complaints;
    @FXML
    private TableColumn<Complaint, String> col_topic;
    @FXML
    private TableColumn<Complaint, String> col_details;
    @FXML
    private TableColumn<Complaint, String> col_type;

    /**
     * Initializes the controller class.
     */
    ComplaintServices complaintServices = new ComplaintServices();
    ObservableList<Complaint> oList = FXCollections.observableArrayList(complaintServices.showAll());
    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the able
        col_topic.setCellValueFactory(new PropertyValueFactory<Complaint, String>("topic"));
        col_details.setCellValueFactory(new PropertyValueFactory<Complaint, String>("message"));
        col_type.setCellValueFactory(new PropertyValueFactory<Complaint, String>("type"));
        //load the data
        table_complaints.setItems(oList);
       //update table to allow modification 
       table_complaints.setEditable(true);
       col_topic.setCellFactory(TextFieldTableCell.forTableColumn());
       col_details.setCellFactory(TextFieldTableCell.forTableColumn());
       col_type.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void showAllComplaints() {

        index = table_complaints.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        col_topic.setText(col_topic.getCellData(index).toString());
        col_details.setText(col_details.getCellData(index).toString());
        col_type.setText(col_type.getCellData(index).toString());
        table_complaints.setItems(oList);
    }

    public void updateComplaintTopic(CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaints.getSelectionModel().getSelectedItem();
        complaintSelected.setTopic(edittedCell.getNewValue().toString());
      complaintSelected.setType(edittedCell.getNewValue().toString());
       complaintSelected.setMessage(edittedCell.getNewValue().toString());
        complaintServices.modify(complaintSelected);
    }
    
    public void updateComplaintDetails(CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaints.getSelectionModel().getSelectedItem();
        complaintSelected.setMessage(edittedCell.getNewValue().toString());
     
        complaintServices.modify(complaintSelected);
    }
    
    public void updateComplaintType(CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaints.getSelectionModel().getSelectedItem();
        complaintSelected.setType(edittedCell.getNewValue().toString());
     
        complaintServices.modify(complaintSelected);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Complaint;
import com.booklab.models.Customer;

import com.booklab.services.ComplaintServices;
import com.booklab.services.CustomerServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ComplaintsController implements Initializable {

    @FXML
    private TableView<Complaint> table_complaint;
    @FXML
    private TableColumn<Complaint, String> col_topic;
    @FXML
    private TableColumn<Complaint, String> col_details;
    @FXML
    private TableColumn<Complaint, String> col_type;
    @FXML
    private Button btnRemove;
    @FXML
    private RadioButton offer;

    @FXML
    private ToggleGroup typeGroup;

    @FXML
    private Button btnAdd;
    @FXML
    private RadioButton order;

    @FXML
    private TextField tfTopic;

    @FXML
    private TextArea tfDetails;

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
        table_complaint.setItems(oList);
        //update table to allow modification 
        table_complaint.setEditable(true);
        col_topic.setCellFactory(TextFieldTableCell.forTableColumn());
        col_details.setCellFactory(TextFieldTableCell.forTableColumn());
        col_type.setCellFactory(TextFieldTableCell.forTableColumn());
    }
  @FXML
    private void addComplaint(ActionEvent event) {
        String complaintTypeText;
        if (order.isSelected() )
                complaintTypeText = order.getText();
        else complaintTypeText = offer.getText();
        complaintServices.add(new Complaint(tfTopic.getText(),complaintTypeText, tfDetails.getText()));
        JOptionPane.showMessageDialog(null, "Complaint Added");
          oList.add(new Complaint(tfTopic.getText(),complaintTypeText, tfDetails.getText()));
          complaintServices.showAll();
            tfTopic.clear();
        tfDetails.clear();
        order.getProperties().clear();
        offer.getProperties().clear();
        typeGroup.getProperties().clear();

    }
    public void showAllComplaints() {
       
        index = table_complaint.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        col_topic.setText(col_topic.getCellData(index).toString());
        col_details.setText(col_details.getCellData(index).toString());
        col_type.setText(col_type.getCellData(index).toString());
        table_complaint.setItems(oList);
        
        
    }

    public void updateComplaintTopic(TableColumn.CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaint.getSelectionModel().getSelectedItem();
        complaintSelected.setTopic(edittedCell.getNewValue().toString());
        complaintServices.modify(complaintSelected);
    }

    public void updateComplaintDetails(TableColumn.CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaint.getSelectionModel().getSelectedItem();
        complaintSelected.setMessage(edittedCell.getNewValue().toString());
        complaintServices.modify(complaintSelected);
    }

    public void updateComplaintType(TableColumn.CellEditEvent edittedCell) {
        Complaint complaintSelected = table_complaint.getSelectionModel().getSelectedItem();
        complaintSelected.setType(edittedCell.getNewValue().toString());

        complaintServices.modify(complaintSelected);
    }

    @FXML
    private void removeComplaint(ActionEvent event) {
        Complaint complaintSelected = table_complaint.getSelectionModel().getSelectedItem();
        if (complaintSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            complaintServices.delete(complaintSelected);
            oList.remove(complaintSelected);
            complaintServices.showAll();
        }
    }

}

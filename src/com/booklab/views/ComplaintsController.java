/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Complaint;
import com.booklab.services.ComplaintServices;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ComplaintsController implements Initializable {

    @FXML
    private TableView<Complaint> table_complaints;
    @FXML
    private TableColumn<Complaint, String> col_topic;
    @FXML
    private TableColumn<Complaint, String> col_details;
    @FXML
    private TableColumn<Complaint, String> col_type;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_delete;

    @FXML
    private TextField tfTopic;

    @FXML
    private RadioButton order;

    @FXML
    private ToggleGroup complaintType;

    @FXML
    private RadioButton offer;

    @FXML
    private TextArea tfComplaintDetails;
    @FXML
    private Button btn_add;
    /**
     * Initializes the controller class.
     */
    ComplaintServices complaintServices = new ComplaintServices();
    ObservableList<Complaint> oList = FXCollections.observableArrayList(complaintServices.showAll());
    int index = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_topic.setCellValueFactory(new PropertyValueFactory<Complaint, String>("topic"));
        col_details.setCellValueFactory(new PropertyValueFactory<Complaint, String>("message"));
        col_type.setCellValueFactory(new PropertyValueFactory<Complaint, String>("type"));
        table_complaints.setItems(oList);
    }

    @FXML
    private void addComplaint(ActionEvent event) {
        ComplaintServices sp = new ComplaintServices();
        String complaintTypeText;
        if (order.isSelected()) {
            complaintTypeText = order.getText();
        } else {
            complaintTypeText = order.getText();
        }
        sp.add(new Complaint(tfTopic.getText(), complaintTypeText, tfComplaintDetails.getText()));
        JOptionPane.showMessageDialog(null, "Complaint ajoutée");
    }

    @FXML
    void getSelected(ActionEvent event) {
        index = table_complaints.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfTopic.setText(col_topic.getCellData(index).toString());
        tfComplaintDetails.setText(col_details.getCellData(index).toString());
        complaintType.setUserData(col_type.getCellData(index));

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
 /*  public void deleteComplaint(ActionEvent event){
           ComplaintServices sp = new ComplaintServices();
             sp.delete(new Complaint(tfTopic.getText(), tfComplaintDetails.getText(),complaintType.getUserData().toString()));
    }
    /* public void updateComplants() {
        col_topic.setCellValueFactory(new PropertyValueFactory<Complaint, String>("topic"));
        col_details.setCellValueFactory(new PropertyValueFactory<Complaint, String>("message"));
        col_type.setCellValueFactory(new PropertyValueFactory<Complaint, String>("type"));

        table_complaints.setItems(oList);
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Customer;
import com.booklab.models.Event;
import com.booklab.services.EventServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author steam
 */
public class ListParticipantsController implements Initializable {

    @FXML
    private TableColumn<Event, String> col_name;
    @FXML
    private TableColumn<Event, String> col_email;
    @FXML
    private Button btnOK;
    @FXML
    private TableView<Customer> list_participant;

    public ObservableList<Customer> listC;
    EventServices es = new EventServices();
    private Event event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_name.setCellValueFactory(new PropertyValueFactory<Event, String>("FirstName"));
        col_email.setCellValueFactory(new PropertyValueFactory<Event, String>("Email"));
    }

    @FXML
    private void Close(ActionEvent event) {
        System.out.println(this.event.getIdEvent());
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
    }
    
    public void setEvent(Event event) throws IOException {
        this.event = event;
        list_participant.setItems(es.afficherEventParticipantss(event));
    }

}

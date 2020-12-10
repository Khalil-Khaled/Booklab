/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.Utils.DataSource;
import com.booklab.models.Event;
import com.booklab.services.EventServices;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author steam
 */
public class CreateEventController implements Initializable {

    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_location;
    @FXML
    private TextField txt_name;
    @FXML
    private TableView<Event> table_events;
    @FXML
    private TableColumn<Event, String> col_organizer;
    @FXML
    private TableColumn<Event, String> col_name;
    @FXML
    private TableColumn<Event, String> col_description;
    @FXML
    private TableColumn<Event, String> col_startdate;
    @FXML
    private TableColumn<Event, String> col_enddate;
    @FXML
    private TableColumn<Event, String> col_location;
    @FXML
    private TextField filterField;
    @FXML
    private DatePicker eventStartDate;
    @FXML
    private DatePicker eventEndDate;
    @FXML

    ObservableList<Event> listM;

    ObservableList<Event> dataList;
    ResultSet rs = null;
    PreparedStatement pst = null;

    Stage dialogStage = new Stage();
    Scene scene;

    int index = -1;

    EventServices es = new EventServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_organizer.setCellValueFactory(new PropertyValueFactory<Event, String>("OrganizerName"));
        col_name.setCellValueFactory(new PropertyValueFactory<Event, String>("EventName"));
        col_description.setCellValueFactory(new PropertyValueFactory<Event, String>("EventDesciption"));
        col_startdate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventStartDate"));
        col_enddate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventEndDate"));
        col_location.setCellValueFactory(new PropertyValueFactory<Event, String>("EventLocation"));

        UpdateTable();
        search_event();
        listM = es.afficherEvents();
        table_events.setItems(listM);
        System.out.println(listM);

        txt_description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\sa-zA-Z")) {
                    txt_description.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });

        txt_location.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\sa-zA-Z")) {
                    txt_location.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });

        col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_description.setCellFactory(TextFieldTableCell.forTableColumn());
        col_location.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    public void Add_events() throws ParseException {

        String date = eventStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        java.util.Date utilStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date date1 = new java.sql.Date(utilStartDate.getTime());

        String date2 = eventEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        java.util.Date utilStartDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        java.sql.Date date3 = new java.sql.Date(utilStartDate.getTime());

        Connection cnx = DataSource.getInstance().getCnx();
        String sql = "insert into event (eventname,eventdescription,eventstartdate,eventenddate,eventlocation,eventOrganizerID)values(?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_description.getText());
            pst.setDate(3, date1);
            pst.setDate(4, date3);
            pst.setString(5, txt_location.getText());
            pst.setInt(6, UserloginController.idlogin);
            pst.execute();
            JOptionPane.showMessageDialog(null, "event Add succes");
            UpdateTable();
            EventMailing.sendMail("hamza.benturkia@esprit.tn");
            search_event();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void Delete(ActionEvent event) {

        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        if (eventSelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            EventServices es = new EventServices();
            es.supprimerEvent(eventSelected);
            UpdateTable();
        }
    }

    @FXML
    private void DiscardEvent(ActionEvent event) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        Object[] options1 = {"Discard Event"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Are you sure you want to discard the event ?"));
        //JTextField textField = new JTextField(10);
        //panel.add(textField);

        int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options1, null);
        if (result == JOptionPane.YES_OPTION) {
            es.supprimerEvent(eventSelected);
            UpdateTable();
            try {
                DiscardEventMailing.sendMail("hamza.benturkia@esprit.tn");
            } catch (Exception ex) {
                Logger.getLogger(ShowEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void search_event() {

        col_organizer.setCellValueFactory(new PropertyValueFactory<Event, String>("OrganizerName"));
        col_name.setCellValueFactory(new PropertyValueFactory<Event, String>("EventName"));
        col_description.setCellValueFactory(new PropertyValueFactory<Event, String>("EventDesciption"));
        col_startdate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventStartDate"));
        col_enddate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventEndDate"));
        col_location.setCellValueFactory(new PropertyValueFactory<Event, String>("EventLocation"));

        dataList = es.afficherEvents();
        table_events.setItems(dataList);
        FilteredList<Event> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getEventName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getEventDesciption().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getEventLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Event> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_events.comparatorProperty());
        table_events.setItems(sortedData);

    }

    //toujours rafraichir la table
    private void UpdateTable() {
        listM = es.afficherEvents();
        table_events.setItems(listM);
    }

    //modification des attributs
    public void updateEventName(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventName(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }

    public void updateEventDescription(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventDesciption(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }

    public void updateEventLocation(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventLocation(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }
}

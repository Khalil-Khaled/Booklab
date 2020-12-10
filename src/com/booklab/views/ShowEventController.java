/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.Utils.DataSource;
import com.booklab.models.Customer;
import com.booklab.models.Event;
import com.booklab.services.EventServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author steam
 */
public class ShowEventController implements Initializable {

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

    ObservableList<Event> listM;
    ObservableList<Customer> listC;

    ObservableList<Event> dataList;
    ResultSet rs = null;
    PreparedStatement pst = null;

    Stage dialogStage = new Stage();
    Scene scene;

    int index = -1;

    EventServices es = new EventServices();
    @FXML
    private Button add;
    @FXML
    private Button btnDiscard;
    @FXML
    private Button btnParticipate;
    @FXML
    private Button btn_Participants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        search_event();
        UpdateTable();

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
    public void Add_events() throws ParseException, Exception {

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
            pst.executeUpdate();
            EventMailing.sendMail("hamza.benturkia@esprit.tn");
            }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            JOptionPane.showMessageDialog(null, "event Add succes");
            UpdateTable();
            search_event();
         
    }

    private void Participants(ActionEvent event) throws IOException {

        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListParticipants.fxml"));
        ListParticipantsController controller = loader.getController();
        System.out.println(eventSelected);
        controller.setEvent(eventSelected);
        search_event();
    }

    @FXML
    public void DiscardEvent(ActionEvent event) throws Exception {

        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        Object[] options1 = {"Remove Event"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Are you sure you want to Remove the event ?"));

        int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options1, null);
        if (result == JOptionPane.YES_OPTION) {
            es.organizerSupprimerEvent(eventSelected);
            UpdateTable();
            EventMailing.sendMailDiscard("hamza.benturkia@esprit.tn");
        }
        search_event();
    }

    @FXML
    private void ParticipatEvent(ActionEvent event) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        System.out.println(eventSelected);
        Connection cnx = DataSource.getInstance().getCnx();
        String sql = "INSERT INTO event_participant (eventId,participantID) values (?, ?)";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, eventSelected.getIdEvent());
            pst.setInt(2, UserloginController.idlogin);
            if (es.showparticipantexist(UserloginController.idlogin, eventSelected.getIdEvent())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("you are already a participant dude");
                alert.setHeaderText(null);
                alert.setContentText("you already participate in this event ");
                alert.showAndWait();
            } else {
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "You are now a participant");
                search_event();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        col_organizer.setCellValueFactory(new PropertyValueFactory<Event, String>("OrganizerName"));
        col_name.setCellValueFactory(new PropertyValueFactory<Event, String>("EventName"));
        col_description.setCellValueFactory(new PropertyValueFactory<Event, String>("EventDesciption"));
        col_startdate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventStartDate"));
        col_enddate.setCellValueFactory(new PropertyValueFactory<Event, String>("EventEndDate"));
        col_location.setCellValueFactory(new PropertyValueFactory<Event, String>("EventLocation"));
        listM = es.afficherEvents();
        table_events.setItems(listM);
        search_event();
    }

    //modification des attributs
    @FXML
    public void updateEventName(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventName(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }

    @FXML
    public void updateEventDescription(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventDesciption(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }

    @FXML
    public void updateEventLocation(TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        eventSelected.setEventLocation(edittedCell.getNewValue().toString());
        es.modifierEvent(eventSelected);
    }

    @FXML
    private void ShowParticipants(MouseEvent event) throws IOException {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        if (eventSelected != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListParticipants.fxml"));
            Parent parent = loader.load();
            ListParticipantsController c = loader.getController();
            
            c.setEvent(eventSelected);
            Scene scene = new Scene(parent);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.show();
        } else {
            System.out.println(eventSelected);
        }
        search_event();
    }

    
}

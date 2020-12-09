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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    @FXML

    ObservableList<Event> listM;
    ObservableList<Customer> listC;

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

        UpdateTable();
        search_event();

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

        addButtonToTable();
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
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "event Add succes");
            UpdateTable();
            EventMailing.sendMail("hamza.benturkia@esprit.tn");
            search_event();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    @FXML
//    private void Participants(ActionEvent event) {
//        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
//        //System.out.println(eventSelected);
//        if (eventSelected == null) {
//            JOptionPane.showMessageDialog(null, "Please Select an Event");
//        } else {
//        try {
//            Node node = (Node) event.getSource();
//            dialogStage = (Stage) node.getScene().getWindow();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListParticipantsController.fxml"));
//            scene = new Scene(FXMLLoader.load(getClass().getResource("ListParticipants.fxml")));
//            ListParticipantsController controller = loader.getController();
//            //controller.listC=es.afficherEventParticipants(eventSelected);
//            //System.out.println(listC);
//            //controller.UpdateTable(); 
//            dialogStage.setScene(scene);
//            dialogStage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ShowEventController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    } 
//    }

//    @FXML
//    private void Delete(ActionEvent event) {
//
//        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
//        if (eventSelected == null) {
//            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
//        } else {
//            EventServices es = new EventServices();
//            es.supprimerEvent(eventSelected);
//            UpdateTable();
//        }
//    }
    @FXML
    private void DiscardEvent(ActionEvent event) throws Exception {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        Object[] options1 = {"Remove Event"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Are you sure you want to Remove the event ?"));
        //JTextField textField = new JTextField(10);
        //panel.add(textField);

        int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options1, null);
        if (result == JOptionPane.YES_OPTION) {
            es.organizerSupprimerEvent(eventSelected);
            UpdateTable();
            EventMailing.sendMailDiscard("hamza.benturkia@esprit.tn");
        }
    }

    @FXML
    private void ParticipatEvent(ActionEvent event) {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        Connection cnx = DataSource.getInstance().getCnx();
        String sql = "INSERT INTO event_participant (eventId,participantID) values (?, ?)";
        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, eventSelected.getIdEvent());
            pst.setInt(2, UserloginController.idlogin);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "You are now a participant");
            search_event();
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

    private void addButtonToTable() {
        Event eventSelected = table_events.getSelectionModel().getSelectedItem();
        TableColumn<Event, Void> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory = new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
                final TableCell<Event, Void> cell = new TableCell<Event, Void>() {

                    private final Button btnParticipants = new Button("Show participants");

                    {
                        btnParticipants.setOnAction(
                                (ActionEvent event) -> {
                                    Node node = (Node) event.getSource();
                                    dialogStage = (Stage) node.getScene().getWindow();
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListParticipantsController.fxml"));
                                        scene = new Scene(FXMLLoader.load(getClass().getResource("ListParticipants.fxml")));
                                        //ListParticipantsController controller = loader.getController();
                                        //Object item = getTableRow().getItem().getClass();
                                        //System.out.println(item);
                                        //controller.UpdateTable(item);
                                    } catch (IOException ex) {
                                        Logger.getLogger(ShowEventController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    dialogStage.setScene(scene);
                                    dialogStage.show();
                                }
                        );
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(btnParticipants);
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        table_events.getColumns().add(colBtn);
    }

}

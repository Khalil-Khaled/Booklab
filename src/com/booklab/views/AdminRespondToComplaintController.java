/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Complaint;
import com.booklab.models.ComplaintResponse;
import com.booklab.services.ComplaintResponseServices;
import com.booklab.services.ComplaintServices;
import com.booklab.services.CustomerServices;
import static com.booklab.views.UserloginController.idlogin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminRespondToComplaintController implements Initializable {

    @FXML
    private AnchorPane respondPane;
    @FXML
    private TextArea tfResponse;
    @FXML
    private Button btn_send;

    /**
     * Initializes the controller class.
     */
    ComplaintResponseServices complaintResponseServices = new ComplaintResponseServices();
    ComplaintServices complaintServices = new ComplaintServices();
    private Complaint complaint;

    ObservableList<ComplaintResponse> oList = FXCollections.observableArrayList(complaintResponseServices.showAll());
    int index = -1;
    int sizeInitial = oList.size();
    
    public AdminRespondToComplaintController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        respondPane.setVisible(true);
       
    }

    @FXML
    public void send(ActionEvent event) {
        String complaintStatus = "";
        //ComplaintsController controller cr=new ComplaintsController();
        
        /*
           String complaintStatus="Completed ";
         ComplaintResponse cr =new ComplaintResponse(complaint.getComplaintId(), "mehdi", tfResponse.getText(), complaintStatus);
        complaintResponseServices.add(cr);
         */
        CustomerServices c=new CustomerServices();
//        String custInfo=c.showcustomer(idlogin).getUserName();

        ComplaintResponse complaintResponse = new ComplaintResponse(0, "", tfResponse.getText(), complaintStatus);
        complaintResponseServices.add(complaintResponse);
        JOptionPane.showMessageDialog(null, "Complaint Response Added");
        //oList.add(new ComplaintResponse(0,0,"",tfResponse.getText(), complaintStatus));
        complaintResponse.setComplaintStatus("Completed");
         JOptionPane.showMessageDialog(null, "Response sent to customer");
      //  setComplaint(complaint);
        respondPane.setVisible(false);
        tfResponse.setText("");
        // complaintServices.showAll().get(0).get
    }

    public void setComplaint(Complaint complaint) throws IOException {
        this.complaint = complaint;
    }

    public boolean isSent(Complaint complaint) {
        int sizeFinal = oList.size();
        boolean sent;
        if (sizeFinal > sizeInitial) {
            sent= true;
        } else {
            sent =false;

        }
        return sent;
    }
}

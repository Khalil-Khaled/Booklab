/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.views;

import com.booklab.models.Category;
import com.booklab.services.CategoryServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Naoures Hidri
 */
public class CategoryAdminController implements Initializable {
    
     @FXML
    private TableView<Category> categoryTable;
     @FXML
    private TableColumn<Category, String> categoryName;
     @FXML
    private Button btnRefresh;
     @FXML
    private Button btnRemove;
     @FXML
    private AnchorPane anchorCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Category> categorys = new ArrayList<>();
        CategoryServices c = new CategoryServices();
        categorys = c.show();
        categoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryName"));
        categoryTable.getItems().addAll(categorys);
        
    }  
    
    @FXML
    public void updateCategoryName(TableColumn.CellEditEvent edittedCell) {
        Category categorySelected = categoryTable.getSelectionModel().getSelectedItem();
        categorySelected.setCategoryName(edittedCell.getNewValue().toString());
        CategoryServices c= new CategoryServices();
        c.update(categorySelected);
    }
    private void update() {
      List<Category> categorys = new ArrayList<>();
        CategoryServices c = new CategoryServices();
        categorys = c.show();
        
        categoryTable.getItems().addAll(categorys);
    }
        @FXML
    private void refreshCategory(ActionEvent event) {
        update();
    }
    @FXML
    private void removeCategory(ActionEvent event) {
        Category categorySelected = categoryTable.getSelectionModel().getSelectedItem();
        if (categorySelected == null) {
            JOptionPane.showMessageDialog(null, "Select Row to Remove!");
        } else {
            CategoryServices ct = new CategoryServices();
            ct.delete(categorySelected);
            update();
        }
    
        
}
}
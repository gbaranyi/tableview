/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtotable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 *
 * @author BaranyiGabriella
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private Button newPbutton;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> namecolumn;
    @FXML
    private TableColumn<Person, String> phonecolumn;
    @FXML
    private TableColumn<Person, String> commentcolumn;
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button detailedButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phonecolumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        commentcolumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        
        table.setEditable(true);
        
        namecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        namecolumn.setOnEditCommit((CellEditEvent<Person, String> t) -> {
            ((Person) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
        });
        
        phonecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phonecolumn.setOnEditCommit( 
                new EventHandler<CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPhone(t.getNewValue());
                    }
                }
        );
        
        deleteButton.setOnAction((ActionEvent e) -> {
            Person selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
        });
        
        detailedButton.setOnAction((ActionEvent e) -> {
            Person selectedItem = table.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("PersonView.fxml"));
                Parent TableViewParent = loader.load();
                Scene PersonViewScene = new Scene(TableViewParent);
                
                //acces the controller and call a method
                PersonViewController controller =loader.getController();
                controller.initData(selectedItem);
                
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                window.setScene(PersonViewScene);
                window.show();
            }catch (IOException exc){
                System.out.println(exc.toString());
        }
        });
        
        ReadToTable.getPersons().forEach((p) -> {
            table.getItems().add(p);
        }); 
    }    

    @FXML
    private void newPerson(ActionEvent event) {
        try {
            Parent newPersonParent = FXMLLoader.load(getClass().getResource("newPDetails.fxml"));
            Scene newPersonScene = new Scene(newPersonParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newPersonScene);
            window.show();
        }catch (IOException e){
             System.out.println(e.toString());
        }
    }
   
}

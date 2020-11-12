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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BaranyiGabriella
 */
public class PersonViewController implements Initializable {
    
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label commentLabel;
    @FXML
    private Button backButton;

    private Person selectedPerson;
    
    public void initData(Person person){
        selectedPerson = person;
        nameLabel.setText(selectedPerson.getName());
        phoneLabel.setText(selectedPerson.getPhone());
        commentLabel.setText(selectedPerson.getComment());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void backToGUI(ActionEvent event) {
        try {          
            Parent newPersonParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene newPersonScene = new Scene(newPersonParent);
            Stage parentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            parentWindow.setScene(newPersonScene);
            parentWindow.show();
        }catch (IOException e){
             System.out.println(e.toString());
        }
    }
}

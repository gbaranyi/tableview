/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtotable;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author BaranyiGabriella
 */
public class NewPDetailsController implements Initializable {

    @FXML
    private TextField nameT;
    @FXML
    private TextField phoneT;
    @FXML
    private TextArea commentT;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        Person p = new Person (nameT.getText(),phoneT.getText(),commentT.getText());
        List<Person> people = new ArrayList<>();
        
        ReadToTable.getPersons().forEach((p1) -> {people.add(p1);}); 
        
        people.add(p);
        ReadToTable.setPersons(people);

        JSONArray list = new JSONArray();
        
        ReadToTable.getPersons().forEach((Person p1) -> {
            JSONObject objPerson=new JSONObject();
            JSONObject objectPeople = new JSONObject();
            objPerson.put("name", p1.getName());
            objPerson.put("phone", p1.getPhone());
            objPerson.put("comment", p1.getComment());
            objectPeople.put("person", objPerson);
            list.add(objectPeople);
        });
          
        
        try(FileWriter file = new FileWriter("people.json")){
            file.write(list.toJSONString());
            file.flush();
            
        }
        catch (IOException e){
            e.printStackTrace();
        }
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

    @FXML
    private void delete(ActionEvent event) {
        nameT.setText("");
        phoneT.setText("");
        commentT.setText("");
    }
    
}

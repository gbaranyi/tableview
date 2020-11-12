/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtotable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author BaranyiGabriella
 */
public class ReadToTable extends Application {
    private static List<Person> persons;

    public static List<Person> getPersons() {
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        ReadToTable.persons = persons;
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        persons = new ArrayList<>();
        
        JSONParser parser = new JSONParser();
        try(FileReader reader= new FileReader("people.json")){
            Object obj =parser.parse(reader);
            JSONArray list =(JSONArray)obj;
            //iterate over userlist array
            list.forEach(p ->parsePersonObj((JSONObject)p));
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (ParseException e) {e.printStackTrace();}
        catch (Exception e) {e.printStackTrace();}
     
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void parsePersonObj(JSONObject p){
         JSONObject userObj = (JSONObject)p.get("person");
         Person p1 = new Person();
         p1.setName((String) userObj.get("name"));
         p1.setPhone((String)userObj.get("phone"));
         p1.setComment((String)userObj.get("comment"));
         persons.add(p1);
        }
    
}

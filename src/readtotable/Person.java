/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readtotable;

import javafx.beans.property.SimpleStringProperty;


public class Person {
    private String name;
    private String phone;  
    private String comment;
    
    public Person(String name, String phone, String comment) {
        this.name = name;
        this.phone = phone;
        this.comment = comment;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
 @XmlRootElement(name = "UserInfo")
 @XmlAccessorType(XmlAccessType.FIELD)
public class UserInfo {
    
    private String FirstName,LastName, username, password;

    public UserInfo(String FirstName, String LastName,String Username, String Password){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.username = Username;
        this.password = Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
   
}
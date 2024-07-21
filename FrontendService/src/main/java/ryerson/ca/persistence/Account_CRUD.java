/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ryerson.ca.helper.UserInfo;

public class Account_CRUD {

    private static Connection getCon() throws ClassNotFoundException, SQLException{
    Connection con=null;

         //String connection="localhost:3306";
         String connection = System.getenv("DB_URL");
         System.out.println(connection);
         
         Class.forName("com.mysql.cj.jdbc.Driver");
       
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/ACCOUNT_WMP?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
       System.out.println("Connection established.");
    return con;
    }


    public static UserInfo FindUser(String username, String password,String typeofaccount) {
        UserInfo bean = null;
        try {
            Connection con = getCon();
            String q = "SELECT * FROM " + typeofaccount + " WHERE "+typeofaccount+"Username = '" + username + "'";
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //been= new UserInfo();
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String Username = rs.getString(typeofaccount+"Username");
                String Password = rs.getString(typeofaccount+"Password");
                
                if (Password.equals(password)) {
                    bean = new UserInfo(FirstName, LastName, Username, Password);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bean;
    }
}
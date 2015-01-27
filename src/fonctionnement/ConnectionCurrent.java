package fonctionnement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thomas
 * 
 */
public class ConnectionCurrent {
     
    private static String url = "jdbc:oracle:thin:@localhost:1521:ufrima";
    private static String user = "henryjo";
    private static String passwd = "bd2015";
    private static Connection connect;
      
    public static Connection getInstance() {
        
      if(connect == null) {
        try {
          connect = (Connection) DriverManager.getConnection(url, user, passwd);
        }
        catch(SQLException e) {}
     }  
         
     return connect;
  }
}
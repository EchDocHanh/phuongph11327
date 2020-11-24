/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class abstractDAO {
    
     public static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    public static String dburl="jdbc:sqlserver://localhost:1433;databaseName=mp3Poly";
    public static String username="sa";
    public static String password="songlong";
    
    //nạp driver
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            
        }
    }
    
    
   public static Connection getConnection (){
        try {
             Connection con=DriverManager.getConnection(dburl, username, password); 
             return con;
        } catch (Exception e) {
            System.out.println("sai");
            return null;
        }   
    }
    public static void query(String sql,Object...args){
        
  
    }
    public static void update(String sql,Object...args){
        
  
    }
   
   
}

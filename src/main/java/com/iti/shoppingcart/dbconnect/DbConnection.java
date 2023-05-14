/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.shoppingcart.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zyadm
 */
public class DbConnection {
    static Connection con = null;
    public static Connection getConnection(){
    if(con==null)
    {
        try {
            Class.forName("org.postgresql.Driver");
           con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shoppingcart","postgres","123");
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    return con;
    }
    
}

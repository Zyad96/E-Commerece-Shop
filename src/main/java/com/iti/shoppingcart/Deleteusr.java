/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IT
 */
public class Deleteusr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req
     * @param resp

     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pt = resp.getWriter();
        String id=req.getParameter("id");
        try {
            
           Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/web" , "postgres", "2022192");
                Statement stmt = con.createStatement()) {
                String queryString = "delete from someone where id =" +id;
                stmt.executeUpdate(queryString);
                pt.println("deleted");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Deleteusr.class.getName()).log(Level.SEVERE, null, ex);
        }
            
}}
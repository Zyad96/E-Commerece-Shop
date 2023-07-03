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
import java.util.ArrayList;
import java.util.List;
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
public class Updatepro extends HttpServlet {

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
        String name=req.getParameter("name");
        String cat=req.getParameter("cat");
        String id=req.getParameter("id");
        String price=req.getParameter("price");
        String img=req.getParameter("img");
  StringBuilder queryBuilder = new StringBuilder("UPDATE product SET ");
    List<String> updateValues = new ArrayList<>();

    if ( !name.isEmpty() ) {
        updateValues.add("fname='" + name + "'");
    }

    if ( !cat.isEmpty() ) {
        updateValues.add("lname='" + cat + "'");
    }
    if ( !price.isEmpty() ) {
        updateValues.add("lname='" + price + "'");
    }
    if ( !img.isEmpty() ) {
        updateValues.add("lname='" + img + "'");
    }

    if (!updateValues.isEmpty()) {
        String updateColumns = String.join(",", updateValues);
        String queryString = queryBuilder.append(updateColumns).append(" WHERE id = ").append(id).toString();

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/web", "postgres", "2022192");
                Statement stmt = con.createStatement()) {
                stmt.executeUpdate(queryString);
                pt.println("updated");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        pt.println("No update values provided");
    }
}
}
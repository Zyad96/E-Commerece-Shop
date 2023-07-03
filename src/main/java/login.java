/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Salma
 */
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
    PrintWriter out=resp.getWriter();
    resp.setContentType("text/html");
    String email=req.getParameter("email");
    String pw=req.getParameter("pw");
    Connection c=null;
    try{
    DriverManager.registerDriver(new org.postgresql.Driver());
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/iti",
            "postgres", "iti");
            Statement st=c.createStatement();
            String s ="Select* from my_customers";
            ResultSet r=st.executeQuery(s);
            int i=0;
            while(r.next()){
            
            if (email.equals(r.getString("email")) && pw.equals(r.getString("password")))
            {
               i++;
                resp.sendRedirect("HOME.html");
               break;


            }
            }
                if (i==0){
                out.println("<br><h1 align='center' style=\"color:red;\">You'r not a member,please signup</h1>");
                req.getRequestDispatcher("index.html").include(req,resp);
                
                
            }
        }   
    
            

        catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
    
    
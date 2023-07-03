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
import com.twilio.Twilio;
import com.twilio.rest.chat.v1.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Salma
 */







public class FORM extends HttpServlet {
    
String ACCOUNT_SID = "ACb780117b3033ed521fe4052dc72ac2c9";
String AUTH_TOKEN = "3d8aa3a03f085f372b357c6b03a585d7";
String VERIFICATION_SID = "VA6231ac070b5b80b43417523d2d566898";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    String userName=req.getParameter("uname");
    String password=req.getParameter("password");
    String email=req.getParameter("email");
    String phone=req.getParameter("phone");    
    String credit = req.getParameter("credit");




    
//    HttpSession  

PrintWriter out = resp.getWriter();
req.getSession().setAttribute("userName", userName);
req.getSession().setAttribute("password", password);
req.getSession().setAttribute("email", email);
req.getSession().setAttribute("phone", phone);
req.getSession().setAttribute("credit", credit);




try{
Connection c=null;
DriverManager.registerDriver(new org.postgresql.Driver());
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/iti",
            "postgres", "iti");
            Statement st=c.createStatement();
            String s ="Select* from my_customers";
            ResultSet r=st.executeQuery(s);
            int i=0;
            while(r.next()){
            
            if (email.equals(r.getString("email")))
            {
                out.println("<br><h1 align='center' style=\"color:red;\">duplicated email</h1>");
                req.getRequestDispatcher("index.html").include(req,resp);

                 i++;              
                 break;

            }
            }
            
            if(i==0){


Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       

        Verification verification = Verification.creator(
                VERIFICATION_SID,
                phone,
                "sms")
            .create();
            
resp.sendRedirect("VERiFICATION.html");

            }

            

            
}
catch (SQLException ex) {
            Logger.getLogger(FORM.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
}



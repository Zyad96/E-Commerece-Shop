/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.twilio.Twilio;
import com.twilio.rest.chat.v1.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/twilioV"})
public class twilioV extends HttpServlet {

String ACCOUNT_SID = "ACb780117b3033ed521fe4052dc72ac2c9";
String AUTH_TOKEN = "3d8aa3a03f085f372b357c6b03a585d7";
//String TWILIO_NUMBER = "+20 114 296 7970";
String VERIFICATION_SID = "VA6231ac070b5b80b43417523d2d566898";
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String code=request.getParameter("code"); 
        request.getSession().setAttribute("code", code);
        
        String phone = (String) request.getSession().getAttribute("phone");
        String userName=(String)request.getSession().getAttribute("userName");
        String password=(String) request.getSession().getAttribute("password");
        String email=(String) request.getSession().getAttribute("email");
        String credit=(String) request.getSession().getAttribute("credit");

      
        
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      VerificationCheck verificationCheck = VerificationCheck.creator(
                VERIFICATION_SID)
            .setTo(phone)
            .setCode(code)
            .create();
        if (verificationCheck.getStatus().equals("approved")) {       
            response.setContentType("text/html");
    try {
          Connection con=null;
           DriverManager.registerDriver(new org.postgresql.Driver());
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/iti",
            "postgres", "iti");
            String queryString = "insert into my_customers values ('"+userName+"' ,'"+password+"','"+email+"' ,'"+phone+"','"+credit+"')";
        Statement st = con.createStatement();
            st.execute(queryString);
//        out.println("<h1 align='center'>WELCOME</h1>");
        response.sendRedirect("HOME.html");        
          
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        }
        else {
            out.println("<br><h1 align='center' style=\"color:red;\">Wrong code</h1>");
            request.getRequestDispatcher("VERiFICATION.HTML").include(request,response);
        }
}
}

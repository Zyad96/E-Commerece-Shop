/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.shoppingcart;

import com.iti.shoppingcart.model.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zyadm
 */
public class AddtoCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddtoCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddtoCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter pw=response.getWriter()){
        ArrayList<Cart> cartlist= new ArrayList<>();
        
        int id = Integer.parseInt(request.getParameter("id").trim());
         Cart c= new Cart();
         c.setId(id);
         c.setQuantity(1);
         
         HttpSession session = request.getSession();
         ArrayList<Cart> cart= (ArrayList<Cart>)session.getAttribute("cart-list");
         
         if(cart==null){
             cartlist.add(c);
             session.setAttribute("cart-list", cartlist);
                    response.sendRedirect("index.jsp");
         
         }
         else {
                cartlist=cart;
                boolean exist = false;
                
                for(Cart x:cart){
                if (x.getId()== id) {
                    exist = true;
                    pw.println("<h3 style='color:crimson; text-align:center'>This Item is Already in the Cart."
                            + " <a href='cart.jsp'>Go to Cart Page </a></h3>");
                }
               
                }
                 if (!exist){
                    cartlist.add(c);
                    response.sendRedirect("index.jsp");
                
                }
         }
         
         
        }
    }

   

}

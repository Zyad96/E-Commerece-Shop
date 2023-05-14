/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.shoppingcart;

import com.iti.shoppingcart.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zyadm
 */
public class QuantityServlet extends HttpServlet {

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
        try (PrintWriter pw = response.getWriter();) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("cart.jsp");
                        }
                    }
                }
                if (action.equals("dec")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;

                        }
                    }
                    response.sendRedirect("cart.jsp");
                }

            } else {
                response.sendRedirect("cart.jsp");
            }

        }
    }

}

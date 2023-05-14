<%-- 
    Document   : orders
    Created on : Apr 10, 2023, 4:09:00 AM
    Author     : zyadm
--%>

<%@page import="java.util.*"%>
<%@page import="com.iti.shoppingcart.model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list !=null)
    {
        request.setAttribute("cart_list",cart_list);}
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap demo</title>
        <%@ include file="includes/header.jsp" %>


    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <%@include file="includes/footer.jsp" %>
    </body>
</html> 
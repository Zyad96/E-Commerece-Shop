<%-- 
    Document   : index
    Created on : Apr 10, 2023, 4:50:34 AM
    Author     : zyadm
--%>
<%@page import="java.util.*"%>
<%@page import="com.iti.shoppingcart.model.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.iti.shoppingcart.dao.ProductDao"%>
<%@page import="com.iti.shoppingcart.dbconnect.DbConnection"%>
<%
    ProductDao pdao = new ProductDao(DbConnection.getConnection());
    List<Product> products = pdao.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
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
        <div class="container">
  <div class="card-header my-3">All Products</div>
  <% if(!products.isEmpty()) { %>
    <div class="row">
      <% int productCounter = 0; %>
      <% for (Product p : products) { %>
        <div class="col-md-3">
          <div class="card">
            <img src="product-images/<%= p.getImage() %>" class="card-img-top w-100" alt="..." style="height: 200px;">
            <div class="card-body">
              <h5 class="card-title"> <%= p.getName() %></h5>
              <h6 class="price">Price: <%= p.getPrice() %>$</h6> 
              <h6 class="category">Category: <%= p.getCategory()%></h6>
              <div class="mt-3 d-flex justify-content-between"></div>
              <a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
              <a href="#" class="btn btn-primary">Buy Now</a>
            </div>
          </div>
        </div>
        <% productCounter++; %>
        <% if ((productCounter % 4) == 0) { %>
          </div><div class="w-100"></div><div class="row">
        <% } %>
      <% } %>
    </div>
  <% } %>
</div>
   <%@include file="includes/footer.jsp" %>
    </body>
</html> 
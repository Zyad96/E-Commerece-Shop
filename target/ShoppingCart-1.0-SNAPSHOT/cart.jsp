<%-- 
    Document   : cart
    Created on : Apr 10, 2023, 4:07:33 AM
    Author     : zyadm
--%>

<%@page import="com.iti.shoppingcart.dbconnect.DbConnection"%>
<%@page import="com.iti.shoppingcart.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.iti.shoppingcart.model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct=null;
    if(cart_list !=null)
    {
        ProductDao pDao= new ProductDao(DbConnection.getConnection());
        cartProduct=pDao.getCartProducts(cart_list);
        int totalPrice = pDao.getTotalCartPrice(cart_list);
        
        request.setAttribute("cart_list",cart_list);
        request.setAttribute("total", totalPrice);
}
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap demo</title>
        <%@ include file="includes/header.jsp" %>

        <style type="text/css">
            .table tbody td{
                vertical-align: middle;
            }
            .btn-incre, .btn-decre{
                box-shadow:none;
                font-size: 25px;
            }
            
            
        </style>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <div class="container">
            <div class="d-flex py-3"><h3>Total Price:$ ${(total>0)?total:0}</h3><a class="mx-3 btn btn-primary" href="#">Check Out</a></div>
            <table class="table ">
                <thead class="table-light">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if(cart_list!=null){
                        for (Cart c:cartProduct)
                        {%>
                         <tr>
                             <td><%=c.getName() %></td>
                             <td>
                                 <%=c.getCategory() %>
                             </td>
                             <td>
                                 <%=c.getPrice() %>
                             </td>
                        <td>
                            <form action="" method="post" class="form-inline">
                                <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input" >
                                <div class="form-group d-flex justify-content-between">
                                    <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId()%>"><i class="fas fa-plus-square"></i></a>
                                  <input type="text" name="quantity" value="<%=c.getQuantity() %>" class="form-control" readonly>
                                    <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%= c.getId()%>"><i class="fas fa-minus-square"></i></a>
                                </div>
                            </form>
                        </td>
                        <td> <a class="btn btn-sm btn-danger active" href="remove-item-from-cart?id=<%=c.getId()%>">Remove</a>  </td>

                    </tr>
                      <%}
    
    } %>
                   
                </tbody>
            </table> 


        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html> 

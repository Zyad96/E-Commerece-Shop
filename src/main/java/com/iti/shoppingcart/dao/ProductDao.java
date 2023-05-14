/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.shoppingcart.dao;

import com.iti.shoppingcart.model.Cart;
import com.iti.shoppingcart.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author zyadm
 */
public class ProductDao {

    private Connection con;
    private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            query = "select * from product";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product prow = new Product();
                prow.setId(rs.getInt("id"));
                prow.setName(rs.getString("name"));
                prow.setCategory(rs.getString("category"));
                prow.setPrice(rs.getInt("price"));
                prow.setImage(rs.getString("image"));

                products.add(prow);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartlist) {
        List<Cart> Products = new ArrayList<Cart>();
        try {
            if (cartlist.size() > 0) {
                for (Cart item : cartlist) {
                    query = "Select * from product where id=?";
                    pstmt = this.con.prepareStatement(query);
                    pstmt.setInt(1, item.getId());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getInt("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        Products.add(row);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Products;

    }

    public int getTotalCartPrice(ArrayList<Cart> cartList) {
        int sum = 0;

        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    query = "select price from product where id=?";
                    pstmt = this.con.prepareStatement(query);
                    pstmt.setInt(1, item.getId());
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        sum += rs.getInt("price") * item.getQuantity();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

}

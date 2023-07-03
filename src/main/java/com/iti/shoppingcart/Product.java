/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.shoppingcart;

/**
 *
 * @author IT
 */
public class Product {
    
private int id ;
   private String name; 
   private int price; 
   private String cat;  
   private String img; 


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
    public void setImg(String img) {
        this.img = img;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCat() {
        return cat;
    }

    public String getImg() {
        return img;
    }
    
}

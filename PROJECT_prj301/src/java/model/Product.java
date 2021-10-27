/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private int ID;
    private String cateID;
    private int quantity;
    private String price;
    private String img;
    private String script;
    private String subcate;
    private String name;
    private String brandID;
    private int sold;

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getSubcate() {
        return subcate;
    }

    public void setSubcate(String subcate) {
        this.subcate = subcate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public Product(int ID, String cateID, int quantity, String price, String img, String script, String subcate, String name, String brandID, int sold) {
        this.ID = ID;
        this.cateID = cateID;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
        this.script = script;
        this.subcate = subcate;
        this.name = name;
        this.brandID = brandID;
        this.sold = sold;
    }

       public Product(int ID, String name, int quantity, String price, String img, String script) {
        this.ID = ID;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
        this.script = script;
        this.name = name;
    }

    public Product() {
    }
    
    
}

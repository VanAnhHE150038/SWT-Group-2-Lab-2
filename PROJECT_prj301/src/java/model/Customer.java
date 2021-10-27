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
public class Customer {
    private String name;
    private String password;
    private String address1;
    private String address2;
    private String address3;
    private String phoneNum;
    private String email;
    private boolean sex;
    private String img;
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer(String name, String password, String address1, String address2, String address3, String phoneNum, String email, boolean sex, String img) {
        this.name = name;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
        this.img = img;
    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public Customer(String name, String password, boolean sex) {
        this.name = name;
        this.password = password;
        this.sex = sex;
    }
    
    public Customer() {
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}

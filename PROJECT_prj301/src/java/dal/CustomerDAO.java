/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "select *from KhachHang";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer s = new Customer();
                s.setName(rs.getString("Username"));
                s.setPassword(rs.getString("MatKhau"));
                s.setAddress1(rs.getString("Diachi1"));
                s.setAddress2(rs.getString("Diachi2"));
                s.setAddress3(rs.getString("Diachi3"));
                s.setImg(rs.getString("linkAnh"));
                s.setPhoneNum(rs.getString("SĐT"));
                s.setEmail(rs.getString("Email"));
                s.setSex(rs.getBoolean("GioiTinh"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Customer getAccount(String username, String password) {
        String sql = "select * from KhachHang where Username=? and MatKhau=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("Username"), rs.getString("MatKhau"), rs.getString("DiaChi1"), rs.getString("DiaChi2"), rs.getString("DiaChi3"), rs.getString("SĐT"), rs.getString("Email"), rs.getBoolean("GioiTinh"), rs.getString("linkAnh"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer getAccount(String username) {
        String sql = "select * from KhachHang where Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("Username"), rs.getString("MatKhau"), rs.getString("DiaChi1"), rs.getString("DiaChi2"), rs.getString("DiaChi3"), rs.getString("SĐT"), rs.getString("Email"), rs.getBoolean("GioiTinh"), rs.getString("linkAnh"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int create(Customer a) {
        String sql = "insert into KhachHang values(?,?, null, null, null, null, null, null, null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getName());
            st.setString(2, a.getPassword());
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int update(Customer s) {
        String sql = "update KhachHang set" + " Username = '" + s.getName() + "'";
        try {
            if (s.getPassword() != null) {
                sql = sql.concat(",MatKhau = '" + s.getPassword() + "'");
            }
            if (s.getImg() != null) {
                sql = sql.concat(",linkAnh = '" + s.getImg() + "'");
            }
            if (s.getEmail() != null) {
                sql = sql.concat(",Email = '" + s.getEmail() + "'");
            }
            if (s.getPhoneNum() != null) {
                sql = sql.concat(",SĐT = '" + s.getPhoneNum() + "'");
            }
            if (s.getAddress1() != null) {
                sql = sql.concat(",DiaChi1 = '" + s.getAddress1() + "'");
            }
            if (s.getAddress2() != null) {
                sql = sql.concat(",DiaChi2 = '" + s.getAddress2() + "'");
            }
            if (s.getAddress3() != null) {
                sql = sql.concat(",DiaChi3 = '" + s.getAddress3() + "'");
            }
            sql = sql.concat("where Username = '" + s.getName() + "'");

            PreparedStatement st = connection.prepareStatement(sql);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
        
    }


}

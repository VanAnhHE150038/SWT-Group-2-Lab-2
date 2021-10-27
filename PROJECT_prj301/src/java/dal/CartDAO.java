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
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class CartDAO extends DBContext {

    public Cart getCart(String username){
        List<Item> list = new ArrayList<>();
        String sql="select * from Cart inner join SanPham on Cart.MaSP = SanPham.MaSP where Username = ?";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            st.setString(1, username);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Item a=new Item();
                a.setId(rs.getInt("MaSP"));
                a.setQuantity(rs.getInt("quantity"));
                a.setPrice(rs.getInt("Gia")*1.2);
                Product s = new Product();
                s.setID(rs.getInt("MaSP"));
                s.setCateID(rs.getString("CategoryID"));
                s.setQuantity(rs.getInt("SoLuong"));
                s.setPrice(rs.getString("Gia"));
                s.setScript(rs.getString("Script"));
                s.setSubcate(rs.getString("Subcate"));
                s.setName(rs.getString("TenSP"));
                s.setBrandID(rs.getString("MaThuongHieu"));
                s.setImg(rs.getString("linkAnh"));
                s.setSold(rs.getInt("DaBan"));
                a.setProduct(s);
                list.add(a);
                connection.rollback();
            }
            return new Cart(list);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public int addItem(String username, Item t){
        int id = t.getId();
        int i = getQuantityById(id, username);
        if (i != 0) {
            return update(id, username, i + t.getQuantity());
        } else {
            return add(id, username, t.getQuantity());
        }
    }

    public int update(int id, String username, int quantity) {
        String sql = "update Cart set quantity = ?"
                + " where Username =? and MaSP = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, quantity);
            st.setString(2, username);
            st.setInt(3, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        return -1;
    }

    public int add(int id, String username, int quantity) {
        String sql = "insert into Cart values(?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(3, quantity);
            st.setString(1, username);
            st.setInt(2, id);
            return st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int delete(int id, String username) {
        String sql = "delete from Cart where Username =? and MaSP =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, id);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int getQuantityById(int id, String username) {
        String sql = "select *from Cart where Username =? and MaSP = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, id);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }


}

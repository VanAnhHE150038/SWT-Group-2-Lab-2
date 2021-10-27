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
import model.Brand;

/**
 *
 * @author Admin
 */
public class BrandDAO extends DBContext{
    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        String sql="select *from ThuongHieu";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Brand s=new Brand();
                s.setID(rs.getString("Ma"));
                s.setName(rs.getString("Ten"));
                s.setImg(rs.getString("linkAnh"));
                s.setCountry(rs.getString("XuatXu"));
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
}

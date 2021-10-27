package dal;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> getFeatured() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 8 *from SanPham order by DaBan desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select *from SanPham";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getSaleOff() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 8 *from SaleOff inner join SanPham on SaleOff.MaSP = SanPham.MaSP";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<String> getSubcate(String cateID) {
        List<String> list = new ArrayList<>();
        String sql = "select *from SubCategory where CategoryID= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cateID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String s = rs.getString("Subcate");
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductBySubcate(String c) {
        List<Product> list = new ArrayList<>();
        String sql = "select*from SanPham where Subcate=? or CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (c.length() > 4) {
                st.setString(1, c);
                st.setString(2, "null");
            } else {
                st.setString(1, "null");
                st.setString(2, c);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductByID(int id) {
        String sql = "select * from SanPham where MaSP=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Product(id,
                        rs.getString("CategoryID"),
                        rs.getInt("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("linkAnh"),
                        rs.getString("Script"),
                        rs.getString("Subcate"),
                        rs.getString("TenSP"),
                        rs.getString("MaThuongHieu"),
                        rs.getInt("DaBan"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> search(String danhmuc, String name) {
        String sql = "select * from SanPham where TenSP like '%" + name + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (danhmuc.length() > 1) {
                sql = sql.concat(" and CategoryID = '" + danhmuc + "'");
            }
            System.out.println(sql);
            List<Product> a = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                a.add(new Product(
                        rs.getInt("MaSP"),
                        rs.getString("CategoryID"),
                        rs.getInt("SoLuong"),
                        rs.getString("Gia"),
                        rs.getString("linkAnh"),
                        rs.getString("Script"),
                        rs.getString("Subcate"),
                        rs.getString("TenSP"),
                        rs.getString("MaThuongHieu"),
                        rs.getInt("DaBan")));
            }
            System.out.println(count);
            return a;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int add(Product a) {
        String sql = "insert into SanPham values (?,?,?,?,?,?,?,?,?,?)";
        try {
            int check = 0;
            ProductDAO db = new ProductDAO();
            List<String> li = db.getSubcate(a.getCateID());

            for (String i : li) {
                if (i.trim().equals(a.getSubcate())) {
                    check = 1;
                }
            }
            System.out.println(li.size());
            if (check == 0) {
                String s = "insert into Subcategory values ('"+ a.getCateID()+"','"+a.getSubcate()+"')";
                PreparedStatement st0 = connection.prepareStatement(s);
                st0.executeQuery();
                System.out.println("NOOOOOOOO");
            }
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getID());
            st.setString(2, a.getCateID());
            st.setInt(3, a.getQuantity());
            st.setString(4, a.getPrice());
            st.setString(5, a.getScript());
            st.setString(6, a.getSubcate());
            st.setString(7, a.getName());
            st.setString(8, a.getBrandID());
            st.setString(9, a.getImg());
            st.setInt(10, a.getSold());
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    } 

    public List<Product> getByPage(List<Product> list, int start, int end) {
        List<Product> list2 = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public int delete(int id) {
        String sql = "delete from Cart where MaSP =?\n"
                + "delete from SanPham where MaSP =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id);
            ResultSet rs = st.executeQuery();
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int update(Product s) {
        String sql = "update SanPham set" + " TenSP = '" + s.getName() + "'";
        try {
            if (s.getQuantity() != 0) {
                sql = sql.concat(",SoLuong = '" + s.getQuantity() + "'");
            }
            if (s.getImg() != null) {
                sql = sql.concat(",linkAnh = '" + s.getImg() + "'");
            }
            if (s.getPrice() != null) {
                sql = sql.concat(", Gia = '" + s.getPrice() + "'");
            }
            if (s.getScript() != null) {
                sql = sql.concat(", Script = '" + s.getScript() + "'");
            }
            sql = sql.concat("where MaSP = '" + s.getID() + "'");
            PreparedStatement st = connection.prepareStatement(sql);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        ProductDAO a = new ProductDAO();
        Product b = new Product(49, "TPCN", 41, "90000","ghjkl", "yuio", "thuốc bổ", "ẠBDJS", "SA", 0);
        a.add(b);
        
    }
}

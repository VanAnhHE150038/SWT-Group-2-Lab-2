package dal;

import java.time.LocalDate;
import model.Cart;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Order;

public class OrderDAO extends DBContext {

    public int addOrder(Customer u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString().substring(0, 10);
        int oid = 0;
        try {
            //add vào bảng Order
            String sql = "insert into HoaDon values(?,?,'1',?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(2, date);
            st.setString(1, u.getName());
            st.setDouble(3, cart.getTotalMoney());
            st.executeUpdate();

            //lấy ra id của Order vừa add
            String sql1 = "select top 1 MaHD from HoaDon order by MaHD desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();

            //add vào bảng Order Detail
            if (rs.next()) {
                oid = rs.getInt("MaHD");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into ChiTietHoaDon values(?,?,?, ?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, i.getProduct().getID());
                    st2.setInt(2, i.getQuantity());
                    st2.setDouble(3, i.getPrice());
                    st2.setDouble(4, oid);
                    st2.executeUpdate();
                }
            }
            //update so luong trong bang Product sau khi ban
            String sql3 = "update SanPham set SoLuong = SoLuong - ? "
                    + "where MaSP = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getID());
                st3.executeUpdate();
            }
            //xoa sp trong MyCart
            String sql4 = "delete from Cart "
                    + "where Username  = ?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            st4.setString(1, u.getName());
            st4.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return oid;
    }

    public List<Order> getOrdering(String username) throws SQLException {
        String sql = "select MaHD, NgayDatHang,KhachHang, TongTien , TinhTrang from HoaDon join TinhTrangDonHang on  HoaDon.StatusID = TinhTrangDonHang.ID\n"
                + " where KhachHang= ? order by MaHD desc";
        PreparedStatement st1 = connection.prepareStatement(sql);
        st1.setString(1, username);
        ResultSet rs = st1.executeQuery();
        List<Order> l = new ArrayList<>();
        while (rs.next()) {
            l.add(new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5)));
        }
        return l;
    }

    public List<Order> getAll() throws SQLException {
        String sql = "select MaHD, NgayDatHang,KhachHang, TongTien , TinhTrang from HoaDon join TinhTrangDonHang on  HoaDon.StatusID = TinhTrangDonHang.ID\n"
                + " order by MaHD desc";
        PreparedStatement st1 = connection.prepareStatement(sql);
        ResultSet rs = st1.executeQuery();
        List<Order> l = new ArrayList<>();
        while (rs.next()) {
            l.add(new Order(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5)));
        }
        return l;

    }

    public int Update(int id, int statusID) throws SQLException {
        String sql3 = "update HoaDon set StatusID = ? "
                + " where MaHD = ?";
        PreparedStatement st3 = connection.prepareStatement(sql3);
        st3.setInt(1, statusID);
        st3.setInt(2, id);
        return st3.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        OrderDAO a = new OrderDAO();
        CustomerDAO b = new CustomerDAO();

        a.Update(23, 2);
    }

}

package controller;

import dal.CartDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Customer;
import model.Item;
import model.Product;

public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletProcess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer o = (Customer) session.getAttribute("account");
        if (o == null) {//chưa đăng nhập
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else { //đã đăng nhập
            String tnum = request.getParameter("num").trim();
            String tid = request.getParameter("id");
            int num, id;
            try {
                num = Integer.parseInt(tnum);
                id = Integer.parseInt(tid);
                CartDAO pdb = new CartDAO();
                ProductDAO b = new ProductDAO();
                int soLuongMua = pdb.getQuantityById(id, o.getName());
                int instock = b.getProductByID(id).getQuantity();
                if (num == -1 && soLuongMua == 1) {
                    pdb.delete(id, o.getName());
                } else {
                    if ((num == 1) && soLuongMua >= instock) {
                        num = 0;
                    }
                    double price = Double.parseDouble(b.getProductByID(id).getPrice()) * 1.2;
                    Item t = new Item(b.getProductByID(id), num, price);
                    pdb.addItem(o.getName(), t);
                }
                Item t = new Item(id, num);
                pdb.addItem(o.getName(), t);
                Cart a = pdb.getCart(o.getName());
                session.setAttribute("Cart", a);
                double total=0;
                for (Item i:a.getItems()) total += i.getPrice()*i.getQuantity();
                session.setAttribute("Total", total);
                session.setAttribute("size", a.getItems().size());
                response.sendRedirect("MyCart.jsp");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer o = (Customer) session.getAttribute("account");
        if (o != null) {//da đăng nhập
            int id = Integer.parseInt(request.getParameter("id"));
            CartDAO cart = new CartDAO();
            cart.delete(id, o.getName());

            session.setAttribute("Cart", cart.getCart(o.getName()));
            session.setAttribute("size", cart.getCart(o.getName()).getItems().size());
            response.sendRedirect("MyCart.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

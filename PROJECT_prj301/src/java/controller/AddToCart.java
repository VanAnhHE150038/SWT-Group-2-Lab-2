/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Customer;
import model.Item;

/**
 *
 * @author ADMIN
 */
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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
            String tid = request.getParameter("id");
            int num, id;
            try {
                num = 1;
                id = Integer.parseInt(tid);
                CartDAO pdb = new CartDAO();
                Item t = new Item(id, num);
                pdb.addItem(o.getName(), t);
                Cart a = pdb.getCart(o.getName());
                session.setAttribute("Cart", a);
                session.setAttribute("size", a.getItems().size());
                request.getRequestDispatcher("Product_page.jsp").forward(request, response);
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
        if (o == null) {//chưa đăng nhập
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else { //đã đăng nhập
            String tid = request.getParameter("id");
            int num, id;
            try {
                num = 1;
                id = Integer.parseInt(tid);
                CartDAO pdb = new CartDAO();
                Item t = new Item(id, num);
                pdb.addItem(o.getName(), t);
                Cart a = pdb.getCart(o.getName());
                session.setAttribute("Cart", a);
                session.setAttribute("size", a.getItems().size());
                
                request.getRequestDispatcher("home.jsp").forward(request, response);
                response.sendRedirect(tid);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
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

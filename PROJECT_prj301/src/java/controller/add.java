/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Admin
 */
public class add extends HttpServlet {

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
            out.println("<title>Servlet add</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet add at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String rid = request.getParameter("id");
        String name = request.getParameter("name");
        String rquantity = request.getParameter("quantity");
        String img = request.getParameter("img");
        String price = request.getParameter("price");
        String script = request.getParameter("script");
        String cate = request.getParameter("cateID");
        String subc = request.getParameter("subcate");
        String brandID = request.getParameter("brand");
        ProductDAO db = new ProductDAO();
        try {
            if (rid == null || cate == null || subc == null || brandID == null || rquantity == null) {
                request.setAttribute("save", "Invalid!");
                request.getRequestDispatcher("add.jsp").forward(request, response);
            } else {
                int id = Integer.parseInt(rid);
                if (db.getProductByID(id) != null) {
                    request.setAttribute("save", "Invalid!");
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                } else {
                    int quantity = Integer.parseInt(rquantity);
                    Product a = new Product(id, cate, quantity, price, img, script, subc, name, brandID, 0);
                    db.add(a);
                    request.setAttribute("save", "Saved!");
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            request.setAttribute("save", "Invalid!");
            PrintWriter out = response.getWriter();
            out.print(e);
            request.getRequestDispatcher("add.jsp").forward(request, response);

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
        processRequest(request, response);
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

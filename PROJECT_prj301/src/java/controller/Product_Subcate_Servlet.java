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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Product_Subcate_Servlet", urlPatterns = {"/product_sub"})
public class Product_Subcate_Servlet extends HttpServlet {

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
        
        String subc = request.getParameter("subcate");
        String spage = request.getParameter("page");
        int page;
        if (spage == null) page = 1;
        else page = Integer.parseInt(spage);
        
        ProductDAO p = new ProductDAO();
        List<Product> list  = p.getProductBySubcate(subc);
        int numperPage=8;
        int size = list.size();
        int numPage = size/numperPage+ (size%numperPage ==0? 0:1);
        int start, end;
        start = (page-1)*numperPage;
        end = Math.min(size, (page)*numperPage);
        ProductDAO db = new ProductDAO();
        List<Product> arr = db.getByPage(list, start, end);
        
        String a="";
        if (subc.equals("TPCN")) a=a.concat("thực phẩm chức năng");
        else if (subc.equals("TKKD")) a=a.concat("thuốc không kê đơn");
        else if(subc.equals("CSCN")) a=a.concat("chăm sóc cá nhân");
        else if (subc.equals("MP")) a=a.concat("mỹ phẩm");
        else a=a.concat(subc);
        
        request.setAttribute("subc", "&subcate="+subc);
        
        
        request.setAttribute("num", numPage);//so trang
        request.setAttribute("data", arr);//ds phan tu cua trang thu page
        request.setAttribute("listP", list);
        request.setAttribute("subcate", a);
        request.getRequestDispatcher("Product_page.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String danhmuc = request.getParameter("op");
        String name = request.getParameter("name");
        String spage = request.getParameter("page");
        
        int page;
        if (spage == null) page = 1;
        else page = Integer.parseInt(spage);
        
        ProductDAO p = new ProductDAO();
        List<Product> list  = p.search(danhmuc, name);
        PrintWriter out = response.getWriter();
        if (list == null) out.print("ooops");
        int numperPage=8;
        int size = list.size();
        int numPage = size/numperPage+ (size%numperPage ==0? 0:1);
        int start, end;
        start = (page-1)*numperPage;
        end = Math.min(size, (page)*numperPage);
        ProductDAO db = new ProductDAO();
        List<Product> arr = db.getByPage(list, start, end);
        
        String subc="";
        if (!name.trim().equals("")) subc = subc.concat("&name="+name);
        if (danhmuc != null) subc = subc.concat("&op="+danhmuc);
        request.setAttribute("subc", subc);
        
        
        request.setAttribute("num", numPage);//so trang
        request.setAttribute("data", arr);//ds phan tu cua trang thu page
        request.setAttribute("listP", list);
        request.setAttribute("subcate", "Sản phẩm bạn đang tìm kiếm");

        request.getRequestDispatcher("Product_page.jsp").forward(request, response);
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

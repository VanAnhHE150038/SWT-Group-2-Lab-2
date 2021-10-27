
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : home
    Created on : Jun 7, 2021, 9:45:09 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="model.Brand"%>
<%@page import="dal.BrandDAO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="dal.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <style>
        table img {
            width: 200px;
            height: 150px;
        }
    </style>
    <head>
        <jsp:useBean id = "a" class="dal.ProductDAO" scope="request"></jsp:useBean>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Nhà thuốc Thái Lâm - Uy tín hàng đầu</title>

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

            <!--Bootstrap and Other Vendors-->
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <link rel="stylesheet" href="css/bootstrap-theme.min.css">
            <link rel="stylesheet" href="css/font-awesome.min.css">

            <!--Mechanic Styles-->
            <link rel="stylesheet" href="css/style.css">
            <link rel="stylesheet" href="css/responsive.css">

        </head>

        <body>
            <header class="row" id="header">
                <div class="row m0 top_menus">
                    <div class="container">
                        <div class="row">
                            <ul class="nav nav-pills fleft">
                                <li><a href="index.html">home</a></li>
                                <li><a href="about.html">about</a></li>
                                <li><a href="contact.html">contact us</a></li>
                            </ul>
                            <ul class="nav nav-pills fright">
                                <li><a href="cart.html">cart</a></li>
                                <li><a href="#">track order</a></li>
                                <li><a href="login.jsp">my account</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row m0 logo_line">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-5 logo">
                                <a href="index.html" class="logo_a"><img src="images/logo.png" alt="Furniture House"></a>
                            </div>
                            <div class="col-sm-7 searchSec">
                                <div class="fleft searchForm">
                                    <form action="#" method="get">
                                        <div class="input-group">
                                            <input type="hidden" name="search_param" value="all" id="search_param">
                                            <input type="search" class="form-control" placeholder="Search Products">
                                            <div class="input-group-btn searchFilters">
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span id="searchFilterValue">Danh mục</span> <i class="fa fa-angle-down"></i>
                                                </button>
                                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                    <li><a href="#all">danh mục</a></li>
                                                    <li>
                                                        <a href="#chairs">Thực phẩm chứ năng</a>
                                                    </li>
                                                    <li><a href="#tables">Thuốc không kê đơn</a></li>
                                                    <li><a href="#dressers">Chăm sóc sức khỏe</a></li>
                                                    <li><a href="#desk">Mỹ phẩm</a></li>
                                                    <li><a href="#beds">Sống khỏe</a></li>
                                                </ul>
                                            </div>
                                            <!-- /btn-group -->
                                            <span class="input-group-btn searchIco">
                                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                            </span>
                                        </div>
                                        <!-- /input-group -->
                                    </form>
                                </div>
                                <div class="fleft wishlistCompare">
                                    <ul class="nav">
                                        <li><a href="#"><i class="fa fa-heart"></i> Wishlist (3)</a></li>
                                        <li><a href="#"><i class="fa fa-exchange"></i> Compare (2)</a></li>
                                    </ul>
                                </div>
                                <div class="fleft cartCount">
                                    <div class="cartCountInner row m0">
                                        <span class="badge">${sessionScope.size}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="navbar navbar-default m0 navbar-static-top">
                <div class="container-fluid container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mainNav">
                            <i class="fa fa-bars"></i> Navigation
                        </button>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="mainNav">
                        <ul class="nav navbar-nav">
                            <li class="active dropdown">
                                <a href="home.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Trang chủ</a>
                            </li>
                            <li class="dropdown">
                                <a href="product_sub?subcate=TPCN" class="dropdown-toggle" role="button" aria-expanded="false">thực phẩm chức năng</a>
                                <ul class="dropdown-menu" role="menu">
                                    <%  ProductDAO p = new ProductDAO();
                                        List<String> lst = p.getSubcate("TPCN");
                                        for (String str : lst) {
                                    %>     
                                    <li><a href="product_sub?subcate=<%=str%>" name="a"><%=str%></a></li>
                                        <% } %>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="product_sub?subcate=TKKD" class="dropdown-toggle" role="button" aria-expanded="false">thuốc không kê đơn</a>
                                <ul class="dropdown-menu" role="menu">
                                    <%
                                        lst = p.getSubcate("TKKD");
                                        for (String str : lst) {
                                    %>     
                                    <li><a href="product_sub?subcate=<%=str%>"><%=str%></a></li>
                                        <% } %>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="product_sub?subcate=CSSK" class="dropdown-toggle"  role="button" aria-expanded="false">chăm sóc cá nhân</a>
                                <ul class="dropdown-menu" role="menu">
                                    <%
                                        lst = p.getSubcate("CSCN");
                                        for (String str : lst) {
                                    %>     
                                    <li><a href="product_sub?subcate=<%=str%>"><%=str%></a></li>
                                        <% } %>

                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="product_sub?subcate=MP" class="dropdown-toggle"  role="button" aria-expanded="true">mỹ phẩm</a>
                                <ul class="dropdown-menu" role="menu">
                                    <%
                                        lst = p.getSubcate("MP");
                                        for (String str : lst) {
                                    %>     
                                    <li><a href="product_sub?subcate=<%=str%>"><%=str%></a></li>
                                        <% }%>

                                </ul>
                            </li>
                            <li>
                                <a href="contact.html">sống khỏe</a>
                            </li>
                            <li>
                                <a href="contact.html">liên hệ</a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </header>
        <section id="featureProducts" class="row contentRowPad">
            <div class="container">
                <div class="content">
                    <h1>Giỏ hàng của tôi</h1>
                    <table border="1px" width="100%">
                        <tr>
                            <th>Thứ tự</th>
                            <th>Ảnh</th>
                            <th>Tên</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                            <th>Tổng tiền</th>
                            <th>Bạn muốn xóa sản phẩm?</th>
                        </tr>
                        <c:set var="o" value="${sessionScope.Cart}"/>
                        <c:set var="t" value="0"/>
                        <c:forEach items="${o.items}" var="i"> 
                            <c:set var="t" value="${t+1}"/>               
                            <tr>                
                                <td>${t}</td>
                                <td><img src="${i.product.img}" alt=""></td>
                                <td>${i.product.name}</td>

                                <td style="text-align: center">
                                    <button ><a href="process?num=-1&id=${i.id}">-</a></button>
                                    <input type="text" readonly  value="${i.quantity}"/>
                                    <button><a href="process?num=1&id=${i.id}">+</a></button>
                                </td>

                                <td class="ta">
                                    $<fmt:formatNumber type="currency" currencySymbol="VNĐ" value="${i.price}"/>
                                </td>
                                <td class="ta">
                                    $<fmt:formatNumber type="currency" currencySymbol="VNĐ" value="${i.quantity*i.price}"/>
                                </td>
                                <td style="text-align: center">
                                    <form action="process" method="post">
                                        <input type="hidden" name="id" value="${i.id}"/>
                                        <input type="submit" value="Return item"/>   
                                    </form>    
                                </td>
                            </tr>
                        </c:forEach>                    
                    </table>
                    <p></p>    
                    <br>
                    <h3>
                        Tổng tiền: $<fmt:formatNumber type="currency" currencySymbol="VNĐ" value="${sessionScope.Total}"/>

                    </h3>
                    <form action="export" method="post">
                        <input type="submit" value="Xuất hóa đơn"/>   
                    </form>  
                </div>    

            </div> 
        </section>                                
        <footer class="row">
            <div class="row m0 topFooter">
                <div class="container line2">
                    <div class="row">
                        <div class="col-sm-3 widget">
                            <div class="row m0">
                                <h4> <b>nhà thuốc thái lâm</b> </h4>
                                <p>Kết nối với chúng tôi</p>
                                <hr style="width: 65%;" align="left">
                                <ul class="list-inline">
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                </ul>
                                <p>Hotline miễn phí: 1800 2001</p>
                                <hr style="width: 65%;" align="left">
                            </div>
                        </div>
                        <div class="col-sm-3 widget">
                            <div class="row m0">
                                <h4>thông tin</h4>
                                <ul class="nav">
                                    <li><a href="about.html">Hệ thống của hàng</a></li>
                                    <li><a href="services.html">Dịch vụ tư vấn</a></li>
                                    <li><a href="blog.html">Tin tức sức khỏe</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3 widget">
                            <div class="row m0">
                                <h4>Chính sách hỗ trợ</h4>
                                <ul class="nav">
                                    <li><a href="#">Chính sách đổi trả</a></li>
                                    <li><a href="#">Chính sách vận chuyển</a></li>
                                    <li><a href="#">Chính sách bảo mật</a></li>
                                    <li><a href="#">Kiểm tra hóa đơn điện tử</a></li>
                                    <li><a href="#">Chính sách thanh toán</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3 widget">
                            <div class="row m0">
                                <h4>đăng kí theo dõi</h4>
                                <form action="subscriptionList.php" method="post" role="form">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                        <input type="email" class="form-control" id="subscribeEmail" name="subscribeEmail" placeholder="ĐỊA CHỈ EMAIL">
                                    </div>
                                    <input type="submit" class="form-control" value="theo dõi" id="submit" name="submit">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row m0 copyRight">
                <div class="container">
                    <div class="row">
                        <div class="fleft">&copy; 2015 <a href="index.html">LatDatDungCam</a> All Rights Reserved.</div>
                        <ul class="nav nav-pills fright">
                            <li><a href="index.html">Home</a></li>
                            <li><a href="about.html">about</a></li>
                            <li><a href="blog.html">blog</a></li>
                            <li><a href="contact.html">contact</a></li>
                        </ul>
                    </div>
                </div>
        </footer>


        <!--jQuery-->
        <script src="js/jquery-2.1.3.min.js"></script>

        <!--Google Maps-->
        <script src="https://maps.googleapis.com/maps/api/js"></script>

        <!--Bootstrap JS-->
        <script src="js/bootstrap.min.js"></script>

        <!--Strella JS-->
        <script src="js/furniture.js"></script>

    </body>

</html>
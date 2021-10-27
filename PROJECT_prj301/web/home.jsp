
<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : home
    Created on : Jun 7, 2021, 9:45:09 PM
    Author     : Admin
--%>

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

    <head>
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

        <style>
            table {
                border-collapse: collapse;
                width: 80%;
            }
            .content {
                margin: auto;
                width: 80%;
            }
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: chocolate;
            }

        </style>
    </head>

    <header class="row" id="header">
        <div class="row m0 top_menus">
            <div class="container">
                <div class="row">
                    <ul class="nav nav-pills fleft">
                        <li><a href="home.jsp">home</a></li>
                        <li><a href="about.html">about</a></li>
                        <li><a href="contact.html">contact us</a></li>
                    </ul>
                    <ul class="nav nav-pills fright">
                        <c:choose>
                            <c:when test="${sessionScope.account == null}">
                                <li><a href="login.jsp">login</a></li>
                                <li><a href="register.jsp">register</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="export">track order</a></li>
                                <li><a href="checkout">log out</a></li>
                                <li><a href="AccountInfo.jsp">my account</a></li>
                                <br>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row m0 logo_line">
            <div class="container">
                <div class="row">
                    <div class="col-sm-5 logo">
                        <a href="home.jsp" class="logo_a"><img src="images/logo.png" alt="Furniture House"></a>
                    </div>
                    <div class="col-sm-7 searchSec">
                        <div class="fleft searchForm">
                            <form action="product_sub" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Nhập tên sản phẩm" name = "name">
                                    <select name="op">
                                        <option value="0">Danh mục</option>
                                        <option value="TPCN">Thực phẩm chứ năng</option>
                                        <option value="TKKD">Thuốc không kê đơn</option>
                                        <option value="CSSK">Chăm sóc sức khỏe</option>
                                        <option value="MP">Mỹ phẩm</option>
                                        <option value="SK">Sống khỏe</option>
                                    </select>
                                    <span class="input-group-btn searchIco">
                                        <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                    </span>
                                    <!-- /btn-group -->
                                </div>
                                <!-- /input-group -->
                            </form>
                        </div>
                        <div class="fleft wishlistCompare">
                            <ul class="nav">

                            </ul>
                        </div>
                        <div class="fleft cartCount">
                            <div class="cartCountInner row m0">
                                <a href="MyCart.jsp"><span class="badge">${sessionScope.size}</span></a>
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
                                    <% } %>

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
        <div class="row topFeatures m0">
            <div class="container">
                <ul class="nav nav-justified text-center">
                    <li><img src="images\icons\delivery-car.png" alt=""> fast delivery</li>
                    <li><img src="images/icons/tel24-7.png" alt="">24/7 customer support</li>
                </ul>
            </div>
        </div>
    </header>
    <!--Header-->
    <c:choose>
        <c:when test="${sessionScope.account.name == 'admin'}">

            <div class="content">
                <br>
                <form action="list" method="POST" >
                    <h3>
                        <input type="submit" value="Tất cả sản phẩm" name="opppp" />
                    </h3>
                </form>

                <div class="pagination">
                    <c:forEach begin="1" end="${requestScope.num}" var="i">
                        <a href="list?page=${i}${requestScope.subc}" >${i}</a>
                    </c:forEach> 
                </div>   
                <table border="1px">
                    <a href="add.jsp">Thêm sản phẩm</a>
                    <thead>
                        <tr>
                            <th>Tên SP</th>
                            <th>Loại thuốc</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                            <th>Hình ảnh</th>
                            <th>Xóa/Sửa</th>

                        </tr>
                        <c:forEach items="${requestScope.data}" var = "p">
                            <tr>
                                <td>${p.name}</td>
                                <th>${p.subcate}</th>
                                <th>${p.quantity}</th>
                                <th>${p.price}</th>
                                <td><img width="100px" src="${p.img}" alt="alt"/></td>
                                <th> <form action="edit" method="get">
                                        <input  type="hidden" name ="id" value="${p.ID}"/>
                                        <input type="submit"  value="Xóa"/>
                                    </form>  
                                    <form action="edit" method="post">
                                        <input  type="hidden" name ="id" value="${p.ID}"/>
                                        <input type="submit" name="edit" value="Sửa"/>
                                    </form> 
                                </th>
                            </tr>
                        </c:forEach>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

                <form action="listOrder"  >
                    <h3>
                        <input type="submit" value="Danh sách hóa đơn"/>
                    </h3>
                </form>

                <table border="1px" width="100%">
                    <tr>
                        <th>Mã hóa đơn</th>
                        <th>Tên khách hàng</th>
                        <th>Ngày đặt hàng</th>
                        <th>Tình trạng đơn hàng</th>
                        <th>Tổng tiền</th>
                    </tr>
                    <c:forEach items="${requestScope.listOrder}" var="i"> 
                        <tr> 
                            <td>${i.id}</td>
                            <td>${i.cusName}</td>
                            <td>${i.date}</td>
                            <td>${i.status} 
                                <c:if test="${i.status.trim() == 'Đang chờ'}">
                                    <a href="cf?id=${i.id}">Xác nhận</a>
                                </c:if>
                            </td>
                            <td>${i.totalmoney}</td>
                        </tr>
                    </c:forEach> 
                </table>
            </div>    
        </c:when>
        <c:otherwise>
            <section id="slider" class="row">
                <div class="row sliderCont flexslider m0">
                    <ul class="slides nav">
                        <li class="flex-active-slide">
                            <img src="images\slider\1.jpg" alt="">
                        </li>
                        <li class="flex-active-slide">
                            <img src="images\slider\2.png" alt="">
                        </li >
                        <li class="flex-active-slide">
                            <img src="images\slider\3.png" alt="">
                        </li>
                    </ul>


                </div>
            </section>
            <!--Slider-->

            <section id="shopFeatures" class="row">
                <div class="container">
                    <div class="row text-center">
                        <div class="col-sm-3 coreFeature">
                            <div class="row m0 coreFeatureInner">
                                <div class="row m0 icon circle"><img src="images/icons/car2.png" alt=""></div>
                                <h5>miễn phí vận chuyển</h5>
                                <p>Miễn phí vận chuyển cho các đơn hàng trên 300,000VNĐ và nhận hàng nhanh chóng.</p>
                            </div>
                        </div>
                        <div class="col-sm-3 coreFeature">
                            <div class="row m0 coreFeatureInner">
                                <div class="row m0 icon circle"><img src="images/icons/heart.png" style="width: 50%" alt=""></div>
                                <h5>tận tâm phục vụ</h5>
                                <p>Dịch vụ chăm sóc khách hàng chuyên nghiệp luôn sẵn sàng giải đáp mọi thắc mắc của bạn.<br> Hotline miễn phí: 1800 2001</p>
                            </div>
                        </div>
                        <div class="col-sm-3 coreFeature">
                            <div class="row m0 coreFeatureInner">
                                <div class="row m0 icon circle"><img src="images/icons/like.png" style="width: 50%" alt=""></div>
                                <h5>sản phẩm chất lượng</h5>
                                <p>Chúng tôi cam kết mang đến những sản phẩm đến từ các thương hiệu uy tín, an toàn cho sức khỏe người tiêu dùng.</p>
                            </div>
                        </div>
                        <div class="col-sm-3 coreFeature">
                            <div class="row m0 coreFeatureInner">
                                <div class="row m0 icon circle"><img src="images/icons/shopping-bag.png" style="width: 50%" alt=""></div>
                                <h5>mua hàng trực tuyến</h5>
                                <p>Giá bán trên trang chỉ áp dụng khi mua sắm trên trang thương mại điện tử của nhà thuốc Thái Lâm.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--Shop Core Feature-->

            <section id="featureProducts" class="row contentRowPad">
                <div class="container">
                    <div class="row sectionTitle">
                        <h3>sản phẩm mới</h3>
                        <h5>các sản phẩm mới nhất của chúng tôi</h5>
                    </div>
                    <div class="row">
                        <%
                            ProductDAO s = new ProductDAO();
                            List<Product> lt = s.getFeatured();
                            for (Product a : lt) {

                        %>
                        <div class="col-sm-3 product">
                            <div class="productInner row m0">
                                <div class="row m0 imgHov">
                                    <img src="<%= a.getImg()%>" alt="">
                                    <div class="row m0 hovArea">
                                        <div class="row m0 icons">
                                            <ul class="list-inline">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="row m0 proType"><a href="#">xem nhanh</a></div>
                                    </div>
                                </div>
                                <div class="row m0 proName">
                                    <a href="single-product.html">
                                        <%= a.getName()%>
                                        <br>
                                        <div class="row m0 proPrice" style="color: green"><%= a.getPrice()%></div>
                                    </a>
                                </div>
                                <div class="row m0 proBuyBtn">
                                    <form action="buy?id=<%=a.getID()%>" method="POST">
                                        <button class="addToCart btn" type="submit">thêm vào giỏ</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <% }%> 
                    </div> 
            </section>
            <!--Feature Products 4 Collumn-->

            <section id="featureCategory" class="row contentRowPad">
                <div class="row m0 sectionTitle">
                    <h3>tiết kiệm hơn, sống khỏe hơn</h3>
                </div>
                <div class="tcb-product-slider">
                    <div class="container">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <div class="row">

                                        <%
                                            List<Product> list = s.getSaleOff();
                                            for (int i = 0; i < 4; i++) {

                                        %>
                                        <div class="col-sm-3 product">
                                            <div class="productInner row m0">
                                                <div class="row m0 imgHov">
                                                    <img src="<%= list.get(i).getImg()%>" alt="">
                                                    <div class="row m0 hovArea">
                                                        <div class="row m0 icons">
                                                            <ul class="list-inline">
                                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="row m0 proType"><a href="#">xem nhanh</a></div>
                                                    </div>
                                                </div>
                                                <div class="row m0 proName">
                                                    <a href="single-product.html">
                                                        <%= list.get(i).getName()%>
                                                        <br><!-- comment -->
                                                        <div class="row m0 proPrice" style="color: green"><%= list.get(i).getPrice().substring(0, list.get(i).getPrice().length() - 2)%></div>
                                                    </a>
                                                </div>  
                                                <div class="row m0 proBuyBtn">
                                                    <form action="buy?id=<%=list.get(i).getID()%>" method = "POST">
                                                        <button class="addToCart btn" type="submit" >thêm vào giỏ</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <% }%>
                                    </div>
                                </div>

                                <div class="item">
                                    <div class="row">
                                        <%
                                            List<Product> list2 = s.getSaleOff();
                                            for (int i = 4; i < 8; i++) {

                                        %>
                                        <div class="col-sm-3 product">
                                            <div class="productInner row m0">
                                                <div class="row m0 imgHov">
                                                    <img src="<%= list2.get(i).getImg()%>" alt="">
                                                    <div class="row m0 hovArea">
                                                        <div class="row m0 icons">
                                                            <ul class="list-inline">
                                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="row m0 proType"><a href="#">xem nhanh</a></div>
                                                    </div>
                                                </div>
                                                <div class="row m0 proName">
                                                    <a href="single-product.html">
                                                        <%= list2.get(i).getName()%>
                                                        <br><!-- comment -->
                                                        <div class="row m0 proPrice" style="color: green"><%= list2.get(i).getPrice()%></div>
                                                    </a>
                                                </div>  
                                                <div class="row m0 proBuyBtn">
                                                    <form action="buy?id=<%=list2.get(i).getID()%>" method="POST">
                                                        <button class="addToCart btn" type="submit">thêm vào giỏ</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <% }%>
                                    </div>
                                </div>
                            </div>
                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </section>
            <!--Sale off-->

            <section id="furnitureHouse" class="row contentRowPad">
                <div class="container">
                    <div class="row sectionTitle">
                        <h3>tăng sức đề kháng mùa dịch</h3>
                        <h5>chung tay chống dịch, nhà nhà bình an</h5>
                        <div class="row">
                            <%
                                ProductDAO d = new ProductDAO();
                                List<Product> list4 = d.getFeatured();
                                for (Product a : list4) {

                            %>
                            <div class="col-sm-3 product">
                                <div class="productInner row m0">
                                    <div class="row m0 imgHov">
                                        <img src="<%= a.getImg()%>" alt="">
                                        <div class="row m0 hovArea">
                                            <div class="row m0 icons">
                                                <ul class="list-inline">
                                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                </ul>
                                            </div>
                                            <div class="row m0 proType"><a href="#">xem nhanh</a></div>
                                        </div>
                                    </div>
                                    <div class="row m0 proName">
                                        <a href="single-product.html">
                                            <%= a.getName()%>
                                            <br>
                                            <div class="row m0 proPrice" style="color: green"><%= a.getPrice()%></div>
                                        </a>
                                    </div>
                                    <div class="row m0 proBuyBtn">
                                        <form action="buy?id=<%=a.getID()%>" method="POST">
                                            <button class="addToCart btn" type="submit">thêm vào giỏ</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div> 
                    </div>
            </section>
            <!--Feature Products 4 Collumn-->

            <section id="testimonialTabs" class="row contentRowPad">
                <div class="container">
                    <div class="row sectionTitle">
                        <h3>Đánh giá từ khách hàng</h3>
                        <h5>sức khỏe của khách hàng là mối quan tâm số 1 của chúng tôi!</h5>
                    </div>
                    <div class="row">
                        <ul class="nav nav-tabs" role="tablist" id="testiTab">
                            <li role="presentation" class="active">
                                <a href="#testi1" aria-controls="testi1" role="tab" data-toggle="tab">
                                    <img src="images/customer/1.jpg" alt=""><i class="fa fa-plus-circle"></i>
                                </a>
                            </li>
                            <%

                                for (int i = 1; i < 5; i++) {
                                }
                            %>
                            <li role="presentation">
                                <a href="#testi2" aria-controls="testi2" role="tab" data-toggle="tab">
                                    <img src="images/customer/2.jpg" alt=""><i class="fa fa-plus-circle"></i>
                                </a>
                            </li>
                            <li role="presentation">
                                <a href="#testi3" aria-controls="testi3" role="tab" data-toggle="tab">
                                    <img src="images/customer/4.jpg" alt=""><i class="fa fa-plus-circle"></i>
                                </a>
                            </li>
                            <li role="presentation">
                                <a href="#testi4" aria-controls="testi4" role="tab" data-toggle="tab">
                                    <img src="images/customer/2.jpg" alt=""><i class="fa fa-plus-circle"></i>
                                </a>
                            </li>
                            <li role="presentation">
                                <a href="#testi5" aria-controls="testi5" role="tab" data-toggle="tab">
                                    <img src="images/customer/3.jpg" alt=""><i class="fa fa-plus-circle"></i>
                                </a>
                            </li>
                        </ul>

                        <div class="tab-content testiTabContent">
                            <div role="tabpanel" class="tab-pane active" id="testi1">
                                <h5 class="customerName">Dwayne johnson</h5>
                                <h5 class="customerType">happy customer</h5>
                                <p>“ FurnitureHouse is really excellent site for furnitures. I am very happy with the FurnitureHouse products and dedicated services from them. FurnitureHouse is really excellent site for furnitures. ”</p>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="testi2">
                                <h5 class="customerName">Dwayne johnson</h5>
                                <h5 class="customerType">happy customer</h5>
                                <p>“ FurnitureHouse is really excellent site for furnitures. I am very happy with the FurnitureHouse products and dedicated services from them. FurnitureHouse is really excellent site for furnitures. ”</p>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="testi3">
                                <h5 class="customerName">Dwayne johnson</h5>
                                <h5 class="customerType">happy customer</h5>
                                <p>“ FurnitureHouse is really excellent site for furnitures. I am very happy with the FurnitureHouse products and dedicated services from them. FurnitureHouse is really excellent site for furnitures. ”</p>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="testi4">
                                <h5 class="customerName">Dwayne johnson</h5>
                                <h5 class="customerType">happy customer</h5>
                                <p>“ FurnitureHouse is really excellent site for furnitures. I am very happy with the FurnitureHouse products and dedicated services from them. FurnitureHouse is really excellent site for furnitures. ”</p>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="testi5">
                                <h5 class="customerName">Dwayne johnson</h5>
                                <h5 class="customerType">happy customer</h5>
                                <p>“ FurnitureHouse is really excellent site for furnitures. I am very happy with the FurnitureHouse products and dedicated services from them. FurnitureHouse is really excellent site for furnitures. ”</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--Testimonial Tabs-->

            <section id="brands" class="row contentRowPad">
                <div class="container">
                    <div class="row sectionTitle">
                        <h3>sản phẩm từ những thương hiệu uy tín hàng đầu</h3>
                        <h5>chọn thương hiệu bạn muốn</h5>
                    </div>
                    <div class="row brands">
                        <ul class="nav navbar-nav">
                            <% BrandDAO b = new BrandDAO();
                                List<Brand> list3 = b.getAll();
                                for (int j = 0; j < 5; j++) {
                            %>
                            <li>
                                <a href="#"><img src="<%= list3.get(j).getImg()%>" alt="brand" style="width: 140; height: 78.13"></a>
                            </li>
                            <% }%>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    </section>
</c:otherwise>
</c:choose>    

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


</html>
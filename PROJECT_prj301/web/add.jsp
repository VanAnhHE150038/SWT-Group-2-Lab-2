<%-- 
    Document   : UpdatePro
    Created on : Jul 15, 2021, 1:48:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <c:choose>
        <c:when test="${sessionScope.account.name == 'admin'}">
            <h1>Thêm sản phẩm</h1>
          
            <form action="add" >
                Ma SP: <input type="text" name="id"/><br/>
                Ten SP: <input type="text" name="name"/><br/>
                So luong: <input type="text" name="quantity" /><br/>
                Anh SP: <input type="text" name="img" /><br/>
                Gia SP: <input type="text" name="price" /><br/>
                Mieu ta: <input type="text" name="script" style="height: 100px"/><br/>
                Phân loại:<select name="cateID">
                    <option value="TPCN">Thực phẩm chứ năng</option>
                    <option value="TKKD">Thuốc không kê đơn</option>
                    <option value="CSSK">Chăm sóc sức khỏe</option>
                    <option value="MP">Mỹ phẩm</option>
                    <option value="SK">Sống khỏe</option>
                </select>
                Loại thuốc theo chức năng: <input type="text" name="subcate" /><br/>
                Mã thương hiệu:
                <select name="brand">
                    <option value="GSK">GlaxoSmithKline</option>
                    <option value="MSD">Merck</option>
                    <option value="NVT">Novartis</option>
                    <option value="PMD">Pharmed</option>
                    <option value="SA">SA</option>
                    <option value="UCB">UCB Farchim SA
                    </option>
                </select>
                <h4>${requestScope.save}</h4>
                <input type="submit" value="Save"/>
            </form>
        </c:when>
        <c:otherwise>
            Access denied!
        </c:otherwise>
    </c:choose>
    <a href="home.jsp">back to Home</a>    </body>
</html>

<%-- 
    Document   : trackOrder
    Created on : Jul 20, 2021, 1:36:28 AM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Tất cả hóa đơn của ${sessionScope.account.name}</h1>
        <table border="1px" width="100%">
            <tr>
                <th>Mã hóa đơn</th>
                <th>Ngày đặt hàng</th>
                <th>Tình trạng đơn hàng</th>
                <th>Tổng tiền</th>
            </tr>
            <c:forEach items="${requestScope.listOrder}" var="i"> 
                <tr> 
                    <td>${i.id}</td>
                    <td>${i.date}</td>
                    <td>${i.status} 
                        <c:if test="${i.status.trim() == 'Đang giao'}">
                            <form action="cf"  method="post">
                                <input type="hidden" value="${i.id}" name = "id">
                                <input type="submit" value="Đã nhận được hàng">
                            </form>
                        </c:if>
                        </td>
                    <td>${i.totalmoney}</td>
                </tr>
            </c:forEach> 
                
        </table>
        <br>
        <a href="home.jsp">Quay về Trang chủ</a>
    </body>
</html>

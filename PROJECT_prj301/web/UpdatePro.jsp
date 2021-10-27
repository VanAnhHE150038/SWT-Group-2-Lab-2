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
            <h1>Hello ${sessionScope.account.name}</h1>
            <c:set  var="i" value="${requestScope.proUpdate}"/>
            <form action="update" method="post">
                Ma SP: <input type="text" name="id" readonly value="${i.ID}"/><br/>
                Ten SP: <input type="text" name="name" value="${i.name}"/><br/>
                So luong: <input type="text" name="quantity" value="${i.quantity}"/><br/>
                Anh SP: <input type="text" name="img" value="${i.img}"/><br/>
                Gia SP: <input type="text" name="price" value="${i.price}"/><br/>
                Mieu ta: <input type="text" name="script" value="${i.script}" style="height: 100px"/><br/>

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

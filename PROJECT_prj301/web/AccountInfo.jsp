<%-- 
    Document   : AccountInfo
    Created on : Jul 13, 2021, 10:20:57 PM
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
            <c:when test="${sessionScope.account != null}">
                <h1>Hello ${sessionScope.account.name}</h1>
                <c:set  var="i" value="${sessionScope.account}"/>
                <form action="update">
                    Username:<input type="text" name="name" readonly value="${i.name}"/><br/>
                    Password:<input type="text" name="pass" value="${i.password}"/><br/>
                    Phone number:<input type="text" name="phone" value="${i.phoneNum}"/><br/>
                    Email:<input type="text" name="email" value="${i.email}"/><br/>
                    Sex:
                    <input type="radio" name="gender" value="1"/>Male
                    <input type="radio" name="gender" value="0"/>Female
                    <br/>
                    Avatar: <input type="text" name="ava"/><br/>
                    Address 1:<input type="text" name="add1" value="${i.address1}"/><br/>
                    Address 2:<input type="text" name="add2" value="${i.address2}"/><br/>
                    Address 3:<input type="text" name="add3" value="${i.address3}"/><br/>
                    <h4>${requestScope.save}</h4>
                    <input type="submit" value="Save"/>
                </form>
            </c:when>
            <c:otherwise>
                Access denied!
            </c:otherwise>
        </c:choose>
        <a href="home.jsp">back to Home</a>

    
    </body>

</html>

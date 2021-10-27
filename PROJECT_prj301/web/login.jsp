<%-- 
    Document   : login
    Created on : Jun 1, 2021, 4:46:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login form</title>
        <link rel="stylesheet" href="css/styleLogin_Register.css"/>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <div class="imgcontainer">
                <img src="images/customer/avatar-male.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="uname" required>
                <br>
                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>
                <br>
                    
                <button type="submit" style="text-align: center; width: 100%">Login</button>
                <br>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
                <h2 style="color: red">${requestScope.error}</h2>
                <h4>Bạn chưa có tài khoản? <a href="register.jsp">ĐĂNG KÍ</a> </h4>
                    
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form>

    </body>
</html>

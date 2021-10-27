<%-- 
    Document   : register
    Created on : Jun 1, 2021, 5:21:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styleLogin_Register.css"/>
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="register" method="post">
            <div class="imgcontainer">
                <img src="images/customer/avatar-male.jpg" alt="Avatar" class="avatar">
                <h2 style="color: red">${requestScope.error}</h2>
            </div>

            <div class="container">
                <label for="uname"><b>Username</b></label> &emsp; &emsp; &emsp; &emsp;
                <input type="text" placeholder="Enter Username" name="uname" required>
                <br>
                <label for="psw"><b>Password</b></label> &emsp; &emsp; &emsp; &emsp;
                <input type="password" placeholder="Enter Password" name="psw" required>
                <br>
                <label for="re-psw"><b>Re-enter password</b></label>&emsp;
                <input type="password" placeholder="Re-enter Password" name="re-psw" required>
                <br>  
                <button type="submit" style="text-align: center; width: 100%; cursor: pointer" onclick="check()">Register</button>
                <br>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                    <h4>Bạn đã có tài khoản? <a href="login.jsp">ĐĂNG NHẬP</a></h4>
                    <button type="button" class="cancelbtn" style="float: right">Cancel</button>
                </label>
            </div>
        </form>
    </body>
</html>
<script type="text/javascript">
    function check() {
        //tu check
        document.getElementsByName("f").action = "register";
        document.getElementsByName("f").submit();
    }
</script>

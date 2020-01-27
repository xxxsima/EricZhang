<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
<h1>欢迎进入主页</h1>

<input  type="button" value="user" onclick="window.location.href='/user/userUI'">
<br>

<br>



<input type="button" value="function" onclick="window.location.href='/function/functionUI'">
<br>

<br>



<input type="button" value="role" onclick="window.location.href='/role/roleUI'">
<br>

<br>


<input type="button" value="product" onclick="window.location.href='/product/productUI'">
<br>

<br>


<input type="button" value="systemLog" onclick="window.location.href='/systemLog/listUI'">
<br>

<br>

<input type="button" value="class" onclick="window.location.href='/class/listUI'">
<br>

<br>

<input type="button" value="student" onclick="window.location.href='/student/listUI1'">
<br>

<br>








<form action="${pageContext.request.contextPath}/user/login">
    <table>
        <tr>
            <td><input type="submit" value="退出登录"></td>
        </tr>
    </table>
</form>
</body>
</html>

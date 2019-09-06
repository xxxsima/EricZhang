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
<input type="button" value="teacher" onclick="window.location.href='/teacher/teacherUI'">

<form action="${pageContext.request.contextPath}/user/login">
    <table>
        <tr>
            <td><input type="submit" value="退出登录"></td>
        </tr>
    </table>
</form>
</body>
</html>

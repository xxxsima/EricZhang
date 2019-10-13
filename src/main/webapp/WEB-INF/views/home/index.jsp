
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%--<h2>Hello World!</h2>
<a href="user/select">user</a>--%>
<%@ include file="/WEB-INF/public/commons.jspf"%>
<body >

<form action="${pageContext.request.contextPath}/user/findUserName" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input id="username" name="username" type="text"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input id="password" name="password" type="password"></td>
        </tr>
        <tr >
            <td><input type="submit" value="登录" ></td>

        </tr>

    </table>
</form>
<td><button id="add" type="button" onclick="userAdd()">注册</button>

<%--
    <input type="button" value="注册" onclick="window.location.href=''"> </td>
--%>
</body>
<script>
    function userAdd(){
        ada.openPopup(window.location.href='/user/regist',
            function () {
                window.location.reload()
            },
            '800px','600px'
        )

    }
</script>
</html>

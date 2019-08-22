<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<html>
<head>
    <title>user</title>
</head>
<body style="margin: 1px">

    id:${user.id}<br/>
    email:${requestScope.user.email}<br/>
    username:${requestScope.user.username}<br/>
    role:${requestScope.user.role}<br/>
    mobile:${requestScope.user.mobile}<br/>
    password:${requestScope.user.password}<br/>

<input  type="button" value="返回上一层" onclick="window.history.back(-1)" >

</body>
</html>

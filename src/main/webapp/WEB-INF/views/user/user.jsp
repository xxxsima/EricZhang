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
    <%@ include file="/WEB-INF/public/commons.jspf" %>
</head>
<body >
<table id="dg" title="My Users" class="easyui-datagrid" style="width:550px;height:250px"
       url="get_users.php"
       toolbar="#toolbar"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="firstname" width="50">First Name</th>
        <th field="lastname" width="50">Last Name</th>
        <th field="phone" width="50">Phone</th>
        <th field="email" width="50">Email</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>


<%--
    id:${user.id}<br/>
    email:${requestScope.user.email}<br/>
    username:${requestScope.user.username}<br/>
    role:${requestScope.user.role}<br/>
    mobile:${requestScope.user.mobile}<br/>
    password:${requestScope.user.password}<br/>--%>

<input  type="button" value="返回上一层" onclick="window.history.back(-1)" >

</body>
</html>

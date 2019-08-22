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
<body class="easyui-layout">
<div id="searchToolbar">
    <div class="container-fluid">
        <div class="row mt-1 mb-1 ">
            <div class="col-3">
                <input id="roleFuzzyString" class="form-control" type="text"
                       placeholder="">
            </div>
            <div class="col-3">
                <button id="btnSearch" type="button" class="btn btn-primary" onclick="searchRoleList()"><i
                        class="fa fa-search"></i>&nbsp;<s:message code="search"
                        ></s:message></button>
            </div>
            <div class="col-6 text-right">
                <button id="roleAdd" type="button" class="btn btn-success " onclick="roleAdd()"><i
                        class="fa fa-plus"></i>&nbsp;<s:message
                     code="add"   ></s:message></button>
            </div>
        </div>
    </div>
</div>

<table id="roleTable">
</table>

<%--
    id:${user.id}<br/>
    email:${requestScope.user.email}<br/>
    username:${requestScope.user.username}<br/>
    role:${requestScope.user.role}<br/>
    mobile:${requestScope.user.mobile}<br/>
    password:${requestScope.user.password}<br/>--%>

<input  type="button" value="return" onclick="window.history.back(-1)" >

</body>
</html>

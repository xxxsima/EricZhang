<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/8/30
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/public/commons.jspf"%>
    <title>Save or Update</title>
    <style>
        .formLabel{
            float: right;
            font-weight: bold;
        }
        form{
            padding: 10px;
        }
    </style>
</head>
<body>
<form id="form" novalidate>
    <div id="app" v-once>
        <input type="hidden" name="id" v-model="id"></input>



        <div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="email"><s:message code="user.email"></s:message> </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="email" placeholder="<s:message code="user.email.placeholder"></s:message>"
                       name="email" v-model="email" required  maxlength="20">
                <div class="invalid-feedback">
                    <s:message code="user.email"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="username"><s:message code="user.name"></s:message> </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="username" placeholder="<s:message code="user.username.placeholder"></s:message>"
                       name="username" v-model="username" required  maxlength="20">
                <div class="invalid-feedback">
                    <s:message code="user.name"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="role"><s:message code="user.role"></s:message> </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="role" placeholder="<s:message code="user.role.placeholder"></s:message>"
                       name="role" v-model="role" required  maxlength="20">
                <div class="invalid-feedback">
                    <s:message code="user.role"></s:message>
                </div>
            </div>
        </div>


        <div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="mobile"><s:message code="user.mobile"></s:message> </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="mobile" placeholder="<s:message code="user.mobile.placeholder"></s:message>"
                       name="mobile" v-model="mobile" required  maxlength="20">
                <div class="invalid-feedback">
                    <s:message code="user.mobile"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="password"><s:message code="user.password"></s:message> </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="password" placeholder="<s:message code="user.password.placeholder"></s:message>"
                       name="password" v-model="password" required  maxlength="20">
                <div class="invalid-feedback">
                    <s:message code="user.password"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group mt-2">
            <div class="col-3 col-form-label">
                <label></label>
            </div>
            <div class="col-8 ">
                <button class="btn btn-success float-right " type="submit"><i class="fa fa-save"></i>&nbsp;<s:message
                        code="save"></s:message></button>
            </div>
        </div>
    </div>

    </div>
</form>
<script>
    "use strict"; // 使用严格模式
    $(document).ready(function(){
        window.app = {}; // 本页面所有需要共享的变量都作为app的属性，方便管理

        app.vm = new Vue({
            el: '#app',
            data: {
                email: "${u.email}",
                username: "${u.username}",
                role: "${u.role}",
                mobile:"${u.mobile}",
                password:"${u.password}",
                id:"${u.id}"
            }
        });

        <c:choose>
        <c:when test="${u.id == null}">
        app.isSave=true;
        app.url = "${pageContext.request.contextPath }/user/saveJson";
        </c:when>
        <c:otherwise>
        app.isSave = false;
        app.url = "${pageContext.request.contextPath }/user/updateJson";
        </c:otherwise>
        </c:choose>
        /*
                $("#createTime").datepicker({
                    dateFormat: 'yyyy-MM-dd HH:mm:ss'
                });*/
        ada.bindSubmit("form",app.url,app.isSave);

    });
</script>
</body>
</html>

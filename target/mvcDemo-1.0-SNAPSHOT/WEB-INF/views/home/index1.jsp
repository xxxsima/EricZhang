
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <%@page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

    <link href="${pageContext.request.contextPath }/css/adaLogin.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/js/layui/2.4.3/layui/css/layui.css"  rel="stylesheet"/>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/layui/2.4.3/layui/layui.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery/2.2.4/jquery-2.2.4.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/ada/ada.js"></script>
</head>

<body>
<%--<h2>Hello World!</h2>
<a href="user/select">user</a>--%>
<body >
<div class="loginWraper">
    <div class="header">
    </div>
    <div class="loginBox" style="text-align: center">
        <form class="layui-form">

            <div>
                <font color="white" size="5">
                    <s:message code="login"></s:message>
                </font>
            </div>
            <br>
            <br>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <i class="icon icon-yonghu"></i>
                </label>
                    <div class="layui-input-block" style="width: 65%;">
                        <input type="text" name="username" value="${username}"
                               placeholder="<s:message code="pleaseEnterTheLoginName"></s:message>" class="layui-input" lay-verify="username" autocomplete="off" lay-verType="tips" />
                        <label class="Validform_label"></label>
                    </div>
            </div>

            <br>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <i class="icon iconfont  icon-iconset0114"></i>
                </label>
                <div class="layui-input-block" style="width: 65%;">
                    <input type="password" name="password" lay-verType="tips" lay-verify="password" value="${password}"
                    placeholder="<s:message code="pleaseEnterTheLoginPassword"></s:message>" class="layui-input" />
                    <label class="Validform_label"></label>
                </div>
            </div>

            <br>
            <br>

            <div style="width: 98%;">
                <button type="reset" class="layui-btn layui-btn-primary">
                    <s:message code="reset"></s:message>
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button id="userAdd" type="button" class="btn btn-success " onclick="userAdd()"><i
                        class="fa fa-plus"></i>&nbsp;<s:message
                        code="register"></s:message>
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="loginForm" type="submit">
                    <s:message code="submit"></s:message>
                </button>
            </div>
            <br>
            <label class="Validform_label"><font>${errorMsg}</font></label>
        </form>
    </div>
    </div>
</body>
<script>
layui.use(['form'],function () {
    var form=layui.form
    //自定义验证规则
    form.verify({
        username:function (value) {
            if (value == undefined || value == "" ||value.length <1) {
                return '<s:message code="user.login.inputUserUsername"/>'
            }

        },
        password:function (value) {
            if (value ==undefined || value=="" || value.length <1) {
                return '<s:message code="user.login.inputPassword"/>'
            }
        }

    });

    //监听事件

    form.on('submit(loginForm)',function(data) {
        console.log(data);
        var loadIndex = layer.load(1);
    var  param =JSON.stringify(data.field);
    var  url= "${pageContext.request.contextPath}/user/findUserName";
        console.log(param);
        $.ajax({
    type:"POST",//方法类型
    contentType:"application/json",
    dataType:"json",
    url:url,
    data:param,
    success:function(resultData) {
    if (resultData.success==true) {
        layer.msg(resultData.message);
        window.location.href="${pageContext.request.contextPath}/user/home"
    } else
    {
        layer.msg(resultData.message);
    }
    layer.close(loadIndex);
    },
    error:function(resultData) {
    layer.msg("error");
    layer.close(loadIndex);
    }
    });
    return false;
    });
});

function userAdd() {
    ada.openPopup("<s:message code="register"/>","${pageContext.request.contextPath}/user/regist",
        function () {
            window.location.reload()
        },
        '800px','600px'
    )
}

</script>
</html>

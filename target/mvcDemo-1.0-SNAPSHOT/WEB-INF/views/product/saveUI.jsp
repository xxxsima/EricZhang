<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/11/30
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save or update</title>
    <%@ include file="/WEB-INF/public/commons.jspf"%>
    <style>
        .formLabel {
            float: right;
            font-weight: bold;
        }
        form {
            padding: 10px;
        }
    </style>
</head>
<body>
<form id="form" novalidate>
    <div id="app" v-once>
        <input type="hidden" name="id" v-model="id"/>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="productName">
                    <s:message code="productName"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="productName" placeholder="<s:message code="productName"/>"
                name="productName" v-model="productName" required maxlength="20" value="/">
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="pleaceOfProduction">
                    <s:message code="pleaceOfProduction"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="pleaceOfProduction" placeholder="<s:message code="pleaceOfProduction"/>"
                       name="pleaceOfProduction" v-model="pleaceOfProduction" required maxlength="20" value="/">
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="nutritive">
                    <s:message code="nutritive"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="nutritive" placeholder="<s:message code="nutritive"/>"
                       name="nutritive" v-model="nutritive" required maxlength="20" value="/">
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="logo">
                    <s:message code="logo"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="logo" placeholder="<s:message code="logo"/>"
                       name="logo" v-model="logo" required maxlength="20" value="/">
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group" >
            <div class="col-3 col-form-label">
            </div>
            <div class="col-8" >
                <button class="btn btn-success float-right " type="submit"><i class="fa fa-save"></i>&nbsp;<s:message code="save"></s:message> </button>
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
                id:"${p.id}",
                productName:"${p.productName}",
                pleaceOfProduction:"${p.pleaceOfProduction}",
                nutritive:"${p.nutritive}",
                logo:"${p.logo}"
            }
        });

        <c:choose>
        <c:when test="${p.id == null}">
        app.isSave = true;
        app.url = "${pageContext.request.contextPath }/product/saveJson";
        </c:when>
        <c:otherwise>
        app.isSave = false;
        app.url = "${pageContext.request.contextPath }/product/updateJson";
        </c:otherwise>
        </c:choose>
        ada.bindSubmit("form",app.url,app.isSave);

        //给必须项添加红点
        //普通文本框
        $("input[required = required]").parent(".col-8").after('<div class="col-1 p-2"><i style="color: red;">*</i></div>');
        //复选框
        $("input[required = required]").parent(".custom-radio").parent().parent(".col-8").after('<div class="col-1 p-2"><i style="color: red;">&nbsp;&nbsp;*</i></div>')

    });
</script>
</body>
</html>

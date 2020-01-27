<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/12/5
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save or update</title>
    <%@include file="/WEB-INF/public/commons.jspf"%>
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
            <label class="formLabel" for="className">
                <s:message code="className"/>
            </label>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" id="className" placeholder="<s:message code="className"/>" name="className"
            v-model="className" required maxlength="20" value=""/>
            <div class="invalid-feedback">
                <s:message code="maxActive"/>
            </div>
        </div>
    </div>

    <div class="input-group">
        <div class="col-3 col-form-label">
            <label class="formLabel" for="classTeacher">
                <s:message code="classTeacher"/>
            </label>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" id="classTeacher" placeholder="<s:message code="classTeacher"/>"
            name="classTeacher" v-model="classTeacher" required maxlength="20" value=""/>
            <div class="invalid-feedback">
                <s:message code="maxActive"/>
            </div>
        </div>
    </div>

    <div class="input-group">
        <div class="col-3 col-form-label">
            <label class="formLabel" for="chineseTeacher">
                <s:message code="chineseTeacher"/>
            </label>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" id="chineseTeacher" placeholder="<s:message code="chineseTeacher"/>"
            name="chineseTeacher" v-model="chineseTeacher" required maxlength="20" value=""/>
            <div class="invalid-feedback">
                <s:message code="maxActive"/>
            </div>
        </div>
    </div>

    <div class="input-group">
        <div class="col-3 col-form-label">
            <label class="formLabel" for="mathTeacher">
                <s:message code="mathTeacher"/>
            </label>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" id="mathTeacher" placeholder="<s:message code="mathTeacher"/>"
            name="mathTeacher" v-model="mathTeacher" required maxlength="20" value=""/>
            <div class="invalid-feedback">
                <s:message code="maxActive"/>
            </div>
        </div>
    </div>

    <div class="input-group">
        <div class="col-3 col-form-label">
            <label class="formLabel" for="englishTeacher">
                <s:message code="englishTeacher"/>
            </label>
        </div>
        <div class="col-8">
            <input type="text" class="form-control" id="englishTeacher" placeholder="<s:message code="englishTeacher"/>"
            name="englishTeacher" v-model="englishTeacher" required maxlength="20" value=""/>
            <div class="invalid-feedback">
                <s:message code="maxActive"/>
            </div>
        </div>
    </div>


<div class="input-group">
    <div class="col-3 col-form-label">

    </div>
    <div class="col-8">
        <button class="btn btn-success float-right" type="submit">
            <i class="fa fa-save"></i>&nbsp;<s:message code="save"></s:message>
        </button>
    </div>
</div>
</div>
</form>

<script>
    "use strict";
    $(document).ready(function () {
        window.app= {};

        app.vm =new Vue({
            el:'#app',
            data:{
                id:"${c.id}",
                className:"${c.className}",
                classTeacher:"${c.classTeacher}",
                chineseTeacher:"${c.chineseTeacher}",
                mathTeacher:"${c.mathTeacher}",
                englishTeacher:"${c.englishTeacher}"
            }
        });

        <c:choose>
        <c:when test="${c.id == null}">
        app.isSave =true;
        app.url = "${pageContext.request.contextPath}/class/saveJson";
        </c:when>
        <c:otherwise>
        app.isSave =false;
        app.url = "${pageContext.request.contextPath}/class/updateJson";
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

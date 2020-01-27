<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/11/29
  Time: 14:07
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
                <label class="formLabel" for="code">
                    <s:message code="code"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="code" placeholder="<s:message code="code"/>"
                name ="code" v-model="code" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"></s:message>
                </div>
            </div>
        </div>


        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="name">
                    <s:message code="name"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="name" placeholder="<s:message code="name"/>"
                name="name" v-model="name" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="remark">
                    <s:message code="remark"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="remark" placeholder="<s:message code="remark"/>"
                       name="remark" v-model="remark" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"></s:message>
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
                id:"${r.id}",
                code:"${r.code}",
                name:"${r.name}",
                remark:"${r.remark}"
            }
        });

        <c:choose>
        <c:when test="${r.id == null}">
        app.isSave = true;
        app.url = "${pageContext.request.contextPath }/role/saveJson";
        </c:when>
        <c:otherwise>
        app.isSave = false;
        app.url = "${pageContext.request.contextPath }/role/updateJson";
        </c:otherwise>
        </c:choose>
        ada.bindSubmit("form",app.url,app.isSave);

        //给必须项添加红点
        //普通文本框
        $("input[required = required]").parent(".col-8").after('<div class="col-1 p-2"><i style="color: #2e55ff;">*</i></div>');
        //复选框
        $("input[required = required]").parent(".custom-radio").parent().parent(".col-8").after('<div class="col-1 p-2"><i style="color: red;">&nbsp;&nbsp;*</i></div>')

    });
</script>

</body>
</html>

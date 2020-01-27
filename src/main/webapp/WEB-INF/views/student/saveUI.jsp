<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/12/11
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save or  update</title>
    <%@include file="/WEB-INF/public/commons.jspf"%>
    <style>
        formLabel {
            float: right;
            font-weight: bold;
        }
        form {
            padding:10px;
        }
    </style>
</head>
<body>
<form id="form" novalidate>
    <div id="app" v-once>
        <input type="hidden" name="id" v-model="id"/>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="studentName">
                    <s:message code="studentName"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="studentName" placeholder="<s:message code="studentName"/>" name="studentName"
                 v-model="studentName" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel">
                    <s:message code="sex"></s:message>
                </label>
            </div>

            <div class="col-8 form-row">
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio" class="custom-control-input" id="man"
                               name="sex" value="1" v-model="sex" />
                        <label class="custom-control-label" for="man">男</label>
                    </div>
                </div>
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio" class="custom-control-input" id="woman"
                               name="sex" value="0" v-model="sex"/>
                        <label class="custom-control-label" for="woman">女</label>
                    </div>
                </div>
            </div>

        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="birthday">
                    <s:message code="birthday"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="birthday" placeholder="<s:message code="birthday"/>" name="birthday"
                v-model="birthday" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="telephone">
                    <s:message code="telephone"/>
                </label>
            </div>
                        <div class="col-8">
                        <input type="text" class="form-control" id="telephone" placeholder="<s:message code="telephone"/>" name="telephone"
                        v-model="telephone" required maxlength="20" value=""/>
                            <div class="invalid-feedback">
                                <s:message code="maxActive"/>
                            </div>
                        </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="wchar">
                    <s:message code="wchar"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="wchar" placeholder="<s:message code="wchar"/>" name="wchar"
                v-model="wchar" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="homeAddress">
                    <s:message code="homeAddress"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="homeAddress" placeholder="<s:message code="homeAddress"/>" name="homeAddress"
                v-model="homeAddress" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="fatherName">
                    <s:message code="fatherName"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="fatherName" placeholder="<s:message code="fatherName"/>" name="fatherName"
                v-model="fatherName" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="telephone1">
                    <s:message code="telephone1"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="telephone1" placeholder="<s:message code="telephone1"/>" name="telephone1"
                v-model="telephone1" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="grade">
                    <s:message code="grade"/>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="grade" placeholder="<s:message code="grade"/>" name="grade"
                v-model="grade" required maxlength="20" value=""/>
                <div class="invalid-feedback">
                    <s:message code="maxActive"/>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label"></div>
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
        window.app={};
        app.vm=new Vue({
            el:'#app',
            data:{
                id:"${s.id}",
                classId:"${s.classId}",
                studentName:"${s.studentName}",
                sex:"${s.sex}",
                birthday:"<fmt:formatDate value="${s.birthday}" pattern="yyyy-MM-dd"/>",
                telephone:"${s.telephone}",
                wchar:"${s.wchar}",
                homeAddress:"${s.homeAddress}",
                fatherName:"${s.fatherName}",
                telephone1:"${s.telephone1}",
                grade:"${s.grade}"
            }
        });
        $("#birthday").datepicker({
            dateFormat:'yy-mm-dd'
        });
        <c:choose>
        <c:when test="${s.id== null}">
        app.isSave=true;
        app.url="${pageContext.request.contextPath}/student/saveJson";
        </c:when>
        <c:otherwise>
        app.isSave=false;
        app.url="${pageContext.request.contextPath}/student/updateJson";
        </c:otherwise>
        </c:choose>
        ada.bindSubmit("form",app.url,app.isSave);
        $("input[required =required]").parent(".col-8").after('<div class="col-1 p-2"> <i style="color:red;">*</i> </div>');
        $("input[required = required]").parent(".custom-radio").parent().parent(".col-8").after('<div class="col-1 p-2"><i style="color: red;">&nbsp;&nbsp;*</i></div>')

    });
</script>
</body>
</html>

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

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="username"><s:message code="user.username"></s:message></label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="username" placeholder="<s:message code="user.username.placeholder"></s:message>" name="username"
                       v-model="username" required maxlength="20"/>
                <div class="invalid-feedback">
                    <s:message code="user.username"></s:message>
                </div>
            </div>
        </div>




        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="password"><s:message code="user.password"></s:message></label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="password" placeholder="<s:message code="user.password.placeholder"></s:message>" name="password"
                       v-model="password" required maxlength="20"/>
                <div class="invalid-feedback">
                    <s:message code="user.password"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="name"><s:message code="user.name"></s:message></label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="name" placeholder="<s:message code="user.name.placeholder"></s:message>" name="name"
                       v-model="name" required maxlength="20"/>
                <div class="invalid-feedback">
                    <s:message code="user.name"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel">
                    <s:message code="user.sex"></s:message>
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
                    <s:message code="user.birthday"></s:message>
                </label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="birthday" placeholder="yyyy-MM-dd"
                name="birthday" v-model="birthday" required pattern="\d{4}-\d{2}-\d{2}" />
                <div class="invalid-feedback">
                    <s:message code="user.birthday"></s:message>
                </div>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="hobbies">
                    <s:message code="user.hobbies"></s:message>
                </label>
            </div>
            <div class="col-8">
                <select class="form-control" id="hobbies" name="hobbies" v-model="hobbies">
                    <option value="" selected>请选择</option>
                    <option value="唱歌">唱歌</option>
                    <option value="跳舞">跳舞</option>
                    <option value="跑步">跑步</option>
                    <option value="游泳">游泳</option>
                    <option value="旅行">旅行</option>
                </select>
            </div>
        </div>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel">
                    <s:message code="user.valid"></s:message>
                </label>
            </div>
            <div class="col-8 form-row">
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio" class="custom-control-input" id="start"
                        name="valid" value="1" v-model="valid"/>
                        <label class="custom-control-label" for="start">启用</label>
                    </div>
                </div>
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio" class="custom-control-input" id="disable"
                        name="valid" value="0" v-model="valid"/>
                        <label class="custom-control-label" for="disable">禁用</label>
                    </div>
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
                username:"${u.username}",
                password:"${u.password}",
                name:"${u.name}",
                sex:"${u.sex}",
                birthday:"<fmt:formatDate value='${u.birthday}' pattern='yyyy-MM-dd' />",
                hobbies:"${u.hobbies}",
                id:"${u.id}",
                valid:"${u.valid}"
            }
        });
        $("#birthday").datepicker({
            dateFormat:'yy-mm-dd'
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

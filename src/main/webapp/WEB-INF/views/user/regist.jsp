<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/9/14
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%@include file="/WEB-INF/public/commons.jspf"%>

    <style>
        .formLabel{
            float: right;
            font-weight: bold;
        }
        form{
            padding: 10px;
        }
    /*    .filter-option-inner-inner{
            height: 40px;
            !*line-height:normal;*!
        }*/
    </style>
</head>
<body>
<form id="form" novalidate>
    <div id="app" v-once>
        <input type="hidden" name="id" v-model="id"/>

        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="username"><s:message code="user.username"></s:message></label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="username" placeholder="<s:message code="user.username.placeholder"></s:message>" name="username"
                v-model="username" required maxlength="20" onBlur="checkUsername(this)"/>
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
                <label class="formLabel"><s:message code="user.sex"></s:message>
                </label>
            </div>
          <div class="col-8 form-row">
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio"  class="custom-control-input" id="man"
                               name="sex" value="1" v-model="sex" required>
                        <label class="custom-control-label" for="man">男</label>
                    </div>
                </div>
                <div class="col-auto my-1">
                    <div class="custom-control custom-radio mr-2">
                        <input type="radio" class="custom-control-input" id="woman"
                               name="sex" value="0" v-model="sex" required>
                        <label class="custom-control-label" for="woman">女</label>
                    </div>
                </div>
            </div>
         </div>
        <div class="input-group">
            <div class="col-3 col-form-label">
                <label class="formLabel" for="birthday"><s:message code="user.birthday"></s:message></label>
            </div>
            <div class="col-8">
                <input type="text" class="form-control" id="birthday" placeholder="yyyy-MM-dd"
                       name="birthday" v-model="birthday" required pattern="\d{4}-\d{2}-\d{2}">
                <div class="invalid-feedback">
                    <s:message code="user.birthday"></s:message>

                </div>
            </div>
        </div>

        <%--<div class="input-group" >
            <div class="col-3 col-form-label">
                <label class="formLabel" for="interest"><s:message
                        code="user.hobbies"></s:message></label>
            </div>
            <div class="col-8" >
                <select class="form-control selectpicker" id="interest" name="interest"
                &lt;%&ndash;v-model="hobbies"&ndash;%&gt; multiple >
                    <option value="" selected>请选择</option>
                    <option value="唱歌">唱歌</option>
                    <option value="跳舞">跳舞</option>
                    <option value="跑步">跑步</option>
                    <option value="游泳">游泳</option>
                    <option value="旅行">旅行</option>
                </select>
            </div>
        </div>--%>
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
                        name="valid" value="1" v-model="valid" />
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
    <div class="input-group mt-2">
        <div class="col-3 col-form-label">
            <label></label>
        </div>
        <div class="col-6 text-left">
            <button class=" btn btn-success " type="button"><a href="/user/login" class="fa fa-plus" <%--data-options="iconCls:'icon-search'"--%>></a>&nbsp;<s:message
                    code="back"></s:message></button>
        </div>
    </div>
    </div>
    </div>
</form>

<script>
    "use strict";//使用严格模式
    $(document).ready(function () {
        window.app={};
        app.vm =new Vue({
            el:'#app',
            data:{
                username:"${username}",
                password:"${password}",
                name:"${name}",
                sex:"${sex}",
                birthday:"${birthday}",
                hobbies:"${hobbies}",
                id:"${id}",
                valid:"${valid}"
            }
        });
        $("#birthday").datepicker({
            dateFormat: 'yy-mm-dd'
        });
        // $("#hobbies").selectpicker();

        app.url="${pageContext.request.contextPath}/user/saveJson";
        ada.bindSubmit("form",app.url);
    })
    
    
    function checkUsername(username) {

        console.log($(username).val());


        $.ajax({
            url: "/user/checkJson",
            data: {"username":$(username).val()},
            success:  function (resultData) {
                            layer.msg(resultData.message)

            }
        });

    }
</script>
</body>
</html>

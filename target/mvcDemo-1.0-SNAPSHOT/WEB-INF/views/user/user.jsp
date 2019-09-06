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
    <title>用户管理</title>
    <%@ include file="/WEB-INF/public/commons.jspf" %>
</head>
<body class="easyui-layout">
<table id="dg"></table>

<div id="searchToolbar">
    <div class="container-fluid">
        <div class="row mt-1 mb-1 ">
            <div class="col-3">
                <input id="userFuzzyString" class="form-control" type="text"
                       placeholder="<s:message code="user.placeholder.userFuzzyString"/>">
            </div>
            <div class="col-3">
                <button id="btnSearch" type="button" class="btn btn-primary" onclick="searchUserList()"><i
                        class="fa fa-search"></i>&nbsp;<s:message
                        code="search"></s:message></button>
            </div>
            <div class="col-6 text-right">
                <button id="userAdd" type="button" class="btn btn-success " onclick="userAdd()"><i
                        class="fa fa-plus"></i>&nbsp;<s:message
                        code="add"></s:message></button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).keyup(function (event) {
        if (event.keyCode ==13) {
            searchUserList();
        }
    });

   $(function () {
       loadDataGrid();
       searchUserList();
   })


    $('.delete-row').on('click', function () {
        $.messager.confirm({
            title: '提示信息',
            ok: '确定',
            icon: 'error',
            cancel: '取消',
            msg: '你确定要删除选中记录？',
            fn: function (r) {
                if (r) {
                    console.log('YES');
                } else {
                    console.log('NO');
                }
            }
        });
        $('.messager-window').find('.l-btn-small').eq(0).addClass('l-btn-selected');
        $('.messager-window').find('.messager-icon').removeClass('messager-question').addClass('messager-error');
    })
    function loadDataGrid () {
        $('#dg').datagrid({
            // height: ($(window).height()-15),
            // width: ($(window).width()-15),
            fitColumns: true,//宽度自适应
            fit: true,
            //    title: '角色列表',
            rownumbers: true,
            singleSelect: true, //只能选择一行
            // autoRowHeight: true,
            pagination: true,
            striped: true, //隔行换色
            checkOnSelect: true,
            selectOnCheck: true,
            // collapsible: true, //是否可以收缩
            toolbar: '#searchToolbar',
            // pageSize:2,
            pagination: true, //底部分页控件
            // url: '/user/listJson', //分页地址,加载表格时候不请求数据.URL 后续放入
            method: 'post', //请求类型
            // fixed:true,
            // sort:{ // SQL 进行排序, 特殊情况使用页面排序
            // sortName: 'id',//排序字段
            // sortOrder: 'desc',//asc desc
            // },
            queryParams: {  //参数 ,参数是静态的,动态需要手动设置
                fuzzy: $("#fuzzyString").val()
            },

            frozenColumns: [[ //固定列
                {width: 0, align: 'center', resizable: true, field: 'id', title: 'ID'},
            ]],
            columns: [[
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'email',
                    title: '<s:message code="user.email"></s:message>'
                },
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'username',
                    title: '<s:message code="user.name"></s:message>'
                },
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'role',
                    title: '<s:message code="user.role"></s:message>'
                },
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'mobile',
                    title: '<s:message code="user.mobile"></s:message>'
                },
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'password',
                    title: '<s:message code="user.password"></s:message>'
                },
                {
                    width: 1,
                    align: 'center',
                    resizable: true,
                    field: 'option',
                    title: '<s:message code="option"></s:message>',
                    align: 'center',
                    formatter:actionFormatter

                }
            ]],
            loadMsg: "数据获取中,请稍等...",//提示

        })
    }

    $(".more").click(function () {
        $(this).closest(".conditions").siblings().toggleClass("hide");
    });

    function searchUserList() {
        var params = {};
        params.fuzzy = $('#fuzzyString').val();
        $('#dg').datagrid("options").queryParams = params;
        $('#dg').datagrid("options").url='${pageContext.request.contextPath}/user/select';
        $("#dg").datagrid("load")
    }

    function userUpdate(userId){
        ada.openPopup("修改","${pageContext.request.contextPath}/user/updateUI?id="+userId,
        function () {
            window.location.reload()
        },
        '800px','600px');
         }
    function userDelete(userId){


        ada.ajaxDelete("${pageContext.request.contextPath}/user/deleteJson?id="+userId,
            function () {
                searchUserList();
            }
        );
    }
    function reload(){
        $("#dg").datagrid("reload");
    }
    function userAdd(){
        ada.openPopup("<s:message code="add"/>","${pageContext.request.contextPath}/user/addUI",
        function () {
            window.location.reload()
        },
            '800px','600px'
        )

    }
    function actionFormatter(value, row, index) {

        var action = "";
        action += '<a href="#"><i class="fa fa-edit" style="cursor:pointer;" onclick="userUpdate(' + row.id + ')" title="修改"></i></a> ';
        action += '&nbsp;&nbsp;&nbsp;<a href="#"><i class="fa fa-trash-o" style="cursor:pointer;" onclick="userDelete(' + row.id + ')" title="删除"></i></a> ';

        return action;
    }

</script>

<%--
    id:${user.id}<br/>
    email:${requestScope.user.email}<br/>
    username:${requestScope.user.username}<br/>
    role:${requestScope.user.role}<br/>
    mobile:${requestScope.user.mobile}<br/>
    password:${requestScope.user.password}<br/>--%>

<input  type="button" value="return" onclick="window.history.back(-1)">


</body>
</html>

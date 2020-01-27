<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2019/9/6
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单管理</title>
    <%@include file="/WEB-INF/public/commons.jspf"%>
</head>
<body class="easyui-layout">
<table id="dg"></table>
<div id="searchToolbar">
    <div class="container-fluid">
        <div class="row mt-1 mb-1">
            <div class="col-3">
                <input id="functionFuzzyString" class="form-control" type="text"
                placeholder="<s:message code="user.placeholder.userFuzzyString"/>">
            </div>
            <div class="col-3">
                <button id="btnSearch" type="button" class="btn btn-primary" onclick="searchFunctionList()">
                    <i class="fa fa-search"></i>
                    &nbsp;
                    <s:message code="search">
                    </s:message>
                </button>
            </div>
            <div class="col-6 text-right">
                <button id="functionAdd" type="button" class="btn btn-success" onclick="functionAdd()">
                    <i class="fa fa-plus"></i>
                    &nbsp;
                    <s:message code="add"></s:message>
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).keyup(function (event) {
        if (event.keyCode==13) {
            searchFunctionList();
        }
    });
    $(function () {
        loadDataGrid();
        searchFunctionList();
    })
    $('.delete-row').on('click',function () {
        $.message.confirm({
            title:'提示信息',
            ok:'确定',
            icon:'error',
            cancel:'取消',
            msg:'您确定要删除选中记录?',
            fn:function (r) {
                if (r){
                    console.log('YES');
                } else
                {
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
            frozenColumns:[[
                {width:0,align:'center',resizable:true,field:'Id',title:'ID'},
            ]],
            columns:[[
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'code',
                    title:'<s:message code="function.code"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'name',
                    title:'<s:message code="function.name"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'displayName',
                    title:'<s:message code="function.displayNames"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'position',
                    title:'<s:message code="function.position"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'ico',
                    title:'<s:message code="function.icons"></s:message>'
                },
                {
                    width:1,
                    align:'center',

                    resizable: true,
                    field:'platform',
                    title:'<s:message code="function.platforms"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'remark',
                    title:'<s:message code="function.remark"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'runConfig',
                    title:'<s:message code="function.runConfigs"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'parentId',
                    title:'<s:message code="function.parentId"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'icon',
                    title:'<s:message code="function.icons"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    resizable: true,
                    field:'readOnly',
                    title:'<s:message code="function.readOnly"></s:message>'
                },
                {
                    width:1,
                    align:'center',
                    field:'option',
                    title:'<s:message code="option"></s:message>',
                    resizable:true,
                    formatter:actionFormatter
                }


            ]],loadMsg:"数据获取中,请稍等......",
        })
    }

    $(".more").click(function () {
        $(this).closest(".conditions").siblings().toggleClass("hide");
    });
    function searchFunctionList() {
        var params={};
        params.fuzzy=$('#functionFuzzyString').val();
        $('#dg').datagrid("options").queryParams =params;
        $('#dg').datagrid("options").url='${pageContext.request.contextPath}/function/functionList';
        $('#dg').datagrid("load");
    }

    function functionUpdate(functionId) {
        ada.openPopup("修改","${pageContext.request.contextPath}/function/updateUI?id="+functionId,
        function () {
            window.location.reload()
        },
            '800px','600px'
        );
    }

    function functionDelete(id) {
        ada.ajaxDelete("${pageContext.request.contextPath}/function/deleteJson?id="+id,
        function () {
            window.location.reload()
        });
    }

    function reload() {
        $("#dg").datagrid("reload");
    }
    function functionAdd() {
        ada.openPopup("<s:message code="add"/>","${pageContext.request.contextPath}/function/addUI",
        function() {
            window.location.reload()
        },
            '800px','600px'
        )
    }





    function actionFormatter(value, row, index) {
        //var valid=${row.valid};
        //console.log(valid);
        var action = "";
        action += '<a href="#"><i class="fa fa-edit" style="cursor:pointer;" onclick="functionUpdate(' + row.id + ')" title="修改"></i></a> ';
        action += '&nbsp;&nbsp;&nbsp;<a href="#"><i class="fa fa-trash-o" style="cursor:pointer;" onclick="functionDelete(' + row.id + ')" title="删除"></i></a> ';


        return action;
    }
</script>
</body>
</html>

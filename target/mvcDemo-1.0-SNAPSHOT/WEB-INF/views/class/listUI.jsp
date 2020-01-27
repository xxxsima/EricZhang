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
    <title>班级列表</title>
    <%@ include file="/WEB-INF/public/commons.jspf"%>
</head>
<body>
<table id="dg"></table>
<div id="searchToolbar">
    <div class="container-fluid">
         <div class="row mt-1 mb-1">
             <div class="col-3">
                 <input id="fuzzy" class="form-control form-control" type="text" placeholder="<s:message code="user.placeholder.userFuzzyString"/>"/>
             </div>
             <div class="col-3">
                 <button id="btnSearch" type="button" class="btn btn-primary btn">
                     <i class="fa fa-search"></i>&nbsp;<s:message code="search"/>
                 </button>
             </div>
             <div class="col-6 text-right">
                 <button id="btnSave" type="button" class="btn btn-success">
                     <i class="fa fa-plus"></i>&nbsp;<s:message code="add"/>
                 </button>
             </div>
         </div>
    </div>
</div>
<div class="modal" tabindex="-1" role="dialog" id="widgetDeleteConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><s:message code="dataPermission.pleaseConfirm"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
        
        <div class="modal-body">
            <p> <s:message code="dataPermission.confirmDelete"/></p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="widgetConfirmDelete">
                <s:message code="dataPermission.confirm"/>
            </button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                <s:message code="dataPermission.cancel"/>
            </button>
        </div></div>
    </div>
</div>

<script>
    "use strict"
    $(document).ready(function () {
        window.app={};
        initDataGrid();
        console.dir('加载页面');
        $('#btnSearch').click(search);
        $('#fuzzy').keyup(function (event) {
            if (event.keyCode==13) {
                search();
            }
        });
        $("#btnSave").click(save);
        $("#widgetConfirmDelete").click(confirmDelete);
    });
    function search() {
        console.dir('点击搜索');
        $("#dg").datagrid("options").queryParams.fuzzy=$('#fuzzy').val();
        $("#dg").datagrid("options").url='${pageContext.request.contextPath}/class/listJson',
            $("#dg").datagrid("load");
    }
    function save() {
        var url="${pageContext.request.contextPath}/class/addUI";
        ada.openPopup("<s:message code="add"/>",url,
        function () {
            $("#dg").datagrid("reload");
        },
        '800px','500px')
    };
        function update(id) {
            var url="${pageContext.request.contextPath}/class/updateUI?id=";
            ada.openPopup("<s:message code="update"/>",url+id,
            function () {
                $("#dg").datagrid("reload");
            });
        }
        function delete1(id) {
           app.currenId=id;
            $('#widgetDeleteConfirm').modal('show');
        }
        function confirmDelete() {
            $('#widgetDeleteConfirm').modal('hide');
            ada.ajaxDelete("${pageContext.request.contextPath}/class/deleteJson?id="+app.currenId,
                function () {
                    $("#dg").datagrid("reload");
                });



        }
        function initDataGrid() {
            $('#dg').datagrid(new ada.DataGridConfig({
                toolbar:'#searchToolbar',
                queryParams:{
                    fuzzy:$('#fuzzy').val(),
                },
                url:'${pageContext.request.contextPath}/class/listJson',
                frozenColumns:[[]],
                columns:[[
                    {
                        field:'className',
                        title:"<s:message code="className"/>",
                        align:'center',
                        width:100,
                        formatter:function (value,row) {
                            return'<a href="#" class="btn" onclick="studentSee('+row.id+')" title="<s:message code="see"/>">'+value+'</a>';
                        }
                    },
                    {
                        field:'classTeacher',
                        title:"<s:message code="classTeacher"/>",
                        align:'center',
                        width:100
                    },
                    {
                        field:'chineseTeacher',
                        title:"<s:message code="chineseTeacher"/>",
                        align:'center',
                        width:100
                    },
                    {
                        field:'mathTeacher',
                        title:"<s:message code="mathTeacher"/>",
                        align:'center',
                        width:100
                    },
                    {
                        field:'englishTeacher',
                        title:"<s:message code="englishTeacher"/>",
                        align:'center',
                        width:100
                    },
                    {
                        field:'action',
                        title:"<s:message code="option"/>",
                        align:'center',
                        width:100,
                        formatter:actionFormatter
                    }
                ]],
            }));
        }
        function studentSee(id) {
            ada.FullShow("student","${pageContext.request.contextPath}/student/listUI?id="+id);
        }
        
        function actionFormatter(value,row,index) {
            var  action=" ";
            action += '<a href="#"><i class="fa fa-edit" style="cursor:pointer;" onclick="update('  +  row.id +')" title="<s:message code="update"/>"></i></a>';
            action += '&nbsp;&nbsp;&nbsp;<a href="#"><i class="fa fa-trash-o" style="cursor:pointer;"onclick="delete1('+row.id+') "title="<s:message code="delete"/>"></i></a>';
            return action;


        }
</script>
</body>
</html>

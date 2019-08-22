var ada = {};
//新增
ada.Add = function(url,w,h){
    layerShow('Add', url,w,h);
};
//编辑
ada.Edit = function(url,w,h){
    layerShow('Edit', url,w,h);
};


//编辑
ada.open = function(url,title,w,h){
    layerShow(title, url,w,h);
};
//删除
ada.DeleteGridRow = function (gridId,url) {
    $.messager.confirm({
        title: '提示信息',
        ok: '确定',
        icon: 'error',
        cancel: '取消',
        msg: '你确定要删除选中的记录？',
        fn: function (r) {
            if (r) {
                //删除服务器.数据
                // layer.msg("删除中...");
                //返回成删除列表行
                //如果不是每次根据选中的行获取index 会导致页面删除失败
                var row = $("#"+gridId).datagrid('getSelected');
                var index = $("#"+gridId).datagrid('getRowIndex',row);
                $.ajax({
                    type: "POST", //方法类型
                    // contentType:"application/json", //请求内容类型
                    dataType: "json", //预期服务器返回的数据类型
                    url: url,
                    data: {"id":row.id},
                    success: function (resultData) {
                        if (resultData.success == true) {
                            // console.dir(row);
                            if(row!==null){
                                $("#"+gridId).datagrid('deleteRow',index);
                            }
                            ada.msg(resultData.message);
                        } else {
                            ada.msg( resultData.message);
                        }
                    },
                    error:function(resultData){
                        ada.msg("发生异常" + resultData.message);
                    }

                });


            } else {
                ada.msg("取消");
            }
        }
    });
};

//删除明细
ada.DeleteGridDetailRow = function (gridId,boxIndex,url) {
    $.messager.confirm({
        title: '提示信息',
        ok: '确定',
        icon: 'error',
        cancel: '取消',
        msg: '你确定要删除选中的明细？',
        fn: function (r) {
            if (r) {
                //删除服务器.数据
                // layer.msg("删除中...");
                //返回成删除列表行
                //如果不是每次根据选中的行获取index 会导致页面删除失败
                var dataGridTmp =$("#"+gridId).datagrid('getRowDetail', boxIndex).find('div.detailGrid');
                var row = dataGridTmp.datagrid('getSelected');
                var index = dataGridTmp.datagrid('getRowIndex',row);
                $.ajax({
                    type: "POST", //方法类型
                    // contentType:"application/json", //请求内容类型
                    dataType: "json", //预期服务器返回的数据类型
                    url: url,
                    data: {"id":row.id},
                    success: function (resultData) {
                        if (resultData.success == true) {
                            // console.dir(row);
                            if(row!==null){
                                dataGridTmp.datagrid('deleteRow',index);
                            }
                            ada.msg(resultData.message);
                        } else {
                            ada.msg( resultData.message);
                        }
                    },
                    error:function(resultData){
                        ada.msg("发生异常" + resultData.message);
                    }

                });


            } else {
                ada.msg("取消");
            }
        }
    });
};
ada.FullShow = function(title,url){
    if (url == null || url == '') {
        url="404.html";
    };
    w = $(window).width() - 50;
    h=($(window).height() - 50);
    var index = layer.open({
        type: 2,
        title: title,
        fix: false,
        shadeClose: true,
        maxmin: true,
        area: [w + 'px',h + 'px'],
        content: url,
    });
    layer.full(index);
};


//msg不自动关闭
ada.layerMsg= function (msg){
    layer.msg(msg, {
        time: 0 //不自动关闭
        ,btn: ['确定']
        ,yes: function(index){
            layer.close(index);
        }
    });
}
//msg 会自动关闭
ada.msg= function (msg){
    $.messager.show({
        title:'提醒',

        msg:msg,
    });
}
/* 弹出层 */
/*
 * 参数解释： title 标题 url 请求的url id 需要操作的数据id w 弹出层宽度（缺省调默认值） h 弹出层高度（缺省调默认值）
 */
function layerShow(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "404.html";
    }
    ;
    if (w == null || w == '') {
        w = ($(window).width() * 0.7);
    }
    ;
    if (h == null || h == '') {
        h = $(window).height() *0.7;
    }
    ;
    layer.open({
        type : 2,
        area : [ w + 'px', h + 'px' ],
        fix : false, // 不固定
        maxmin : true,
        shade : 0.3,
        title : title,
        content : url
    });
}
/* 关闭弹出框口 延迟 */
ada.closeLayerDelay=function () {

    setTimeout("ada.closeLayerSpeed()",800); //延迟关闭窗口

}
ada.closeLayerSpeed=function(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
//千分位
ada.formatThousands=  function  (num) {
    var reg=/\d{1,3}(?=(\d{3})+$)/g;
    return (num + '').replace(reg, '$&,');
}





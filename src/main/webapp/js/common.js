"use strict"; // 使用严格模式

toastr.options = {
    "closeButton": true, // 是否显示关闭按钮，（提示框右上角关闭按钮）
    "debug": false, // 是否使用deBug模式
    "newestOnTop": true,
    "progressBar": false, // 是否显示进度条，（设置关闭的超时时间进度条）
    "positionClass": "toast-bottom-right", // 设置提示框显示的位置
    "preventDuplicates": false,
    "onclick": null, // 点击消息框自定义事件
    "showDuration": "300", // 显示动画的时间
    "hideDuration": "1000", // 消失的动画时间
    "timeOut": 0, //  自动关闭超时时间, 0，则不会自动关闭
    "extendedTimeOut": "1000", //  加长展示时间
    "showEasing": "swing", //  显示时的动画缓冲方式
    "hideEasing": "linear", //   消失时的动画缓冲方式
    "showMethod": "fadeIn", //   显示时的动画方式
    "hideMethod": "fadeOut", //   消失时的动画方式
    //"tapToDismiss": true
};

var ada = {};
// easyUI的datagrid的默认选项
ada.DataGridConfig = function (config) {
    for (var i in config) {
        if (i != undefined) {
            this[i] = config[i];
        }
    }
}

ada.DataGridConfig.prototype.fitColumns = true;
ada.DataGridConfig.prototype.fit = true;
ada.DataGridConfig.prototype.autoRowHeight = false;
ada.DataGridConfig.prototype.nowrap = false;
ada.DataGridConfig.prototype.striped = true;
ada.DataGridConfig.prototype.singleSelect = true;
ada.DataGridConfig.prototype.idField = 'id';
ada.DataGridConfig.prototype.method = 'post';
ada.DataGridConfig.prototype.pagination = true;
ada.DataGridConfig.prototype.pageNumber = 1;
ada.DataGridConfig.prototype.pageSize = 10;
ada.DataGridConfig.prototype.pageList = [10, 20, 50, 100];
ada.DataGridConfig.prototype.rownumbers = true;
ada.DataGridConfig.prototype.rownumberWidth = 30;
ada.DataGridConfig.prototype.loadMsg = "loading...";
ada.DataGridConfig.prototype.emptyMsg = "<br><br><h3 class='text-center' style='color:rgb(0,159,227);'>No Data</h3>";
ada.DataGridConfig.prototype.border=false;
ada.DataGridConfig.prototype.displayMsg = "";

// Date增加format方法
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};

// $(XXX)增加serializeJson方法，主要用于表单数据转JSON对象
(function ($) {
    $.fn.serializeJson = function (multiToString) {
        var serializeObj = {};
        $(this.serializeArray()).each(function () {
            if(serializeObj[this.name] != undefined){
                if(multiToString == undefined || multiToString == false) {
                    if (Array.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        var tmpValue = serializeObj[this.name];
                        serializeObj[this.name] = [];
                        serializeObj[this.name].push(tmpValue);
                        serializeObj[this.name].push(this.value);
                    }
                }else{
                    serializeObj[this.name] = serializeObj[this.name] + "," + this.value;
                }
            }else{
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

(function ($) {
    // arrayFields 为需要按数组处理的字段清单,格式如:["field1","field2"..]
    $.fn.serializeJsonWithArrayFields = function (arrayFields) {
        var serializeObj = {};
        $(this.serializeArray()).each(function () {
            if(serializeObj[this.name] != undefined){
                if (Array.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    var tmpValue = serializeObj[this.name];
                    serializeObj[this.name] = [];
                    serializeObj[this.name].push(tmpValue);
                    serializeObj[this.name].push(this.value);
                }
            }else{
                if(arrayFields != undefined && arrayFields.indexOf(this.name) >= 0){
                    serializeObj[this.name] = [];
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name] = this.value;
                }
            }
        });
        return serializeObj;
    };
})(jQuery);


// ajax提交表单（表单数据为JSON字符串）
ada.ajaxSubmitForm = function(form,url,isSave,multiToString) {
    var param = JSON.stringify($(form).serializeJson(multiToString));
    console.dir(param);

    ada.privateAjaxSubmitForm(form,url,isSave,param);
};

ada.ajaxSubmitFormWithArrayFields = function(form,url,isSave,arrayFields) {
    var param = JSON.stringify($(form).serializeJsonWithArrayFields(arrayFields));
    console.dir(param);

    ada.privateAjaxSubmitForm(form,url,isSave,param);
};

ada.privateAjaxSubmitForm = function(form,url,isSave,param) {
    $.ajax({
        method: "POST",
        contentType: "application/json",
        dataType: "json", //预期服务器返回的数据类型
        url: url,
        data: param,
        success: function (resultData) {
            if (resultData.success == true) {
                toastr.clear();
                toastr.success(resultData.message, "", {
                    "timeOut": 2000,
                });

                if (isSave) {
                    $(form).removeClass('was-validated');
                    if (form != null && form.reset != undefined) {
                        form.reset();
                    }
                } else {
                    setTimeout(
                        "if(window.name){" +
                        "   var index=parent.layer.getFrameIndex(window.name);" +
                        "   parent.layer.close(index);" +
                        "}",
                        1000);
                }
            } else {
                // console.dir(e);
                toastr.clear();
                toastr.error(resultData.message);
                // alert("123");
            }
        },
        error: function (e) {
            console.dir(e);
            toastr.clear();
            toastr.error('Exception!');
        }

    });
};

// ajax删除
ada.ajaxDelete = function (url, successFunc) {
    console.dir("awer");
    $.ajax({
        method: "POST",
        contentType: "application/json",
        dataType: "json", //预期服务器返回的数据类型
        url: url,
        // data: JSON.stringify(param), //
        success: function (resultData) {
            if (resultData.success == true) {
                toastr.clear();
                toastr.success(resultData.message, "", {
                    "timeOut": 3000,
                });

                if (successFunc) {
                    successFunc();
                }
            } else {
                console.dir(e);
                toastr.clear();
                toastr.error(resultData.message);
            }
        },
        error: function (e) {
            console.dir(e);
            toastr.clear();
            toastr.error("Exception!");
        }

    });
};

// 表单submit事件绑定处理程序
// multiToString-- 如果表单数据转成Json时一个Key有多个值的处理方式。
//  >> true -- 逗号隔开的拼接字符串
//  >> false -- 数组
ada.bindSubmit = function (formId,url,isSave,multiToString) {
    var jQueryForm = $("#" + formId);
    jQueryForm.submit(function (e) {
        e.preventDefault();
        e.stopPropagation();
        jQueryForm.addClass("was-validated");

        if (jQueryForm[0].checkValidity() === true) {
            ada.ajaxSubmitForm(jQueryForm[0],app.url,app.isSave,multiToString);
        }
        $("#" + formId+" select:invalid").parent().addClass("is-invalid");
        $("#" + formId+" select:valid").parent().removeClass("is-valid");
        //无效的获取光标
        $($(":invalid")[1]).focus()
    });
};

ada.bindSubmitWithArrayFields = function (formId,url,isSave,arrayFields) {
    var jQueryForm = $("#" + formId);
     // console.dir("1234");
    jQueryForm.submit(function (e) {
        // console.log(e);

        console.log("----------------------");
        e.preventDefault();
        e.stopPropagation();
        jQueryForm.addClass("was-validated");
        if (jQueryForm[0].checkValidity() === true) {
            ada.ajaxSubmitFormWithArrayFields(jQueryForm[0],app.url,app.isSave,arrayFields);
        }
        //无效的获取光标
        $("#" + formId+" select:invalid").parent().addClass("is-invalid");
        $("#" + formId+" select:valid").parent().removeClass("is-valid");
        $($(":invalid")[1]).focus()
    });
};

// 以弹出层形式打开指定url
ada.openPopup = function (title, url, closeFunc, width, height) {
    if (width == null) {
        width = $(window).width()*0.7 +"px";
    }
    if (height == null) {
        height = $(window).height()*0.7+"px";
    }
    layer.open({
        type: 2,
        title: title,
        content: url,
        area: [width, height],
        maxmin: true,
        shade: 0.3,
        resize: true,
        end: closeFunc
    });
};

ada.FullShow = function (title, url) {
    if (url == null || url == '') {
        url = "404.html";
    }
    ;
    var w = $(window).width() ;
    var h = $(window).height();
    var index = layer.open({
        type: 2,
        title: title,
        fix: false,
        shadeClose: true,
        maxmin: true,
        shade: 0,
        area: [w + 'px', h + 'px'],
        content: url,
    });
    layer.full(index);
};


function amountFormatter(value,row,index) {
    if (value == null) {
        return 0;
    }
    if (!isNaN(value)) {
        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
    }
    return value;
}

//千分位
ada.formatThousands = function (num) {
    var reg = /\d{1,3}(?=(\d{3})+$)/g;
    return (num + '').replace(reg, '$&,');
}
/*0 有值显示值 没值显示0

# 有值显示值 没值忽略*/
jQuery.download = function(url, method, filedir, filename) {
    jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' +  // action请求路径及推送方法
        // '<input type="text" name="filedir" value="'+filedir+'"/>' + // 文件路径
        // '<input type="text" name="filename" value="'+filename+'"/>' + // 文件名称
        '</form>')
        .appendTo('body').submit().remove();
};


//打开加载层
ada.loading =function(){
    var index = layer.load(2, {
        shade: [0.3,'#fff'] //0.1透明度的白色背景
    });
}
//关闭所有加载层
ada.closeLoading =function(){
    layer.closeAll('loading');
}

//简单日期格式化

function dateFormatter(value,row,index) {
    return new Date(value).format("yyyy-MM-dd");
}
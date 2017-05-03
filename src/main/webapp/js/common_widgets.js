var CommonAjax = {
    /*
            通用的ajax发送之前
            将操作组件所在的box加上loading遮罩
         */
    beforeSend: function(jqXHR, settings) {
        var tmpl = $("#overlayTmpl").html(),
            result = ejs.render(tmpl);
        this.append(
            result);
    },

    /*
        通用的ajax响应成功之后
        将操作组件所在的box去掉loading遮罩
     */
    always: function(jqXHR, textStatus) {
        this.children(".overlay").remove();
    },

    fail: function(jqXHR, textStatus, errorThrown) {
        var message = "";
        if (textStatus == "error") {
            if (jqXHR.status == 0) {
                message = "网络连接错误";
            } else if (jqXHR.status == 500) {
                message = "服务器内部错误";
            } else if (jqXHR.status === 404) {
                message = "URL地址出错";
            } else if (jqXHR.status == 400) {
                message = "参数出错"
            } else if (jqXHR.status == 422) {

                // 业务上的错误信息
                var errors = $.parseJSON(jqXHR.responseText);
                for (i in errors) {
                    message += errors[i];
                }
            }
        } else if (textStatus == "timeout") {
            message = "浏览器等待数据超时";
        } else if (textStatus == "parsererror") {
            message = "数据解析异常";
        }

        $("#alertModal").data("message", message).modal("show");
    }
};

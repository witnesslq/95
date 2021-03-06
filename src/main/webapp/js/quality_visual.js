$(function() {

    var DateRange = (function(argument) {

        var DEFAULT_OPTS = { type: "day" };

        return function(startDate, endDate) {


            this.type;
            this.startDate = startDate;
            this.endDate = endDate;

            $.extend(this, DEFAULT_OPTS);

        };
    })();

    DateRange.prototype.toString = function() {
        return this.startDate.format("YYYY-MM-DD") + "到" + this.endDate.format("YYYY-MM-DD")+"日";
    };

    DateRange.prototype.startDateMilliSeconds = function() {
        return this.startDate.valueOf();
    };
    DateRange.prototype.endDateMilliSeconds = function() {
        return this.endDate.valueOf();
    };

    var today = new DateRange(moment(), moment());

    $('#datetime').data("dateRange", today).daterangepicker({
        "showDropdowns": true,
        "ranges": {
            "今天": [
                today.startDate,
                today.endDate
            ],
            "昨天": [
                moment(today.startDate).subtract(1, "days"),
                moment(today.endDate).subtract(1, "days")
            ],
            "最近7天": [
                moment(today.startDate).subtract(6, "days"),
                today.endDate
            ],
            "最近30天": [
                moment(today.startDate).subtract(29, "days"),
                today.endDate
            ],
            "本月": [
                moment(today.startDate).date(1),
                moment(today.endDate).date(moment().daysInMonth())
            ],
            "上月": [

                moment(today.startDate).subtract(1, "month").date(1),
                moment(today.endDate).subtract(1, "month").date(moment().subtract(1, "month").daysInMonth())
            ]
        },
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " 到 ",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "从",
            "toLabel": "到",
            "customRangeLabel": "自定义",
            "weekLabel": "周",
            "daysOfWeek": [
                "日",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六"
            ],
            "monthNames": [
                "一月",
                "二月",
                "三月",
                "四月",
                "五月",
                "六月",
                "七月",
                "八月",
                "九月",
                "十月",
                "十一月",
                "十二月"
            ],
            "firstDay": 1
        },
        "autoUpdateInput": true,
        "alwaysShowCalendars": true,
        "startDate": today.startDate,
        "endDate": today.endDate
    }, function(start, end, label) {
        $(this.element[0]).data("dateRange", new DateRange(start, end));
    });


    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    var ratioRender = {
        render: function(data) {
            var discard = {
                    type: 'line',
                    color: "red",
                    name: '丢包率',
                    data: []
                },
                error = {
                    type: 'line',
                    color: 'yellow',
                    name: '错包率',
                    data: []
                };
            for (var i = 0, size = data.length; i < size; i++) {
                var portips = data[i];
                var date = new Date(portips.collecttime),
                    utcDate = Date.UTC(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
                discard.data.push([date.getTime(), parseFloat(portips.discardsperc)]);
                error.data.push([date.getTime(), parseFloat(portips.errorsperc)]);
            }
            ratioPlot.addSeries(discard);
            ratioPlot.addSeries(error);
            ratioPlot.redraw();
        },
        loading: function() {
            var series = ratioPlot.series;
            while (series.length > 0) {
                series[0].remove();
            }
            ratioPlot.showLoading('数据加载中...');
        },
        loaded: function(argument) {
            ratioPlot.hideLoading();
        }
    };

    function parseDateOpts() {
        var $queryBtn = $(this),

            $datetimeInput = $queryBtn.parents(".input-group").find("input"),

            dateRange = $datetimeInput.data("dateRange");

        return dateRange;
    }
    /*
    点击 查询 ，查询客户或者采集口，相应日期的丢包、错包数据
     */
    $("#dateForRatio").on("click", "button.query-btn", function(event) {


        var dateRange = parseDateOpts.call(this);

        fetchPorts(dateRange);

        $("#ratioContainer").triggerHandler('render.ratio.chart', dateRange);

    });

    $("#ratioContainer").on("render.ratio.chart", function(event, dateRange) {

        var selectedPortBtnList = $("#portList div.box-body dl button.btn-primary");

        // 有端口按钮被选中
        if (selectedPortBtnList.length > 0) {
            var interfaceList = [];
            selectedPortBtnList.each(function(index, element) {
                var nodeId = $(element).attr("data-node-id"),
                    ifIndex = $(element).attr("data-if-index");

                interfaceList.push({
                    nodeId: nodeId,
                    ifIndex: ifIndex
                });
            });

            // 多个端口的丢、错数据
            visualize({
                url: "query_portips_list_for_gather_interface.action",
                contentType: "application/json",
                data: JSON.stringify({
                    type: dateRange.type,
                    startDate: dateRange.startDateMilliSeconds(),
                    endDate: dateRange.endDateMilliSeconds(),
                    "gatherInterfaceList": interfaceList
                }),
                plotOther: (function() {
                    ratioPlot.setTitle({
                        text: dateRange + customerName + '的错误率、丢包率'
                    });

                })(),
                render: ratioRender.render,
                loading: [function(argument) {


                }, ratioRender.loading],
                loaded: [ratioRender.loaded, function(argument) {

                }]
            });
        } else {
            visualize({
                url: "query_customer_portips_list.action",
                data: {
                    type: dateRange.type,
                    startDate: dateRange.startDateMilliSeconds(),
                    endDate: dateRange.endDateMilliSeconds(),
                    "customer.customerId": customerId
                },
                plotOther: (function() {
                    ratioPlot.setTitle({
                        text: dateRange + customerName + '的错误率、丢包率'
                    });
                })(),
                render: ratioRender.render,
                loading: [function(argument) {


                }, ratioRender.loading],
                loaded: [ratioRender.loaded, function(argument) {

                }]
            });
        }
    });
    /*$("#dateForAlarm").on("click", "button.query-btn", function(event) {
        var date = $(event.delegateTarget).children("input[data-mask]").val();

    });*/

    Highcharts.setOptions({
        lang: {
            contextButtonTitle: "图表导出菜单",
            decimalPoint: ".",
            downloadJPEG: "下载JPEG图片",
            downloadPDF: "下载PDF文件",
            downloadPNG: "下载PNG文件",
            downloadSVG: "下载SVG文件",
            drillUpText: "返回 {series.name}",
            loading: "加载中",
            months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            noData: "没有数据",
            numericSymbols: ["千", "兆", "G", "T", "P", "E"],
            printChart: "打印图表",
            resetZoom: "恢复缩放",
            resetZoomTitle: "恢复图表",
            shortMonths: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            thousandsSep: ",",
            weekdays: ["星期一", "星期二", "星期三", "星期三", "星期四", "星期五", "星期六", "星期天"]
        }
    });

    var ratioPlot = Highcharts.chart("ratioContainer", {
        chart: {
            zoomType: 'x'
        },
        credits: {
            enabled: false
        },
        title: {
            text: today+customerName + '的错误率、丢包率'
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                '鼠标拖动可以进行缩放' : '手势操作进行缩放'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%e. %b',
                week: '%e. %b',
                month: '%b \'%y',
                year: '%Y'
            }
        },
        tooltip: {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%e. %b',
                week: '%e. %b',
                month: '%b \'%y',
                year: '%Y'
            },
            valueSuffix: "%"
        },
        yAxis: {
            title: {
                text: '比率（%）'
            },
            plotLines: [{
                value: 1,
                width: 2,
                color: '#999999'
            }]
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        }
    });

    /*
        异步获取客户丢包率、错包率数据
         */
    function visualize(o) {

        function executeThese(funcArrayOrFunc) {
            if ($.isArray(funcArrayOrFunc)) {
                for (var i = 0, size = funcArrayOrFunc.length; i < size; i++) {
                    if ($.isFunction(funcArrayOrFunc[i])) {
                        funcArrayOrFunc[i]();
                    }
                }
            } else if ($.isFunction(funcArrayOrFunc)) {
                funcArrayOrFunc();
            }
        }
        $.ajax({
            url: o.url,
            contentType: o.contentType || "application/x-www-form-urlencoded",
            method: "POST",
            data: o.data,
            beforeSend: function(jqXHR, settings) {
                var funcArrayOrFunc = o.loading;
                executeThese(funcArrayOrFunc);
            },
            success: function(data, textStatus, jqXHR) {
                $.isFunction(o.render) && o.render(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                var message;
                if (textStatus == "error") {
                    message = "服务器内部错误，无法获取数据";
                } else if (textStatus == "timeout") {
                    message = "浏览器等待数据超时";
                } else if (textStatus == "parsererror") {
                    message = "数据解析异常";
                }

                $("#alertModal").data("message", message).modal("show");
            },
            complete: function(jqXHR, textStatus) {
                var funcArrayOrFunc = o.loaded;

                executeThese(funcArrayOrFunc);
            }
        });
    }

    /*
        展示不同的告警信息
     */
    $("#alertModal").on("show.bs.modal", function(event) {
        var modal = $(this);
        modal.find(".modal-body").text(modal.data("message"));
    });

    /*
        初始查询当天此客户的丢包率、错包率数据
     */
    visualize({
        url: "query_customer_portips_list.action",
        data: {
            type: "day",
            startDate: today.startDateMilliSeconds(),
            endDate: today.endDateMilliSeconds(),
            "customer.customerId": customerId
        },

        render: ratioRender.render,
        loading: [function(argument) {

            $("#dateForRatio .query-btn").prop("disabled", true).text("查询中...");
        }, ratioRender.loading],
        loaded: [ratioRender.loaded, function(argument) {
            $("#dateForRatio .query-btn").prop("disabled", false).text("查询");
        }]
    });

    $("#portList div.box-body").on("click", "button", function(event) {
                    var clickedPort = $(this);
                    //当前点击的按钮变色，其它按钮置灰，目前同时只能查询一个端口
                    clickedPort.toggleClass("btn-default").toggleClass("btn-primary");
                    var dateOpts = parseDateOpts.call($("button.query-btn"));
                    $("#ratioContainer").triggerHandler('render.ratio.chart', dateOpts);
                });
    /*
        客户某时段内占用的端口
     */
    function fetchPorts(dateRange) {
        var $box = $("#portList");

        $.ajax({
            url: "query_customer_port_list.action",
            contentType: "application/x-www-form-urlencoded",
            method: "POST",
            data: {
                "customer.customerId": customerId,
                "startDate": dateRange.startDateMilliSeconds(),
                "endDate": dateRange.endDateMilliSeconds()
            },
            beforeSend:$.proxy(CommonAjax.beforeSend,$box),
            success: function(data, textStatus, jqXHR) {


                var ips = [];
                //构造IP：端口列表 键值对;
                for (var i = 0, size = data.length; i < size; i++) {
                    var port = data[i],
                        last = ips[ips.length > 0 ? ips.length - 1 : 0];
                    if (last && last.ip == port.nodeId) {
                        last.ports && last.ports.push(port);
                    } else { //第一个IP或者前一个IP和当前portIP不同
                        ips.push({
                            ip: port.nodeId,
                            ports: [port]
                        });
                    }
                }


                var tmpl = $("#portBtnTmpl").html(),
                    result = "无";
                if (ips.length > 0) {
                    result = ejs.render(tmpl, {
                        ips: ips
                    });
                }

                $("#portList div.box-body").html(result);
            },
            error: CommonAjax.fail,
            complete: $.proxy(CommonAjax.always,$box)
            
        });
    }
    fetchPorts(today);
});

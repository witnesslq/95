$(function() {

    function Customer(opts) {
        $.extend(this, {
            customerId: "",
            customerName: ""
        }, opts);

    }

    Customer.prototype.getCustomerSummary = function() {
        if (!!this.customerId) {
            $.ajax({
                    url: basePath + 'customer_config/query_customer_summary_for_this_customer.action',
                    type: 'POST',
                    dataType: 'json',
                    data: { "customer.customerId": customerId }
                })
                .done(function(data) {
                    $(".port-count-label").text(data.portCount);
                })
                .fail(commonFail)
                .always(function() {
                    console.log("complete");
                });
        }
    };

    var customer = new Customer({
        customerId: customerId
    });
    customer.getCustomerSummary();
    /*
        通用的ajax发送之前
        将操作组件所在的box加上loading遮罩
     */
    function commonBeforeSend($box) {
        return function(jqXHR, settings) {
            var tmpl = $("#overlayTmpl").html(),
                result = ejs.render(tmpl);
            $box.append(
                result);
        };
    }

    /*
        通用的ajax响应成功之后
        将操作组件所在的box去掉loading遮罩
     */
    function commonAlways(jqXHR, textStatus) {
        this.children(".overlay").remove();
    }

    function commonFail(jqXHR, textStatus, errorThrown) {
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
    /*
        解绑客户占用的端口
     */
    $(".unbound-customer").click(function(event) {

        var $unboundCustomer = $(this);

        $confirmModal.data('message', '确定要解绑这个客户的所有端口?')
            .find('button.ok').on('click', function(event) {

                /*
                    删除附加的解绑客户的操作
                 */
                $(this).off('click');
                $confirmModal.on('hidden.bs.modal', function(event) {

                    $(this).off('hidden.bs.modal');
                    $unboundCustomer.triggerHandler('unbound.customer');
                }).modal('hide');
            });

        $confirmModal.modal('show');
    }).on('unbound.customer', function(event) {

        var $customerBox = $(this).parents('.box');

        $.ajax({
                url: basePath + 'customer_config/unbound_all_interface_for_this_customer.action',
                type: 'POST',
                dataType: 'json',
                data: { "customer.customerId": customerId },
                beforeSend: commonBeforeSend($customerBox)
            })
            .done(function() {
                location.reload();
            })
            .fail(function() {
                console.log("error");
            })
            .always($.proxy(commonAlways, $customerBox));
    });

    // 保存客户名
    $("#saveCustomer").click(function(event) {
        var $customerBtn = $(this),
            $customerName = $customerBtn.parents(".input-group").children('input'),
            customerName = $customerName.val();

        if (customerName === "") {
            $customerName.tooltip("show");
            return;
        }

        var data = {
            "customer.customerName": customerName
        };
        if (!!customerId) data["customer.customerId"] = customerId;
        // 添加新客户或者修改客户名称
        $.ajax({
                url: basePath + 'customer_config/save_customer.action',
                type: 'POST',
                dataType: 'json',
                data: data,
                beforeSend: function(jqXHR, settings) {
                    $customerBtn.prop({
                        "disabled": true
                    });
                }
            })
            .done(function(data) {
                customerId = data.customerId,
                    customerName = data.customerName;

                $customerBtn.triggerHandler('update.button.dropdown');
            })
            .fail(commonFail)
            .always(function(jqXHR, textStatus) {
                $customerBtn.prop('disabled', false);
            });

    }).on("update.button.dropdown", function(event) {
        // 修改客户
        if (!!customerId) {
            $("#saveCustomer").text('更新').parent()
                .children('.dropdown-menu').children().removeClass('disabled');
        }

        event.stopPropagation();
    }).trigger('update.button.dropdown').parents(".input-group").children('input').tooltip({
        placement: "bottom",
        trigger: "manual",
        title: function() {
            return "用户名不能空";
        }
    }).focus(function(event) {
        $(this).tooltip("hide");
    });

    // 显示警告消息，需要用户确认
    var $confirmModal = $('#confirmModal').on('show.bs.modal', function(event) {
        var $confirmModal = $(this),
            message = $confirmModal.data('message');

        $confirmModal.find('.modal-body').text(message);
    });
    /*
        删除用户以及用户绑定的端口信息
     */
    $('.delete-customer').on('click', function(event) {

        var $deleteCustomer = $(this);

        $confirmModal.data('message', '确定要删除这个客户?')
            .find('button.ok').on('click', function(event) {

                /*
                    删除附加的删除客户的操作
                 */
                $(this).off('click');
                $confirmModal.on('hidden.bs.modal', function(event) {

                    $(this).off('hidden.bs.modal');
                    $deleteCustomer.triggerHandler('delete.customer');
                }).modal('hide');
            });

        $confirmModal.modal('show');

    }).on('delete.customer', function(event) {

        var $customerBox = $(this).parents('.box');
        $.ajax({
                url: basePath + 'customer_config/delete_customer.action',
                type: 'POST',
                data: {
                    "customer.customerId": customerId
                },
                beforeSend: commonBeforeSend($customerBox)
            })
            .done(function(data) {

                //回到客户概况页面，不可回退
                location.replace(basePath + "system/customer_config/customer_summary.jsp");
            })
            .fail(commonFail)
            .always($.proxy(commonAlways, $customerBox));
    });

    // 所有设备IP
    $.ajax({
            url: basePath + 'customer_config/query_all_device_ip.action',
            type: 'POST',
            dataType: 'json'
        })
        .done(function(data) {
            var ips = [];

            for (var i in data) {
                ips.push(data[i].ipAddress);
            }

            renderIpTemplate(ips);
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });

    function renderIpTemplate(data) {
        var template = $('#deviceIpTmpl').html(),
            result = ejs.render(template, {
                ips: data
            });

        $('select.ip').append(result).select2();
    }

    // 查询过滤出来所输入IP的设备Panel
    $("#queryDeviceBtn").click(function(event) {
        var $ip = $(this).parents(".input-group").children('select'),
            ip = $ip.val();

        if (ip === "") { //所有设备Panel
            deviceAccordion.getPagingDeviceDetail({
                countPerPage: 10,
                currentPageNumber: 0
            });
        } else if (validator.isIP(ip)) {

            var $box = $(this).parents('.box');
            $.ajax({
                    url: basePath + 'customer_config/query_some_device_detail.action',
                    type: 'POST',
                    contentType: "application/json",
                    dataType: 'json',
                    data: JSON.stringify({
                        "hostList": [{
                            ipAddress: ip
                        }]
                    }),
                    beforeSend: commonBeforeSend($box)
                })
                .done(function(data) {

                    //格式化数据
                    var devices = [];
                    for (var i = 0, list = data, size = list.length; i < size; i++) {
                        var device = list[i],
                            interfaces = device.interfaces,
                            ports = [];

                        for (var j in interfaces) {
                            var inf = interfaces[j];
                            ports.push({
                                "id": inf.ifIndex,
                                "name": inf.ifDesc,
                                "status": inf.ifStatus,
                                "customerId": inf.customerId,
                                "speed": inf.ifSpeed
                            });
                        }

                        devices.push({
                            ip: device.device.ipAddress,
                            ports: ports
                        });

                    }

                    data = devices;
                    $("#deviceAccordion").data("deviceDetail", data).triggerHandler("render.bs.accordion");


                    $(".info").text('');

                    $(".panel-list ul.pagination").html('<li class="disabled"><a href="#">上页</a></li><li class="disabled"><a href="#">下页</a></li>');
                })
                .fail(commonFail)
                .always($.proxy(commonAlways, $box));

        } else {
            $ip.tooltip("show");
        }
    }).parents(".input-group").children('input').tooltip({
        placement: "bottom",
        trigger: "manual",
        title: function() {
            return "请输入合法的IP";
        }
    }).focus(function(event) {
        $(this).tooltip("hide");
    });

    var Accordion = (function(argument) {

        // 渲染accordion分页组件
        function renderAccordionPagingComponent(data) {
            $(this.id).parents(".accordion").find(".panel-list ul.pagination").triggerHandler('render.bs.pagination', {
                totalPageCount: data.totalPageCount,
                currentPageNumber: data.currentPageNumber
            });
        }

        // 渲染accordion分页信息
        function renderAccordionPagingInfo(data) {
            $(this.id).parents(".accordion").find(".info").triggerHandler("render.bs.info", {
                currentPageStart: data.currentPageStart,
                currentPageEnd: data.currentPageEnd,
                totalCount: data.totalCount
            });
        }

        var Accordion = function(opts) {
            $.extend(this, {
                countPerPage: 10,
                currentPageNumber: 0
            }, opts);

            var that = this;
            this.$collapse = $(this.id);
            this.$accordion = this.$collapse.parents(".accordion");
            /*
                所有设备的端口点击处理
            */
            this.$collapse.on("click", "button.toggle-bound-port", function(event) {

                    if (!!!customerId) {
                        return;
                    }
                    var url = basePath + 'customer_config/unbound_port.action',
                        currentCustomerId = customerId,
                        $toggleBoundBtn = $(this),
                        ip = $toggleBoundBtn.parents('div[data-ip]').attr("data-ip").trim(),
                        portId = $toggleBoundBtn.attr("data-port-id"),
                        portName = $toggleBoundBtn.attr("data-port-name"),
                        increment = -1;

                    var data = {
                        "topoInterface.nodeId": ip,
                        "topoInterface.ifIndex": portId,
                        "topoInterface.ifDesc": portName,
                        "topoInterface.customerId": currentCustomerId
                    };


                    if (!$toggleBoundBtn.hasClass('active')) {
                        url = basePath + 'customer_config/bound_port.action';
                        increment = 1;
                    }

                    var $box = $(this).parents('.box');
                    $.ajax({
                            url: url,
                            type: 'POST',
                            dataType: 'json',
                            data: data,
                            beforeSend: commonBeforeSend($box)
                        })
                        .done(function(data) {
                            if ($toggleBoundBtn.hasClass('active'))
                                $toggleBoundBtn.text('上线');
                            else
                                $toggleBoundBtn.text('下线');
                            $(".port-count-label").trigger("render.bs.label",increment);
                            $toggleBoundBtn.toggleClass('active');
                        })
                        .fail(commonFail)
                        .always($.proxy(commonAlways, $box));

                }).on("render.bs.accordion", function(event) {
                    var $accordion = $(this),
                        devices = $accordion.data('deviceDetail'),
                        template = $("#deviceTmpl").html(),
                        result = ejs.render(template, {
                            devices: devices,
                            customerId: customerId,
                            accordionId: $accordion.attr("id"),
                            bounded: $accordion.attr("data-bounded")
                        });

                    $accordion.html(result);

                    /*$deviceAccordion.find('table.table').DataTable({
                        "language": {
                                "url": basePath + "/lang/dataTables.chinese.lang"
                        }
                    });*/
                })
                //解绑此客户在设备上占用的端口
                .on("click", "button.unbound-device", function(event) {

                    var $unboundDevice = $(this);

                    $confirmModal.data('message', '确定要解绑当前客户在此设备上的所有端口?')
                        .find('button.ok').on('click', function(event) {

                            /*
                                删除附加的解绑客户的操作
                             */
                            $(this).off('click');
                            $confirmModal.on('hidden.bs.modal', function(event) {

                                $(this).off('hidden.bs.modal');
                                $unboundDevice.trigger('unbound.device');
                            }).modal('hide');
                        });

                    $confirmModal.modal('show');

                }).on('unbound.device', 'button', function(event) {

                    var ip = $(this).attr('data-ip'),
                        portCount = parseInt($(this).attr('data-port-count'));

                    var $box = $(this).parents('.box');
                    $.ajax({
                            url: basePath + 'customer_config/unbound_device_interface_for_this_customer.action',
                            type: 'POST',
                            dataType: 'json',
                            data: {
                                "host.ipAddress": ip,
                                "customer.customerId": customerId
                            },
                            beforeSend: commonBeforeSend($box)
                        })
                        .done(function() {
                            location.reload();
                        })
                        .fail(commonFail)
                        .always($.proxy(commonAlways, $box));
                    event.preventDefault();
                }).tooltip({
                    trigger:"hover",
                    selector:"button.toggle-bound-port",
                    title: function() {
                        if(!!!customerId)
                            return "请先点击“保存”按钮创建客户";
                    }
                });
            // 设备Panel分页
            this.$accordion.find(".panel-list ul.pagination").on("click", "li", function(event) {

                var currentPageNumber = $(this).attr("data-page");

                if (isNaN(parseInt(currentPageNumber))) {

                    if (currentPageNumber === 'prev') {
                        currentPageNumber = parseInt($(event.delegateTarget).children('li.active').attr("data-page"));
                        currentPageNumber = currentPageNumber - 1 < 1 ? 1 : (currentPageNumber - 1);
                    } else {
                        currentPageNumber = parseInt($(event.delegateTarget).children('li.active').attr("data-page"));
                        var totalPageCount = parseInt($(this).prev().attr("data-page"));
                        currentPageNumber = currentPageNumber + 1 > totalPageCount ? totalPageCount : (currentPageNumber + 1);
                    }
                }

                currentPageNumber = parseInt(currentPageNumber);
                currentPageNumber -= 1;

                that.getPagingDeviceDetail({
                    countPerPage: 10,
                    currentPageNumber: currentPageNumber
                });
            }).on("render.bs.pagination", function(event, data) {
                var template = $("#devicePaginationTmpl").html(),
                    result = ejs.render(template, data);

                $(this).html(result);
            });

            this.$accordion.find(".info").on("render.bs.info", function(event, data) {
                data.currentPageEnd = data.currentPageEnd < data.totalCount ? data.currentPageEnd : data.totalCount;
                $(this).text('显示第' + (data.currentPageStart + 1) + '至第' + data.currentPageEnd + '项结果，共' + data.totalCount + '项');
            });
        };

        // 渲染accordion
        Accordion.prototype.render = function(devices) {

            this.$collapse.data("deviceDetail", devices)
                .triggerHandler("render.bs.accordion");
        };

        Accordion.prototype.getPagingDeviceDetail = function(opts) {
            var accordion = this,
                $box = this.$collapse.parents(".box");

            var data = {
                "customer.customerId": customerId
            };
            $.extend(data, {
                countPerPage: 10,
                currentPageNumber: 0
            }, opts);
            /*
                获取设备
            */
            $.ajax({
                    url: this.url,
                    type: 'POST',
                    dataType: 'json',
                    data: data,
                    beforeSend: function(jqXHR, settings) {
                        var tmpl = $("#overlayTmpl").html(),
                            result = ejs.render(tmpl);
                        $box.append(result);
                    }
                })
                .done(function(data, textStatus, jqXHR) {

                    if (data && data.totalCount > 0) {

                        //格式化数据
                        var devices = [];
                        for (var i = 0, list = data.list, size = list.length; i < size; i++) {
                            var device = list[i],
                                interfaces = device.interfaces,
                                ports = [];

                            for (var j in interfaces) {
                                var inf = interfaces[j];
                                ports.push({
                                    "id": inf.ifIndex,
                                    "name": inf.ifDesc,
                                    "status": inf.ifStatus,
                                    "customerId": inf.customerId,
                                    "speed": inf.ifSpeed
                                });
                            }

                            devices.push({
                                ip: device.device.ipAddress,
                                ports: ports
                            });

                        }
                        data.devices = devices;
                        renderAccordionPagingInfo.call(accordion, data);
                        renderAccordionPagingComponent.call(accordion, data);

                        accordion.render(data.devices);
                    }
                })
                .fail(commonFail)
                .always($.proxy(commonAlways, $box));
        };

        return Accordion;
    })();


    var deviceAccordion = new Accordion({
        id: "#deviceAccordion",
        url: basePath + 'customer_config/query_unbound_device_detail.action'
    });

    deviceAccordion.getPagingDeviceDetail();

    var boundAccordion = new Accordion({
        id: "#boundAccordion",
        url: basePath + 'customer_config/query_bound_device_detail.action'
    });

    boundAccordion.getPagingDeviceDetail();


    $(".port-count-label").on("render.bs.label", function(event, increment) {
        var $this = $(this);
        $this.text(parseInt($this.text()) + increment);
    });

    /*
        展示不同的告警信息
     */
    $("#alertModal").on("show.bs.modal", function(event) {
        var modal = $(this);
        modal.find(".modal-body").text(modal.data("message"));
    });
});

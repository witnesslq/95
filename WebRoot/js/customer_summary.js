$(function() {

    $.ajax({
            url: basePath + 'customer_config/query_all_customer_summary.action',
            type: 'POST',
            dataType: 'json',
            beforeSend: function(jqXHR, settings) {
                var tmpl = $("#overlayTmpl").html(),
                    result = ejs.render(tmpl);
                $("#customerBox").append(result);
            }
        })
        .done(function(data, textStatus, jqXHR) {
            $("#customerTable").data("customerSummary", data).triggerHandler('dataTables.render');
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            $("#customerBox").children(".overlay").remove();
        });


    $("#customerTable").on("dataTables.render", function(event) {
        var $customerTable = $(this),
            data = $customerTable.data('customerSummary'),
            template = $("#customerOperationTmpl").html();

        $customerTable.DataTable({
            processing: true,
            serverSide: false,
            data: data,

            autoWidth: false,
            "language": {
                "url": basePath + "/lang/dataTables.chinese.lang"
            },
            columns: [{
                data: null,
                title: "序号",
                render: function(data, type, row, meta) {
                    return meta.row + 1;
                }
            }, {
                data: "customer.customerName",
                title: "客户名称"
            }, {
                data: "status",
                title: "在线状态"
            }, {
                data: "portCount",
                title: "端口数量"
            }, {
                data: "null",
                title: "操作",
                render: function(data, type, row, meta) {
                    return ejs.render(template, 
                    	row);
                }
            }]
        });
    })

    /*
    解绑客户占用的端口
 */
    .on("click",".unbound-customer",function(event) {

        var customerId = $(this).attr('data-customer-id');
        $.ajax({
            url: basePath + 'customer_config/unbound_all_interface_for_this_customer.action',
            type: 'POST',
            dataType: 'json',
            data: {"customer.customerId": customerId},
        })
        .done(function() {
           location.reload();
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
        
    });

});

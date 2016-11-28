$(function() {

/*
客户端口数、总平均丢包、错包率统计
 */
	$.ajax({
		url: "query_all_customer_summary_for_gather_interface.action",
		beforeSend: function(jqXHR, settings) {
			var tmpl = $("#overlayTmpl").html(),
				result = ejs.render(tmpl);
			$("#customerSummary").append(result);
		},
		type: "json",
		cache:true
	}).done(function(data, textStatus, jqXHR) {

		/*
		分页显示客户
	 */
		$("#customerTable").DataTable({
			processing: true,
			// serverSide: true,
			searching: true,
			ordering: true,
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
				data: "customerName",
				title: "客户名称"
			}, {
				data: "portCount",
				title: "端口数量"
			}, {
				data: "portips.errorsperc",
				title: "错包率"
			}, {
				data: "portips.discardsperc",
				title: "丢包率"
			}, {
				data: null,
				title: "操作",
				render: function(data, type, row, meta) {

					return '<a href="quality_visual.jsp?customerId='+ data.customerId +'&customerName='+data.customerName+'&portCount='+data.portCount+'" class="btn btn-default btn-sm">查看</a>';
				}
			}]
		});

	}).fail(function(jqXHR,textStatue,errorThrown) {
		var message;
				if (textStatus == "error") {
					message = "服务器内部错误，无法获取数据";
				} else if (textStatus == "timeout") {
					message = "浏览器等待数据超时";
				} else if (textStatus == "parsererror") {
					message = "数据解析异常";
				}

				$("#alertModal").data("message", message).modal("show");
	}).always(function(jqXHR) {
		$("#customerSummary").children(".overlay").remove();
	});

	/*
		展示不同的告警信息
	 */
	$("#alertModal").on("show.bs.modal", function(event) {
		var modal = $(this);
		modal.find(".modal-body").text(modal.data("message"));
	});
});
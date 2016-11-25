$(function() {

	var mockData = [{
		"customerId": "1234kjsoife",
		"customerName": "淘宝",
		"ipCount": 15,
		"alarmCount": 50,
		"errorRatio": "3%",
		"lostRatio": "2%"
	}, {
		"customerId": "1234kjsoife",
		"customerName": "淘宝",
		"ipCount": 15,
		"alarmCount": 50,
		"errorRatio": "3%",
		"lostRatio": "2%"
	}, {
		"customerId": "1234kjsoife",
		"customerName": "淘宝",
		"ipCount": 15,
		"alarmCount": 50,
		"errorRatio": "3%",
		"lostRatio": "2%"
	}];

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

					return '<a href="quality_visual.jsp?customerId='+ data.customerId +'&customerName='+data.customerName+'" class="btn btn-default btn-sm">查看</a>';
				}
			}]
		});

	}).fail(function(jqXHR) {
		// body...
	}).always(function(jqXHR) {
		$("#customerSummary").children(".overlay").remove();
	});


});
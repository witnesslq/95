$(function() {
	var data = [{
		ip: "2.16 .1 .1",
		category: "客户带宽利用率",
		description: "192.168.1.1 客户带宽利用率 90% 告警值80-100",
		time: "2016-10-17 10:10:10"
	}, {
		ip: "192.168 .0 .1",
		category: "客户带宽利用率",
		description: "192.168.1.1 客户带宽利用率 90% 告警值80-100",
		time: "2016-10-17 10:10:10"
	}, {
		ip: "92.168 .1 .1",
		category: "客户带宽利用率",
		description: "192.168.1.1 客户带宽利用率 90% 告警值80-100",
		time: "2016-10-17 10:10:10"
	}, {
		ip: "192.168 .1 .1",
		category: "客户带宽利用率",
		description: "192.168.1.1 客户带宽利用率 90% 告警值80-100",
		time: "2016-10-17 10:10:10"
	}, {
		ip: "12.18 .1 .1",
		category: "客户带宽利用率",
		description: "192.168.1.1 客户带宽利用率 90% 告警值80-100",
		time: "2016-10-17 10:10:10"
	}];
	var alarmDataTable = $("#alarmTable").DataTable({
		data: data,
		autoWidth:false,
		"language": {
			"url": basePath + "/lang/dataTables.chinese.lang"
		},
		columns: [{
			data: null,
			title: "序号",
			orderable:false,
			render: function(data, type, row, meta) {
				return meta.row+1;
			}
		}, {
			data: "ip",
			title: "IP"
		}, {
			data: "category",
			title: "告警类型"
		}, {
			data: "description",
			title: "描述"
		}, {
			data: "time",
			title: "时间"
		}]
	});
});
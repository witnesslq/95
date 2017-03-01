$(function() {

	/*
		分页显示告警
	 */
	var alarmDataTable = $("#alarmTable").DataTable({
		processing:true,
		serverSide:true,
		searching:false,
		ordering:false,
		ajax: {
			url: basePath + "/query_alarm_info.action",
			type:"POST",
			dataSrc:function(data){
				data.recordsTotal = data.totalCount;
				data.recordsFiltered = data.totalCount;
				return data.list;
			},
			data:function(d){
				var data = {},
				start = d.start,
				length = d.length;

				//将开始索引转换为页号（0开始）
				data["currentPageNumber"] = parseInt((start+1)/length)+(start%length>0?1:0);
				data["countPerPage"] = length;
				return data;
			},
			error:function(){

			}
		},
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
			data: "ip",
			title: "IP"
		}, {
			data: "name",
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
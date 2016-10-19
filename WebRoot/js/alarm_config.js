$(function() {

	/*
		客戶带宽利用率滑动块
	 */
	$("#threshold1").ionRangeSlider({
		min: 0,
		max: 100,
		from: 10,
		to: 80,
		type: 'double',
		grid: true,
		grid_num: 10,
		postfix: "%",
		keyboard: true,
		keyboard_step: 1
	});

	/*
	设备带宽利用率滑动块
	 */
	$("#threshold2").ionRangeSlider({
		min: 0,
		max: 100,
		from: 10,
		to: 80,
		type: 'double',
		grid: true,
		grid_num: 10,
		postfix: "%",
		keyboard: true,
		keyboard_step: 1
	});

	/*
	丢包率滑动块
	 */
	$("#threshold3").ionRangeSlider({
		min: 0,
		max: 100,
		from: 10,
		step: 0.1,
		type: 'single',
		grid: true,
		grid_num: 100,
		postfix: "%",
		keyboard: true,
		keyboard_step: 0.1
	});

	/*
	错包率滑动块
	 */
	$("#threshold4").ionRangeSlider({
		min: 0,
		max: 100,
		from: 10,
		step: 0.1,
		type: 'single',
		grid: true,
		grid_num: 100,
		postfix: "%",
		keyboard: true,
		keyboard_step: 0.1
	});

	/*
	异常流量滑动块
	 */
	$("#threshold5").ionRangeSlider({
		min: 0,
		max: 100,
		from: 10,
		type: 'single',
		grid: true,
		grid_num: 10,
		postfix: "倍",
		keyboard: true,
		keyboard_step: 1
	});

	/*
	选择告警等级，文字变色
	 */
	$("select").on("change", function(event) {
		var $select = $(this).removeClass().addClass("form-control");
		$select.addClass($select.children("option:selected").attr("class"));
	});

	/*
		复选框使用iCheck
	 */
	$('input[type="checkbox"]').iCheck({
		checkboxClass: 'icheckbox_minimal-blue'
	});
});
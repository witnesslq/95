$(function() {

	/*
	 告警门限配置
	 */
	$.ajax({
		url: basePath + "/query_all_with_number_type.action",
		dataType: "json",
		type: "POST",
		beforeSend: function(jqXHR, settings) {
			// body...
		}
	}).done(function(data, textStatue, jqXHR) {
		var tmpl = $("#alarmIndicatorsTmpl").html(),
			result = ejs.render(tmpl, {
				"alarmIndicators": data
			});

		$("#alarmIndicatorsTable tbody").append(result);

		// 初始化门限slider
		for (var i = 0, iSize = data.length; i < iSize; i++) {

			var alarmIndicator = data[i],
				alarmRules = alarmIndicator.alarmRules;

			// 解析区间值，多值以逗号分隔
			var values = [];
			for (var j = 0, jSize = alarmRules.length; j < jSize; j++) {
				values = values.concat(alarmRules[j].value.split(","));
			}

			// 按从小到大排序
			values.sort(function(a, b) {
				a = parseInt(a), b = parseInt(b);
				if (a < b) {
					return -1;
				} else if (a > b) {
					return 1;
				}
				return 0;
			});

			var options = {
				grid: true,
				grid_num: 10,
				postfix: alarmIndicator.unit,
				keyboard: true,
				keyboard_step: 1,
				onFinish: function(params) {
					// body...
					var indicatorId = params.input.context.id,
						alarmRules = $("#btn-" + indicatorId).data().alarmRules;

					// 指标对应的值(按照单值和区间分)
					var isSingleValue = alarmRules && alarmRules[0] && (alarmRules[0].alarmRuleValueType.valueCount == 1),
						isDoubleValue = alarmRules && alarmRules[0] && (alarmRules[0].alarmRuleValueType.valueCount == 2);

					if (isSingleValue) {
						alarmRules[0].value = params.from;
					} else if (isDoubleValue) {

						// 多区间
						if (alarmRules.length == 2) {

							// 小区间（区间值小）排序靠前
							alarmRules.sort(function(a, b) {
								if (a.valueField < b.valueField) {
									return -1;
								} else if (a.valueField > b.valueField) {
									return 1;
								}
								return 0;
							});

							alarmRules[0].value = params.min + "," + params.from;
							alarmRules[1].value = params.to + "," + params.max;
						} else if (alarmRules.length == 1) {
							alarmRules[0].value = params.min + "," + params.from;
						}
					}
				}
			};

			// 单值（倍数值）
			if (values.length == 1) {
				options.min = 1;
				options.max = 100;
				options.from = values[0] == undefined ? 10 : values[0];
				options.type = "single";
			} else {
				// 区间值
				options.min = values[0] == undefined ? 0 : values[0];
				options.max = values[3] == undefined ? 100 : values[3];
				options.from = values[1] == undefined ? 0 : values[1];
				values[2] == undefined ? (options.type = "single") : (options.to = values[2], options.type = "double");
			}

			// 滑动块
			$("#indicator" + alarmIndicator.indicatorId).ionRangeSlider(options);

			//存储告警指标
			// 绑定保存
			$("#btn-indicator" + alarmIndicator.indicatorId).on("click", function(event) {
				var alarmIndicator = $(this).data();

				$.ajax({
					url: "update_alarm_indicator.action",
					type: "POST",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify({
						"indicator": alarmIndicator
					})
				}).done(function(data, textStatus, jqXHR) {
					// body...
				});

				return false;
			}).data(alarmIndicator);
		}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		// body...
	}).always(function(jqXHR, textStatus) {
		// body...
	});

	/*
	错包率滑动块
	 将来升级带小数使用
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
	});*/

	/*
		告警规则表格
	 */
	$.when($.ajax({
		url: basePath + "/query_all_alarm_rule.action",
		dataType: "json",
		type: "POST",
		beforeSend: function(jqXHR, settings) {

		}
	}), $.ajax({
		url: basePath + "/query_all_alarm_severity.action",
		dataType: "json",
		type: "POST",
		beforeSend: function(jqXHR, settings) {

		}
	}), $.ajax({
		url: basePath + "/query_all_alarm_notice_type.action",
		dataType: "json",
		type: "POST",
		beforeSend: function(jqXHR, settings) {

		}
	})).done(function(a1, a2, a3) { //a1,2,3是按$.when中ajax顺序的success回调函数的参数数组
		this.data = {
			alarmRules: a1[0],
			alarmSeveritys: a2[0],
			alarmNoticeTypes: a3[0]
		};
	}).fail(function(a1, a2, a3) {
		console.log(a1 + a2 + a3);
	}).always(function(a1, a2, a3) {}).then(function() {

		//渲染告警规则
		var tmpl = $("#alarmRulesTmpl").html(),
			result = ejs.render(tmpl, this.data);

		$("#alarmRulesTable tbody").append(result);

		// 复选框使用iCheck美化
		$('input[type="checkbox"]').iCheck({
			checkboxClass: 'icheckbox_minimal-blue'
		});

		var alarmRules = this.data.alarmRules;

		for (var i = 0, size = alarmRules.length; i < size; i++) {
			var rule = alarmRules[i];

			$("#btn-rule" + rule.ruleId).on("click", function(event) {
				$.ajax({
					url: "update_alarm_rule.action",
					data: JSON.stringify({
						"rule": $(this).data()
					}),
					dataType: "json",
					contentType: "application/json",
					type: "POST"
				}).done(function(data, textStatus, jqXHR) {
					console.log();
				}).fail(function(jqXHR, textStatus, errorThrown) {
					// body...
				}).always(function(jqXHR, textStatus) {
					// body...
				});
			}).data(rule);

			// 增加或者减少通知方式
			$('input[name="noticeType-rule' + rule.ruleId + '"]').on("ifToggled", function(event) {
				var $this = $(this),
					btnRuleId = "#btn-" + $this.attr("name").replace("noticeType-", ""),
					rule = $(btnRuleId).data(),
					noticeTypeId = $this.val();

				if ($this.prop("checked")) {
					rule.alarmNoticeTypes.push({
						"noticeTypeId": noticeTypeId
					});
				} else {
					rule.alarmNoticeTypes = $.map(rule.alarmNoticeTypes, function(element, index) {
						if (element.noticeTypeId != noticeTypeId) {
							return element;
						}
					});
				}
			});

			// 切换是否告警抑制
			$('select[name="isSuppress-rule' + rule.ruleId + '"]').on("change", function(event) {
				var $this = $(this),
					btnRuleId = "#btn-" + $this.attr("name").replace("isSuppress-", ""),
					rule = $(btnRuleId).data();

				rule.isSuppress = $this.val();
			});

			// 切换告警严重性
			$('select[name="severity-rule' + rule.ruleId + '"]').on("change", function(event) {
				var $this = $(this),
					btnRuleId = "#btn-" + $this.attr("name").replace("severity-", ""),
					rule = $(btnRuleId).data();

				/*
				选择告警等级，文字变色
	 			*/
				$this.removeClass().addClass("form-control");
				$this.addClass($this.children("option:selected").attr("class"));

				rule.alarmSeverity = {
					severityId: $this.val()
				};
			});

			// 显示通知人
			$("#btn-user-rule" + rule.ruleId).on("click", function(event) {
				var $this = $(this),
					btnRuleId = "#btn-" + $this.attr("id").replace("btn-user-", ""),
					rule = $(btnRuleId).data();

				var users = $("#generalModal").data("users"),
					tmpl = $("#usersTmpl").html(),
					result = ejs.render(tmpl, {
						"users": users,
						"receivers": rule.tsusers
					});

				$("#generalModal .modal-body .container-fluid").html("").append(result);

				// 复选框使用iCheck美化
				$('#generalModal input[type="checkbox"]').iCheck({
					checkboxClass: 'icheckbox_minimal-blue'
				});

				$('#generalModal .modal-footer button[name="btn-save-receiver"]').attr("id", "btn-save-rule" + rule.ruleId);
			});
		}
	});

	/*
		取得所有用户
	 */
	$.ajax({
		url: "query_all_user.action",
		dataType: "json"
	}).done(function(data, textStatus, jqXHR) {
		$("#generalModal").data("users", data);
	});

	/*
		添加或者去掉通知接收人
	 */
	$('button[name="btn-save-receiver"]').on("click", function(event) {
		var $this = $(this),
			btnRuleId = "#btn-" + $this.attr("id").replace("btn-save-", ""),
			textRuleId = "user-" + $this.attr("id").replace("btn-save-", "")
		rule = $(btnRuleId).data(),
			receivers = $('#generalModal .modal-body input[type="checkbox"]:checked');
		rule.tsusers = receivers.map(function(index, element) {
			var $this = $(element);

			return {
				"id": $this.val()
			};
		}).get();

		$('input[name="' + textRuleId + '"]').val(receivers.map(function(index, element) {
			return $(element).attr("data-username");
		}).get().join(","));
	});
});
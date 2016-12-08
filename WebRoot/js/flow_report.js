$(function() {

	$(".calendar-year").text(new Date().getUTCFullYear()); //初始年份为今年
	$("button.prev-year").on("click", function(event) {
		validate&&validate.call(this)&&postValidate&&postValidate.call(this,-1);
	});

	$("button.next-year").on("click", function(event) {
		
		validate&&validate.call(this)&&postValidate&&postValidate.call(this,1);
	});

	var validate = function() {
		
		//验证是否选中存在的客户，若选中，输入框会有绿色对勾提示
		if($customerPopover.data("customerId") == null) 
			return false;
		return true;

	}, postValidate = function(increment) {
		var $calendarYear = $(".calendar-year");
		$calendarYear.text(parseInt($calendarYear.text()) + increment);

		var customerId = $customerPopover.data("customerId"),
			dateMillisecond = new Date($calendarYear.text()).getTime();

		getFlowBill && getFlowBill.call(this, customerId, dateMillisecond);
	}, popoverContent = "",
		generateContent = function(argument) {
			return popoverContent;
		},

		getCustomer = function(event) {

			var $that = $(this),
				customerName = $that.val();

			if (customerName == "") {
				$customerPopover.popover("hide"); //没有任何文字不显示客户列表下拉框
				return;
			}

			$.ajax({
				url: "query_customer_list_by_customer_name.action",
				contentType: "application/x-www-form-urlencoded",
				dataType: "json",
				type: "POST",
				data: {
					"customer.customerName": customerName
				},
				beforeSend: function(jqXHR, settings) {
					
				}
			}).done(function(data, textStatus, jqXHR) {

				//没有包含这个关键字的客户
				if (data && data.length == 0) {
					$customerPopover.popover("hide");
					return;
				}

				var tmpl = $("#customerTmpl").html(),
					result = ejs.render(tmpl, {
						customerList: data
					});

				popoverContent = result;
				$customerPopover.popover("show");

			}).fail(function(jqXHR, textStatus, errorThrown) {
				var message;
				if (textStatus == "error") {
					message = "服务器内部错误，无法获取数据";
				} else if (textStatus == "timeout") {
					message = "浏览器等待数据超时";
				} else if (textStatus == "parsererror") {
					message = "数据解析异常";
				}

				$("#alertModal").data("message", message).modal("show");
			}).always(function(jqXHR, textStatus) {
			
			});
		},
		getFlowBill = function(customerId, dateMillisecond) {
			var $that = $(this);
			$.ajax({
				url: "query_flow_account_list_by_customer_id.action",
				contentType: "application/x-www-form-urlencoded",
				dataType: "json",
				type: "POST",
				data: {
					"flowAccount.id.customerId": customerId,
					"date": dateMillisecond
				},
				beforeSend: function(jqXHR, settings) {
					var tmpl = $("#overlayTmpl").html(),
						result = ejs.render(tmpl);

					$that.parents(".box").append(result);
				}
			}).done(function(data, textStatus, jqXHR) {

				var year = $(".calendar-year").text(),
					$month = $(".calendar-month"),
					$pdf = $(".pdf"),
					$word = $(".word");

				//将之前客户的月账单清除
				$pdf.text("");
				$word.text("");

				// 这个客户没有月账单
				if (data && data.length == 0) {
					return;
				}

				$month.each(function(index, element) {

					$.each(data, function(indexInArray, elementInArray) {

						var calendarDate = new Date(year + "-" + $(element).text()).getTime(),
							flowBillDate = new Date(elementInArray.id.datetime).getTime();

						//有客户账单的月生成文件下载按钮
						if (calendarDate == flowBillDate) {

							var pdfTemplate = $("#pdfTmpl").html(),
								wordTemplate = $("#wordTmpl").html(),

								pdfResult = ejs.render(pdfTemplate, {
									flowAccount: elementInArray,
									date: flowBillDate
								}),

								wordResult = ejs.render(wordTemplate, {
									flowAccount: elementInArray,
									date: flowBillDate
								});
							$pdf.eq(index).html(pdfResult);
							$word.eq(index).html(wordResult);
						}

					});
				});


			}).fail(function(jqXHR, textStatus, errorThrown) {
				var message;
				if (textStatus == "error") {
					message = "服务器内部错误，无法获取数据";
				} else if (textStatus == "timeout") {
					message = "浏览器等待数据超时";
				} else if (textStatus == "parsererror") {
					message = "数据解析异常";
				}

				$("#alertModal").data("message", message).modal("show");
			}).always(function(jqXHR, textStatus) {
				$that.parents(".box").children(".overlay").remove();
			});
		},
		$customerPopover = $('#customerName').popover({
			trigger: "manual",
			html: true,
			placement: "bottom",
			content: generateContent
		}).on({
			keyup: function(event) {
				//选择存在的客户为当前客户成功提示
				if ($customerPopover.parent().hasClass("has-success has-feedback")) {
					$customerPopover.parent().removeClass("has-success has-feedback");
					$customerPopover.nextAll("span").remove();
				}
				$(this).data("customerId",null);
				getCustomer && getCustomer.call(this, event);
			},
			focus: getCustomer,
			blur: function(event) {
				$(this).popover("hide");
			}
		}).on("inserted.bs.popover", function(event) {
			var $that = $(this);

			//点选查到的客户
			$that.nextAll(".popover").find(".popover-content .list-group a").click(function(event) {

				var customerId = $(this).attr("data-customer-id");
				$that.val($(this).text()); //输入框显示所点击的客户名称

				//选择存在的客户为当前客户成功提示
				if (!$that.parent().hasClass("has-success has-feedback")) {
					$that.parent().addClass("has-success has-feedback");
					$that.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
				}

				$that.data("customerId", customerId);
				var dateMillisecond = new Date($(".calendar-year").text()).getTime();
				getFlowBill && getFlowBill.call($that,customerId, dateMillisecond);
			});
		}).data("customerId",null);

	/*
		展示不同的告警信息
	 */
	$("#alertModal").on("show.bs.modal", function(event) {
		var modal = $(this);
		modal.find(".modal-body").text(modal.data("message"));
	});
});
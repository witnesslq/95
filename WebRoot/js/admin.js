$(function() {
	/*
		根据窗口高度来动态调整内容区的高度
	 */
	$.AdminLTE.layout.fixContent = function() {
		var window_height = $(window).height();
		//Set the min-height of the content and sidebar based on the
		//the height of the document.
		if ($("body").hasClass("fixed")) {
			$(".content-wrapper, .right-side").css('height', window_height - $('.main-footer').outerHeight());
		}
	};
	$.AdminLTE.layout.fixContent();

	$(window, ".wrapper").resize(function() {
		$.AdminLTE.layout.fixContent();
	});

	/*
		显示右边菜单
		
	 */
	$.ajax({
		url: "allMenuQuery.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
			var menu = [];
			for (var i = 0; i < data.length; i++) {
				var item = data[i];

				//顶层菜单项
				if (item.pid == "0") {
					data.splice(i--, 1);
					menu.push(item);
				}
			}

			for (i = 0; i < menu.length; i++) {
				item = menu[i];

				//二级菜单
				var secondMenu = [];
				for (var j = 0; j < data.length; j++) {
					var secondItem = data[j];
					if (item.id == secondItem.pid) {
						data.splice(j--, 1);
						secondMenu.push(secondItem);
					}
				}
				item.subMenu = secondMenu;
			}

			var tmpl = $("#menuTmpl").html(),
				result = ejs.render(tmpl, {
					menu: menu
				});

			$("#menu").append(result);
		}
	});
});
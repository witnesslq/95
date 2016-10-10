$(function() {

	/*
		显示右边菜单
	 */
	$.ajax({
		url: "allMenuQuery.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
			console.log(data+"");
			var menu = [];
			for(var i = 0;i<data.length;i++){
				var item = data[i];

				//顶层菜单项
				if(item.pid == "0"){
					data.splice(i,1);
					menu.push(item);
				}
			}

			for(i=0;i<menu.length;i++){
				item = menu[i];

				//二级菜单
				var secondMenu = [];
				for(var j = 0;j<data.length;j++){
					var secondItem = data[j];
					if(item.id == secondItem.pid){
						data.splice(j,1);
						secondMenu.push(secondItem);
					}
				}
				item.subMenu = secondMenu;
			}
			
			var tmpl = $("#menuTmpl").html(),
			result = ejs.render(tmpl,{menu:menu});

			$("#menu").append(result);
		}
	});
});
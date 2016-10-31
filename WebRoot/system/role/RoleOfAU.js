$(function() {

    	/*
    		显示左边菜单
    		
    	 */
    	$.ajax({
    		url: "topMenuQueryAll.action",
    		type: "post",
    		dataType: 'json',
    		success: function(data) {
    		var id=$("#hidd_value").val();
    			var menu = [];
    			for (var i = 0; i < data.length; i++) {
    				var item = data[i];
    				$("#menu").append("<li><a href='roleRightList.jsp?role_id="+id+"&&node_id="+item.id+"' target='content_right'> <span>"+item.title+"</span></a></li>");
    			}
    		}
    	});
    });
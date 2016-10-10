$(function() {
	$.ajax({
		url: "topMenuQueryHomePage.action",
		type: "post",
		dataType: 'json',
		success: function(data) {
			console.log(data);
			var tmpl = $("#topMenuTmpl").html(),
			result = ejs.render(tmpl,{data:data});
			$("#mainMenu").append(result);
		}
	});
});
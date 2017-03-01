
	function loadInfo(id){
	$.ajax({
		url:"treeuserGetById.action", 
		data:"id="+id, 
		dataType:"json", 
	    async:false,
		type:"post",
		success:function (mm) {
	        $("#detail_loginUser").val(mm.loginname);
	        $("#detail_userName").val(mm.username);
	        $("#detail_sex").val(mm.sex=="M"?'男':'女');
	        $("#detail_datepicker").val(getFormatDate(mm.birth));  
	        $("#detail_department").val(mm.deptname);
	        $("#detail_role").val(mm.rolename);
	        $("#detail_job").val(mm.stationname);
	        $("#detail_post").val(mm.postname);
	        $("#detail_companyPhone").val(mm.phonepublic);
	        $("#detail_companyMobPhone").val(mm.mobilepublic);
	        $("#detail_homePhone").val(mm.phoneprivate);
	        $("#detail_PersonPhone").val(mm.mobileprivate);
	        $("#detail_companyMail").val(mm.emailpublic);
	        $("#detail_PersonMail").val(mm.emailprivate);
	        $("#detail_description").val(mm.remark);
	        $("#detail_area").val(mm.areaname);
	        $("#detail_dataCenter").val(mm.dcidname);
	        window.parent.$("#detailUser").modal('show');
		}, 
		error:function (error) {
			 window.parent.$("#tipContent").html("获取单个信息失败！");
			 window.parent.$("#myModal").modal('show'); 
	}});
	}	

jQuery(function($){
    my_initGrid();
});
/**
初始化表格
CustomersData：要填充的数据
*/
function my_initGrid(){
	window['g']=$("#maingrid").ligerGrid({
		checkbox: true,
		rowHeight:25,
		url:"codeQueryList.action",
		columns: [
			{ display: '编码类别', name: 'codetypevalue', width:"10%"},
			{ display: '编码类型', name: 'type',  width:"10%",
				render:function(item){
					if(item.type == "0"){
						return "固定编码";
					}else{
						return "程序编码";
					}
				}
			},
			{ display: '编码前缀', name: 'codevalue',  width:"10%" },
			{ display: '是否显示', name: 'displayvalue',  width:"5%",
				render:function(item){
					if(item.displayvalue == "0"){
						return "否";
					}else{
						return "是";
					}
				}
			},
			{ display: '编码格式', name: 'codeformat',  width:"20%" },
			{ display: '是否显示', name: 'displayformat',  width:"5%",
				render:function(item){
					if(item.displayformat == "0"){
						return "否";
					}else{
						return "是";
					}
				}
			},
			{ display: '序列号起始', name: 'seqvalue',  width:"10%"},
			{ display: '序列号长度', name: 'seqlength',  width:"10%"},
			{ display: '是否显示', name: 'displayseq',  width:"5%",
				render:function(item){
					if(item.displayseq == "0"){
						return "否";
					}else{
						return "是";
					}
				}
			},
			{ display: '备注', name: 'remark',  width:"10%",
				render:function(item){
					return "<span title='"+item.remark+"'>"+item.remark+"</span>";
				}
			 }
		],
		pageSize:10,
		root:"listmodel",
		record:"record",
		rownumbers :false,
		enabledSort :false,
		frozen:false,//放置拖拽表格变形
		height:"100%",
		heightDiff:-8,
		title:"系统编码配置列表",
		toolbar: { items: [
			{ text: '增加', click: itemclick, icon: 'add' },
			{ line: true },
			{ text: '修改', click: itemedit, icon: 'modify' },
			{ line: true },
			{ text: '删除', click: itemdelete, icon: 'delete'}
		]}
	});
}
   
   /**
     打开添加信息窗口的方法
   */
function  itemclick(){
	var url = "bussiness/sxydidc/opcode/OpcodeAdd.jsp";
	winOpen(url,'添加编码',700,400,'添加','取消',function(data){
       	$.ajax({
			url:"codeAdd.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (mm) {
       			if("error" == mm.result){
       				top.$.ligerDialog.error("添加编码信息失败!");
       			}else{
	       			g.addRow(mm);
	       			top.$.ligerDialog.success("添加编码信息成功!");
       			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加编码信息失败!" + error.status,"错误");
		}});
	});
}
   
    /**
     打开修改信息窗口的方法
   */
function  itemedit(){
	var selected = g.getSelected();
	if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
	var id = (g.getSelectedRow()).id; 
	var url = "bussiness/sxydidc/opcode/OpcodeEdit.jsp?id="+id;
	winOpen(url,'修改编码',700,400,'修改','取消',function(data){
       	$.ajax({
			url:"codeModify.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (mm) {
	       		if("error" == mm.result){
	   				top.$.ligerDialog.error("修改编码信息失败!");
	   			}else{
	   				g.updateRow(selected,mm);
       				top.$.ligerDialog.success("修改编码信息成功!","提示");
	   			}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("修改编码信息失败!" + error.status,"错误");
		}});
	});
}
   
   
   /**
    删除信息的方法
   */
function  itemdelete(){
	var selected = g.getSelected();
    if (!selected) {  top.my_alert('请选择要删除的数据行!',"warn"); return; }
    window.top.$.ligerDialog.confirm("确定删除选择的数据", "提示", function (ok) {
	    if (ok) {
	      	g.deleteSelectedRow();
			var selecteds = g.getSelecteds();
			var idstr="";//所有选择行的id
			for(var i=0;i<selecteds.length;i++){
				idstr = idstr + selecteds[i].id;
				if(i!=(selecteds.length-1)){
					idstr = idstr + ",";
				}
			}
			/**
				删除数据库数据
			 */
			$.ajax({
				url:"codeDelete.action?ids="+idstr, 
				dataType:"json", 
				type:"post",
				success:function (mm) {
		       		if("error"==mm.result){
		   				top.$.ligerDialog.error("删除编码信息失败!");
		   			}else{
		   				top.$.ligerDialog.success("删除编码信息成功!");
		   			}
				}, 
				error:function (error) {
					top.$.ligerDialog.error("删除系统配置信息失败!" + error.status);
				}
				});
	     }
	 });
}


function winOpen(url,title,width,height,button1,button2,callback){
	window.top.$.ligerDialog.open({
		width: width, height: height, url: url, title: title, buttons: [{
			text: button1, onclick: function (item, dialog) {
				var fn = dialog.frame.f_validate || dialog.frame.window.f_validate;
				var data = fn();
				if(data){
					callback(data);
					dialog.close();
				}
			}
		},{
			text: button2, onclick: function (item, dialog) {
				dialog.close();
			}
		}]
     });
}
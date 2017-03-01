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
		url:"componentQueryList.action",
		columns: [
			{ display: '组件名', name: 'title', minWidth: 100 ,width:"30%",isSort:true},
			{ display: 'URL', name: 'url', minWidth: 100,width:"40%" },
			{ display: '高度', name: 'height', minWidth: 100,width:"40%" }
		],
		pageSize:10,
		rownumbers:true,
		root:"listmodal",
		record:"record",
		title:"组件管理",
		height: '100%',
		heightDiff:-8,
		frozenCheckbox:false,
		frozenRownumbers:false,
		toolbar: { items: [
			{ text: '增加', click: itemclick, icon: 'add' },
			{ line: true },
			{ text: '修改', click: itemedit, icon: 'modify' },
			{ line: true },
			{ text: '删除', click: itemdelete, icon: 'delete'},
			{ line: true },
			//{ text: '操作权限',click: itemqx2, icon: 'role'},
			//{ line: true },
			//{ text: '按钮权限',click: itemqx3, icon: 'settings'}
		]}
	});
}

	/**
	  打开添加信息窗口的方法
	*/
	function  itemclick(){
		var url = "system/component/ComponentAdd.jsp";
		winOpen(url,'添加组件',490,300,'添加','取消',function(data){
       	$.ajax({
			url:"componentAdd.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (mm) {
				if(mm.result == "error"){
					top.$.ligerDialog.error("添加组件信息失败!");
				}else{
					g.addRow(mm);
	       			top.$.ligerDialog.success("添加组件信息成功!");
				}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加组件信息失败!" + error.status);
		}});
	});
	}
   
    /**
     打开修改信息窗口的方法
   */
   function  itemedit(){
      var selected = g.getSelected();
      if (!selected) { top.my_alert('请选择数据行',"warn"); return; }
      var id = (g.getSelectedRow()).id; 
      var url = "system/component/ComponentEdit.jsp?id="+id;
      winOpen(url,'修改组件信息',490,300,'保存','取消',function(data){
       	$.ajax({
			url:"componentUpdate.action", 
			data:data,
			dataType:"json", 
			type:"post",
			success:function (mm) {
				if(mm.result == "error"){
					top.$.ligerDialog.error("修改组件信息失败!");
				}else{
					 var selected = g.getSelected();
      				 g.updateRow(selected,mm);
	       			 top.$.ligerDialog.success("修改组件信息成功!");
				
				}
			}, 
			error:function (error) {
				top.$.ligerDialog.error("添加组件失败!" + error.status);
			}
			});
		});
   }
   
   
   /**
    删除信息的方法
   */
   /**
   删除信息的方法
  */
  function  itemdelete(){
   var selected = g.getSelected();
   if (!selected) {  top.my_alert('请选择要删除的数据行!',"warn"); return; }
    window.top.$.ligerDialog.confirm("确定删除选择的数据?数据删除后不可恢复！", "提示", function (ok) {
	if (ok) {
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
     $.post("componentDelete.action", { ids: idstr},
      function(data){
       g.deleteSelectedRow();
       top.my_alert("删除数据成功!","success");
     });
    }
    });
  }
   
   
   function  itemdelete1(){
    var selected = g.getSelected();
    if (!selected) {  top.$.ligerDialog.warn('请选择要删除的数据行!'); return; }
    window.top.$.ligerDialog.confirm("确定删除选择的数据?数据删除后不可恢复！", "提示", function (ok) {
	if (ok) {
      var selecteds = g.getSelecteds();
      var idstr="";//所有选择行的id
      for(var i=0;i<selecteds.length;i++){
         idstr = idstr + selecteds[i].id;
         if(i!=(selecteds.length-1)){
         idstr = idstr + ",";
         }
        }
        if(QueryIsDelete("role",idstr)=="false"){
			top.$.ligerDialog.error("删除数据失败，已分配人员的角色不能删除！");
		}else{
	        /**
	           删除数据库数据
	        */
	      $.post("componentDel.action", { ids: idstr},
	       function(data){
	       		if(data.result == "success"){
			      	g.deleteSelectedRow();
			        top.$.ligerDialog.success("删除数据成功!");
		        }else{
		        	top.$.ligerDialog.error("删除数据失敗!");
		        }
	      },"json");
      }
     }
     });
   }
   
/**
	是否可以删除信息
*/
function  QueryIsDelete(type,id){
   	var dataPost = {"type":type,"ids":id};
   	var dataMM;
	$.ajax({
		url:"componentDel.action", 
		data:dataPost, 
		async:false,
		dataType:"json", 
		type:"post",
		success:function (mm) {
			dataMM = mm; 
		}, 
		error:function (error) {
			dataMM = false;
		}
	});
	return dataMM;
  }
/**
 打开窗口的方法
 */
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
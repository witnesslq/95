<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>网络设备信息列表页面</title>
	<jsp:include page="../common/head.jsp" flush="true"/>  
    <script type="text/javascript">
		$(function($){
			var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
			var dialogData = dialog.get('data');//获取data参数
			var parms=dialogData.name;
			$("#searchform").ligerForm();
    		$("#Button1").click(function(){
				searchclick(parms);
			});	
			$("#Button2").click(function(){
				$('#txtName').val('');
			});
			$("#parmsLength").val(parms.length);		
			window['g']=$("#maingrid").ligerGrid(queryDeviceData(parms));  			  
		});
		
			
    	function f_validate(){
    		var selected = g.getSelected();
    		if (!selected) {  top.$.ligerDialog.warn('请选择行'); return; }
			return g.getSelectedRow();
		}
		
		function searchclick(parms){
    		var devicename= $('#txtName').val();
    		if(devicename!=null&&devicename.trim().length!=0){
    			if(parms.length>$("#parmsLength").val()){//说明已经添加了按关键字查询条件需要删除原有的过滤条件重新添加
    				parms.pop();
    			}     		
    			parms.push({name:'device.name',value:devicename}); 
    		}else{
    			if(parms.length>$("#parmsLength").val()){//说明已经添加了按关键字查询条件需要删除原有的过滤条件重新添加
    				parms.pop();
    			}
    		}    		
			g.setOptions({newPage:1});
			g.setOptions({parms: parms});
			g.loadData();
		}		    
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>    
</head>
<body> 
	<form id="searchform">
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
            <tr>
                <td align="right" class="l-table-edit-td">设备名称:</td>
                <td align="left" ></td>
                <td align="left" class="l-table-edit-td"><input name="txtName" type="text" id="txtName" ltype="text" /></td>
                <td align="left"><input type="button" value="查询" id="Button1" class="l-button l-button-submit" /> </td>
                <td align="left"><input type="button" value="重置" id="Button2" class="l-button l-button-reset"/></td>
            </tr>
        </table>	
	</form> 
    <div id="maingrid" style="margin: 5px;"></div>
    <input type="hidden" id="parmsLength"/>
</body>
</html>

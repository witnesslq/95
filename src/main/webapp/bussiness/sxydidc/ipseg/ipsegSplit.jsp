<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>IP段拆分</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
    <script type="text/javascript">
        $(function (){
        	$("#form").ligerForm({
        		inputWidth: 20, 
        		labelWidth: 120        	
        	});
        	getIPSegInfo();
        });
        
		function getIPSegInfo(){
		  $.ajax({
				url:"queryIPSegById.action", 
				data:{"id":"<%=id%>"}, 
				async:false,
				dataType:"json", 
				type:"post",
				success:function (msg) {
					$("#startip0").append(msg.startip);
					$("#endip0").append(msg.endip);
					$("#count0").append(msg.count);
					$("#id1").val(msg.id);
					$("#startip1").val(msg.startip);
					$("#count1").val(msg.count);
					$("#endip1").val(msg.endip);
					$("#totalstartip").val(msg.startip);
					$("#totalendip").val(msg.endip);
					$("#totalcount").val(msg.count);						
				}, 
				error:function (error) {
					alert("获取IP段信息失败" + error.status);
				}
			});
		} 
		
		function getId(){
			return $("#totalid").val();
		}
		
		function fillEndIP(group){
			var no=parseInt(group);
        	var startip=$("#startip"+no).val();
        	var count=$("#count"+no).val();
            var totalcount=0;
            $("input[name='count']").each(function(){
            	totalcount=totalcount+parseInt($(this).val());
            });
            var array=new Array();
            array=startip.split(".");
            var lastip;
            if(count=='0'){
            	lastip=parseInt(array[3]);
            }else{
            	if(parseInt(totalcount)>parseInt($("#totalcount").val())){
            		$.ligerDialog.warn("拆分IP地址数量大于原有IP地址总数量");
            		$("#endip"+no).val('');
            		$("#endip"+no).val($("#totalendip").val());
            		return;
            	}else{
            		lastip=parseInt(array[3])+parseInt(count)-1;
            	}
            }
            
            if(parseInt(totalcount)>=parseInt($("#totalcount").val())){
            	$("#addBtn"+no).attr("disabled","disabled");
            }

             var endip=array[0]+"."+array[1]+"."+array[2]+"."+lastip.toString();
             $("#endip"+no).val(endip);		
		
		}; 
		
		function hideTip(group){
			$("#endip"+group).ligerHideTip();
		};
		
		function addRow(group){
			var no=parseInt(group)+1;
        	var endip=$("#endip"+group).val();
            var array=endip.split(".");
            var lastip=parseInt(array[3])+1;
			var startip=array[0]+"."+array[1]+"."+array[2]+"."+lastip.toString();
			var totalcount=$("#totalcount").val();
            var count=0;
            $("input[name='count']").each(function(){
            	count=count+parseInt($(this).val());
            });
				
			var tr="<tr id='row"+no+"'>"
               			+"<td align='right' class='l-table-edit-td'>起始IP:</td>"
                		+"<td align='left' class='l-table-edit-td'>"
                			+"<input type='hidden' id='id"+no+"' value=''/>"
                    		+"<input name='startip' type='text' id='startip"+no+"' ltype='text' size='17' value='"+startip+"'/>"
                		+"</td>"
                		+"<td align='right' class='l-table-edit-td'>IP数量:</td>"
                		+"<td align='left' class='l-table-edit-td'>"
							+"<input name='count' type='text' id='count"+no+"' ltype='text' size='17' onblur=\"fillEndIP('"+no+"');\"/>"
                		+"</td>"                
                		+"<td align='right' class='l-table-edit-td'>终止IP:</td>"
                		+"<td align='left' class='l-table-edit-td'>"
                    		+"<input name='endip' type='text' id='endip"+no+"' ltype='text' size='17'/>"
                		+"</td>"                
                		+"<td align='center' class='l-table-edit-td'>" 
                			+"<input name='button' type='button' id='addBtn"+no+"' value='增加' class='l-button' onclick=\"addRow('"+no+"');\"/>"
                		+"</td>"
                		+"<td align='center' class='l-table-edit-td'>" 
                			+"<input type='button' id='delBtn"+no+"' value='删除' class='l-button' onclick=\"delRow('"+no+"');\"/>"
                		+"</td>"                                               
            		+"</tr>"; 
			$("table:eq(1)").append(tr);
			$("#count"+no).val(totalcount-count);
			$("#endip"+no).val($("#totalendip").val());
		}
		
		function delRow(group){
			$("#row"+group).remove();
		}
		
		
	    /**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var data = {"splitArray":[]};
			var element;
			var no;
			$("input[name='startip']").each(function(i){
				no=parseInt(i)+1;
				element={"id":$("#id"+no).val(),"startip":$("#startip"+no).val(),"endip":$("#endip"+no).val(),"count":$("#count"+no).val()};
				data.splitArray.push(element);
			});
			return data;
		}      
        
    </script>
    <style type="text/css">
        html,body{ 
	        margin:0;
	        padding:0;
        	font-size:14px;
        }
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}        
    </style>
</head>
<body style="padding:10px"> 
	<form id="form">
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >                  
            <tr>
                <td align="right" class="l-table-edit-td">起始IP:<input id="totalstartip" type="hidden" /></td>
                <td align="center" class="l-table-edit-td"><span id="startip0"></span></td>
                <td align="right" class="l-table-edit-td">IP数量:<input id="totalcount" type="hidden" /></td>
                <td align="center" class="l-table-edit-td"><span id="count0"></span></td>                
                <td align="right" class="l-table-edit-td">终止IP:<input id="totalendip" type="hidden" /></td>
                <td align="center" class="l-table-edit-td"><span id="endip0"></span></td>                
			</tr>
			
        </table> 
         
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >                  
            <tr id="row1">
                <td align="right" class="l-table-edit-td">起始IP:</td>
                <td align="left" class="l-table-edit-td">
                	<input type="hidden" id="id1" />
                    <input name="startip" type="text" id="startip1" ltype="text" size="10"/>
                </td>
                <td align="right" class="l-table-edit-td">IP数量:</td>
                <td align="left" class="l-table-edit-td">
					<input name="count" type="text" id="count1" ltype="text" size="3" onblur="fillEndIP('1');"/>
                </td>                
                <td align="right" class="l-table-edit-td">终止IP:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="endip" type="text" id="endip1" ltype="text" size="10"/>
                </td>                
                <td align="center" class="l-table-edit-td"> 
                	<input name="button" type="button" id="btn1" value="增加" class="l-button" onclick="addRow('1');"/>
                </td>
			</tr>
        </table>	
	</form>	
</body>
</html>

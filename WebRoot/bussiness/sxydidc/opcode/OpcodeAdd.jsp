<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编码添加</title>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>css/reportMain.css"/>
    <link    rel="stylesheet" type="text/css" href="<%=basePath %>include/LigerUI/skins/Aqua/css/ligerui-all.css"/>
    <link    rel="stylesheet" type="text/css"  href="<%=basePath %>include/LigerUI/skins/ligerui-icons.css" /> 
    <script  type="text/javascript"  src="<%=basePath  %>include/jQuery/jquery-1.3.2.min.js"></script>    
    <script  type="text/javascript"  src="<%=basePath  %>include/LigerUI/js/ligerui.all.js" ></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath  %>include/LigerUI/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
        var form;
        var dataGrid = [
 	                 { id: 'yyyy', name: '年'}, 
 	                 { id: 'MM', name: '月'}, 
 	                 { id: 'dd', name: '日'}, 
 	                 { id: 'HH', name: '时'}, 
 	                 { id: 'mm', name: '分'}, 
 	                 { id: 'ss', name: '秒'}, 
 	                 { id: 'SSS', name: '毫秒'}
                 ]; 
        
        $(function (){
        	form = $("#form2").ligerForm({
                inputWidth: 170, 
                space: 20,
				validate : true,
                fields: [ 
               		{ label: "", name: "ctype", type: "hidden"},
                    { label: "编码类别", name: "codetype", newline: true, type: "select",width:230, validate: { required: true},
                    	editor:{
                    		url:"queryConfigSelected.action",
	                    	parms :[{name:"dtype",value:"CODETYPE"}],
                    		width:180
                    	}	
                    },
                    { label: "编码类型", name: "types", newline: false, type: "select",width:150, validate: { required: true},
                    	editor:{
                    		data:[{"id":"0","text":"固定编码"},{"id":"1","text":"程序编码"}],
                    		width:130,
                    		onSelected : function(value,text){
                    			$("[name=ctype]").val(value);
                    			if(value == "0"){
                    			 	form.setVisible(["codevalue", "displayvalue", "codeformat", "displayformat", "seqvalue", "seqlength", "displayseq"], true);
                    			}else{
                    				form.setVisible(["codevalue", "displayvalue", "codeformat", "displayformat", "seqvalue", "seqlength", "displayseq"], false);
                    			}
                    		}
                    	}	
                    },
                    { label: "编码前缀", name: "codevalue", newline: true, type: "text", width:230,validate: {maxlength: 100} },
                    { label: "是否显示",  labelWidth: 0, name: "displayvalue", newline: false, type: "select", width:132,
                    	editor:{
                    		data:[{id:"1",text:"显示"},{id:"0",text:"不显示"}],
                    		initValue:"1"
                    	}	
                    },
                    { label: "编码格式", name: "codeformat", newline: true, type: "checkboxlist",width:230,
                   		 editor:{
                    		data: dataGrid,
                    		textField: 'name' 
                    	}	
                    },
                    { label: "是否显示",  labelWidth: 0, name: "displayformat", newline: false, type: "select", width:132,
                    	editor:{
                    		data:[{id:"1",text:"显示"},{id:"0",text:"不显示"}],
                    		initValue:"1"
                    	}	
                    },
                    { label: "序列号起始", name: "seqvalue", newline: true, type: "digits", width:80,validate: {maxlength: 6} },
                    { label: "长度",  labelWidth: 50, name: "seqlength", newline: false, type: "digits",width:80,},
                    { label: "是否显示", labelWidth: 0, name: "displayseq", newline: false, type: "select", width:132,
                    	editor:{
                    		data:[{id:"1",text:"显示"},{id:"0",text:"不显示"}],
                    		initValue:"1"
                    	}	
                    },
                    { label: "备注", name: "remark", newline: true, width:470,type:"textarea", validate: { maxlength: 200 }}
                ]
            }); 
            hideDiv();
        });
		function hideDiv(){
			form.setVisible(["codevalue", "displayvalue", "codeformat", "displayformat", "seqvalue", "seqlength", "displayseq"], false);
		}
        function f_validate(){ 
			if(form.valid()){
				return datePost();
			}else{
			    form.showInvalid();
			}
		}
		function datePost(){
			var formData = form.getData();
			var codetype = formData.codetype;
			var types = formData.ctype;
			var codevalue = formData.codevalue;
			if(codevalue == null){
				codevalue = "";
			}
			var displayvalue = formData.displayvalue;
			var codeformat = formData.codeformat;
			var displayformat = formData.displayformat;
			var seqvalue = formData.seqvalue;
			if(seqvalue == null){
				seqvalue = "";
			}
			var seqlength = formData.seqlength;
			if(seqlength == null){
				seqlength = "";
			}
			var displayseq = formData.displayseq;
			var dateFormat = "";
			if(codeformat == null || codeformat == "null"){
			
			}else{
				dateFormat = codeformat.split(";").join("");
			}
			var remark = formData.remark;
			var data = {"model.type":types,"model.codetype":codetype,"model.codevalue":codevalue,"model.displayvalue":displayvalue,"model.codeformat":dateFormat,"model.displayformat":displayformat,"model.seqvalue":seqvalue,"model.seqlength":seqlength,"model.displayseq":displayseq,"model.remark":remark};
			return data;
		}
    </script>
    <style type="text/css">
        body{ 
        	font-size:14px;
        }
    </style>
</head>
<body style="padding:10px">   
	<form id="form2"></form> 
</body>
</html>

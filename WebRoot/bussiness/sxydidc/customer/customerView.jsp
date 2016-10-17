<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
String type = request.getParameter("type");

String busOrdCount=request.getParameter("busOrdCount")!=null?request.getParameter("busOrdCount").toString():"0";
String serOrdCount=request.getParameter("serOrdCount")!=null?request.getParameter("serOrdCount").toString():"0";

String serverCount=request.getParameter("serverCount")!=null?request.getParameter("serverCount").toString():"0";
String rackCount=request.getParameter("rackCount")!=null?request.getParameter("rackCount").toString():"0";
String useatCount=request.getParameter("useatCount")!=null?request.getParameter("useatCount").toString():"0";
String ipsegCount=request.getParameter("ipsegCount")!=null?request.getParameter("ipsegCount").toString():"0";
String ipCount=request.getParameter("ipCount")!=null?request.getParameter("ipCount").toString():"0";
String portCount=request.getParameter("portCount")!=null?request.getParameter("portCount").toString():"0";

String preServerCount=request.getParameter("preServerCount")!=null?request.getParameter("preServerCount").toString():"0";
String preRackCount=request.getParameter("preRackCount")!=null?request.getParameter("preRackCount").toString():"0";
String preUseatCount=request.getParameter("preUseatCount")!=null?request.getParameter("preUseatCount").toString():"0";
String preIpsegCount=request.getParameter("preIpsegCount")!=null?request.getParameter("preIpsegCount").toString():"0";
String preIpCount=request.getParameter("preIpCount")!=null?request.getParameter("preIpCount").toString():"0";
String prePortCount=request.getParameter("prePortCount")!=null?request.getParameter("prePortCount").toString():"0";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
    <title>客户信息展示</title>
	<jsp:include page="../common/head.jsp" flush="true"/>
	<link    rel="stylesheet" type="text/css" href="<%=basePath %>css/flowStyle.css"/>
    <script type="text/javascript">
        var form; 
        var navtab;
        var prenavtab;
        var bustab;
        $(function (){
  			$("#main").ligerPanel({
                 title:'客户信息',
                 width:'98%',
                 height:'670' 
             });
             
  			$("#orderPanel").ligerPanel({
                 title:'客户工单信息',
                 width:'98%',
                 height:'358'  
             });
             
  			$("#productPanel").ligerPanel({
                 title:'客户产品信息',
                 width:'98%',
                 height:'300',
                 url:'<%=basePath%>bussiness/sxydidc/product/custProduct.jsp?custid=<%=id%>&orderid='
             }); 
  			$("#contractPanel").ligerPanel({
                 title:'客户合同信息',
                 width:'98%',
                 height:'300',
                 url:'<%=basePath%>bussiness/sxydidc/contract/custContract.jsp?custid=<%=id%>&orderid='
             }); 
 
   			$("#resPanel").ligerPanel({
                 title:'客户资源信息',
                 width:'98%',
                 height:'358'
             }); 
         
   			$("#occupiedResPanel").ligerPanel({
                 title:'客户预占资源信息',
                 width:'98%',
                 height:'358'
             });                        
                                               
        	form = $("#form").ligerForm({
        		inputWidth: 180, 
        		labelWidth: 120, 
        		space: 40, 
				validate : true,
				readonly:true,
                fields: [ 
               		{ label: "客户编号",name: "no",newline: true},
                    { label: "客户类型",name: "type",newline: false,type: "select",
                        editor:{
                    		data:queryDictionary('CUSTOMERTYPE',null),
                    		initValue:"",
                    		readonly:true
                    	}
                    },
                    { label: "客户级别",name: "customerlevel",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTOMERLEVEL',null),
                    		initValue:"1",
                    		readonly:true
                    	}                   
                    },               		
                    { label: "客户名称",name: "name",newline: false, type: "text"},
                    { label: "客户简称",name: "sortname",newline: true,type: "text"},
                    { label: "客户性质",name: "customerproperty",newline: false,type: "select",
                        editor:{
                    		data:queryDictionary('CUSTOMERPROPERTY',null),
                    		initValue:"",
                    		readonly:true
                    	}
                    },
                    { label: "上级客户",name: "parentid",newline: true,type: "text"},                             
                    { label: "客户省份",name: "province",newline: false,type: "select",
						editor:{
                    		data:[{id:'BEIJING',text:'北京市'},                        
								{id:'HEBEI',text:'河北省'},
								{id:'HENAN',text:'河南省'},
								{id:'YUNNAN',text:'云南省'},
								{id:'LIAONING',text:'辽宁省'},
								{id:'HEILONGJIANG',text:'黑龙江省'},
								{id:'HUNAN',text:'湖南省'},
								{id:'ANHUI',text:'安徽省'},
								{id:'SHANDONG',text:'山东省'},
								{id:'XINJIANG',text:'新疆省'},
								{id:'JIANGSU',text:'江苏省'},
								{id:'ZHEJIANG',text:'浙江省'},
								{id:'JIANGXI',text:'江西省'},
								{id:'HUBEI',text:'湖北省'},
								{id:'GUANGXI',text:'广西省'},
								{id:'GANSU',text:'甘肃省'},
								{id:'SHANXI',text:'山西省'},
								{id:'NEIMENG',text:'内蒙古'},
								{id:'SHAANXI',text:'陕西省'},
								{id:'JILING',text:'吉林省'},
								{id:'FUJIAN',text:'福建省'},
								{id:'GUIZHOU',text:'贵州省'},
								{id:'GUANGDONG',text:'广东省'},
								{id:'QINGHAI',text:'青海省'},
								{id:'XIZANG',text:'西藏'},
								{id:'SICHUAN',text:'四川省'},
								{id:'NINGXIA',text:'宁夏省'},
								{id:'HAINAN',text:'海南省'},
								{id:'TAIWAN',text:'台湾省'},
								{id:'XIANGGANG',text:'香港'},
								{id:'AOMEN',text:'澳门'}
							],
                    		initValue:"SHANXI",
                    		readonly:true
                    	}                     
                    },
                     { label: "客户经理",name: "manager",newline: true,type: "popupedit",
						editor: {
                			condition: {
                   			 	prefixID: 'condtion_',
                    			fields: [{ name: 'loginname',type:'text', label: '客户经理' }]
                			},
                			readonly:true,
                			grid: selectManager(true),
                			valueField: 'id',
                			textField: 'loginname',
                			width: 600,
                			onButtonClick:function(){
                				$.ligerui.get("manager").clear();
                			}       									
						}
                    
                    },
                    { label: "客户经理手机",name: "managermobile",newline: false,type: "text"},
                    { label: "客户联系人",name: "contactname",newline: true,type: "text"},
                    { label: "客户联系人手机",name: "mobilephone",newline: false,type: "text"},
                    { label: "座机号码",name: "contactphone",newline: true,type: "text"},
                    { label: "法人代表",name: "corporate",newline: false,type: "text"},
                    { label: "通讯地址",name: "contactaddress",newline: true,type:"text",width:520},
                    { label: "客户域级",name: "customerfield",newline: true,type: "select",
                         editor:{
                    		data:queryDictionary('CUSTFIELD',null),
                    		readonly:true,
                    		initValue:""
                    	}                   
                    },                    
                    { label: "ICP证号",name: "icpno",newline: false,type: "text"}, 
                    { label: "网站名称",name: "sitename",newline: true,type: "text"},
                    { label: "网站主域名",name: "domainname",newline: false,type: "text"},
                    { label: "技术负责人",name: "skillpeople",newline: true,type: "text"},
                    { label: "引用内容域名",name: "subdomain",newline: false, type: "text"},
                    { label: "引用内容",name: "content",newline: true,type: "text"},
                    { label: "带宽要求",name: "bandwidth",newline: false,type: "text"},
                    { label: "引用方式",name: "method",newline: true,type: "text"},
                    { label: "调度方式",name: "dispatch",newline: false,type: "text"},   
                    { label: "主要协议",name: "prot",newline: true,type: "text"}, 
                    
                    { label: "开户属地",name: "regionid",newline: false,type: "select",
						editor: {
							width : 180, 
							selectBoxWidth: 190,
							selectBoxHeight: 190, 
							valueField: 'id',
							treeLeafOnly: false,
							readonly:true,
							tree: { 
								url:"cropDeptTreeQuery.action", 
								ajaxType:'post',
								idFieldName: 'id',
								parentIDFieldName: 'pid',
								checkbox: false								
							}							
						}                    
                    },   
                    { label: "备案名称",name: "registername",newline: true,type: "text"},
                    { label: "公司名称",name: "companyname",newline: false,type: "text"},
                    { label: "合同终止日期",name: "enddate",newline: true,type: "date",readonly:true}, 
                    { label: "SLA",name: "slano",newline: true,width:520,type: "textarea"},                                                                                 
                    { label: "备注", name: "remark", newline: true, width:520,type:"textarea", validate: { maxlength: 200 }}
                ]
            });
             
            $("#busTab").ligerTab({
           		onAfterSelectTabItem:function(tabid){
           			var url;
					switch (tabid+"-bus"){
               			 case "home-bus":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/custOrder.jsp?custid=<%=id%>&orderid=&type=<%=type%>'></iframe>";
                    	 	break;					
               			 case "tabitem1-bus":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/serverOrder.jsp?custid=<%=id%>'></iframe>";
                    	 	break;                      	 	                    	 	                    	 	                    	 	
                    };
           			$("#"+tabid+"-bus").html(url);
           			bustab.reload(bustab.getSelectedTabItemID()+"-bus");
           		}
            });
            bustab = $("#busTab").ligerGetTabManager();           
            
            $("#tab").ligerTab({
           		onAfterSelectTabItem:function(tabid){
           			var url;
					switch (tabid){
               			 case "home":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=SERVER&custid=<%=id%>&orderid='></iframe>";
                    	 	break;					
               			 case "tabitem1":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=RACK&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem2":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=USEAT&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem3":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=IPSEG&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem4":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=IP&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem5":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=PORT&custid=<%=id%>&orderid='></iframe>";
                    	 	break;                     	 	                    	 	                    	 	                    	 	
                    };
           			$("#"+tabid).html(url);
           			navtab.reload(navtab.getSelectedTabItemID());
           		}
            }); 
            navtab = $("#tab").ligerGetTabManager();
            
            $("#preTab").ligerTab({
           		onAfterSelectTabItem:function(tabid){
           			var url;
					switch (tabid+"-pre"){
               			 case "home-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=SERVER&custid=<%=id%>&orderid='></iframe>";
                    	 	break;					
               			 case "tabitem1-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=RACK&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem2-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=USEAT&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem3-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=IPSEG&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem4-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=IP&custid=<%=id%>&orderid='></iframe>";
                    	 	break; 
               			 case "tabitem5-pre":
               			 	url="<iframe frameborder='0' src='<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=PORT&custid=<%=id%>&orderid='></iframe>";
                    	 	break;                     	 	                    	 	                    	 	                    	 	
                    };
           			$("#"+tabid+"-pre").html(url);
           			prenavtab.reload(prenavtab.getSelectedTabItemID()+"-pre");
           		}
            });
            prenavtab = $("#preTab").ligerGetTabManager();
              
            getCustomerInfo();
            init();
        });
		
		/**供回调方法使用*/
		function f_validate(){ 
			if(form.valid()){
				return datePost();
			}else{
			    form.showInvalid();
			}
		}
		
		/**获取表单要保存的数据以json格式返回*/
		function datePost(){
			var formData = form.getData();		
			var data = {"customer.id":"<%=id %>",
						"customer.no":formData.no,
						"customer.name":formData.name,
						"customer.type":formData.type,
						"customer.customerlevel":formData.customerlevel,
						"customer.contactname":formData.contactname,
						"customer.mobilephone":formData.mobilephone,
						"customer.contactphone":formData.contactphone,
						"customer.contactaddress":formData.contactaddress,
						"customer.customerproperty":formData.customerproperty,
						"customer.parentid":formData.parentid,			
						"customer.sortname":formData.sortname,			
						"customer.corporate":formData.corporate,			
						"customer.customerfield":formData.customerfield,	
						"customer.icpno":formData.icpno,					
						"customer.sitename":formData.sitename,				
						"customer.domainname":formData.domainname,			
						"customer.skillpeople":formData.skillpeople,		
						"customer.subdomain":formData.subdomain,			
						"customer.content":formData.content,				
						"customer.bandwidth":formData.bandwidth,			
						"customer.method":formData.method,					
						"customer.dispatch":formData.dispatch,	
						"customer.prot":formData.prot,				
						"customer.province":formData.province,				
						"customer.regionid":formData.regionid,				
						"customer.registername":formData.registername,		
						"customer.companyname":formData.companyname,		
						"customer.slano":formData.slano,
						"customer.enddate":formData.enddate,
						"customer.remark":formData.remark
			};
			return data;
		}
		
		/**表单赋值函数*/
		function getCustomerInfo(){
		  $.ajax({
			url:"queryCustomerById.action", 
			data:{"id":"<%=id%>","type":"<%=type%>"}, 
			async:false,
			dataType:"json", 
			type:"post",
			success:function (msg) {
		       liger.get("form").setData({
					no:msg.no,
					name:msg.name,
					type:msg.type,
					customerlevel:msg.customerlevel,
					contactname:msg.contactname,
					mobilephone:msg.mobilephone,
					contactphone:msg.contactphone,
					contactaddress:msg.contactaddress,
					customerproperty:msg.customerproperty,
					parentid:msg.parentid,			
					sortname:msg.sortname,			
					corporate:msg.corporate,			
					customerfield:msg.customerfield,	
					icpno:msg.icpno,					
					sitename:msg.sitename,				
					domainname:msg.domainname,			
					skillpeople:msg.skillpeople,		
					subdomain:msg.subdomain,			
					content:msg.content,				
					bandwidth:msg.bandwidth,			
					method:msg.method,					
					dispatch:msg.dispatch,	
					prot:msg.prot,				
					province:msg.province,				
					regionid:msg.regionid,				
					registername:msg.registername,		
					companyname:msg.companyname,		
					slano:msg.slano,
					enddate:msg.enddate,
					remark:msg.remark,
					managermobile:msg.managermobile			
				});
				$.ligerui.get("manager").setText(msg.managername);
			}, 
			error:function (error) {
				alert("获取客户信息失败" + error.status);
			}
			});
		}
		
		function init(){
			var regionid=$.ligerui.get("regionid").getValue();
			if($.trim(regionid).length==0){
				$.ligerui.get("regionid").setText('');
			}			
		};
		
		function selectManager(checkbox){
            var options = {
            	url: "UserQueryList.action",
            	checkbox: checkbox,
				columns: [
					{ display: 'ID', name: 'id', align: 'left',minWidth: 10,width: 10,hide:true,isAllowHide:false},
					{ display: '登陆名', name: 'loginname', minWidth:30 ,width:100,isSort:true},
					{ display: '用户姓名', name: 'username', minWidth: 30 ,width:100,isSort:true},
					{ display: '性别', name: 'sex', minWidth: 20 ,width:80,
						render: function (item){
							if (item.sex == 'M'){ 
								return '男';
							}else{
								return '女';
							} 
		        		}
					},
					{ display: '手机', name: 'mobileprivate', minWidth: 110 ,width:100,isSort:true},
					{ display: '邮箱', name: 'emailprivate', minWidth: 120 ,width:200,isSort:true},
					{ display: '备注', name: 'remark', minWidth: 150,width:200 }
				], 
                pageSize: 10,
				rownumbers:true,
				root:"listmodal",
				record:"record"                
             };
             return options;			
		}		
				
    </script>
    <style type="text/css">
        html,body{ 
	        margin:0;
	        padding:0;
        	font-size:14px;
        }
    </style>
</head>
<body style="padding:10px">
	   <div style="text-align: center;width:98%;margin-bottom: 10px;" ><font size="5em">客户信息展示</font></div>
	   <div id="main">
	   		<form id="form"></form>
	   </div>
       <div id="orderPanel" style="margin-top:5px;" >
       		<div id="busTab" style="padding:2px;overflow:hidden; border:1px solid #A3C0E8;"> 
       			<div  tabid="home-bus" title="业务单信息(<%=busOrdCount%>)" style="height:300px;">
       				<iframe frameborder="0" src="<%=basePath%>bussiness/sxydidc/order/custOrder.jsp?custid=<%=id%>&orderid=&type=<%=type%>"></iframe>
       			</div>
       			<div id="tabitem1-bus" title="服务单信息(<%=serOrdCount%>)" style="height:300px;"></div>                                                     
    		</div>       
       </div>	   	
       <div id="productPanel" style="margin-top:5px;" ></div>	   	  
       <div id="contractPanel"  style="margin-top:5px;"></div>
       <div id="resPanel"  style="margin-top:5px;">
       		<div id="tab" style="padding:2px;overflow:hidden; border:1px solid #A3C0E8;"> 
       			<div  tabid="home" title="业务服务器(<%=serverCount%>)" style="height:300px;">
       				<iframe frameborder="0" src="<%=basePath%>bussiness/sxydidc/order/orderRes.jsp?restype=SERVER&custid=<%=id%>&orderid="></iframe>
       			</div>
       			<div id="tabitem1" title="机架(<%=rackCount%>)" style="height:300px;"></div> 
       			<div id="tabitem2" title="U位(<%=useatCount%>)" style="height:300px"></div>
      	 		<div id="tabitem3" title="IP段(<%=ipsegCount%>)" style="height:300px"></div> 
       			<div id="tabitem4" title="IP地址(<%=ipCount%>)" style="height:300px"></div>
       			<div id="tabitem5" title="端口(<%=portCount%>)" style="height:300px"></div>                                                     
    		</div>       
       </div>
       <div id="occupiedResPanel"  style="margin-top:5px;">
       		<div id="preTab" style="padding:2px;overflow:hidden; border:1px solid #A3C0E8;"> 
       			<div  tabid="home-pre" title="业务服务器(<%=preServerCount%>)" style="height:300px;">
       				<iframe frameborder="0" src="<%=basePath%>bussiness/sxydidc/order/occupiedRes.jsp?restype=SERVER&custid=<%=id%>&orderid="></iframe>
       			</div>
       			<div id="tabitem1-pre" title="机架(<%=preRackCount%>)" style="height:300px;"></div> 
       			<div id="tabitem2-pre" title="U位(<%=preUseatCount%>)" style="height:300px"></div>
      	 		<div id="tabitem3-pre" title="IP段(<%=preIpsegCount%>)" style="height:300px"></div> 
       			<div id="tabitem4-pre" title="IP地址(<%=preIpCount%>)" style="height:300px"></div>
       			<div id="tabitem5-pre" title="端口(<%=prePortCount%>)" style="height:300px"></div>                                                     
    		</div>       
       </div>       
</body>
</html>

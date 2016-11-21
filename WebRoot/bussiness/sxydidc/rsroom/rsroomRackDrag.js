
var RackTool = (function(){
	return {
		selectedRackArray:[],
		selectRack:function(selectedRack,event){
		
		
			event = window.event || event;
			
			
			
			if(event.ctrlKey){
				if(selectedRack!="1"){	
				//为已选择元素添加   选中标识  选中isSelected 为true  未选中为false
				if(selectedRack.isSelected){
					selectedRack.isSelected = false;
					selectedRack.className = 'rack';
					var currentSelectedRackArray = [];
					for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
						
						if(this.selectedRackArray[i].id != selectedRack.id)
							currentSelectedRackArray[currentSelectedRackArray.length] = this.selectedRackArray[i];
						
					}
					this.selectedRackArray = currentSelectedRackArray;
				}else{
					selectedRack.isSelected = true;
					selectedRack.className = selectedRack.className +' selected';
					if(this.isNotExistThisRack(selectedRack)){
						this.selectedRackArray[this.selectedRackArray.length] = selectedRack;
					}
				}
				}
			}else{
				
				for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
					this.selectedRackArray[i].isSelected = false;
					this.selectedRackArray[i].className = 'rack';
				}
				this.selectedRackArray = [];
				selectedRack.isSelected = true;
				selectedRack.className = selectedRack.className +' selected';
				if(selectedRack!="1"){		
				if(this.isNotExistThisRack(selectedRack)){
					this.selectedRackArray[this.selectedRackArray.length] = selectedRack;
				}}
			}
			
			
		
			
			stopPrapagation(event)
			return false;
		},
		isNotExistThisRack:function(rack){
			
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				if(this.selectedRackArray[i].id == rack.id)
				{
					return false;
				}
			}
			return true;
		},
		calDeltaPosForEachSelectedRack:function(event,scroll){
			//文档坐标
			var startX = event.clientX + scroll.x;
			var startY = event.clientY + scroll.y;
			
			//元素的偏移坐标 文档坐标
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				var elementToDrag = this.selectedRackArray[i];
				var origx = elementToDrag.offsetLeft;
				var origy = elementToDrag.offsetTop;
				
				
				var deltaX = startX - origx;
				var deltaY = startY - origy;	
				elementToDrag.deltaX = deltaX;
				elementToDrag.deltaY = deltaY;
			}
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				var elementToDrag = this.selectedRackArray[i]
			

				
			}
		},
		setPosForEachSelectedRack:function(e,scroll){
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				var elementToDrag = this.selectedRackArray[i];
				elementToDrag.style.left = (e.clientX + scroll.x - elementToDrag.deltaX)+'px';
				elementToDrag.style.top = (e.clientY + scroll.y - elementToDrag.deltaY)+'px';
				
			}
		
		},
		alignRacks:function(){
			this.verticalAlignRacks();
			this.balanceFormatPos();
		},
		removerack:function(){
			if(this.selectedRackArray.length==0){
				$("#tipContent").html("请选择要删除机架")
	           	   $('#myModal').modal('show');
				
			}else{
			var ids=this.selectedRackArray[0].id;
			for(var i=1;i<this.selectedRackArray.length;i++){
				ids=ids+","+this.selectedRackArray[i].id;
			}	
			return ids;}
		},
		/**
		 * 以第一个选中的机架的高度为标准对齐
		 * @returns
		 */
		verticalAlignRacks:function(){
			if(this.selectedRackArray.length <= 0) {
				$("#tipContent").html("请选择两个或以上机架")
	           	   $('#myModal').modal('show');
				return false;
			}
			var baseRack = this.selectedRackArray[0];
			for(var i = 1,size = this.selectedRackArray.length;i < size;i++){
				if(parseInt(baseRack.style.top)>parseInt(this.selectedRackArray[i].style.top)){
					baseRack = this.selectedRackArray[i];
				}
			}
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				var elementToDrag = this.selectedRackArray[i];
				
				elementToDrag.style.top = baseRack.style.top;
				
			}
		},
		/**
		 * 机房的水平位置上（机房图的横向）平均分布机架
		 * @returns
		 */
		balanceFormatPos:function(){
			if(this.selectedRackArray.length <= 1) {
				$("#tipContent").html("请选择两个或以上机架")
	           	   $('#myModal').modal('show');

				return false;
			}
			this.selectedRackArray.sort(function(a,b){
				return parseInt(a.left)-parseInt(b.left);
			});
			var room = this.selectedRackArray[0].parentNode;
			var roomWidth = parseInt(room.offsetWidth);
			var rackWidth = parseInt(this.selectedRackArray[0].offsetWidth);
			var rackUsedRoomWidth = "41";
			var baseRack =parseInt(this.selectedRackArray[0].style.left);
			for(var i = 1,size = this.selectedRackArray.length;i < size;i++){
				if(baseRack>parseInt(this.selectedRackArray[i].style.left)){
					baseRack=parseInt(this.selectedRackArray[i].style.left);
				}
			}
			var arr = new Array(this.selectedRackArray.length)
			for(var i = 0,size = this.selectedRackArray.length;i < size;i++){
				arr[i]=[i,parseInt(this.selectedRackArray[i].style.left)];
			}
			arr.sort(new Function("a","b","return a[1]-b[1];"));
	        for(var j = 0;j<arr.length;j++){     	
				var elementToDrag = this.selectedRackArray[arr[j][0]];		
					elementToDrag.style.left = (baseRack+j*rackUsedRoomWidth)+'px';	
		    }
	    }	
	};
})();


function getScrollOffsets(w){
	w = w||window;
	
	if(w.pageXOffset!=null){
		return {x:w.pageXOffset,y:w.pageYOffset};
	}
	
	var d = w.document;
	
	if(document.compatMode == 'CSS1Compat'){
		return {x:d.documentElement.scrollLeft,y:d.documentElement.scrollTop};
		
	}
	return {x:d.body.scrollLeft,y:d.body.scrollTop};
}

function drag(elementToDrag,event){
	var scroll = getScrollOffsets();
	
	/* //文档坐标
	var startX = event.clientX + scroll.x;
	var startY = event.clientY + scroll.y;
	
	//元素的偏移坐标 文档坐标
	var origx = elementToDrag.offsetLeft;
	var origy = elementToDrag.offsetTop;
	
	
	var deltaX = startX - origx;
	var deltaY = startY - origy;
	 */

	RackTool.calDeltaPosForEachSelectedRack(event,scroll);
	if(document.addEventListener){
		document.addEventListener('mousemove',moveHandler,true);
		document.addEventListener('mouseup',upHandler,true);
		
		
	}else if(document.attachEvent){
		
		elementToDrag.setCapture();
		elementToDrag.attachEvent('onmousemove',moveHandler);
		elementToDrag.attachEvent('onmouseup',upHandler);
		
		elementToDrag.detachEvent('onlosecapture',moveHandler);
		
	}
	
	
	function moveHandler(e){
		if(!e){
			e = window.event;
		}
		
		var scroll = getScrollOffsets();
		/* elementToDrag.style.left = (e.clientX + scroll.x - deltaX)+'px';
		elementToDrag.style.top = (e.clientY + scroll.y - deltaY)+'px';
		 */
		 RackTool.setPosForEachSelectedRack(e,scroll);
		stopPrapagation(e);
	}
	

	
	function upHandler(e){
		if(!e){ 
			e = window.event;
		}
		
		if(document.removeEventListener){
			document.removeEventListener('mouseup',upHandler,true);
			document.removeEventListener('mousemove',moveHandler,true);

		}else if(document.detachEvent){
			elementToDrag.detachEvent('onlosecapture',upHandler);
			
			elementToDrag.detachEvent('onmouseup',upHandler);
			elementToDrag.detachEvent('onmousemove',moveHandler);
		
			elementToDrag.releaseCapture();
		
		}
		
		stopPrapagation(e);
	}
	
}

function stopPrapagation(e){
	if(e.stopPrapagation) {
		e.stopPrapagation();
	}else{
		e.cancelBubble = true;
	}
}
function changePosition(){
	RackTool.alignRacks();
}
function saveAllInfo(){
doucment.getElementsTagName("")
}
function  classchange(div){
	div.className="blackcolor";
}
function removeclass(div){
	div.className="";
}
var ids="";
function deleteRack(id){
	ids=RackTool.removerack();
	if(ids!=null){
		$('#confirm').modal('show');     
	}
}

$(function(){
	$("div[name='rackinfo']").mousedown(function(e){
		if(e.which=="3"){
			if($("div[name='updatemenu']").length>0){$("div[name='updatemenu']").remove();}
			var rack_id = $(e.target).attr('id'); 
			var room_id=$("#markroomid").val();
			var X=$("#main").get(0).scrollLeft+e.pageX-10;
	        var Y=$("#main").get(0).scrollTop+e.pageY-60;
	        if(e.pageY>429){     	 
	        	Y=Y-70;
	        }
	        if(e.pageX>620){
	            X=X-110;	
	        }
			$("#main").append("<div name='updatemenu' style=\"text-align:center;width:100px; height:63px;top:"+Y+"px;left:"+X+"px;background-color:#C4E1E1\" ><div style=\"text-align:center;width:100px; height:20px;top:23px;line-height:20px\" onmouseover='classchange(this)' onmouseout='removeclass(this)' onclick='updaterack(\""+rack_id+"\")'>修改</div><div style=\"text-align:center;width:100px; height:23px; top:0px;line-height:20px;\" onmouseover='classchange(this)' onmouseout='removeclass(this)' onclick='changePosition()'>对齐</div><div style=\"text-align:center;width:100px; height:20px;top:43px;line-height:20px\" onmouseover='classchange(this)' onmouseout='removeclass(this)' onclick='deleteRack(\""+rack_id+"\")'>删除</div></div>");
			
			}else if(e.which=="1"){
				 drag(this,event);
			}
	return false;
	});
	$("div[name='rackinfo']").click(function(e){
		RackTool.selectRack(this,event);
	});
	$("div[name='updatemenu']").blur(function(e){
			$("div[name='updatemenu']").remove();
		});
	$("#saveAllInfo").click(function(){
		var date= new Array();;
		for(var i=0;i<$("div[name='rackinfo']").length;i++){
			var everyInfo={"id":$("div[name='rackinfo']")[i].id,"xposition":$("div[name='rackinfo']")[i].style.left.replace("px",""),"yposition":$("div[name='rackinfo']")[i].style.top.replace("px","")};
			date[i]=everyInfo;
		}
		$.ajax({
			url:"drag.action",
			data:{"date":JSON.stringify(date)},
			dataType:"json", 
			type:"post",
			success:function (msg) {
				$("#tipContent").html("保存成功")
	           	   $('#myModal').modal('show');
			}
		});
		
	});
	$(document).click(function (event) { 
		 $("div[name='updatemenu']").remove(); 
		 RackTool.selectRack("1",event);
		}); 
	
	$("#detleteinfo").click(function(){
		var freeMumber=document.getElementById("free").innerHTML;
		var paillarMumber=document.getElementById("pillar").innerHTML;
		$.ajax({
			url:"deleteSomeRack.action",
			data:{"ids":ids},
			dataType:"json", 
			type:"post",
			success:function (msg) {
				if("error" == msg.result){
					 $("#tipContent").html("删除机架失败")
	            	 $('#myModal').modal('show');
				}else if("success" == msg.result){	
					for(var i=0;i<ids.split(",").length;i++){
						$("#"+ids.split(",")[i]).remove();			
					}
				
					document.getElementById("free").innerHTML=parseInt(freeMumber)-parseInt(ids.split(",").length)+parseInt(msg.PillarCount);
					document.getElementById("pillar").innerHTML=parseInt(paillarMumber)-parseInt(msg.PillarCount);
					document.getElementById("alldevnumber").value=parseInt(document.getElementById("alldevnumber").value)-1;
					$("#tipContent").html("删除机架成功")
	           	   $('#myModal').modal('show');
				}
			}
		});
	});
});
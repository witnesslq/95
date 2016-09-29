


var imgs = document.getElementsByName("cximg");
var left=0;
var right=360;
function tupian(){
	
	var img =imgs.item(t);
	var img2 =imgs.item(b);
	
	if(img!=null){
		
		img.style.left=right+"px";
		left=left-2;
		img2.style.left=left+"px";
		right=right-2;
		if(right==0){
			right=360;
			
		}
		if(left==-360){
			left=0;	
			stopGDTime();
		}
	}
	
}


//图片轮播开关控制
var flag2;
function endTime()
{
	if(flag2!=null){
		studyTime();
		flag2 = setTimeout("endTime()", 5000); 
	}else{
	flag2 = setTimeout("endTime()", 2000); 
	}
}
endTime();

var t = 0;
var b=1;
var flag1;
function studyTime()
{
	flag1 = setTimeout("studyTime()", 10);  
	tupian();
	t++;
	b++;
	if(b>imgs.length-1){
		b=0;
	}
	if(t>imgs.length-1){
		t=0;
	}
}
//终止计时器
function stopGDTime()
{
    clearTimeout(flag1);
}



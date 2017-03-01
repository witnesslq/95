<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<STYLE type=text/css>
	A {
		TEXT-DECORATION: none
	}
	
	BODY {
		SCROLLBAR-FACE-COLOR: #e8e7e7; FONT-SIZE: 12px; SCROLLBAR-HIGHLIGHT-COLOR: #ffffff; SCROLLBAR-SHADOW-COLOR: #ffffff; COLOR: #001111; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR: #ff6600; SCROLLBAR-TRACK-COLOR: #efefef; FONT-FAMILY: 宋体; SCROLLBAR-DARKSHADOW-COLOR: #b2b2b2; SCROLLBAR-BASE-COLOR: #000000; BACKGROUND-COLOR: #ffffff
	}

	</STYLE>

<META content="MSHTML 6.00.2900.2523" name=GENERATOR></HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" align=center 
border=0>
  <TBODY>
  <TR>
    <TD align="center">
      <DIV align=center>
      <CENTER>
      <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 height=340 
      cellSpacing=0 cellPadding=0 width=700 border=0>
        <TBODY>
        <TR>
          <TD  width="700px" height="340px">
            <P align=center><IMG src="images/filenotfound.jpg" border=0></P>
            <P align=center>返回：<FONT color=#ff0000> <A href="javascript:void(0);"  onclick="window.history.go(-1);"><U>
            <FONT color=#0000ff>返回</FONT></U></A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            </FONT>
            </P>
            <P align=center>　</P>
            <P align=center>　</P>
            </TD>
            </TR>
            </TBODY>
            </TABLE>
            </CENTER>
            </DIV>
            </TD>
           </TR>
          </TBODY>
       </TABLE>
  </BODY>
  </HTML>

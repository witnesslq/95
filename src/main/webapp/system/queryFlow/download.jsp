<%@ page language="java" contentType="text/html; charset=gb2312" %>
<%@ page  import="com.jspsmart.upload.SmartUpload"%>
<%
  String rootPath = request.getContextPath();
  out.println(rootPath);
%>
<html>
<head>
<meta http-equiv="Page-Enter" content="revealTrans(duration=x, transition=y)">
</head>
<BODY leftmargin="0" topmargin="0" bgcolor="#cedefa">
<%    
    String fileName = (String)request.getAttribute("filename");
    //fileName = new String(fileName.getBytes(),"ISO8859-1");
    System.out.println(fileName);
    if (fileName == null) {
            fileName = "";
        }
	try
	{
	    SmartUpload download = new SmartUpload();    
	    download.initialize(pageContext);   
	    download.downloadFile(fileName);
	    	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Error in download!");
	}finally{
		out.clear();
    	out = pageContext.pushBody();
	}
%>
<div id="loading">
	<div class="loading-indicator">
	Loading...</div>
</div>
<div id="loading-mask" style=""></div>
<table align="center" cellSpacing="0" cellPadding="0" id="tblListTitle" class="WorkPage_ListTable" width="90%" border="0" > 
<tr> 
<td  height="7" align=center >
<input type=button value="¹Ø±Õ´°¿Ú" onclick="window.close()">
</td>
</tr>
</table>

</BODY>
</HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统主界面</title>
  </head>
  <%
  	String userId = request.getParameter("userId");
  %>
  <body>
	<div style="width: 10%;display: inline-block;vertical-align: top;padding-top: 50px;">
	 	<div>
	 		当前用户：<%=userId %><br>
	 		<h4 style="color: red;">部署</h4>
	 		<a href="<%=basePath%>simple/bushu.jsp" target="mainFrame">部署请假流程</a>
	 		<h4 style="color: red;">申请</h4>
	 		<a href="<%=basePath%>simple/shenqingqingjia.jsp" target="mainFrame">申请请假</a>
	 		<h4 style="color: red;">任务列表</h4>
	 		<a href="<%=basePath%>simple/renwuliebiao.jsp" target="mainFrame">任务列表</a>
	 		<br><br>
	 		<a href="<%=basePath%>simple/login.jsp">注销</a>
	 	</div>
	</div>
	<div style="width: 89%;height:100%;display: inline-block;">
		<iframe width="100%" height="100%" name="mainFrame"></iframe>
	</div>

  </body>
</html>

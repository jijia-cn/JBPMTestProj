<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>JBPM登陆界面</title>
  </head>
  <body>
	<div align="center">
		<form action="<%=basePath%>/simple/loginpage.jsp" method="post">
		<table border="1">
			<tr>
				<td colspan="2" align="center"">系统登陆</td>
			</tr>
			<tr>
				<td>请选择用户</td>
				<td>
					<select name="userId">
						<option value="0">管理员</option>
						<option value="1">小王</option>
						<option value="2">刘经理</option>
						<option value="3">张总</option>
						<option value="4">财务部</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="登陆"></td>			
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>

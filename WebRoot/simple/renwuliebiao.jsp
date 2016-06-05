<%@page import="org.jbpm.taskmgmt.def.Task"%>
<%@page import="org.jbpm.db.TaskMgmtSession"%>
<%@page import="org.jbpm.JbpmContext"%>
<%@page import="org.jbpm.JbpmConfiguration"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务列表</title>

  </head>
  
  <body>
    	任务列表<hr>
    	<%
    		String userId = request.getParameter("userId");
    		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
    		List tasks = jbpmContext.getTaskList(userId);
    		for(Task task : tasks)
    		{
    			out.write(task.getId()+" "+task.getName()+" <br>")
    		}
    		jbpmContext.close();
    	%>
  </body>
</html>

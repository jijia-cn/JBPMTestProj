<%@page import="org.jbpm.graph.exe.ProcessInstance"%>
<%@page import="org.jbpm.db.GraphSession"%>
<%@page import="org.jbpm.graph.def.ProcessDefinition"%>
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
    <title>申请请假</title>
  </head>
  <body>
  	  申请请假<br>
  	 <%
  	 	JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
  		GraphSession graphSession = jbpmContext.getGraphSession();
  		ProcessDefinition processDefinition = graphSession.findLatestProcessDefinition("qingjia");
  	 	ProcessInstance processInstance = new ProcessInstance(processDefinition);
  		jbpmContext.save(processInstance);
  		jbpmContext.close();
  	 %>
  </body>
</html>

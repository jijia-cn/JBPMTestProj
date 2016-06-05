
<%@page import="java.io.File"%>
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
    
    <title>部署流程</title>

  </head>
  
  <body>
  	 <%
  	 	JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
  	 	ProcessDefinition processDefinition = ProcessDefinition.parseXmlString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<process-definition  xmlns=\"urn:jbpm.org:jpdl-3.1\"  name=\"qingjia\">"+
			"<start-state name=\"begin\"><transition to=\"jingli\"></transition></start-state>"+
			"<task-node name=\"jingli\"><task name=\"jingLi\"><assignment actor-id=\"2\"></assignment></task><transition to=\"end\"></transition></task-node>"+
			"<end-state name=\"end\"></end-state>"+
			"</process-definition>"); 
  	 	//jbpmContext.deployProcessDefinition(ProcessDefinition.parseXmlResource(basePath+"simple/qingjia/processdefinition.xml"));
  	 	jbpmContext.deployProcessDefinition(processDefinition);
  	 	jbpmContext.close();
  	 	out.write("部署成功");
  	 %>
  </body>
</html>

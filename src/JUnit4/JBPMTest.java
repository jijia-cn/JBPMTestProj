package JUnit4;

import static org.junit.Assert.*;

import java.util.List;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.junit.Test;

public class JBPMTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	//部署请假流程定义
	@Test
	public void deploy()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
	 	ProcessDefinition processDefinition = ProcessDefinition.parseXmlResource("qingjia/processdefinition.xml");
	 	jbpmContext.deployProcessDefinition(processDefinition);
	 	jbpmContext.close();	
	}
	//启动一次请假流程
	@Test
	public void qiDongLiuChen()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		GraphSession graphSession = jbpmContext.getGraphSession();
		ProcessDefinition processDefinition = graphSession.findLatestProcessDefinition("qingjia");
		ProcessInstance pInstance = new ProcessInstance(processDefinition);
		pInstance.end();
		jbpmContext.save(pInstance);
		jbpmContext.close();
	}
	//启动指定的流程实例
	@Test
	public void start()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		ProcessInstance processInstance = jbpmContext.loadProcessInstance(5);
		processInstance.signal();
		jbpmContext.close();
	}
	
	//查看经理的任务列表
	@SuppressWarnings("unchecked")
	@Test
	public void chaKanJingLiJobs()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
		List<TaskInstance> list = taskMgmtSession.findTaskInstances("2");
		for(TaskInstance instance:list)
		{
			System.out.println(instance.getId()+" "+instance.getName());
		}
		jbpmContext.close();
	}
	
	
}

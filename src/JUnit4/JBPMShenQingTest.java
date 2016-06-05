package JUnit4;

import static org.junit.Assert.*;

import java.util.List;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.junit.Test;

public class JBPMShenQingTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	//部署请假流程定义
	@Test
	public void deploy()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
	 	ProcessDefinition processDefinition = ProcessDefinition.parseXmlResource("shenqing/shenqing/processdefinition.xml");
	 	processDefinition.setDescription("测试请假工作流");
	 	jbpmContext.deployProcessDefinition(processDefinition);
	 	jbpmContext.close();	
	 	System.out.println("部署完成");
	}
	
	//启动一次请假流程
	@Test
	public void qiDongLiuChen()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		GraphSession graphSession = jbpmContext.getGraphSession();
		ProcessDefinition processDefinition = graphSession.findLatestProcessDefinition("shenqing");
		ProcessInstance pInstance = new ProcessInstance(processDefinition);
		pInstance.setKey("李四业务id");
		pInstance.signal();	//启动流程  会在流程实例中创建下一节点的记录  在任务实例中填充创建时间
		jbpmContext.save(pInstance);
		jbpmContext.close();
	 	System.out.println("启动流程实例");
	}
	
	//查看经理的任务列表
	@SuppressWarnings("unchecked")
	@Test
	public void chaKanJingLiJobs()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
		List<TaskInstance> list = taskMgmtSession.findTaskInstances("2");
		System.out.println("经理任务数:"+list.size());
		for(TaskInstance instance:list)
		{
			instance.start();
			System.out.println("业务："+instance.getProcessInstance().getKey()+" "+instance.getId()+" "+instance.getName()+" create time:"+instance.getCreate()+" start time:"+instance.getStart());
			instance.end();	
		}
		jbpmContext.close();
		System.out.println("查看经理任务并审批");
	}
	
	//查看总经理任务
	@SuppressWarnings("unchecked")
	@Test
	public void chaKanZongJingLi()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
		List<TaskInstance> list = taskMgmtSession.findTaskInstances("3");	//查询的是未审批的任务
		System.out.println("总经理任务数:"+list.size());
		for(TaskInstance instance:list)
		{
			instance.start();
			System.out.println("业务："+instance.getProcessInstance().getKey()+" "+instance.getId()+" "+instance.getName()+" create time:"+instance.getCreate()+" start time:"+instance.getStart());
			instance.end();
		}
		jbpmContext.close();
		System.out.println("查看总经理任务并审批");
	}
	
	
}

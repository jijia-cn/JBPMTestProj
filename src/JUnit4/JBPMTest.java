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
	//����������̶���
	@Test
	public void deploy()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
	 	ProcessDefinition processDefinition = ProcessDefinition.parseXmlResource("qingjia/processdefinition.xml");
	 	jbpmContext.deployProcessDefinition(processDefinition);
	 	jbpmContext.close();	
	}
	//����һ���������
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
	//����ָ��������ʵ��
	@Test
	public void start()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		ProcessInstance processInstance = jbpmContext.loadProcessInstance(5);
		processInstance.signal();
		jbpmContext.close();
	}
	
	//�鿴����������б�
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

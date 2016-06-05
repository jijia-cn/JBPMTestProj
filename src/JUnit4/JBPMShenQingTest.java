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
	
	//����������̶���
	@Test
	public void deploy()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
	 	ProcessDefinition processDefinition = ProcessDefinition.parseXmlResource("shenqing/shenqing/processdefinition.xml");
	 	processDefinition.setDescription("������ٹ�����");
	 	jbpmContext.deployProcessDefinition(processDefinition);
	 	jbpmContext.close();	
	 	System.out.println("�������");
	}
	
	//����һ���������
	@Test
	public void qiDongLiuChen()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		GraphSession graphSession = jbpmContext.getGraphSession();
		ProcessDefinition processDefinition = graphSession.findLatestProcessDefinition("shenqing");
		ProcessInstance pInstance = new ProcessInstance(processDefinition);
		pInstance.setKey("����ҵ��id");
		pInstance.signal();	//��������  ��������ʵ���д�����һ�ڵ�ļ�¼  ������ʵ������䴴��ʱ��
		jbpmContext.save(pInstance);
		jbpmContext.close();
	 	System.out.println("��������ʵ��");
	}
	
	//�鿴����������б�
	@SuppressWarnings("unchecked")
	@Test
	public void chaKanJingLiJobs()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
		List<TaskInstance> list = taskMgmtSession.findTaskInstances("2");
		System.out.println("����������:"+list.size());
		for(TaskInstance instance:list)
		{
			instance.start();
			System.out.println("ҵ��"+instance.getProcessInstance().getKey()+" "+instance.getId()+" "+instance.getName()+" create time:"+instance.getCreate()+" start time:"+instance.getStart());
			instance.end();	
		}
		jbpmContext.close();
		System.out.println("�鿴������������");
	}
	
	//�鿴�ܾ�������
	@SuppressWarnings("unchecked")
	@Test
	public void chaKanZongJingLi()
	{
		JbpmContext jbpmContext =  JbpmConfiguration.getInstance().createJbpmContext();
		TaskMgmtSession taskMgmtSession = jbpmContext.getTaskMgmtSession();
		List<TaskInstance> list = taskMgmtSession.findTaskInstances("3");	//��ѯ����δ����������
		System.out.println("�ܾ���������:"+list.size());
		for(TaskInstance instance:list)
		{
			instance.start();
			System.out.println("ҵ��"+instance.getProcessInstance().getKey()+" "+instance.getId()+" "+instance.getName()+" create time:"+instance.getCreate()+" start time:"+instance.getStart());
			instance.end();
		}
		jbpmContext.close();
		System.out.println("�鿴�ܾ�����������");
	}
	
	
}

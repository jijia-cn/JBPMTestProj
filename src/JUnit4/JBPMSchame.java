package JUnit4;

import static org.junit.Assert.*;

import org.jbpm.JbpmConfiguration;
import org.junit.Test;

public class JBPMSchame {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	//����jbpm����Ҫ�ı�ṹ
	@Test
	public void createSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.createSchema();
		jbpmConfiguration.close();
		System.out.println("�������");
	}
	//ɾ��jbpm����Ҫ�ı�ṹ
	@Test
	public void dropSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.dropSchema();
		jbpmConfiguration.close();
		System.out.println("ɾ�����еı�ṹ");
	}
	//����jbpm��ṹ
	@Test
	public void cleanSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.cleanSchema();
		jbpmConfiguration.close();
		System.out.println("�����ṹ�е�����");
	}	
}

package JUnit4;

import static org.junit.Assert.*;

import org.jbpm.JbpmConfiguration;
import org.junit.Test;

public class JBPMSchame {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	//创建jbpm所需要的表结构
	@Test
	public void createSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.createSchema();
		jbpmConfiguration.close();
		System.out.println("创建完成");
	}
	//删除jbpm所需要的表结构
	@Test
	public void dropSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.dropSchema();
		jbpmConfiguration.close();
		System.out.println("删除所有的表结构");
	}
	//清理jbpm表结构
	@Test
	public void cleanSchema()
	{
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		jbpmConfiguration.cleanSchema();
		jbpmConfiguration.close();
		System.out.println("清理表结构中的数据");
	}	
}

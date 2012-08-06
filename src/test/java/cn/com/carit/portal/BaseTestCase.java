package cn.com.carit.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:resources/spring/spring.xml")
@TransactionConfiguration(defaultRollback=true) // 测试通过后事务回滚
public abstract class BaseTestCase<T> extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void saveOrUpdate() throws Exception{}
	
	@Test
	public void delete(){}
	
	@Test
	public void batchDelete(){}
	
	@Test
	public void queryById(){}
	
	@Test
	public void queryByExemple(){}
	
	@Test
	public void queryAll(){}
}

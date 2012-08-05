package cn.com.carit.portal.dao;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.Menu;

public class MenuDaoTestCase extends BaseTestCase {
	@Resource
	private MenuDao<Menu> menuDao;

	@Test
	public void testAdd(){
		Menu t=new Menu();
		t.setCode("home");
		t.setDisplayIndex(1);
		t.setLevel(1);
		t.setStatus(1);
		t.setRemark("首页");
		menuDao.add(t);
	}
	
	@Test
	public void testUpdate(){
		Menu t=new Menu();
		t.setId(1);
		t.setRemark("网站首页");
		menuDao.update(t);
	}
	
	@Test
	public void testQueryById(){
		System.out.println(menuDao.queryById(1));
	}
	
}

package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.Menu;

public class MenuServiceTestCase extends BaseTestCase<Menu> {

	@Resource
	private MenuService<Menu> menuService;
	
	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		Menu t=new Menu();
		t.setCode("test");
//		t.setParentId(null);
		t.setLevel(1);
		t.setDisplayIndex(11);
		t.setRemark("测试");
		t.setStatus(1);
		menuService.saveOrUpdate(t);
		
		t.setCode("test2");
		t.setId(1);
		menuService.saveOrUpdate(t);
	}

	@Override
	@Test
	public void delete() {
		menuService.delete(4);
	}

	@Override
	@Test
	public void batchDelete() {
		menuService.batchDelete("1, 2, 3, 4");
	}

	@Override
	@Test
	public void queryById() {
		System.out.println(menuService.queryById(4));
	}

	@Override
	@Test
	public void queryByExemple() {
		Menu t=new Menu();
		t.setCode("test");
//		t.setParentId(null);
		t.setLevel(1);
		t.setDisplayIndex(11);
		t.setRemark("测试");
		t.setStatus(1);
		System.out.println(menuService.queryByExemple(t, new DataGridModel()));
		
		System.out.println(menuService.queryByExemple(t));
	}


	@Override
	@Test
	public void queryAll() {
		System.out.println(menuService.queryAll());
	}
	
	@Test
	public void testQueryByParent(){
		System.out.println(menuService.queryByParent(1));
	}
	
	@Test
	public void testQueryTopMenus(){
		System.out.println(menuService.queryTopMenus());
	}
	
	@Test
	public void testQuerySubMenus(){
		System.out.println(menuService.querySubMenus());
	}
}

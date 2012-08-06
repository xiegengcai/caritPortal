package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.Catalog;

public class CatalogServiceTestCase extends BaseTestCase<Catalog> {
	@Resource
	private CatalogService<Catalog> catalogService;

	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		Catalog t=new Catalog();
		t.setCatalogCode("test");
		t.setDescription("测试");
		t.setDisplayIndex(1);
		t.setStatus(1);
		catalogService.saveOrUpdate(t);
		
		t.setId(1);
		t.setCatalogCode("test2");
		catalogService.saveOrUpdate(t);
	}

	@Override
	@Test
	public void delete() {
		catalogService.delete(1);
	}

	@Override
	@Test
	public void batchDelete() {
		catalogService.batchDelete("1, 2, 3, 4");
	}

	@Override
	@Test
	public void queryById() {
		System.out.println(catalogService.queryById(1));
	}

	@Override
	@Test
	public void queryByExemple() {
		Catalog t=new Catalog();
		t.setCatalogCode("test");
		t.setDescription("测试");
		t.setDisplayIndex(1);
		t.setStatus(1);
		System.out.println(catalogService.queryByExemple(t, new DataGridModel()));
		System.out.println(catalogService.queryByExemple(t));
	}

	@Override
	@Test
	public void queryAll() {
		System.out.println(catalogService.queryAll());
	}
	
}

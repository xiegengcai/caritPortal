package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.ProductRelease;

public class ProductReleaseServiceTestCase extends BaseTestCase<ProductRelease> {

	@Resource
	private ProductReleaseService<ProductRelease> service;
	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		ProductRelease t=new ProductRelease();
		t.setCatalogId(1);
		t.setContent("测试");
		t.setLanguage("cn");
		t.setPicture("test");
		t.setThumb("test");
		t.setTitle("测试");
		t.setTop(true);
		t.setStatus(1);
		service.saveOrUpdate(t);
		
		t.setTitle("测试2");
		t.setId(1);
		service.saveOrUpdate(t);
	}

	@Override
	@Test
	public void delete() {
		service.delete(1);
	}

	@Override
	@Test
	public void batchDelete() {
		service.batchDelete("1, 2, 3, 4");
	}

	@Override
	@Test
	public void queryById() {
		System.out.println(service.queryById(1));
	}

	@Override
	@Test
	public void queryByExemple() {
		ProductRelease t=new ProductRelease();
		t.setCatalogId(1);
		t.setContent("测试");
		t.setLanguage("cn");
		t.setPicture("test");
		t.setThumb("test");
		t.setTitle("测试");
		t.setTop(true);
		t.setStatus(1);
		System.out.println(service.queryByExemple(t, new DataGridModel()));
		System.out.println(service.queryByExemple(t));
	}

	@Override
	@Test
	public void queryAll() {
		System.out.println(service.queryAll());
	}

	@Test
	public void queryProductRelease(){
		System.out.println(service.queryProductRelease("cn", 5));
	}
	
	@Test
	public void queryTopProductRelease(){
		System.out.println(service.queryTopProductRelease("cn", 5));
	}
}

package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.News;

public class NewsServiceTestCase extends BaseTestCase<News> {
	
	@Resource
	private NewsService<News> service;

	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		News t=new News();
		t.setTitle("测试");
		t.setType(0);
		t.setContent("测试");
		t.setLanguage("cn");
		t.setStatus(1);
		
		service.saveOrUpdate(t);
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
		News t=new News();
		t.setTitle("测试");
		t.setType(0);
		t.setContent("测试");
		t.setLanguage("cn");
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
	public void queryNews(){
		System.out.println(service.queryNews(1, "cn", 5));
	}
}

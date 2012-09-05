package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.DemoVideo;

public class DemoVideoServiceTestCase extends BaseTestCase<DemoVideo>{
	@Resource
	private DemoVideoService<DemoVideo> service;

	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		DemoVideo t=new DemoVideo();
		t.setName("测试");
		t.setUrl("测试");
		t.setStatus(1);
		service.saveOrUpdate(t);
		
		
		t.setName("测试q");
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
		DemoVideo t=new DemoVideo();
		t.setName("测试");
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
	public void queryNewest(){
		System.out.println(service.queryNewest(5));
	}
	
}

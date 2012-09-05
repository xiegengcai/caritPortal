package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.GlobalAddress;

public class GlobalAddressServiceTestCase extends BaseTestCase<GlobalAddress>{
	@Resource
	private GlobalAddressService<GlobalAddress> service;

	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		GlobalAddress t=new GlobalAddress();
		t.setLanguage("en");
		t.setAddress("ddddddddd");
		t.setTelephone("1111111111");
		t.setFax("0020");
		t.setPostalcode("518000");
		t.setStatus(1);
		service.saveOrUpdate(t);
		
		t.setPostalcode("518008");
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
		GlobalAddress t=new GlobalAddress();
		t.setAddress("ddddddddd");
		t.setTelephone("1111111111");
		t.setFax("0020");
		t.setPostalcode("518000");
		t.setStatus(1);
		System.out.println(service.queryByExemple(t, new DataGridModel()));
		System.out.println(service.queryByExemple(t));
	}

	@Override
	@Test
	public void queryAll() {
		System.out.println(service.queryAll());
	}
	
}

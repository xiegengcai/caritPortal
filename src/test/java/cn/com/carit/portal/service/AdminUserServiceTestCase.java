package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.AdminUser;

public class AdminUserServiceTestCase extends BaseTestCase<AdminUser> {
	
	@Resource
	private AdminUserService<AdminUser> adminUserService;
	
	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		AdminUser t=new AdminUser();
		t.setEmail("test@test.com");
		t.setGender((byte)1);
		t.setNickName("测试");
		t.setStatus(1);
		t.setPassword("@carit123456");
		t.setLastLoginIp("127.0.0.1");
		adminUserService.saveOrUpdate(t);
		
		t.setId(1);
		t.setEmail("test2@test.com");
		t.setNickName("测试2");
		
		adminUserService.saveOrUpdate(t);
	}

	@Override
	@Test
	public void delete() {
		adminUserService.delete(4);
	}

	@Override
	@Test
	public void batchDelete() {
		adminUserService.batchDelete("1, 2, 3, 4");
	}

	@Override
	@Test
	public void queryById() {
		System.out.println(adminUserService.queryById(1));
	}

	@Override
	@Test
	public void queryByExemple() {
		AdminUser t=new AdminUser();
		t.setEmail("admin@admin.com");
		t.setGender((byte)1);
		t.setNickName("系统管理员");
		t.setStatus(1);
		t.setPassword("@carit123456");
		System.out.println(adminUserService.queryByExemple(t, new DataGridModel()));
		
		System.out.println(adminUserService.queryByExemple(t));
	}

	@Override
	@Test
	public void queryAll() {
		System.out.println(adminUserService.queryAll());
	}

	
	@Test
	public void testCheckAdminUser(){
		System.out.println(adminUserService.checkAdminUser("admin@admin.com", null));
		System.out.println(adminUserService.checkAdminUser(null, "系统管理员"));
	}
	
	@Test
	public void testLogin() throws Exception{
		System.out.println(adminUserService.login("admin@admin.com", "123456", "127.0.0.1"));
	}
}

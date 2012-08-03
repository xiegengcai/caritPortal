package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.AdminUser;

public class AdminUserServiceTestCase extends BaseTestCase {
	
	@Resource
	private AdminUserService<AdminUser> adminUserService;
	
	@Test
	public void testSaveOrUpdate() throws Exception{
		AdminUser t=new AdminUser();
		t.setEmail("admin@admin.com");
		t.setGender((byte)1);
		t.setNickName("系统管理员");
		t.setStatus(1);
		t.setPassword("@carit123456");
		adminUserService.saveOrUpdate(t);
		
		t.setLastLoginIp("127.0.0.1");
		
		adminUserService.saveOrUpdate(t);
	}
	
}

package cn.com.carit.portal.dao;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.AdminUser;

public class AdminDaoTestCase extends BaseTestCase{
	
	@Resource
	private AdminUserDao<AdminUser> adminUserDao;
	
	@Test
	public void testAddAndUpdate(){
		AdminUser t=new AdminUser();
		t.setEmail("admin@admin.com");
		t.setGender((byte)1);
		t.setNickName("系统管理员");
		t.setStatus(1);
		t.setPassword("@carit123456");
		adminUserDao.add(t);
	}
	
	@Test
	public void testUpdate(){
		AdminUser t=new AdminUser();
		t.setId(1);
		t.setLastLoginIp("127.0.0.1");
		adminUserDao.update(t);
	}
	
	@Test
	public void queryById(){
		adminUserDao.queryById(1);
	}
	
	@Test
	public void queryByEmail(){
		adminUserDao.queryByEmail("admin@admin.com");
	}
	
	@Test
	public void testQueryAll(){
		adminUserDao.queryAll();
	}
	
	@Test
	public void testQueryByEmail(){
		adminUserDao.queryByEmail("test@test.com");
	}
	
	@Test
	public void testQueryByExemple(){
		AdminUser simple=new AdminUser();
		simple.setEmail("test@test.com");
		adminUserDao.queryByExemple(simple);
		adminUserDao.queryByExemple(simple, new DataGridModel());
	}

	@Test
	public void testCheckUser(){
		adminUserDao.checkAdminUser("admin@admin.com", "系统管理员");
	}
	
}

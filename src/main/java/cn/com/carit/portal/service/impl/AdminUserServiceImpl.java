package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.MD5Util;
import cn.com.carit.portal.bean.AdminUser;
import cn.com.carit.portal.dao.AdminUserDao;
import cn.com.carit.portal.service.AdminUserService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class AdminUserServiceImpl implements AdminUserService<AdminUser> {

	@Resource
	private AdminUserDao<AdminUser> adminUserDao;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int saveOrUpdate(AdminUser t) throws Exception {
		String password=t.getPassword();
		if (StringUtils.hasText(password)) {
			// 加密密码，加密规则 MD5Util.md5Hex(MD5Util.md5Hex(password){email}disturbStr)
			t.setPassword(MD5Util.md5Hex(t.getEmail()+MD5Util.md5Hex(password)+MD5Util.DISTURBSTR));
		}
		if (t.getId()>0) {
			int id=adminUserDao.add(t);
			t.setId(id);
			return 1;
		} else {
			return adminUserDao.update(t);
		}
	}

	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return adminUserDao.delete(id);
	}

	@Override
	public AdminUser queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return adminUserDao.queryById(id);
	}

	@Override
	public JsonPage<AdminUser> queryByExemple(AdminUser t, DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return adminUserDao.queryByExemple(t, dgm);
	}

	@Override
	public List<AdminUser> queryByExemple(AdminUser t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return adminUserDao.queryByExemple(t);
	}

	@Override
	public List<AdminUser> queryAll() {
		return adminUserDao.queryAll();
	}

	@Override
	public int checkAdminUser(String email, String nickName) {
		if (!StringUtils.hasText(nickName) && !StringUtils.hasText(email)) {
			throw new IllegalArgumentException("email and nickName must one is not empty..");
		}
		return adminUserDao.checkAdminUser(email, nickName);
	}

}

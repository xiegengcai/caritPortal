package cn.com.carit.portal.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.MD5Util;
import cn.com.carit.portal.bean.AdminUser;
import cn.com.carit.portal.dao.AdminUserDao;
import cn.com.carit.portal.service.AdminUserService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class AdminUserServiceImpl implements AdminUserService<AdminUser> {

	private final Logger log = Logger.getLogger(getClass());
	
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
			return adminUserDao.update(t);
		} else {
			return adminUserDao.add(t);
		}
	}

	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return adminUserDao.delete(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return adminUserDao.batchDelete(ids);
		}
		return 0;
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

	@Override
	public Map<String, Object> login(String email, String password, String ip) throws Exception {
		if (!StringUtils.hasText(email)) {
			throw new IllegalArgumentException("email must be not empty...");
		}
		if (!StringUtils.hasText(password)) {
			throw new IllegalArgumentException("password must be not empty...");
		}
		Map<String, Object> resultMap=new HashMap<String, Object>();
		AdminUser adminUser=adminUserDao.queryByEmail(email);
		if (adminUser==null) {
			log.error("User["+email+"] does not exist...");
			resultMap.put(Constants.ANSWER_CODE, -2);
			return resultMap;
		}
		// 密码加密
		password=MD5Util.md5Hex(password);
		// 二次加密
		password=MD5Util.md5Hex(email+password+MD5Util.DISTURBSTR);
		if (!password.equalsIgnoreCase(adminUser.getPassword())) {
			//密码错误
			resultMap.put(Constants.ANSWER_CODE, 0);
			return resultMap;
		}
		if(adminUser.getStatus().intValue()!=Constants.STATUS_VALID){
			// 账号已经停用
			resultMap.put(Constants.ANSWER_CODE, -1);
			return resultMap;
		}
		// 更新
		AdminUser updateUser=new AdminUser();
		updateUser.setId(adminUser.getId());
		updateUser.setLastLoginIp(ip);
		updateUser.setLastLoginTime(Calendar.getInstance().getTime());
		adminUserDao.update(updateUser);
		resultMap.put(Constants.ANSWER_CODE, 1);
		resultMap.put(email, adminUser);
		return resultMap;
	}

}

package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.GlobalAddress;
import cn.com.carit.portal.dao.GlobalAddressDao;
import cn.com.carit.portal.service.GlobalAddressService;
import cn.com.carit.portal.web.CacheManager;
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
public class GlobalAddressServiceImpl implements
		GlobalAddressService<GlobalAddress> {

	@Resource
	private GlobalAddressDao<GlobalAddress> dao;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int saveOrUpdate(GlobalAddress t) throws Exception {
		if (t.getId()>0) {
			return dao.update(t);
		} else {
			return dao.add(t);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return dao.delete(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			String [] array=ids.split(",");
			for (String id : array) {
				delete(Integer.valueOf(id.trim()));
			}
			return array.length;
		}
		return 0;
	}
	
	@Override
	public GlobalAddress queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return dao.queryById(id);
	}

	@Override
	public JsonPage<GlobalAddress> queryByExemple(GlobalAddress t,
			DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return dao.queryByExemple(t, dgm);
	}

	@Override
	public List<GlobalAddress> queryByExemple(GlobalAddress t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return dao.queryByExemple(t);
	}

	@Override
	public List<GlobalAddress> queryAll() {
		return dao.queryAll();
	}

	@Override
	public List<GlobalAddress> query(String language) {
		List<GlobalAddress> list=dao.query(language);
		if (list==null || list.size()<1 && !Constants.DEAFULD_LANGUAGE.equals(language)) {
			return CacheManager.getInstance().getDefaultAddressList();
		}
		return list;
	}


}

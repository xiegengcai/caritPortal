package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.DemoVideo;
import cn.com.carit.portal.dao.DemoVideoDao;
import cn.com.carit.portal.service.DemoVideoService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class DemoVideoServiceImpl implements
		DemoVideoService<DemoVideo> {

	@Resource
	private DemoVideoDao<DemoVideo> dao;
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int saveOrUpdate(DemoVideo t) throws Exception {
		if (t.getId()>0) {
			return dao.update(t);
		} else {
			return dao.add(t);
		}
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
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
	public DemoVideo queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return dao.queryById(id);
	}

	@Override
	public JsonPage<DemoVideo> queryByExemple(DemoVideo t,
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
	public List<DemoVideo> queryByExemple(DemoVideo t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return dao.queryByExemple(t);
	}

	@Override
	public List<DemoVideo> queryAll() {
		return dao.queryAll();
	}

	@Override
	public List<DemoVideo> queryNewest(int limit) {
		return dao.queryNewest(limit);
	}

}

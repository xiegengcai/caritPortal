package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.DemoVideo;
import cn.com.carit.portal.dao.DemoVideoDao;
import cn.com.carit.portal.service.DemoVideoService;
import cn.com.carit.portal.web.CacheManager;
@Service
@Transactional(readOnly=true)
public class DemoVideoServiceImpl implements
		DemoVideoService<DemoVideo> {

	@Resource
	private DemoVideoDao<DemoVideo> dao;
	
	@Transactional(readOnly=false)
	@Override
	public int saveOrUpdate(DemoVideo t) throws Exception {
		int rows=0;
		if (t.getId()>0) {
			rows = dao.update(t);
		} else {
			rows = dao.add(t);
		}
		if (rows>0) {
			CacheManager.getInstance().refreshVideo();
		}
		return rows;
	}

	@Transactional(readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		int rows = dao.delete(id);
		if (rows>0) {
			CacheManager.getInstance().refreshVideo();
		}
		return rows;
	}
	
	@Transactional(readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			String [] array=ids.split(",");
			for (String id : array) {
				delete(Integer.valueOf(id.trim()));
			}
			CacheManager.getInstance().refreshVideo();
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

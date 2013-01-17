package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.Catalog;
import cn.com.carit.portal.dao.CatalogDao;
import cn.com.carit.portal.service.CatalogService;
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
public class CatalogServiceImpl implements CatalogService<Catalog> {
	
	@Resource
	private CatalogDao<Catalog> catalogDao;

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int saveOrUpdate(Catalog t) throws Exception {
		if (t.getId()>0) {
			return catalogDao.update(t);
		} else {
			return catalogDao.add(t);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return catalogDao.delete(id);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return catalogDao.batchDelete(ids);
		}
		return 0;
	}

	@Override
	public Catalog queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return catalogDao.queryById(id);
	}

	@Override
	public JsonPage<Catalog> queryByExemple(Catalog t, DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return catalogDao.queryByExemple(t, dgm);
	}

	@Override
	public List<Catalog> queryByExemple(Catalog t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return catalogDao.queryByExemple(t);
	}

	@Override
	public List<Catalog> queryAll() {
		return catalogDao.queryAll();
	}

}

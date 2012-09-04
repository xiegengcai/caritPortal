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
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.dao.ProductReleaseDao;
import cn.com.carit.portal.service.ProductReleaseService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class ProductReleaseServiceImpl implements
		ProductReleaseService<ProductRelease> {
	
	@Resource
	private ProductReleaseDao<ProductRelease> productReleaseDao;

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int saveOrUpdate(ProductRelease t) throws Exception {
		if (t.getId()>0) {
			return productReleaseDao.update(t);
		} else {
			return productReleaseDao.add(t);
		}
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return productReleaseDao.delete(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return productReleaseDao.batchDelete(ids);
		}
		return 0;
	}

	@Override
	public ProductRelease queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return productReleaseDao.queryById(id);
	}

	@Override
	public JsonPage<ProductRelease> queryByExemple(ProductRelease t,
			DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return productReleaseDao.queryByExemple(t, dgm);
	}

	@Override
	public List<ProductRelease> queryByExemple(ProductRelease t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return productReleaseDao.queryByExemple(t);
	}

	@Override
	public List<ProductRelease> queryAll() {
		return productReleaseDao.queryAll();
	}

	@Override
	public List<ProductRelease> queryProductRelease(String language, int limit) {
		if (!StringUtils.hasText(language)) {
			throw new IllegalArgumentException("language must be not empty ...");
		}
		if (limit<1) {
			throw new IllegalArgumentException("limit must be bigger than 0 ...");
		}
		return productReleaseDao.queryProductRelease(language, limit);
	}

	@Override
	public List<ProductRelease> queryTopProductRelease(String language,
			int limit) {
		if (!StringUtils.hasText(language)) {
			throw new IllegalArgumentException("language must be not empty ...");
		}
		if (limit<1) {
			throw new IllegalArgumentException("limit must be bigger than 0 ...");
		}
		List<ProductRelease> list=productReleaseDao.queryTopProductRelease(language, limit);
		if (list==null || list.size()<1 && !Constants.DEAFULD_LANGUAGE.equals(language)) {
			return productReleaseDao.queryTopProductRelease(Constants.DEAFULD_LANGUAGE, limit);
		}
		return list;
	}

}

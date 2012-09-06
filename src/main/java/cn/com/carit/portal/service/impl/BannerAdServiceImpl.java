package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.AttachmentUtil;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.BannerAd;
import cn.com.carit.portal.dao.BannerAdDao;
import cn.com.carit.portal.service.BannerAdService;
import cn.com.carit.portal.web.CacheManager;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class BannerAdServiceImpl implements
		BannerAdService<BannerAd> {

	@Resource
	private BannerAdDao<BannerAd> dao;
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int saveOrUpdate(BannerAd t) throws Exception {
		int rows=0;
		if (t.getId()>0) {
			if (StringUtils.hasText(t.getImage())) {
				BannerAd old=dao.queryById(t.getId());
				AttachmentUtil.deleteImage(old.getImage());
				AttachmentUtil.deleteImage(old.getThumb());
			}
			rows = dao.update(t);
		} else {
			rows = dao.add(t);
		}
		if (rows>0) {
			CacheManager.getInstance().refreshBannerAd();
		}
		return rows;
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		BannerAd old=dao.queryById(id);
		AttachmentUtil.deleteImage(old.getImage());
		AttachmentUtil.deleteImage(old.getThumb());
		int rows = dao.delete(id);
		if (rows>0) {
			CacheManager.getInstance().refreshBannerAd();
		}
		return rows;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			String [] array=ids.split(",");
			for (String id : array) {
				delete(Integer.valueOf(id.trim()));
			}
			CacheManager.getInstance().refreshBannerAd();
			return array.length;
		}
		return 0;
	}
	
	@Override
	public BannerAd queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return dao.queryById(id);
	}

	@Override
	public JsonPage<BannerAd> queryByExemple(BannerAd t,
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
	public List<BannerAd> queryByExemple(BannerAd t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return dao.queryByExemple(t);
	}

	@Override
	public List<BannerAd> queryAll() {
		return dao.queryAll();
	}

	@Override
	public List<BannerAd> query(int limit) {
		return dao.query(limit);
	}

}

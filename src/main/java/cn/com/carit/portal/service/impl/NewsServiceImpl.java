package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.dao.NewsDao;
import cn.com.carit.portal.service.NewsService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class NewsServiceImpl implements NewsService<News> {
	
	@Resource
	private NewsDao<News> newsDao;

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int saveOrUpdate(News t) throws Exception {
		if (t.getId()>0) {
			return newsDao.update(t);
		} else {
			return newsDao.add(t);
		}
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return newsDao.delete(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return newsDao.batchDelete(ids);
		}
		return 0;
	}

	@Override
	public News queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return newsDao.queryById(id);
	}

	@Override
	public JsonPage<News> queryByExemple(News t, DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return newsDao.queryByExemple(t, dgm);
	}

	@Override
	public List<News> queryByExemple(News t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return newsDao.queryByExemple(t);
	}

	@Override
	public List<News> queryAll() {
		return newsDao.queryAll();
	}

	@Override
	public List<News> queryNews(int type, String language, int limit) {
		if(type<0 || type >1){
			throw new IllegalArgumentException("type must be 0 or 1 ...");
		}
		if (!StringUtils.hasText(language)) {
			throw new IllegalArgumentException("language must be not empty ...");
		}
		if (limit<1) {
			throw new IllegalArgumentException("limit must be bigger than 0 ...");
		}
		return newsDao.queryNews(type, language, limit);
	}

	@Override
	public List<News> queryNews(String language, int limit) {
		return newsDao.queryNews(language, limit);
	}

	@Override
	public News queryPrevNews(String language, int type, int currentId) {
		if (currentId==1) {
			return null;
		}
		return newsDao.queryPrevNews(language, type,currentId);
	}

	@Override
	public News queryNextNews(String language, int type,int currentId) {
		return newsDao.queryNextNews(language, type,currentId);
	}

}

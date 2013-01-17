package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.SupportLanguage;
import cn.com.carit.portal.dao.SupportLanguageDao;
import cn.com.carit.portal.service.SupportLanguageService;

@Service
@Transactional(readOnly=true)
public class SupportLanguageServiceImpl implements
		SupportLanguageService<SupportLanguage> {

	@Resource
	private SupportLanguageDao<SupportLanguage> supportLanguageDao;
	
	@Transactional(readOnly=false)
	@Override
	public int saveOrUpdate(SupportLanguage t) throws Exception {
		if (t.getId()>0) {
			return supportLanguageDao.update(t);
		} else {
			return supportLanguageDao.add(t);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return supportLanguageDao.delete(id);
	}

	@Transactional(readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return supportLanguageDao.batchDelete(ids);
		}
		return 0;
	}

	@Override
	public SupportLanguage queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return supportLanguageDao.queryById(id);
	}

	@Override
	public JsonPage<SupportLanguage> queryByExemple(SupportLanguage t,
			DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return supportLanguageDao.queryByExemple(t, dgm);
	}

	@Override
	public List<SupportLanguage> queryByExemple(SupportLanguage t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return supportLanguageDao.queryByExemple(t);
	}

	@Override
	public List<SupportLanguage> queryAll() {
		return supportLanguageDao.queryAll();
	}

}

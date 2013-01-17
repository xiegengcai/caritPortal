package cn.com.carit.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.Menu;
import cn.com.carit.portal.dao.MenuDao;
import cn.com.carit.portal.service.MenuService;

@Service
@Transactional(readOnly=true)
public class MenuServiceImpl implements MenuService<Menu> {
	
	@Resource
	private MenuDao<Menu> menuDao;
	
	@Transactional(readOnly=false)
	@Override
	public int saveOrUpdate(Menu t) throws Exception {
		if (t.getId()>0) {
			return menuDao.update(t);
		} else {
			return menuDao.add(t);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public int delete(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return menuDao.delete(id);
	}
	
	@Transactional(readOnly=false)
	@Override
	public int batchDelete(String ids) {
		if (StringUtils.hasText(ids)) {
			return menuDao.batchDelete(ids);
		}
		return 0;
	}
	@Override
	public Menu queryById(int id) {
		if(id<=0){
			throw new IllegalArgumentException("id must be bigger than 0...");
		}
		return menuDao.queryById(id);
	}

	@Override
	public JsonPage<Menu> queryByExemple(Menu t, DataGridModel dgm) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		if (dgm==null) {
			throw new NullPointerException("DataGridModel is null..");
		}
		return menuDao.queryByExemple(t, dgm);
	}

	@Override
	public List<Menu> queryByExemple(Menu t) {
		if (t==null) {
			throw new NullPointerException("sample is null..");
		}
		return menuDao.queryByExemple(t);
	}

	@Override
	public List<Menu> queryAll() {
		return menuDao.queryAll();
	}

	@Override
	public List<Menu> queryByParent(int parentId) {
		if(parentId<=0){
			throw new IllegalArgumentException("parentId must be bigger than 0...");
		}
		return menuDao.queryByParent(parentId);
	}

	@Override
	public List<Menu> queryTopMenus() {
		return menuDao.queryTopMenus();
	}

	@Override
	public List<Menu> querySubMenus() {
		return menuDao.querySubMenus();
	}

	@Override
	public int checkExisted(String code) {
		return menuDao.checkExisted(code);
	}

}

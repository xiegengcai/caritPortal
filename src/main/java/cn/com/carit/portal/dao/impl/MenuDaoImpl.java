package cn.com.carit.portal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.dao.MenuDao;

@Repository
public class MenuDaoImpl<Menu> extends BaseDaoImpl implements MenuDao<Menu> {

	@Override
	public int add(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Menu queryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonPage<Menu> queryByExemple(Menu t, DataGridModel dgm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> queryByExemple(Menu t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

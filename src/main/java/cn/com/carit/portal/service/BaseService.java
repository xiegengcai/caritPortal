package cn.com.carit.portal.service;

import java.util.List;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;

public interface BaseService<T> {
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	int saveOrUpdate(final T t) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(final int id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int batchDelete(final String ids);
	
	/**
	 * 按Id查询
	 * @param id
	 * @return
	 */
	T queryById(final int id);
	
	/**
	 * 分页的样例查询
	 * @param t
	 * @param dgm
	 * @return
	 */
	JsonPage<T> queryByExemple(final T t, final DataGridModel dgm);
	
	/**
	 * 不带分页的样例查询
	 * @param t
	 * @return
	 */
	List<T> queryByExemple(final T t);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<T> queryAll();
}

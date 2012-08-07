package cn.com.carit.portal.service;

import java.util.List;

public interface MenuService<Menu> extends BaseService<Menu> {
	/**
	 * 按父级菜单查询
	 * @param parentId
	 * @return
	 */
	List<Menu> queryByParent(int parentId);
	
	/**
	 * 查询顶级菜单
	 * @return
	 */
	List<Menu> queryTopMenus();
	
	/**
	 * 查询非顶级菜单
	 * @return
	 */
	List<Menu> querySubMenus();
	
	/**
	 * 检测
	 * @param code
	 * @return
	 */
	int checkExisted(String code);
}

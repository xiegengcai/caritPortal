package cn.com.carit.portal.dao;

public interface AdminUserDao<AdminUser> extends BaseDao<AdminUser> {
	
	/**
	 * 按邮箱地址查询
	 * @param email
	 * @return
	 */
	AdminUser queryByEmail(String email);
	
	/**
	 * 按邮箱or昵称检测用户是否存在
	 * @param email
	 * @param nickName
	 * @return
	 */
	int checkAdminUser(String email, String nickName);
}

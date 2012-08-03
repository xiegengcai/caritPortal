package cn.com.carit.portal.service;


public interface AdminUserService<AdminUser> extends BaseService<AdminUser> {
	
	/**
	 * 按邮箱or昵称检测用户是否存在
	 * @param email
	 * @param nickName
	 * @return
	 */
	int checkAdminUser(String email, String nickName);
	
}

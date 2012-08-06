package cn.com.carit.portal.service;

import java.util.Map;


public interface AdminUserService<AdminUser> extends BaseService<AdminUser> {
	
	/**
	 * 按邮箱or昵称检测用户是否存在
	 * @param email
	 * @param nickName
	 * @return
	 */
	int checkAdminUser(String email, String nickName);
	
	/**
	 * 用户登录
	 * @param email
	 * @param password
	 * @param ip 登录Ip地址
	 * @return {@link Map} 
	 * 返回的 Map 包含两个key， 一个是key为 answerCode 的响应值，值描述如下：<br>
	 *  <ul>
	 *  <li>-2 用户不存在</li>
	 * 	<li>-1  账号已经停用</li>
	 * 	<li>0	密码错误</li>
	 * 	<li>1	登录成功</li>
	 * 	<li>其它  后台异常</li>
	 * </ul>
	 * 另外一个是一emai为key，值为用户{@link AdminUser} 对象。如果响应值不为1，不需要处理该值
	 * @throws Exception
	 */
	Map<String, Object> login(String email, String password, String ip) throws Exception;
	
}

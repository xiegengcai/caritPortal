package cn.com.carit.portal.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.carit.portal.web.CacheManager;

public class BaseController {

	/**
	 * 获取session
	 * @return
	 */
	protected HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * 获取Request
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 设置属性
	 * @param attributeName
	 * @param attributeValue
	 * @param inSession
	 */
	protected void addAttribute(String attributeName, Object attributeValue, boolean inSession){
		if (inSession) {
			getSession().setAttribute(attributeName, attributeValue);
		} else {
			getRequest().setAttribute(attributeName, attributeValue);
		}
	}
	
	/**
	 * 获取地区默认语言，如果不支持则返回英文
	 * @return
	 */
	protected String getLocaleLanguage() {
		return getLocaleLanguage(getRequest().getLocale());
	}
	
	protected String getLocaleLanguage(Locale locale) {
		String language=locale.getLanguage().toLowerCase();
		if ("zh".equals(language)) { //中文地区特别处理
			if ("cn".equalsIgnoreCase(locale.getCountry())) {
				language="cn";
			} else {
				language="tw";
			}
		}
		return language;
	}
	
	protected void addCommonAttribute(String language) {
		// 设置语言参数
		getRequest().setAttribute("language", language);
		// 设置菜单列表
		getRequest().setAttribute("menuTree", CacheManager.getInstance().getMenuTree());
		getRequest().setAttribute("supportLanguages", CacheManager.getInstance().getSupportLanguages());
	}
}

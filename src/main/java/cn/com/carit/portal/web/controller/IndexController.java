package cn.com.carit.portal.web.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import cn.com.carit.common.Constants;
import cn.com.carit.portal.LanguageConfig;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.service.NewsService;
import cn.com.carit.portal.web.CacheManager;
@Controller
public class IndexController {
	
	@Resource(name="localeResolver")
	private CookieLocaleResolver localeResolver;
	
	@Resource
	private NewsService<News> newsService;
	
	/**
	 * 首页
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest req, Model model){
		Locale locale = req.getLocale();
		String language=locale.getLanguage().toLowerCase();
		if ("zh".equals(language)) { //中文地区特别处理
			if ("cn".equalsIgnoreCase(locale.getCountry())) {
				language="cn";
			} else {
				language="tw";
			}
		}
		doLocalIndex(model, language);
		return "index";
	}
	
	/**
	 * 切换语言
	 * @param language
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{language}", method=RequestMethod.GET)
	public String toLocal(@PathVariable String language, Model model){
		doLocalIndex(model, language.toLowerCase());
		return "index";
	}
	
	private void doLocalIndex(Model model, String language){
		// 切换语言
		localeResolver.setDefaultLocale(LanguageConfig.getInstance().getLocale(language));
		// 设置语言参数
		model.addAttribute("language", language);
		// 设置菜单列表
		model.addAttribute("menuTree", CacheManager.getInstance().getMenuTree());
		// 设置对应语言的业界新闻
		model.addAttribute("industryNewsList", newsService.queryNews(
				News.NEWS_TYPE_INDUSTRY, language, Constants.INDEX_SHOW_LIMIT));
		// 设置对应语言的公司新闻
		model.addAttribute("companyNewsList", newsService.queryNews(
				News.NEWS_TYPE_COMPANY, language, Constants.INDEX_SHOW_LIMIT));
		// 支持语言列表
		model.addAttribute("supportLanguages"
				, CacheManager.getInstance().getSupportLanguages());
	}
}
package cn.com.carit.portal.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
public class IndexController extends BaseController{
	
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
	public String index(){
		doLocalIndex(getLocaleLanguage());
		return "index";
	}
	
	/**
	 * 切换语言
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/{language}", method=RequestMethod.GET)
	public String toLocal(@PathVariable String language){
		doLocalIndex(language.toLowerCase());
		return "index";
	}
	
	private void doLocalIndex(String language){
		// 切换语言
		localeResolver.setDefaultLocale(LanguageConfig.getInstance().getLocale(language));
		addCommonAttribute(language);
		/*// 设置对应语言的业界新闻
		model.addAttribute("industryNewsList", newsService.queryNews(
				News.NEWS_TYPE_INDUSTRY, language, Constants.INDEX_SHOW_LIMIT));
		// 设置对应语言的公司新闻
		model.addAttribute("companyNewsList", newsService.queryNews(
				News.NEWS_TYPE_COMPANY, language, Constants.INDEX_SHOW_LIMIT));*/
		// 最新动态
		addAttribute("lastestNews", newsService.queryNews(language
				, Constants.INDEX_SHOW_LIMIT), false);
		// 支持语言列表
		addAttribute("supportLanguages"
				, CacheManager.getInstance().getSupportLanguages(), false);
	}
	
	/**
	 * 静态页面控制器
	 * @param language
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{language}/static/{page}", method=RequestMethod.GET)
	public String staticPage(@PathVariable String language, @PathVariable String page){
		defaultAttribute(language);
		if (!StringUtils.hasText(page)) {
			return toLocal(language);
		}
		if (!StringUtils.hasText(language)) {
			return "static/"+language+"/"+page;
		}
		return "static/"+language+"/"+page;
	}
	/**
	 * 产品页面
	 * @param language
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{language}/products", method=RequestMethod.GET)
	public String productsIndex(@PathVariable String language, Model model){
		defaultAttribute(language);
		model.addAttribute("catalogList", CacheManager.getInstance().getAllCatalogList());
		return "products";
	}
	
	private void defaultAttribute(String language) {
		if (!StringUtils.hasText(language)) {
			language=getLocaleLanguage();
		}
		// 切换语言
		localeResolver.setDefaultLocale(LanguageConfig.getInstance().getLocale(language));
		addCommonAttribute(language);
	}
	
	/**
	 * 联系我们
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/{language}/contact_us", method=RequestMethod.GET)
	public String contactUs(@PathVariable String language){
		defaultAttribute(language);
		return "contact_us";
	}
}
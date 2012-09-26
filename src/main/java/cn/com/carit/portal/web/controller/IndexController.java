package cn.com.carit.portal.web.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import cn.com.carit.common.Constants;
import cn.com.carit.portal.LanguageConfig;
import cn.com.carit.portal.bean.GlobalAddress;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.service.GlobalAddressService;
import cn.com.carit.portal.service.NewsService;
import cn.com.carit.portal.service.ProductReleaseService;
import cn.com.carit.portal.web.CacheManager;
@Controller
public class IndexController extends BaseController{
	
	@Resource(name="localeResolver")
	private SessionLocaleResolver localeResolver;
	
	@Resource
	private NewsService<News> newsService;
	
	@Resource
	private ProductReleaseService<ProductRelease> productReleaseService;
	
	@Resource
	private GlobalAddressService<GlobalAddress> globalAddressService;
	
	/**
	 * 首页
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(){
		doLocalIndex(getLocaleLanguage(localeResolver.resolveLocale(getRequest())));
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
		// 获取 Locale
		Locale locale=CacheManager.getInstance().getSupportedLocale(language);
		// 切换语言
		localeResolver.setDefaultLocale(locale);
		language=getLocaleLanguage(locale);
		addCommonAttribute(language);
		// 最新动态
		addAttribute("lastestNews", newsService.queryNews(language
				, Constants.INDEX_SHOW_LIMIT), false);
		// 支持语言列表
		addAttribute("supportLanguages"
				, CacheManager.getInstance().getSupportLanguages(), false);
		// 首页广告图片
		addAttribute("bannerAdList", CacheManager.getInstance().getBannerAdList(), false);
		// 产品
		addAttribute("products", productReleaseService.queryTopProductRelease(
				language, Constants.INDEX_SHOW_LIMIT), false);
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
	 * 产品列表页面
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
	
	/**
	 * 产品明细页面
	 * @param language
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{language}/product/{id}", method=RequestMethod.GET)
	public String productDetai(@PathVariable String language, @PathVariable int id){
		defaultAttribute(language);
		addAttribute("product", productReleaseService.queryById(id), false);
		return "product_detail";
	}
	

	/**
	 * 菜单跳转
	 * @param language
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/{language}/{menu}", method=RequestMethod.GET)
	public String menuForward(@PathVariable String language, @PathVariable String menu){
		defaultAttribute(language);
		if ("global".equals(menu)) {
			addAttribute("addressList", globalAddressService.query(language), false);
		}
		if("online_demo".equals(menu)){
			addAttribute("videoList", CacheManager.getInstance().getNewestVideoList(), false);
		}
		return menu;
	}
	
	/**
	 * 查看新闻
	 * @param language
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{language}/news/{id}", method=RequestMethod.GET)
	public String news(@PathVariable String language, @PathVariable int id){
		defaultAttribute(language);
		News currentNews=newsService.queryById(id);
		addAttribute("prevNews", newsService.queryPrevNews(language, currentNews.getType(), id), false);
		addAttribute("news", currentNews, false);
		addAttribute("nextNews", newsService.queryNextNews(language, currentNews.getType(), id), false);
		return "news";
	}
	
}
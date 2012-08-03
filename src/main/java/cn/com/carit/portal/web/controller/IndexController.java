package cn.com.carit.portal.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import cn.com.carit.portal.LanguageConfig;
@Controller
public class IndexController {
	
	@Resource(name="localeResolver")
	private CookieLocaleResolver localeResolver;

	/**
	 * 首页
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest req, Model model){
		doLocalIndex(model, req.getLocale().getCountry().toLowerCase());
		return "index";
	}
	
	/**
	 * 切换语言
	 * @param local
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{locale}", method=RequestMethod.GET)
	public String toLocal(@PathVariable String locale, Model model){
		doLocalIndex(model, locale.toLowerCase());
		return "index";
	}
	
	private void doLocalIndex(Model model, String locale){
		// 切换语言
		localeResolver.setDefaultLocale(LanguageConfig.getLocale(locale));
		model.addAttribute("locale", locale);
	}
}
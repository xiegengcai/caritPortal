package cn.com.carit.portal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 语言配置
 * @author ivan
 *
 */
public class LanguageConfig {

	public static Map<String, String> cache=new HashMap<String, String>();
	
	static {
		// 中文地区
		cache.put("CN", "zh");// 简体中文
		
		cache.put("TW", "zh");// 繁体中文
		
		// 英文地区
		cache.put("US", "en");//美国
		
		// 俄语地区
		cache.put("RU", "ru");//俄罗斯
		
		//法语地区
		cache.put("FR", "fr");//法语
		
		// 德语
		cache.put("DE", "de");
		
		// 意大利语
		cache.put("IT", "it");
		
		//日语
		cache.put("JP", "ja");
		
		//韩语
		cache.put("KR", "ko");
		
	}
	
	/**
	 * 根据国家地区ISO代码返回 Locale
	 * @param country
	 * @return
	 */
	public static Locale getLocale(String country){
		if (StringUtils.hasText(country)) {
			String lang=cache.get(country.toUpperCase());
			if (StringUtils.hasText(lang)) {
				return new Locale(lang, country);
			} else if ("HK".equalsIgnoreCase(country)){// 香港
				return Locale.TRADITIONAL_CHINESE;
			} else if ("MO".equalsIgnoreCase(country)){// 澳门
				return Locale.TRADITIONAL_CHINESE;
			} else if ("SG".equalsIgnoreCase(country)){ //新加坡
				return Locale.TRADITIONAL_CHINESE;
			}
		}
		return Locale.US;
	}
}

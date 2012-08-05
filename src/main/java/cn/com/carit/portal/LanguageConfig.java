package cn.com.carit.portal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 语言配置
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 *
 */
public class LanguageConfig {

	public static Map<String, Locale> cache=new HashMap<String, Locale>();
	
	static {
		// 中文地区
		cache.put("cn", Locale.SIMPLIFIED_CHINESE);// 简体中文
		
		cache.put("tw", Locale.TRADITIONAL_CHINESE);// 繁体中文
		
		// 英文地区
		cache.put("en", Locale.US);//美国
		
		// 俄语地区
		cache.put("ru", new Locale("ru", "RU"));//俄罗斯
		
		//法语地区
		cache.put("fr", Locale.FRANCE);//法语
		
		// 德语
		cache.put("de", Locale.GERMANY);
		
		// 意大利语
		cache.put("it", Locale.ITALY);
		
		//日语
		cache.put("ja", Locale.JAPAN);
		
		//韩语
		cache.put("ko", Locale.KOREA);
		
	}
	
	/**
	 * ISO语言代码<br>
	 * @param languageCode
	 * @return
	 */
	public static Locale getLocale(String languageCode){
		if (StringUtils.hasText(languageCode)) {
			Locale locale=cache.get(languageCode);
			if (locale!=null) {
				return locale;
			}
		}
		return Locale.US;
	}
}

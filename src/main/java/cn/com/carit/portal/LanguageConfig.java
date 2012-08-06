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

	public static class Holder {
		private static final LanguageConfig INSTANCE = new LanguageConfig();
	}
	
	private Map<String, Locale> cache;
	private Map<String, String> languagesMapper;
	
	private LanguageConfig(){
		cache=new HashMap<String, Locale>();
		languagesMapper=new HashMap<String, String>();
		
		// 中文地区
		cache.put("cn", Locale.SIMPLIFIED_CHINESE);// 简体中文
		languagesMapper.put("cn", "简体中文");
		
		cache.put("tw", Locale.TRADITIONAL_CHINESE);// 繁体中文
		languagesMapper.put("cn", "繁体中文");
		
		// 英文地区
		cache.put("en", Locale.US);//美国
		languagesMapper.put("en", "英语");
		
		// 俄语地区
		cache.put("ru", new Locale("ru", "RU"));//俄罗斯
		languagesMapper.put("ru", "俄语");
		
		//法语地区
		cache.put("fr", Locale.FRANCE);//法语
		languagesMapper.put("fr", "法语");
		
		// 德语
		cache.put("de", Locale.GERMANY);
		languagesMapper.put("de", "德语");
		
		// 意大利语
		cache.put("it", Locale.ITALY);
		languagesMapper.put("it", "意大利语");
		
		//日语
		cache.put("ja", Locale.JAPAN);
		languagesMapper.put("ja", "日语");
		
		//韩语
		cache.put("ko", Locale.KOREA);
		languagesMapper.put("ko", "韩语");
	}
	
	public static LanguageConfig getInstance(){
		return Holder.INSTANCE;
	}
	
	/**
	 * ISO语言代码<br>
	 * @param languageCode
	 * @return
	 */
	public Locale getLocale(String languageCode){
		if (StringUtils.hasText(languageCode)) {
			Locale locale=cache.get(languageCode);
			if (locale!=null) {
				return locale;
			}
		}
		return Locale.US;
	}

	public Map<String, String> getLanguagesMapper() {
		return languagesMapper;
	}
	
}

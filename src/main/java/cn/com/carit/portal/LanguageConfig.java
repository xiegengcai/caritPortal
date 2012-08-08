package cn.com.carit.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private List<Map<String, String>> configLanguages;
	
	private LanguageConfig(){
		cache=new HashMap<String, Locale>();
		configLanguages=new ArrayList<Map<String,String>>();
		
		
		// 中文地区
		cache.put("cn", Locale.SIMPLIFIED_CHINESE);// 简体中文
		configLanguages.add(buildLanguageMap("cn", "简体中文"));
		
		cache.put("tw", Locale.TRADITIONAL_CHINESE);// 繁体中文
		configLanguages.add(buildLanguageMap("tw", "繁体中文"));
		
		// 英文地区
		cache.put("en", Locale.US);//美国
		configLanguages.add(buildLanguageMap("en", "英语"));
		
		// 俄语地区
		cache.put("ru", new Locale("ru", "RU"));//俄罗斯
		configLanguages.add(buildLanguageMap("ru", "俄语"));
		
		//法语地区
		cache.put("fr", Locale.FRANCE);//法语
		configLanguages.add(buildLanguageMap("fr", "法语"));
		
		// 德语
		cache.put("de", Locale.GERMANY);
		configLanguages.add(buildLanguageMap("de", "德语"));
		
		// 意大利语
		cache.put("it", Locale.ITALY);
		configLanguages.add(buildLanguageMap("it", "意大利语"));
		
		//日语
		cache.put("ja", Locale.JAPAN);
		configLanguages.add(buildLanguageMap("ja", "日语"));
		
		//韩语
		cache.put("ko", Locale.KOREA);
		configLanguages.add(buildLanguageMap("ko", "韩语"));
	}
	
	private Map<String, String> buildLanguageMap(String isoCode, String name){
		Map<String, String> map=new HashMap<String, String>();
		map.put("code", isoCode);
		map.put("value", name);
		return map;
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

	public List<Map<String, String>> getConfigLanguages() {
		return configLanguages;
	}

}

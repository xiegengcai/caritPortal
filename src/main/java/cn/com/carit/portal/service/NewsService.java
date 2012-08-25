package cn.com.carit.portal.service;

import java.util.List;

public interface NewsService<News> extends BaseService<News> {
	/**
	 * 获取 ｛limit｝ 条  ｛type｝ 类别 ｛language｝语言的新闻动态
	 * @param type
	 * @param language
	 * @param limit
	 * @return
	 */
	List<News> queryNews(int type, String language, int limit);
	
	/**
	 * 获取 ｛limit｝ 条  ｛language｝语言的新闻动态
	 * @param language
	 * @param limit
	 * @return
	 */
	List<News> queryNews(String language, int limit);
}

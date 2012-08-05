package cn.com.carit.portal.service;

import java.util.List;

public interface NewsService<News> extends BaseService<News> {
	/**
	 * 获取 ｛limit｝ 条  ｛type｝ 类别 ｛language｝语言的新闻记录
	 * @param type
	 * @param language
	 * @param limit
	 * @return
	 */
	List<News> queryNews(int type, String language, int limit);
}
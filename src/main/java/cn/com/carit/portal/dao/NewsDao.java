package cn.com.carit.portal.dao;

import java.util.List;

public interface NewsDao<News> extends BaseDao<News> {
	/**
	 * 获取 ｛limit｝ 条  ｛type｝ 类别 ｛language｝语言的新闻记录
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
	
	/**
	 * 上一条
	 * @param language
	 * @param type
	 * @param currentId
	 * @return
	 */
	News queryPrevNews(String language, int type, int currentId);
	
	/**
	 * 下一条
	 * @param language
	 * @param type
	 * @param currentId
	 * @return
	 */
	News queryNextNews(String language, int type, int currentId);
}

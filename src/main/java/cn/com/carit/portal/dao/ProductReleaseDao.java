package cn.com.carit.portal.dao;

import java.util.List;

public interface ProductReleaseDao<ProductRelease> extends
		BaseDao<ProductRelease> {
	/**
	 * 获取 ｛limit｝ 条  ｛language｝语言的产品发布记录
	 * @param type
	 * @param language
	 * @param limit
	 * @return
	 */
	List<ProductRelease> queryProductRelease(String language, int limit);
	
	/**
	 * 获取 ｛limit｝ 条  ｛language｝语言置顶的产品发布记录
	 * @param language
	 * @param limit
	 * @return
	 */
	List<ProductRelease> queryTopProductRelease(String language, int limit);
}

package cn.com.carit.portal.service;

import java.util.List;

public interface ProductReleaseService<ProductRelease> extends
		BaseService<ProductRelease> {
	/**
	 * 获取 ｛limit｝ 条  ｛language｝语言的产品发布记录
	 * @param type
	 * @param language
	 * @param limit
	 * @return
	 */
	List<ProductRelease> queryProductRelease(String language, int limit);
}

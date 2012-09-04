package cn.com.carit.portal.service;

public interface MediaGalleryService<MediaGallery> extends
		BaseService<MediaGallery> {
	/**
	 * 检查分类是否存在
	 * @param name
	 * @return
	 */
	int checkExisted(String name);
}

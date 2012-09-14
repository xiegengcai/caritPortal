package cn.com.carit.portal.dao;

public interface MediaGalleryDao<MediaGallery> extends BaseDao<MediaGallery> {
	/**
	 * 检查分类是否存在
	 * @param name
	 * @return
	 */
	int checkExisted(String name);
	
	/**
	 * 检查是否使用
	 * @param url
	 * @return
	 */
	int checkUsed(String url);
}

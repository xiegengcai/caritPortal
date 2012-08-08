package cn.com.carit.portal.bean;

/**
 * 媒体库：图片、视频、flash
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 * 2012-8-5
 */
public class MediaGallery extends BaseBean {

	public static final int TYPE_IMAGE=0;
	public static final int TYPE_VIDEO=1;
	public static final int TYPE_FLASH=2;
	private String url;
	private String name;
	private Integer type;
	private String remark;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "MediaGallery [url=" + url + ", name=" + name + ", type=" + type
				+ ", remark=" + remark + ", id=" + id + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
}

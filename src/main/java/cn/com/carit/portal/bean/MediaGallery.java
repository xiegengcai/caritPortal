package cn.com.carit.portal.bean;

/**
 * 图片库
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 * 2012-8-5
 */
public class MediaGallery extends BaseBean {
	
	public static final int TOP=1;

	private String url;
	private String name;
	private Integer top;
	private String remark;
	private String href;//跳转链接
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
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	@Override
	public String toString() {
		return "MediaGallery [url=" + url + ", name=" + name + ", top=" + top
				+ ", remark=" + remark + ", href=" + href + ", id=" + id
				+ ", status=" + status + ", updateTime=" + updateTime + "]";
	}
	
}

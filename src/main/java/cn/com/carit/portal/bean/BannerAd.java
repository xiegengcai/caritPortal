package cn.com.carit.portal.bean;

public class BannerAd extends BaseBean {

	private String name;
	private String image;
	private String thumb;
	private String href;
	private String remark;
	private Integer displayIndex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}
	@Override
	public String toString() {
		return "BannerAd [name=" + name + ", image=" + image + ", thumb="
				+ thumb + ", href=" + href + ", remark=" + remark
				+ ", displayIndex=" + displayIndex + ", id=" + id + ", status="
				+ status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
}

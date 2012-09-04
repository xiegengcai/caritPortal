package cn.com.carit.portal.bean;

public class ProductRelease extends BaseBean {
	
	public static final int TOP=1;

	private Integer catalogId;
	private String language;
	private String title;
	private String content;
	private Boolean top;
	private String picture;
	private String mainPic;
	private String thumb;
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getTop() {
		return top;
	}
	public void setTop(Boolean top) {
		this.top = top;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	@Override
	public String toString() {
		return "ProductRelease [catalogId=" + catalogId + ", language="
				+ language + ", title=" + title + ", content=" + content
				+ ", top=" + top + ", picture=" + picture + ", mainPic="
				+ mainPic + ", thumb=" + thumb + ", id=" + id + ", status="
				+ status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
}

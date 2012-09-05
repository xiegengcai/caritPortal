package cn.com.carit.portal.bean;

public class DemoVideo extends BaseBean {

	private String name;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "DemoVideo [name=" + name + ", url=" + url + ", id=" + id
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}

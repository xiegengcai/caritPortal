package cn.com.carit.portal.bean;

public class Menu extends BaseBean {
	
	private String code;
	private String url;
	private Integer parentId;
	private Integer level;
	private Integer displayIndex;
	private String remark;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Menu [code=" + code + ", parentId=" + parentId + ", level="
				+ level + ", displayIndex=" + displayIndex + ", remark="
				+ remark + ", id=" + id + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}

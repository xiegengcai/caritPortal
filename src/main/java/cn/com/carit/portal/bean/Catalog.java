package cn.com.carit.portal.bean;

/**
 * 产品分类
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 * 2012-8-5
 */
public class Catalog extends BaseBean {
	/**
	 * catalogCode
	 */
	private String catalogCode;
	
	/**
	 * description
	 */
	private String description;
	
	/**
	 * displayIndex
	 */
	private Integer displayIndex;

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}

	@Override
	public String toString() {
		return "Catalog [catalogCode=" + catalogCode + ", description="
				+ description + ", displayIndex=" + displayIndex + ", id=" + id
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}

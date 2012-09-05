package cn.com.carit.portal.bean;

public class GlobalAddress extends BaseBean {

	private String language;
	private String address;
	private String telephone;
	private String fax;
	private String postalcode;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	@Override
	public String toString() {
		return "GlobalAddress [language=" + language + ", address=" + address
				+ ", telephone=" + telephone + ", fax=" + fax + ", postalcode="
				+ postalcode + ", id=" + id + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	
}

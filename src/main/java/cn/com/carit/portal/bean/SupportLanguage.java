package cn.com.carit.portal.bean;

/**
 * 支持语言
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 * 2012-8-6
 */
public class SupportLanguage extends BaseBean {
	
	private String isoCode;
	private String configKey;
	private String name;
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SupportLanguage [isoCode=" + isoCode + ", configKey="
				+ configKey + ", name=" + name + ", id=" + id + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
}

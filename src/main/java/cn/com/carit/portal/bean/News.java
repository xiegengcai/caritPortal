package cn.com.carit.portal.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.carit.common.jackjson.CustomDateSerializer;

public class News extends BaseBean {
	public static int NEWS_TYPE_COMPANY=0;
	public static int NEWS_TYPE_INDUSTRY=1;
	
	private Integer type;
	private String language;
	private String title;
	private String content;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	
	@Override
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return super.getCreateTime();
	}
	@Override
	public String toString() {
		return "News [type=" + type + ", language" + language 
				+ ", title=" + title + ", content="
				+ content + ", id=" + id + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}

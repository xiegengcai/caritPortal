package cn.com.carit.common.springmvc;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class LongEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals(""))
			text = "0";
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Long.parseLong(text));// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
		}

	}

}

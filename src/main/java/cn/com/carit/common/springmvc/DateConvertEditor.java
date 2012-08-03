package cn.com.carit.common.springmvc;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import cn.com.carit.common.Constants;

/**
 * spring中日期转换
 * 
 * <pre>
 * &#064;InitBinder
 * public void initBinder(WebDataBinder binder) {
 * 	binder.registerCustomEditor(Date.class, new DateConvertEditor());
 * 	// binder.registerCustomEditor(Date.class, new
 * 	// DateConvertEditor(&quot;yyyy-MM-dd&quot;));
 * 	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
 * }
 * </pre>
 * @author Xie Gengcai
 *
 */
public class DateConvertEditor extends PropertyEditorSupport {
	private DateFormat format;

	public DateConvertEditor() {
		this.format = new SimpleDateFormat(Constants.DATE_FORMATTER);
	}

	public DateConvertEditor(String format) {
		this.format = new SimpleDateFormat(format);
	}

	/** Date -> String */
	@Override
	public String getAsText() {
		if (getValue() == null)
			return "";
		return this.format.format(getValue());
	}

	/** String -> Date */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.isNotBlank(text)) {
			setValue(null);
		} else {
			try {
				setValue(this.format.parse(text));
			} catch (ParseException e) {
				throw new IllegalArgumentException("不能被转换的日期字符串，请检查!", e);
			}
		}
	}
}

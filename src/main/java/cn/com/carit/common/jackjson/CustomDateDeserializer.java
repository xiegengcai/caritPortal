package cn.com.carit.common.jackjson;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.carit.common.Constants;
/**
 * son由日期字符串转换为日期对象时做的转换
 * 
 * <pre>
 * &#064;JsonDeserialize(using = CustomDateDeserializer.class)
 * public void setTime(Date time) {
 * 	this.time = time;
 * }
 * </pre>
 * @author <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a>
 *
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {
	private static final Logger logger = LoggerFactory.getLogger(CustomDateDeserializer.class);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext arg1) throws IOException, JsonProcessingException {
		try {
			return DateUtils.parseDate(parser.getText(), new String []{Constants.DATE_FORMATTER});
		} catch (ParseException e) {
			logger.error("ParseException: ", e);
		}
		return null;
	}

}
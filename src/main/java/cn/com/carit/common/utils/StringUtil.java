package cn.com.carit.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class StringUtil {

	/**
	 * 将字段单词拆分，以"_"区分单词。如 updateTime，拆分后为update_time
	 * @param field
	 * @return
	 */
	public static String splitFieldWords(String field){
		StringBuilder str=new StringBuilder();
		char [] array=field.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (Character.isUpperCase(array[i])) {
				str.append("_").append(Character.toLowerCase(array[i]));
			} else {
				str.append(array[i]);
			}
		}
		return str.toString();
	}
	
	/**
	 * 将日期转为字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String dateToStr(Date date, String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	
	public static Date strToDate(String dateStr, String formatter) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.parse(dateStr);
	}
	public static void main(String[] args) {
		System.out.println(splitFieldWords("updateTime"));
		Calendar calendar=Calendar.getInstance();// 当前时间
		calendar.add(Calendar.DATE, -30);
		List<String> dateList=new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DATE, +1);
			System.out.println(dateToStr(calendar.getTime(), "yyyy-M-d"));
			dateList.add(dateToStr(calendar.getTime(), "yyyy-M-d"));
		}
		System.out.println(dateList.size());
		System.out.println(dateList.contains("2012-6-20"));
		System.out.println(dateList.contains("2012-5-22"));
		System.out.println(dateList.indexOf("2012-5-23"));
		Iterator<String> iterator = dateList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			iterator.remove();
		}
	}
	
}

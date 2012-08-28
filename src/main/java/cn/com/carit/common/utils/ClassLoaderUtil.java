package cn.com.carit.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打为jar包后也能找到配置文件，并以流的方式读取。 InputStream is =
 * ClassLoaderUtil.getResourceAsStream("config/others/config.properties",
 * MainTest.class); if (null != is) { reader = new InputStreamReader(is,
 * "UTF-8"); }
 * @author <a href="mailto:xiegengcai@gmail.com">Ivan Xie</a>
 *
 */
public class ClassLoaderUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassLoaderUtil.class);
	public static URL getResource(String resourceName, Class<?> callingClass) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
		if (url == null) {
			url = ClassLoaderUtil.class.getClassLoader().getResource(resourceName);
		}
		if (url == null) {
			ClassLoader cl = callingClass.getClassLoader();
			if (cl != null) {
				url = cl.getResource(resourceName);
			}
		}
		if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) {
			return getResource('/' + resourceName, callingClass);
		}
		if (url != null) {
			logger.info("config file path= " + url.getPath());
		}
		return url;
	}

	public static InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
		URL url = getResource(resourceName, callingClass);
		try {
			return (url != null) ? url.openStream() : null;
		} catch (IOException e) {
			logger.error("can't find this config file[" + resourceName + "]...", e);
			return null;
		}
	}
}

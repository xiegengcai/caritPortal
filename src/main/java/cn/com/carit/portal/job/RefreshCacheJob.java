package cn.com.carit.portal.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.com.carit.portal.web.CacheManager;

/**
 * 缓存更新Job
 * @author <a href="mailto:xiegengcai@gmail.com">Gengcai Xie</a>
 * 2012-8-16
 */
public class RefreshCacheJob extends QuartzJobBean  {

	private final Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("refresh cache start...");
		CacheManager.getInstance().refreshCache();
		logger.info("refresh cache end...");
	}

}

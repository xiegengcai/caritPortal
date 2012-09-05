package cn.com.carit.portal.service;

import java.util.List;


public interface DemoVideoService<DemoVideo> extends
		BaseService<DemoVideo> {
	List<DemoVideo> queryNewest(int limit);
}

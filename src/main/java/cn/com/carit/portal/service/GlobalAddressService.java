package cn.com.carit.portal.service;

import java.util.List;


public interface GlobalAddressService<GlobalAddress> extends
		BaseService<GlobalAddress> {
	
	List<GlobalAddress> query(String language);
}

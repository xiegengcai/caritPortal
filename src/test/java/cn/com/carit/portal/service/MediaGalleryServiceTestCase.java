package cn.com.carit.portal.service;

import javax.annotation.Resource;

import org.junit.Test;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.portal.BaseTestCase;
import cn.com.carit.portal.bean.MediaGallery;

public class MediaGalleryServiceTestCase extends BaseTestCase<MediaGallery>{
	@Resource
	private MediaGalleryService<MediaGallery> service;

	@Override
	@Test
	public void saveOrUpdate() throws Exception {
		MediaGallery t=new MediaGallery();
		t.setName("测试");
		t.setRemark("测试");
		t.setUrl("5555555555");
		t.setStatus(1);
		t.setTop(1);
		service.saveOrUpdate(t);
		
		t.setRemark("");
		
		t.setName("测试q");
		t.setId(1);
		service.saveOrUpdate(t);
	}

	@Override
	@Test
	public void delete() {
		service.delete(1);
	}

	@Override
	@Test
	public void batchDelete() {
		service.batchDelete("1, 2, 3, 4");
	}

	@Override
	@Test
	public void queryById() {
		System.out.println(service.queryById(1));
	}

	@Override
	@Test
	public void queryByExemple() {
		MediaGallery t=new MediaGallery();
		t.setName("测试");
		t.setRemark("测试");
		t.setStatus(1);
		t.setTop(1);
		System.out.println(service.queryByExemple(t, new DataGridModel()));
		System.out.println(service.queryByExemple(t));
	}

	@Override
	@Test
	public void queryAll() {
		System.out.println(service.queryAll());
	}
	
}

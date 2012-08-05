package cn.com.carit.portal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.com.carit.common.Constants;
import cn.com.carit.portal.bean.Catalog;
import cn.com.carit.portal.bean.Menu;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.bean.TreeNode;
import cn.com.carit.portal.service.CatalogService;
import cn.com.carit.portal.service.MenuService;
import cn.com.carit.portal.service.NewsService;
import cn.com.carit.portal.service.ProductReleaseService;

public class CacheManager {
	private final Logger logger=Logger.getLogger(getClass());
	
	private MenuService<Menu> menuService;
	
	private NewsService<News> newsService;
	
	private CatalogService<Catalog> catalogService;
	
	private ProductReleaseService<ProductRelease> productReleaseService;

	private List<TreeNode> menuTree;
	
	private Map<Integer, News> allNewsCache;
	
	private List<Catalog> allCatalogList;
	
	private Map<Integer, ProductRelease> allProductReleaseCache;
	
	public static class CacheHolder {
		private static final CacheManager INSTANCE = new CacheManager();
	}
	
	@SuppressWarnings("unchecked")
	private CacheManager() {
		logger.info(" init cache start...");
		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		menuService = (MenuService<Menu>) ctx.getBean("menuServiceImpl");
		newsService = (NewsService<News>) ctx.getBean("newsServiceImpl");
		catalogService = (CatalogService<Catalog>) ctx.getBean("catalogServiceImpl");
		productReleaseService = (ProductReleaseService<ProductRelease>) ctx.getBean("productReleaseServiceImpl");
		
		menuTree=new ArrayList<TreeNode>();
		buildMenuTree();
		
		allNewsCache=new ConcurrentHashMap<Integer, News>();
		buildNews();
		
//		allCatalogList=new ArrayList<Catalog>();
		refreshCatalogs();
		
		allProductReleaseCache=new ConcurrentHashMap<Integer, ProductRelease>();
		buildProductReleases();
		logger.info(" init cache end ...");
	}
	
	public static CacheManager getInstance(){
		return CacheHolder.INSTANCE;
	}
	
	public void refreshCache(){
		refreshMenu();
		refreshNews();
		refreshCatalogs();
		refreshProducts();
	}
	
	public void refreshMenu(){
		menuTree.clear();
		buildMenuTree();
	}
	
	private void buildMenuTree(){
		List<Menu> topMenus=menuService.queryTopMenus();
		for (Menu menu : topMenus) {
			TreeNode node=new TreeNode();
			node.setId(menu.getId());
			node.setCode(menu.getCode());
			node.setUrl(menu.getUrl());
			menuTree.add(node);
			buildSubTree(node);
		}
	}
	
	private void buildSubTree(TreeNode node){
		List<Menu> subMenus=menuService.queryByParent(node.getId());
		if (subMenus!=null && subMenus.size() > 0) {
			List<TreeNode> children=new ArrayList<TreeNode>();
			for (Menu menu : subMenus) {
				TreeNode subNode=new TreeNode();
				subNode.setId(menu.getId());
				subNode.setCode(menu.getCode());
				subNode.setUrl(menu.getUrl());
				children.add(subNode);
				buildSubTree(subNode);
			}
			node.setChildren(children);
		}
	}
	
	public List<TreeNode> getMenuTree(){
		return menuTree;
	}
	
	public void refreshNews(){
		allNewsCache.clear();
		buildNews();
	}
	
	private void buildNews(){
		News sample=new News();
		sample.setStatus(Constants.STATUS_VALID);
		List<News> allNewsList = newsService.queryByExemple(sample);
		for (News t : allNewsList) {
			allNewsCache.put(t.getId(), t);
		}
	}
	
	public News getNews(int id) {
		return allNewsCache.get(id);
	}
	
	private void buildProductReleases(){
		ProductRelease sample = new ProductRelease();
		sample.setStatus(Constants.STATUS_VALID);
		List<ProductRelease> list=productReleaseService.queryByExemple(sample);
		for (ProductRelease productRelease : list) {
			allProductReleaseCache.put(productRelease.getId(), productRelease);
		}
	}
	
	public void refreshProducts(){
		allProductReleaseCache.clear();
		buildProductReleases();
	}
	
	public ProductRelease getProductRelease(int id){
		return allProductReleaseCache.get(id);
	}
	
	public void refreshCatalogs(){
		Catalog sample=new Catalog();
		sample.setStatus(Constants.STATUS_VALID);
		allCatalogList=catalogService.queryByExemple(sample);
	}

	public List<Catalog> getAllCatalogList() {
		return allCatalogList;
	}
	
}
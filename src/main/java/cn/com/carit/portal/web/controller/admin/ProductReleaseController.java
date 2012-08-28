package cn.com.carit.portal.web.controller.admin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.service.ProductReleaseService;
import cn.com.carit.portal.web.controller.BaseController;

@Controller
@RequestMapping(value="admin/product")
public class ProductReleaseController extends BaseController {
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	@Resource
	private ProductReleaseService<ProductRelease> service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("product", new ProductRelease());
		return "admin/product";
	}
	
	/**
	 * 新增/保存<br>
	 * admin/product/save
 	 * @param product
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public int save(@ModelAttribute(value="product") ProductRelease product, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return -1;
		}
		return service.saveOrUpdate(product);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public String edit(@RequestParam(required=false) int id, Model model){
		if(id>0){
			model.addAttribute("product", service.queryById(id));
		}else{
			model.addAttribute("product", new ProductRelease());
		}
		return "admin/edit_product";
	}
	
	/**
	 * 查看<br>
	 * admin/product/view?id={id}
	 * @param id
	 * @return
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	@ResponseBody
	public ProductRelease view(@RequestParam int id){
		if (id<=0) {
			log.warn("The param id must be bigger than 0...");
			return null;
		}
		return service.queryById(id);
	}
	
	/**
	 * 删除
	 * admin/product/delete?id=|ids=
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.GET)
	@ResponseBody
	public int delete(@RequestParam(required=false) int id
			, @RequestParam(required=false) String ids){
		if (StringUtils.hasText(ids)) {
			return service.batchDelete(ids);
		} else if (id>0) {
			return service.delete(id);
		} else {
			throw new IllegalArgumentException("both id and ids are empty...");
		}
	}
	
	/**
	 * 查询
	 * admin/product/query
	 * @return {@link JsonPage<ProductRelease>}
	 */
	@RequestMapping(value="query", method=RequestMethod.GET)
	@ResponseBody
	public JsonPage<ProductRelease> query(@ModelAttribute(value="product") ProductRelease product, BindingResult result,DataGridModel dgm){
		return service.queryByExemple(product, dgm);
	}
}

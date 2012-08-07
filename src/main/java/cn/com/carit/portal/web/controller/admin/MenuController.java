package cn.com.carit.portal.web.controller.admin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import cn.com.carit.portal.bean.Menu;
import cn.com.carit.portal.service.MenuService;

@Controller
@RequestMapping(value="admin/menu")
public class MenuController {
	private final Logger log = Logger.getLogger(getClass());
	@Resource
	private MenuService<Menu> menuService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute(new Menu());
		return "admin/menu";
	}
	
	/**
	 * 新增/保存<br>
	 * admin/menu/save
 	 * @param menu
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public int save(@ModelAttribute Menu menu, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return -1;
		}
		return menuService.saveOrUpdate(menu);
	}
	
	/**
	 * 查看<br>
	 * admin/menu/view?id={id}
	 * @param id
	 * @return
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	@ResponseBody
	public Menu view(@RequestParam int id){
		if (id<=0) {
			log.warn("The param id must be bigger than 0...");
			return null;
		}
		return menuService.queryById(id);
	}
	
	/**
	 * 删除
	 * admin/menu/delete?id=|ids=
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.GET)
	@ResponseBody
	public int delete(@RequestParam(required=false) int id
			, @RequestParam(required=false) String ids){
		if (StringUtils.hasText(ids)) {
			return menuService.batchDelete(ids);
		} else if (id>0) {
			return menuService.delete(id);
		} else {
			throw new IllegalArgumentException("both id and ids are empty...");
		}
	}
	
	/**
	 * 查询
	 * admin/menu/query
	 * @return {@link JsonPage<Menu>}
	 */
	@RequestMapping(value="query", method=RequestMethod.GET)
	@ResponseBody
	public JsonPage<Menu> query(@ModelAttribute Menu menu, BindingResult result,DataGridModel dgm){
		return menuService.queryByExemple(menu, dgm);
	}
}

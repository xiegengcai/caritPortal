package cn.com.carit.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import cn.com.carit.portal.bean.DemoVideo;
import cn.com.carit.portal.service.DemoVideoService;
import cn.com.carit.portal.web.controller.BaseController;

@Controller
@RequestMapping(value="admin/demo/video")
public class DemoVideoController extends BaseController {
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	@Resource
	private DemoVideoService<DemoVideo> service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("demoVideo", new DemoVideo());
		return "admin/demo_video";
	}
	
	/**
	 * 新增/保存<br>
	 * admin/demo/video/save
 	 * @param demo/video
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody int save(@ModelAttribute(value="demoVideo") DemoVideo demoVideo
			, BindingResult result, HttpServletRequest request) throws Exception{
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return -1;
		}
    	return service.saveOrUpdate(demoVideo);
	}
	
	/**
	 * 查看<br>
	 * admin/demo/video/view?id={id}
	 * @param id
	 * @return
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	@ResponseBody
	public DemoVideo view(@RequestParam int id){
		if (id<=0) {
			log.warn("The param id must be bigger than 0...");
			return null;
		}
		return service.queryById(id);
	}
	
	/**
	 * 删除
	 * admin/demo/video/delete?id=|ids=
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
	 * admin/demo/video/query
	 * @return {@link JsonPage<DemoVideo>}
	 */
	@RequestMapping(value="query", method=RequestMethod.GET)
	@ResponseBody
	public JsonPage<DemoVideo> query(@ModelAttribute(value="demoVideo") DemoVideo demoVideo, BindingResult result,DataGridModel dgm){
		return service.queryByExemple(demoVideo, dgm);
	}
}

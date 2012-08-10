package cn.com.carit.portal.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.AttachmentUtil;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.MD5Util;
import cn.com.carit.portal.bean.MediaGallery;
import cn.com.carit.portal.service.MediaGalleryService;

@Controller
@RequestMapping(value="admin/media")
public class MediaGalleryController {
	private final Logger log = Logger.getLogger(getClass());
	
	@Resource
	private MediaGalleryService<MediaGallery> service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("media", new MediaGallery());
		return "admin/media";
	}
	
	/**
	 * 新增/保存<br>
	 * admin/media/save
 	 * @param media
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public int save(@ModelAttribute(value="media") MediaGallery media
			, BindingResult result, HttpServletRequest request) throws Exception{
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return -1;
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		MultipartFile multipartFile = multipartRequest.getFile("file");
		if(media.getId()==0&&(multipartFile==null||multipartFile.getOriginalFilename().length()<=0)){
			// 新增时必须上传Apk文件
			log.debug("file must be not empty ...");
			return -1;
		}
    	if (multipartFile!=null && multipartFile.getOriginalFilename().length()>0) { // 上传文件
    		String prefix=MD5Util.md5Hex(System.nanoTime()+"");
    		String suffix = multipartFile.getOriginalFilename().substring(
    				multipartFile.getOriginalFilename().lastIndexOf(".")-1);
    		String fileName = (prefix + "." + suffix).toLowerCase();// 构建文件名称
    		// 图片文件
    		if (media.getType()==MediaGallery.TYPE_IMAGE) {
    			media.setUrl(Constants.BASE_PATH_IMAGE+fileName);
    			multipartFile.transferTo(AttachmentUtil.getImageFile(fileName));
			} else if (media.getType()==MediaGallery.TYPE_VIDEO) {
				media.setUrl(Constants.BASE_PATH_VIDEO+fileName);
    			multipartFile.transferTo(AttachmentUtil.getVideoFile(fileName));
			} else if (media.getType()==MediaGallery.TYPE_FLASH){
				media.setUrl(Constants.BASE_PATH_FLASH+fileName);
    			multipartFile.transferTo(AttachmentUtil.getFlashFile(fileName));
			} else {
				throw new IllegalArgumentException("not support media type...");
			}
		}
		return service.saveOrUpdate(media);
	}
	
	/**
	 * 查看<br>
	 * admin/media/view?id={id}
	 * @param id
	 * @return
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	@ResponseBody
	public MediaGallery view(@RequestParam int id){
		if (id<=0) {
			log.warn("The param id must be bigger than 0...");
			return null;
		}
		return service.queryById(id);
	}
	
	/**
	 * 删除
	 * admin/media/delete?id=|ids=
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
	 * admin/media/query
	 * @return {@link JsonPage<MediaGallery>}
	 */
	@RequestMapping(value="query", method=RequestMethod.GET)
	@ResponseBody
	public JsonPage<MediaGallery> query(@ModelAttribute(value="media") MediaGallery media, BindingResult result,DataGridModel dgm){
		return service.queryByExemple(media, dgm);
	}
}

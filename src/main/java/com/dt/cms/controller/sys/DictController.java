package com.dt.cms.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.cms.controller.BaseController;
import com.dt.cms.domain.sys.Dict;
import com.dt.cms.entity.Page;
import com.dt.cms.persistant.PropertyFilter;
import com.dt.cms.service.sys.DictService;

/**
 * 字典controller
 * @author 岳海亮
 * @date 2017年7月4日
 */
@Controller
@RequestMapping("system/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/dictList";
	}

	/**
	 * 获取字典json
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> dictList(HttpServletRequest request) {
		Page<Dict> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = dictService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 添加字典跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sys:dict:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("dict", new Dict());
		model.addAttribute("action", "create");
		return "system/dictForm";
	}

	/**
	 * 添加字典
	 * 
	 * @param dict
	 * @param model
	 */
	@RequiresPermissions("sys:dict:add")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(Dict dict, Model model) {
		dictService.save(dict);
		return "success";
	}

	/**
	 * 修改字典跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:dict:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("dict", dictService.get(id));
		model.addAttribute("action", "update");
		return "system/dictForm";
	}

	/**
	 * 修改字典
	 * 
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:dict:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute @RequestBody Dict dict, Model model) {
		dictService.update(dict);
		return "success";
	}

	/**
	 * 删除字典
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sys:dict:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		dictService.delete(id);
		return "success";
	}

	@ModelAttribute
	public void getDict(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("dict", dictService.get(id));
		}
	}

}

package com.dt.cms.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.cms.controller.BaseController;
import com.dt.cms.domain.sys.Organization;
import com.dt.cms.service.sys.OrganizationService;

/**
 * 机构信息controller
 * @author 岳海亮
 * @date 2017年7月4日
 */
@Controller
@RequestMapping("system/organization")
public class OrganizationController extends BaseController {

	@Autowired
	private OrganizationService organizationService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/organization/orgList";
	}

	/**
	 * 获取机构信息json
	 */
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> areaInfoList(HttpServletRequest request) {
		List<Organization> organizations = organizationService.getAll();
		return organizations;
	}

	/**
	 * 添加机构信息跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("organization", new Organization());
		model.addAttribute("action", "create");
		return "system/organization/orgForm";
	}

	/**
	 * 添加机构信息
	 * 
	 * @param organization
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(Organization organization, Model model) {
		organizationService.save(organization);
		return "success";
	}

	/**
	 * 修改机构信息跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("organization", organizationService.get(id));
		model.addAttribute("action", "update");
		return "system/organization/orgForm";
	}

	/**
	 * 修改机构信息
	 * 
	 * @param goodsType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute @RequestBody Organization organization, Model model) {
		organizationService.update(organization);
		return "success";
	}

	/**
	 * 删除机构信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		organizationService.delete(id);
		return "success";
	}

}

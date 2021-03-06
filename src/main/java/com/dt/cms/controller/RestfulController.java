package com.dt.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dt.cms.domain.sys.User;
import com.dt.cms.entity.Page;
import com.dt.cms.persistant.PropertyFilter;
import com.dt.cms.service.sys.UserService;

/**
 * 为前端跨域调用做的接口
 * 
 * @author 岳海亮
 * @date 2017年7月18日
 */
@Controller
@RequestMapping("api")
public class RestfulController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 获取用户列表
	 */
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public String list(HttpServletRequest request) {
		Page<User> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = userService.search(page, filters);
		String jsonStr = JSONObject.toJSONString(getEasyUIData(page));
		return "jsonpCallback(" + jsonStr + ")";
	}

}

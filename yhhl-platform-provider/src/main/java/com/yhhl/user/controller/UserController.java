package com.yhhl.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.yhhl.common.StringUtil;
import com.yhhl.core.Page;
import com.yhhl.interceptor.Token;
import com.yhhl.user.model.User;
import com.yhhl.user.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {

	public static String TABLETAG = "com.yhhl.user.model.User";

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		return new ModelAndView("user/showUser");
	}
	
	/**
	 * 进入到初始化新增、修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddUser")
	@Token(save = true)
	public ModelAndView initAdd(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			User user = userService.getById(id);
			request.setAttribute("user", user);
		}
		return new ModelAndView("user/addUser");
	}

	/**
	 * 新增、修改
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	@Token(remove = true)
	@ResponseBody
	public Map<String, Object> saveUser(User user, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(user.getId())){
			User u = userService.getById(user.getId());
			u.setName(user.getName());
			u.setPwd(user.getPwd());
			u.setModifydatetime(new Date());
			userService.updateUser(u);
			map.put("flag", "T");
			map.put("msg", "修改成功");
			return map;
		}
		Date d = new Date();
		user.setCreateTime(d);
		user.setCreatedatetime(d);
		user.setUpdateTime(d);
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setModifydatetime(d);
		userService.saveUser(user);
		map.put("flag", "T");
		map.put("msg", "保存成功");
		return map;
	}

	/**
	 * 查询用户 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public Map<String, Object> getUsers(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows) {
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<User> dataPage = new Page<User>();
		dataPage = userService.getAll(filterMap, dataPage, page, rows);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		return mapData;
	}
	
	
	
	@RequestMapping("/delUser")
	@ResponseBody
	public Map<String, Object> delUser(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		userService.deleteById(id);
		map.put("flag", "T");
		map.put("msg", "删除成功");
		return map;
	}
	
	
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable String id, HttpServletRequest request) {
		User u = userService.getById(id);
		request.setAttribute("user", u);
		return "user/showUser";
	}
	
	/**
	 * 进入Json列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index-json.do")
	public ModelAndView indexJson() {
		return new ModelAndView("user/user-json-page");
	}
	
	/**
	 * 获取json数据
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/jsonPage")
	@ResponseBody
	public Map<String, Object> getJsonPage(HttpServletRequest request, @RequestParam(value = "page") int page,
			@RequestParam(value = "rows") int rows){
		Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "filter_");
		Page<User> dataPage = new Page<User>();
		dataPage = userService.getAll(filterMap, dataPage, page, rows);
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", dataPage.getTotalCount());
		mapData.put("rows", dataPage.getResult());
		mapData.put("nextPage", dataPage.getNextPage());
		mapData.put("prePage", dataPage.getPrePage());
		mapData.put("pageNo", dataPage.getPageNo());
		return mapData;
	}
}

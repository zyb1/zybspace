package com.sbq.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sbq.model.User;
import com.sbq.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController
{
	
	@Autowired
	private IUserService userService = null;
	
	@RequestMapping(value = "/getUserList")
	public @ResponseBody List<User> getUserList()
	{
		List<User> result = userService.getUserList();
		return result;
	}
	
	@RequestMapping(value = "/getUserListAndDept")
	public @ResponseBody List<Map<String, Object>> getUserListAndDept()
	{
		
		List<Map<String, Object>> result = userService.getUserListAndDept();
		
		if (result != null && result.size() > 0)
		{
			for (int i = 0; i < result.size(); i++)
			{
				Map<String, Object> map = result.get(i);
				// 可通过key获取对应变量值
				// logger.info("user_name:" + map.get("username"));
				// logger.info("dept_name:" + map.get("dept_name"));
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/getUserListByPage")
	public @ResponseBody PageInfo<User> getUserListByPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize)
	{
		return userService.getUserListByPage(pageIndex, pageSize);
	}
	
	@RequestMapping(value = "/insertUser")
	public void insertUser()
	{
		User user = new User();
		user.setUsername("zhangy");
		user.setPassword("34567");
		long key = userService.insert(user);
		
	}
	
	@RequestMapping(value = "/table")
	public String table()
	{
		return "table";
	}
	
	@RequestMapping(value = "/tab")
	public String tab()
	{
		return "tab";
	}
}

package com.sbq.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sbq.model.User;

public interface IUserService {

	public List<User> getUserList();

	public List<Map<String, Object>> getUserListAndDept();

	public PageInfo<User> getUserListByPage(int pageNum, int pageSize);
	
	public long insert(User user);
}

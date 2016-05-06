package com.sbq.dao;

import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.sbq.model.User;

import tk.mybatis.mapper.common.Mapper;

public interface IUserDao extends Mapper<User> {

	@Cacheable(cacheName = "baseCache", keyGeneratorName = "getUserList")
	public List<User> getUserList();

	public List<Map<String, Object>> getUserListAndDept();

	public List<User> getUserListByPage(int pageNum, int pageSize);
}

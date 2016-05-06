package com.sbq.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sbq.dao.IUserDao;
import com.sbq.model.User;
import com.sbq.service.BaseService;
import com.sbq.service.IUserService;
import com.sbq.util.EhcacheUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	private IUserDao userDao;

	// 注解方式，配置文件定义了存活时间，超过时间还会在查库
	// @Cacheable(value = "MyCache")
	public List<User> getUserList() {

		// 注销的内容是手动进行缓存的检查,缓存也是有时间的

		// Element el = null;
		// CacheManager manager = CacheManager.create();
		// Cache cache = manager.getCache("MyCache");
		//
		// if (cache.isKeyInCache("getUserList")) {
		// el = cache.get("getUserList");
		// // 到期的瞬间，进行null的判断
		// if (el != null)
		// return (List<User>) el.getObjectValue();
		// }

		if (EhcacheUtils.get("getUserList") != null) {
			return (List<User>) EhcacheUtils.get("getUserList");
		}

		List<User> result = userDao.getUserList();

		if (result != null) {
			EhcacheUtils.put("getUserList", result);
			// el = new Element("getUserList", result);
			// cache.put(el);
		}
		return result;
	}

	public List<Map<String, Object>> getUserListAndDept() {
		return userDao.getUserListAndDept();
	}

	public PageInfo<User> getUserListByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userDao.getUserList();
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Transactional
	// 注解方式，由于配置文件存活时间的限制，删除无法立即生效
	// @CacheEvict(value = "MyCache")
	public long insert(User user) {

		EhcacheUtils.remove("getUserList");
		
//		// 手动处理
//		CacheManager manager = CacheManager.create(); //
//		// 通过manager可以生成指定名称的Cache对象
//		Cache cache = manager.getCache("MyCache");
//		if (cache.isKeyInCache("getUserList")) { // 将指定key的缓存对象从缓存中清除
//			cache.remove("getUserList");
//		}
		return userDao.insert(user);
	}

	// UserInfo userInfo = new UserInfo();
	// userInfo.setUsername("abel533");
	// userInfo.setPassword("123456");
	// userInfo.setUsertype("2");
	// userInfo.setEmail("abel533@gmail.com");
	// //新增一条数据
	// Assert.assertEquals(1, mapper.insert(userInfo));
	// //ID回写,不为空
	// Assert.assertNotNull(userInfo.getId());
	// //6是当前的ID
	// Assert.assertEquals(6, (int)userInfo.getId());
	// //通过主键删除新增的数据
	// Assert.assertEquals(1,mapper.deleteByPrimaryKey(userInfo));

	// //获取Mapper
	// CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
	// //查询总数
	// Assert.assertEquals(183, mapper.selectCount(new Country()));
	// //查询100
	// Country country = mapper.selectByPrimaryKey(100);
	// //根据主键删除
	// Assert.assertEquals(1, mapper.deleteByPrimaryKey(100));
	// //查询总数
	// Assert.assertEquals(182, mapper.selectCount(new Country()));
	// //插入
	// Assert.assertEquals(1, mapper.insert(country));

	// 条件查询
	// Example example = new Example(Country.class);
	// example.createCriteria()
	// .andCondition("countryname like 'C%' and id < 100")
	// .andCondition("length(countryname) = ", 5)
	// .andCondition("countrycode =", "CN", StringTypeHandler.class);
	// List<Country> countries = mapper.selectByExample(example);
}

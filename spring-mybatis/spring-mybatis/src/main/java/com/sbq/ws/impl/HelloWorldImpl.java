package com.sbq.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbq.model.User;
import com.sbq.service.IUserService;
import com.sbq.util.SpringContext;
import com.sbq.ws.IHelloWorld;

@WebService()
public class HelloWorldImpl implements IHelloWorld {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private IUserService userService = null;

	public IUserService getUserService() {

		if (userService == null) {
			userService = (IUserService) SpringContext.getBean("userServiceImpl");
		}
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public String sayHello(String username) {

		List<User> userList = this.getUserService().getUserList();
		logger.info(userList.size() + "");
		return userList.toString();
	}

}

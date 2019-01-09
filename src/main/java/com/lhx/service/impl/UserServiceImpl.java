package com.lhx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhx.dao.UserDao;
import com.lhx.entity.User;
import com.lhx.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Resource
	private UserDao userDao;
	@Override
	public User getTest() {
		// TODO Auto-generated method stub
		return userDao.getTest();
	}


}

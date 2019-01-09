package com.lhx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lhx.dao.UserDao;
import com.lhx.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	
	@Override
	public User getTest() {
		// TODO Auto-generated method stub
		
		return (User) getSession().createQuery("from User where id=1").uniqueResult();
	}


}

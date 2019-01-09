package com.lhx.dao;


import org.springframework.stereotype.Repository;

import com.lhx.entity.User;

public interface UserDao extends BaseDao<User, Integer>  {
	

	public User getTest();
}

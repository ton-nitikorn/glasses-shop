package com.glassshop.dao;

import com.glassshop.model.UserModel;


public interface UserDao {

	public boolean selectPassword(String password);
	
	public int changePassword(String password);
	
	public UserModel selectById(String id);
}

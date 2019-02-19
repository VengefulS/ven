package cn.org.cflac.home.service;

import java.util.Map;

import cn.org.cflac.entity.User;

public interface UserService {

	User getUser(String userLoginname,String userPassword);
	
}

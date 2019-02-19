package cn.org.cflac.home.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.cflac.entity.User;
import cn.org.cflac.home.mapper.UserMapper;
import cn.org.cflac.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public User getUser(String userLoginname,String userPassword) {
		userPassword=MD5Util.getMD5(userPassword);	
		/*System.out.println("userPassword:"+userPassword);
		User user = userMapper.getUser(userLoginname, userPassword);*/
		return userMapper.getUser(userLoginname,userPassword);
	}

}

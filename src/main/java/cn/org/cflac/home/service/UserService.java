package cn.org.cflac.home.service;

import cn.org.cflac.entity.User;

public interface UserService {

	User getUser(String userLoginname,String userPassword);
}

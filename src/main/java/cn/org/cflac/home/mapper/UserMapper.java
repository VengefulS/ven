package cn.org.cflac.home.mapper;

import cn.org.cflac.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}
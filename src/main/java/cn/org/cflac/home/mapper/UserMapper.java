package cn.org.cflac.home.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.org.cflac.entity.User;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    
    User getUser(@Param("userLoginname")String userLoginname,@Param("userPassword")String userPassword);
}
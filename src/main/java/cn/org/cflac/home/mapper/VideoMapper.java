package cn.org.cflac.home.mapper;

import cn.org.cflac.entity.Video;

public interface VideoMapper {
    int insert(Video record);

    int insertSelective(Video record);
}
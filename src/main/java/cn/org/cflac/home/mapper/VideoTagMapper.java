package cn.org.cflac.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoTagMapper {
    
    //根据视频标签查到活动
    List<String> findAidListByVtn(String videoTag);
}
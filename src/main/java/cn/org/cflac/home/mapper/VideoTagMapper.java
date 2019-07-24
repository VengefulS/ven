package cn.org.cflac.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.org.cflac.entity.VideoTag;

@Mapper
public interface VideoTagMapper {
    
    //根据视频标签查到活动
    List<String> findAidListByVtn(String videoTag);
    //添加视频标签
    Integer insertVideoTag(VideoTag videoTag);
    //删除视频标签
    Integer deleteVideoTag(String invalid);
    //修改视频标签
    Integer updateVideoTag(String tagId,String tagName);
    
}
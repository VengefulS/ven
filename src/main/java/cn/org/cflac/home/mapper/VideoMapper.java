package cn.org.cflac.home.mapper;

import java.util.List;
import java.util.Map;



import org.apache.ibatis.annotations.Mapper;

import cn.org.cflac.entity.Video;
@Mapper
public interface VideoMapper {
    
    
    //根据活动ID查询视频列表
    List<Video> findVideoByActid(Map map);
}
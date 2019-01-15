package cn.org.cflac.home.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.org.cflac.entity.Activity;


@Mapper
public interface ActivityMapper {
    
    List<Activity> findAll(String activityId);

    // 分页查询数据
    List<Activity> findActivityList(Map map);
    // 查询总数量
    Integer findActivityCount(Map map);
}
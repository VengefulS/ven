package cn.org.cflac.home.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface VideoTagMapper {
    
//    //根据视频标签查到活动
//    List<String> findAidListByVtn(String videoTag);
    //查询所有视频标签名称
	List<String> findAllVideoTagName();
	//
	List<Map<String,String>> findAllVideoTag();
	//根据标签名字查标签ID
	String findTagIdByName(String tagName);
    //添加视频标签
    Integer insertVideoTag(Map videoTag);
    //添加视频标签关系
    Integer insertRelvt(Map relvt);
    //删除视频标签
    Integer deleteVideoTag(String tagId);
    //修改视频标签
    Integer updateVideoTag(Map<String,String> videoTag);
   
    
}
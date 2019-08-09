package cn.org.cflac.home.mapper;

import java.util.List;
import java.util.Map;










import org.apache.ibatis.annotations.Mapper;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Video;
@Mapper
public interface VideoMapper {
    
    
    //根据活动ID查询视频列表
    List<Video> findVideoByActid(Map map);

    //添加视频
	public int insertVideo(Map videoMap);
	
	//向关联表插入数据
	public int insertActivityVideoRel(Map relMap);
	
	public int insertVideoTagRel(Map<String,String> relTagMap);
	
	public int updateVideoTransform(Map videoMap);
	
	public int deleteVideoById(Map delVideoMap);
	
	public int deleteTagById(Map<String,String> delTagMap);

	public String queryCountByMd5(String fileMd5);
	
	//查询所有视频
	List<Video> queryAllVideo(Map map);
	// 查询总数量
    Integer queryVideoCount();
    
    //根据活动ID查询视频列表2
    List<Video> findVideoByActid2(Map map);
    //查询活动视频ID总数量
    Integer findActivityCount2(Map map1);

	String findVideoAddrById(String videoId);
	//按videoId查一个video
	Video findVideoById(String videoId);
}
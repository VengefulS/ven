package cn.org.cflac.home.service;

import java.util.List;
import java.util.Map;

import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;

public interface VideoService {

	
	Paging<Video> findVideoByActid(String activityId);

	
	Map insertVideo(Map videoMap);
	 
	Map insertRel(Map relMap);
	
	int updateVideoTransform(Map videoMap);
	
	int deleteVideoById(Map delVideoMap);
	 
	String isrepByAddress(String fileMd5);
}

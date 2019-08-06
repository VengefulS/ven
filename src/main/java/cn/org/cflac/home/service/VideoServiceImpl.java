package cn.org.cflac.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;














import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;
import cn.org.cflac.home.mapper.VideoMapper;

@Service
public class VideoServiceImpl implements VideoService {

	
	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public Paging<Video> findVideoByActid(String activityId) {
		Map<String,Object> map = new HashMap<>();
		List<Video> list =null;
		
		map.put("activityId", activityId);
		
		try {
			list = videoMapper.findVideoByActid(map);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Paging paging = new Paging();;
        paging.setData(list);
        return paging;
	}

	@Override
	public Map insertVideo(Map videoMap) {
		
			videoMapper.insertVideo(videoMap);
			return videoMap;
	}

	@Override
	public Map insertRel(Map relMap) {
		
		videoMapper.insertActivityVideoRel(relMap);
		
		return relMap;
	}
	
	@Override
	public Map<String, String> insertVideoTagRel(Map<String, String> relTagMap) {
		videoMapper.insertVideoTagRel(relTagMap);
		return relTagMap;
	}


	@Override
	public int updateVideoTransform(Map videoMap) {
		videoMapper.updateVideoTransform(videoMap);
		return 0;
	}

	@Override
	public int deleteVideoById(Map delVideoMap) {
		
		videoMapper.deleteVideoById(delVideoMap);
		return 0;
	}
	
	@Override
	public int deleteTagById(Map<String, String> delTagMap) {
		videoMapper.deleteTagById(delTagMap);
		return 0;
	}

	@Override
	public String isrepByAddress(String fileMd5) {
		return videoMapper.queryCountByMd5(fileMd5);
	}

	
	@Override
	public Paging<Video> queryAllVideo(String search,
			  Integer start,
			  Integer length,
			  Integer draw) {

		// 将参数放进map中
        Map<String, Object> map = new HashMap<>();
        //Map<String, Object> map1 = new HashMap<>();
        List<Video> list = null;
        map.put("search", search);
        map.put("start", start);
        map.put("length",length);

         
        Integer count = null;
        try {
            list = videoMapper.queryAllVideo(map);
            count = videoMapper.queryVideoCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paging paging = new Paging(start, length, count, draw);
        paging.setRecordsFiltered(count);
        paging.setData(list);
        return paging;
	}
	
	//根据活动找视频
	@Override
	public Paging<Video> findVideoByActid2(String activityId, Integer start,Integer length, Integer draw) {

		// 将参数放进map中
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        List<Video> list = null;
        map.put("activityId", activityId);
        map.put("start", start);
        map.put("length",length);

        map1.put("activityId", activityId); 
        Integer count = null;
        try {
            list = videoMapper.findVideoByActid2(map);
            count = videoMapper.findActivityCount2(map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paging paging = new Paging(start, length, count, draw);
        paging.setRecordsFiltered(count);
        paging.setData(list);
        return paging;
	}

	
	


}

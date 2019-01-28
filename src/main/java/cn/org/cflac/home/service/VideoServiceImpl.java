package cn.org.cflac.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








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



	

	

}

package cn.org.cflac.home.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.cflac.home.mapper.VideoTagMapper;

@Service
public class VideoTagServiceImpl implements VideoTagService {
	
	@Autowired
	private VideoTagMapper videoTagMapper;
    
	@Override
	public List<String> findAllVideoTagName() {
		List<String> list = new ArrayList<String>();
		try {
			list = videoTagMapper.findAllVideoTagName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String findTagIdByName(String tagId) {
		
		return videoTagMapper.findTagIdByName(tagId);
		
	}
	
	@Override
	public Map insertVideoTag(Map videoTag) {
		videoTagMapper.insertVideoTag(videoTag);
		return videoTag;
	}
	@Override
	public Map insertRelvt(Map relvt) {
		videoTagMapper.insertRelvt(relvt);
		return relvt;
	}

	@Override
	public Integer deleteVideoTag(String tagId) {
		videoTagMapper.deleteVideoTag(tagId);
		return 0;
	}

	@Override
	public Integer updateVideoTag(Map<String,String> videoTag) {
		videoTagMapper.updateVideoTag(videoTag);
		return 0;
	}

	@Override
	public List<Map<String,String>> findAllVideoTag() {
		return videoTagMapper.findAllVideoTag();
		
	}

	
	
	
	
//	@Override
//	public List<String> findAvrList(String videoTag) {
//		return videoTagMapper.findAidListByVtn(videoTag);
//		
//	}
	

}

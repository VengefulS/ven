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
	public Integer deleteVideoTag(Map tagName) {
		videoTagMapper.deleteVideoTag(tagName);
		return 0;
	}
	
	
//	@Override
//	public List<String> findAvrList(String videoTag) {
//		return videoTagMapper.findAidListByVtn(videoTag);
//		
//	}
	

}

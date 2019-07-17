package cn.org.cflac.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.cflac.home.mapper.VideoTagMapper;

@Service
public class VideoTagServiceImpl implements VideoTagService {
	
	@Autowired
	private VideoTagMapper videoTagMapper;

	@Override
	public List<String> findAvrList(String videoTag) {
		return videoTagMapper.findAidListByVtn(videoTag);
		
	}

}

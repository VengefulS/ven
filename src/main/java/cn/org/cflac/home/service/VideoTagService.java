package cn.org.cflac.home.service;

import java.util.List;
import java.util.Map;

public interface VideoTagService {

//	List<String> findAvrList(String videoTag);
	List<String> findAllVideoTagName();
	//
	List<Map<String,String>> findAllVideoTag();
	String findTagIdByName(String tagName);
	Map insertVideoTag(Map videoTag);
	Map insertRelvt(Map relvt);
	Integer deleteVideoTag(String tagId);
	Integer updateVideoTag(Map<String,String> videoTag);
}

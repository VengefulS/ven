package cn.org.cflac.home.service;

import java.util.List;
import java.util.Map;

public interface VideoTagService {

//	List<String> findAvrList(String videoTag);
	List<String> findAllVideoTagName();
	Map insertVideoTag(Map videoTag);
	Map insertRelvt(Map relvt);
	Integer deleteVideoTag(Map tagName);
}

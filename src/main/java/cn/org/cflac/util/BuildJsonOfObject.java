package cn.org.cflac.util;

import com.alibaba.fastjson.JSONObject;

public class BuildJsonOfObject {

	
	public static String buildJsonOfString(String msg){
		JSONObject json=new JSONObject();
		json.put("text", "请求成功");
		json.put("msg",msg);
		return json.toJSONString(); 
	} 
	public static String buildJsonOfJsonObject(JSONObject json_o){
		JSONObject json=new JSONObject();
		JSONObject o =new JSONObject();
		json.put("data", json_o);
		return json.toString();
		}
}

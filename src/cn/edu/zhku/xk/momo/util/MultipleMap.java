package cn.edu.zhku.xk.momo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public class MultipleMap<T>{
	private Map<String,List<T>> map;
	private List<String> keyList;
	public void put(String key,T value){
		if(map.get(key)==null){
			List<T>valueList=new ArrayList<>();
			keyList.add(key);
			valueList.add(value);
			map.put(key, valueList);
		}else{
			List<T>valueList=(List<T>)map.get(key);
			valueList.add(value);
		}
	}
	public List<T> getValue(String key){
		if(map.get(key)!=null)return  map.get(key);
		else return new ArrayList<T>();
	}
	public List<String> getKey(){
		return keyList;
	}
	public MultipleMap() {
		super();
		// TODO Auto-generated constructor stub
		map=new HashMap<>();
		keyList=new ArrayList<>();
	}
	
	public MultipleMap(Map<String, List<T>> map, List<String> keyList) {
		super();
		this.map = map;
		this.keyList = keyList;
	}
	public String toJSONObject(){
		JSONObject  temp=new JSONObject();
		JSONObject jsMap=new JSONObject();
		for(String key:keyList){
			List<T> tempList=map.get(key);
			jsMap.put(key, JSONArray.fromObject(tempList).toString());
		}
		temp.put("keyList", JSONArray.fromObject(keyList).toString());
		temp.put("valueList", jsMap.toString());
		return temp.toString();
	}
	public static <T> MultipleMap<T> toMultipleMap(String jstr,T object){
		JSONObject temp=JSONObject.fromObject(jstr);
		@SuppressWarnings("unchecked")
		List<String> keyListTemp = (List<String>)JSONArray.toList(JSONArray.fromObject(temp.get("keyList")), String.class);
		Map<String, List<T>> mapTemp=new HashMap<>();
		JSONObject jsMap=temp.getJSONObject("valueList");
		for(String key:keyListTemp){
			@SuppressWarnings("unchecked")
			List<T> tempList=(List<T>)JSONArray.toList(JSONArray.fromObject(jsMap.get(key)),object.getClass());
			mapTemp.put(key, tempList);
		}
		return new MultipleMap<T>(mapTemp,keyListTemp);
	}
}

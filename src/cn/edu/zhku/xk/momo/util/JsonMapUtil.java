package cn.edu.zhku.xk.momo.util;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
  
import net.sf.json.JSONArray;  
import net.sf.json.JSONObject;  
  
public class JsonMapUtil {  
    @SuppressWarnings("unchecked")  
    public static Map<String, Object> Json2Map(String jsonStr){    
        Map<String, Object> map = new HashMap<String, Object>();    
        //��������    
        JSONObject json = JSONObject.fromObject(jsonStr);    
        for(Object k : json.keySet()){    
            Object v = json.get(k);     
            //����ڲ㻹������Ļ�����������    
            if(v instanceof JSONArray){    
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();    
                Iterator<JSONObject> it = ((JSONArray)v).iterator();    
                while(it.hasNext()){    
                    JSONObject json2 = it.next();    
                    list.add(Json2Map(json2.toString()));    
                }    
                map.put(k.toString(), list);    
            } else {    
                map.put(k.toString(), v);    
            }    
        }    
        return map;    
    }   
      
    public static JSONObject map2Json(Map<String,Object> map){  
        JSONObject json = new JSONObject();  
        Set<String> set = map.keySet();  
        for (Iterator<String> it = set.iterator();it.hasNext();) {  
            String key = it.next();  
            json.put(key, map.get(key));  
        }         
        return json;  
    }  
}  

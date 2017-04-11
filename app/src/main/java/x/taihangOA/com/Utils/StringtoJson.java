package x.taihangOA.com.Utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class StringtoJson {
	public String getJson(Object object) {
		HashMap<String ,Object> hashMaps=new HashMap<String, Object>();
		hashMaps.put("code",0);
		hashMaps.put("info",object);
		HashMap<String ,Object> hashMap=new HashMap<String, Object>();
		hashMap.put("ret", 200);
		hashMap.put("data", hashMaps);
		String myjson=JSON.toJSONString(hashMap);
		return myjson;
		
	}

}

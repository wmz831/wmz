package com.wmz.demo;

import java.util.*;

public class CompareUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private List<String> getNeedAddOpenidList(List<String> allOpenidList, List<String> dbOpenidList) {
	    if (dbOpenidList != null && !dbOpenidList.isEmpty()) {
	    	Map<String, String> dataMap = new HashMap<String, String>();
			for (String id : dbOpenidList) {
				dataMap.put(id, id);
			}
			
			List<String> newList = new ArrayList<String>();
			for (String id : allOpenidList) {
				if (!dataMap.containsKey(id)) {
					newList.add(id);
				}
			}
			return newList;
	    } else {
	    	return allOpenidList;
	    }
    }
	
	public static List<String> getRefRemove(List<String> before, List<String> now) {
        long startTime = System.currentTimeMillis();
        List<String> diffent = new LinkedList<String>();
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (String object : now) {
            map.put(object, 1);
        }
        for (String object : before) {
            if(map.get(object)==null)//之前的集合元素在扫描后的集合中找不到就是 此元素已经删除
            {
                diffent.add(object);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(" totle= "+(endTime-startTime)+" result="+diffent.size());
        return diffent;
    }

}

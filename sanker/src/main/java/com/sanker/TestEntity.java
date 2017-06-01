package com.sanker;

import java.util.HashMap;
import java.util.Map;

import com.sanker.service.utils.JSONHelper;

/**
 * 测试
 * @author 滕洁
 * @date 2016-11-4
 */
public class TestEntity {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("peopleNum", "3");
		map.put("wayStatus", "xueliu");
		System.out.println(JSONHelper.toJson(map)+"xxx");
	}

}

package com.zhiqi.test;



import org.junit.*;

import com.zhiqi.utils.DateUtil;


public class SimpleTest {
	
	@Test
	public void testTime(){
		System.out.println(DateUtil.xDaysAgo("2017-05-09", 3));
	}
}

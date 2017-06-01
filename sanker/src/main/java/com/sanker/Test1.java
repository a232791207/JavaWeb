package com.sanker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.sanker.service.logic.FenJie;
import com.sanker.service.logic.HuPaiLogic;

public class Test1 {

	//分解牌测试
	@Test
	public void fenjieTest(){
		List<String> paiList = new ArrayList<>();
		for(int i=1;i<10;i++){
			paiList.add(i+"D");
		}
		paiList.add("2T");
		paiList.add("2T");
		paiList.add("3T");
		paiList.add("3T");
		paiList.add("3T");
		System.out.println(paiList);
		
		int[] count = FenJie.fenJie(paiList);
		
		for(int n:count){
			System.out.print(n+"  ");                //输出分解后的序列
		}
		fanfenjieTest(count);
	}
	//根据数组返回麻将
	public void fanfenjieTest(int [] paiArray){
		List<String> paiList =  FenJie.fanFenJie(paiArray);
		System.out.println();
		for (String string : paiList) {
			
			System.out.print(string);
		}
	}
	/**
	 * 乱清胡牌
	 */
	@Test
	public void luanTest(){
		List<String> paiList = new ArrayList<>();
		for(int i=1;i<10;i++){
			paiList.add(i+"D");
		}
		paiList.add("2D");
		paiList.add("2D");
		paiList.add("3D");
		paiList.add("3D");
		System.out.println(paiList);
		boolean y = HuPaiLogic.luanQing(paiList, "T", new ArrayList<String>());
		System.out.println(y);
	}
	
	
	/**
	 * 赖子胡牌
	 * @throws InterruptedException 
	 */
	@Test
	public void replace() throws InterruptedException{
		List<String> paiList = new ArrayList<>();
		for(int i=1;i<10;i++){
			paiList.add(i+"D");
		}
//		paiList.add("2T");
//		paiList.add("2T");
//		paiList.add("7T");
		//paiList.add("3T");
	
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss SSS") .format(new Date()));
		System.out.println(new Date());
		System.out.println(paiList);
		List<String> huList = canHu(paiList, "W");
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss SSS") .format(new Date()));
		System.out.println(huList);
	}
	public static List<String> canHu(List<String> paiList, String que) {
		//System.out.println("牌的张数："+paiList.size());
		int[] count = new int[30];// TDW排列
		System.out.println("传入的牌："+paiList);
		count = FenJie.fenJie(paiList);
		System.out.println(count);
		int[] tmp = new int[30];// TDW排列

		List<String> huPai = new ArrayList<String>();

		int num1 = 31;
		int num2 = -1;
		if (que.equals("T")) {
			num1 = 0;
			num2 = 8;
		} else if (que.equals("D")) {
			num1 = 9;
			num2 = 17;
		} else if (que.equals("W")) {
			num1 = 18;
			num2 = 26;
		}
		for (int i = 0; i < 27; i++) {
			//每一种牌给他加一张，看他可以胡牌不
			if (i < num1 || i > num2) {
				if (count[i] < 4)
					count[i]++;//循环27次让每种张数小于4的牌都加一张看是否能胡
				else
					continue;
				for (int j = 0; j < 30; j++) {

					tmp[j] = count[j];

				}
				System.out.println("每次添加的牌："+FenJie.fanFenJie(tmp));
				List<String> paiL = canHu2(FenJie.fanFenJie(tmp), que);
				//判断是否有值
				if(paiL!=null && paiL.size()>0){
					huPai.addAll(paiL);
				}
				count[i]--;//循环一次就将牌还原，再在下一张牌的基础上加一张
			}
		}
		return huPai;
	}
	public static List<String> canHu2(List<String> paiList, String que) {
		//System.out.println("牌的张数："+paiList.size());
		int[] count = new int[30];// TDW排列

		System.out.println(paiList);
		count = FenJie.fenJie(paiList);

		int[] tmp = new int[30];// TDW排列

		List<String> huPai = new ArrayList<String>();

		int num1 = 31;
		int num2 = -1;
		if (que.equals("T")) {
			num1 = 0;
			num2 = 8;
		} else if (que.equals("D")) {
			num1 = 9;
			num2 = 17;
		} else if (que.equals("W")) {
			num1 = 18;
			num2 = 26;
		}
		for (int i = 0; i < 30; i++) {
			//每一种牌给他加一张，看他可以胡牌不
			if (i < num1 || i > num2) {
				if (count[i] < 4)
					count[i]++;//循环27次让每种张数小于4的牌都加一张看是否能胡
				else
					continue;
				for (int j = 0; j < 30; j++) {

					tmp[j] = count[j];

				}
				boolean flag = HuPaiLogic.tryHU(tmp, paiList.size() + 1, num1, num2);

				if (flag) {
					huPai.add(HuPaiLogic.translate(i));
				}

				count[i]--;//循环一次就将牌还原，再在下一张牌的基础上加一张
			}
		}
		return huPai;
	}
	
}

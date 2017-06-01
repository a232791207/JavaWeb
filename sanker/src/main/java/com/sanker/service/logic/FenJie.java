package com.sanker.service.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 将牌分解
 * @author 滕洁
 * @date 2016-11-8
 */
public class FenJie {
	
	public static int[] fenJie(List<String> paiList){
		int[] count = new int[30];// 返回为TDWZFB排列


		for (int i = 0; i < 30; i++) {

			count[i] = 0;

		}
		String line = "";
		for (String str : paiList) {
			line+=str;
		}
		int len = line.length();
		for (int i = 0; i < len; i += 2) {
			if (line.charAt(i + 1) == 'T') {

				int num = Integer.parseInt(line.charAt(i) + "") - 1;

				count[num]++; 

			} else if (line.charAt(i + 1) == 'D') {

				int num = Integer.parseInt(line.charAt(i) + "") - 1;

				count[num + 9]++;

			} else if (line.charAt(i + 1) == 'W') {

				int num = Integer.parseInt(line.charAt(i) + "") - 1;

				count[num + 9 * 2]++;

			}else if(line.charAt(i+1)=='Z'){
				count[27]++;
			}else if(line.charAt(i+1)=='F'){
				count[28]++;
			}else if(line.charAt(i+1)=='B'){
				count[29]++;
			}

		}
		/*************************************************************************/
		//System.out.println(aNum);
//		System.out.print("分解之后：");
//		System.out.println();
		/*************************************************************************/
		return count;
	}
	
	//反分解
	public static List<String> fanFenJie(int[] paiArray){
		List<String> paiList = new ArrayList<String>();
		/*for (int pai : paiArray) {
			paiList.add(translate(pai));
		}*/
		for (int i = 0; i < paiArray.length; i++) {
			if(paiArray[i]>0){
				for (int j = 0; j < paiArray[i]; j++) {
					paiList.add(translate(i));
				}
			}
		}
		/***************反分解********************/
//		for (String string : paiList) {
//			System.out.print(string);
//		}
//		System.out.println();
		/***********************************/
		return paiList;
		
	}
	//根据取余判断D（筒）T（条）W（万）
	private static String translate(int i) {

		// TODO Auto-generated method stub

		int n = i / 9;

		int pre = i % 9 + 1;

		String r = null;

		switch (n) {

		case 0:

			r = pre + "T";

			break;

		case 1:

			r = pre + "D";

			break;

		case 2:

			r = pre + "W";

			break;

		default:

			break;

		}

		return r;

	}
	

}

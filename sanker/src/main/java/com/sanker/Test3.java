package com.sanker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sanker.service.logic.FenJie;
import com.sanker.service.logic.HuPaiLogic;

public class Test3 {

	@Test
	public void hupai() {
		List<String> paiList = getpai();
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss SSSS").format(new Date()));
		
//		roundNumFour(paiList);  //大约300-400ms
//		roundNumThree(paiList); //大约50ms
//		roundNumTwo(paiList);	//大约10ms
//		roundNumOne(paiList); 	//大约5ms
//		roundNoNum(paiList);
//		qiDuiCanHu(paiList,1);
		isDuiDuiHu(FenJie.fenJie(paiList),2);
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss SSSS").format(new Date()));

		// System.out.println(is);
	}

	/**
	 * 获得一副牌
	 * 
	 * @return
	 */
	public List<String> getpai() {
		List<String> paiList = new ArrayList<>();
		paiList.add("2T");// 12T, 3T, 4D, 5D, 6D, 2W, 2W, 3W, 4W, 5W, 7W, 8W, 9W
		paiList.add("2T");// 1
		paiList.add("6W");// 2
		paiList.add("2W");// 3

//		paiList.add("5D");// 4

//		paiList.add("6D");// 5
//		paiList.add("4T");// 6
//		paiList.add("3W");// 7
//		paiList.add("5T");// 7
//		paiList.add("6T");// 7
//		paiList.add("3W");// 8
//		paiList.add("3W");// 9
		paiList.add("5W");// 10
		paiList.add("5W");// 11
		paiList.add("5W");// 12
		paiList.add("8W");// 12
		paiList.add("8W");// 12
		System.out.println(paiList);

		return paiList;
	}

	/**
	 * 4个癞子牌
	 * 
	 * @param paiList
	 */
	public void roundNumFour(List<String> paiList) {
		int[] count = FenJie.fenJie(paiList);
		int[] tmp = new int[30];
		Set<String> hupaiSet = new HashSet<String>();
		for (int l = 0; l < 27; l++) {

			tmp[l] = count[l];

		}
		for (int m = 0; m < 27; m++) {
			tmp[m] += 1;
			for (int i = 0; i < 27; i++) {
				tmp[i] += 1;
				for (int j = 0; j < 27; j++) {
					tmp[j] += 1;
					for (int k = 0; k < 27; k++) {
						tmp[k] += 1;
						if (tryHU(tmp, paiList.size() + 4)) {
							hupaiSet.add(HuPaiLogic.translate(k));
						}
						;
						tmp[k] -= 1;
					}
					tmp[j] -= 1;
				}
				tmp[i] -= 1;
			}
			tmp[m] -= 1;
		}
		System.out.println(hupaiSet);
	}

	/**
	 * 3个癞子牌
	 * 
	 * @param paiList
	 */
	public void roundNumThree(List<String> paiList) {
		int[] count = FenJie.fenJie(paiList);
		int[] tmp = new int[30];
		Set<String> hupaiSet = new HashSet<String>();
		for (int i = 0; i < 27; i++) {
			count[i]++;
			for (int l = 0; l < 27; l++) {
	
				tmp[l] = count[l];
	
			}
			for (int m = 0; m < 27; m++) {
				tmp[m] += 1;
				for (int j = 0; j < 27; j++) {
					tmp[j] += 1;
					for (int k = 0; k < 27; k++) {
						tmp[k] += 1;
						if (tryHU(tmp, paiList.size() + 4)) {
							hupaiSet.add(HuPaiLogic.translate(k));
						}
						;
						tmp[k] -= 1;
					}
					tmp[j] -= 1;
				}
				tmp[m] -= 1;
			}
			count[i]--;
		}
		System.out.println(hupaiSet);
	}

	/**
	 * 2个癞子牌
	 * 
	 * @param paiList
	 */
	public void roundNumTwo(List<String> paiList) {
		int[] count = FenJie.fenJie(paiList);
		int[] tmp = new int[30];
		Set<String> hupaiSet = new HashSet<String>();
		for (int i = 0; i < 27; i++) {
			count[i]++;
			for (int l = 0; l < 27; l++) {
				tmp[l] = count[l];
			}
			for (int m = 0; m < 27; m++) {
				tmp[m] += 1;
				for (int k = 0; k < 27; k++) {
					tmp[k] += 1;
					if (tryHU(tmp, paiList.size() + 3)) {
						hupaiSet.add(HuPaiLogic.translate(k));
					}
					;
					tmp[k] -= 1;
				}
				tmp[m] -= 1;
			}
			count[i]--;
		}
		System.out.println(hupaiSet);
	}
	
	/**
	 * 1个癞子牌
	 * 
	 * @param paiList
	 */
	public void roundNumOne(List<String> paiList) {
		int[] count = FenJie.fenJie(paiList);
		int[] tmp = new int[30];
		Set<String> hupaiSet = new HashSet<String>();
		for (int i = 0; i < 27; i++) {
			count[i]++;
			for (int l = 0; l < 27; l++) {
	
				tmp[l] = count[l];
	
			}
			for (int m = 0; m < 27; m++) {
				tmp[m] += 1;
					if (tryHU(tmp, paiList.size() + 2)) {
						hupaiSet.add(HuPaiLogic.translate(m));
					};
				tmp[m] -= 1;
			}
			count[i]--;
		}
		System.out.println(hupaiSet);
	}
	/**
	 * 没有癞子牌
	 * 
	 * @param paiList
	 */
//	public Set<String> roundNoNum(List<String> paiList,String pai) {
		/**
		 * 没有癞子牌
		 * 
		 * @param paiList
		 */
		public static Set<String> roundNoNum(List<String> paiList) {
			int[] count = new int[30];// TDW排列

			count = FenJie.fenJie(paiList);

			int[] tmp = new int[30];// TDW排列

			Set<String> huPai = new HashSet<String>();

			int num1 = 31;
			int num2 = -1;
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
					boolean flag = tryHU(tmp, paiList.size() + 1);

					if (flag) {
						huPai.add(HuPaiLogic.translate(i));
					}

					count[i]--;//循环一次就将牌还原，再在下一张牌的基础上加一张
				}
			}
			return huPai;
	}
		/**
		 * 
		 * @param paiList 手牌
		 * @param round 癞子数
		 * @return
		 */
		public static Set<String>  qiDuiCanHu(List<String> paiList,int round) {
			// TODO Auto-generated method stub
			if(paiList.size()+round != 13){
				return null;
			}
			Set<String> huPai = new HashSet<>();
			for (int i = 0; i < 27; i++) {
					String pai = HuPaiLogic.translate(i);
					List<String> newList = new ArrayList<String>(paiList);
					newList.add(pai);
					if (qiDuiisHu(FenJie.fenJie(newList),round))
						huPai.add(pai);
			}
			System.out.println(huPai);
			return huPai;
		}
		/**
		 * 幺鸡带
		 * 是否是对对胡 手中的牌只能为n*A + 2n n>=3
		 */
		@SuppressWarnings("unused")
		private static boolean isDuiDuiHu(int[] count,int round) {
			int dan  =0;
			int janNum = 0;
			for(int i=0;i< count.length;i++){
				if(count[i]!=3){
					if(count[i]==2){
						if (janNum == 0)
							janNum++;
						else
							dan ++;
						if(count[i]==1){
							dan +=2;
						}
					}
				}
			}
			if(dan != round){
				return false;
			}

			System.out.println("可以胡！！");
			return true;
		}
		/**
		 * 七对
		 * @param count 牌
		 * @param round 癞子个数
		 * @return
		 */
		public static boolean qiDuiisHu(int[] count,int round) {

			boolean canHu = true;
			List<String> list = new ArrayList<>();
			for (int i = 0; i < 30; i++) {
				if (count[i] % 2 != 0) {
					list.add(HuPaiLogic.translate(count[i]));
				}
			}
			if(round == list.size()){
				canHu = true;
			}else{
				canHu = false;
			}

			return canHu;
		}
	/**
	 * 判断33332 一直递减 最后长度为0
	 * 
	 * @param count
	 *            一副牌（数组类型）
	 * @param len
	 *            胡牌长度
	 * @param rep
	 *            癞子牌个数
	 * @return
	 */
	public static boolean tryHU(int[] count, int len) {
		// TODO Auto-generated method stub
		// System.out.println(count.toString());
		if (len == 0)
			return true;
		if ((len) % 3 == 2) {

			for (int i = 0; i < 27; i++) {
				if (count[i] >= 2) {

					count[i] -= 2;

					if (tryHU(count, len - 2)) {// 将能凑成一对的牌从队列Z移除，判断其他的牌是否能组成三个或者顺子
						count[i] += 2;
						return true;
					}
					count[i] += 2;
				}
			}
		} else {
			// 三个一样的 将三个一样的剔除队列。

			for (int i = 0; i < 27; i++) {
				if (count[i] >= 3) {

					count[i] -= 3;

					if (tryHU(count, len - 3)) {
						count[i] += 3;
						return true;
					}
					count[i] += 3;
				}

			}

			// 是否是顺子 将顺子剔除队列
			for (int t = 0; t < 3; t++) {
				int num3 = 0;
				int num4 = 0;
				if (t == 0) {
					num3 = 0;
					num4 = 7;
				} else if (t == 1) {

					num3 = 9;
					num4 = 16;
				} else if (t == 2) {
					num3 = 18;
					num4 = 25;

				}

				for (int i = num3; i < num4; i++) {
					if (count[i] > 0 && count[i + 1] > 0 && count[i + 2] > 0) {

						count[i] -= 1;

						count[i + 1] -= 1;

						count[i + 2] -= 1;

						if (tryHU(count, len - 3)) {
							count[i] += 1;

							count[i + 1] += 1;

							count[i + 2] += 1;
							return true;
						}
						count[i] += 1;

						count[i + 1] += 1;

						count[i + 2] += 1;

					}

				}

			}
		}
		return false;

	}
}

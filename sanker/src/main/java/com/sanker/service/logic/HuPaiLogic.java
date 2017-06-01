package com.sanker.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sanker.param.Param;

/**
 * 和牌逻辑
 * @author 滕洁
 * @date 2016-11-2
 */
public class HuPaiLogic {

	/**
	 * 普通和牌
	 * @return
	 */
	public static List<String> canHu(List<String> paiList, String que) {
		//System.out.println("牌的张数："+paiList.size());
		int[] count = new int[30];// TDW排列

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
				boolean flag = tryHU(tmp, paiList.size() + 1, num1, num2);

				if (flag) {
					huPai.add(translate(i));
				}

				count[i]--;//循环一次就将牌还原，再在下一张牌的基础上加一张
			}
		}
		return huPai;
	}

	/**
	 * 七对和牌
	 * @return
	 */
	public static List<String> qiDuiCanHu(List<String> paiList, String GameType, String paiNum, String que) {

		List<String> huPai = new ArrayList<String>();
		if (que != null && !que.equals("") && paiList.toString().contains(que)) {
			return huPai;
		}
		if (paiNum == Param.PAINUM_THIRTEEN) {
			if (paiList.size() != 13) {
				return huPai;
			}
		} else if (paiNum == Param.PAINUM_TEN) {
			if (paiList.size() != 10) {
				return huPai;
			}
		} else if (paiNum == Param.PAINUM_SEVEN) {
			if (paiList.size() != 7) {
				return huPai;
			}
		}

		int num = GameType.equals(Param.TY_ZFB) ? 30 : 27;
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
		for (int i = 0; i < num; i++) {
			if (i < num1 || i > num2) {
				String pai = translate(i);
				List<String> newList = new ArrayList<String>(paiList);
				newList.add(pai);
				if (qiDuiisHu(FenJie.fenJie(newList)))
					huPai.add(pai);
			}
		}
		return huPai;
	}

	/**
	 * 乱清 （清一色，并且没叫）
	 * @return
	 */
	public static boolean luanQing(List<String> paiList, String que, List<String> pgList) {

		List<String> huPaiList = canHu(paiList, que);
		String huaSe = String.valueOf(paiList.get(0).charAt(1));
		String huaSe1 = "";
		String huaSe2 = "";
		if (huaSe.equals("T")) {

			huaSe1 = "D";
			huaSe2 = "W";
		} else if (huaSe.equals("D")) {

			huaSe1 = "T";
			huaSe2 = "W";
		} else if (huaSe.equals("W")) {

			huaSe1 = "T";
			huaSe2 = "D";
		}

		//这里的胡牌数量等于0 不懂
		if (huPaiList.size() == 0 && !paiList.toString().contains(huaSe1) && !paiList.toString().contains(huaSe2) 
				&& !pgList.toString().contains(huaSe1) && !pgList.toString().contains(huaSe2)) {
			return true;
		}
		return false;
	}

	/**
	 * 听用 ——得到所有能胡的牌组
	 * @param que 缺的牌
	 * @param tingType 0：4听  1：8听
	 * @return 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> tingYongList(List<String> paiList, String que, String ben, String tingType) {
		List<String> groupList = new ArrayList<String>();
		if (que == null || paiList.toString().contains(que)) {
			return groupList;
		}
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
		int[] count = FenJie.fenJie(paiList);
		int benIndex = getIndex(ben);
		Map<String, Object> map = getTingYInfo(benIndex, tingType, 0, count);
		int[] temp = (int[]) map.get("count");
		int tYNum = (Integer) map.get("num");
		System.out.println(tYNum + "听用牌数量");
		if (tYNum > 0) {
			/**************************************************************************耗时很长********/
			tingYongGroup(temp, paiList.size(), tYNum, num1, num2, groupList);
			if (paiList.size() == 13) {
				tyGuoQiDui(temp, tYNum, num1, num2, groupList);
			}
		} else {
			groupList.addAll(canHu(paiList, que));
			groupList.addAll(qiDuiCanHu(paiList, Param.TY_TY, Param.PAINUM_THIRTEEN, que));

		}
		HashSet h = new HashSet(groupList);
		groupList.clear();
		groupList.addAll(h);
		return groupList;
	}

	/**
	 * 鬼牌得到一组能胡的牌
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> guiHuList(List<String> paiList, String que) {
		List<String> groupList = new ArrayList<String>();
		if (que == null || paiList.toString().contains(que)) {
			return groupList;
		}
		int num1 = -1;
		int num2 = 31;
		if (que.equals("T")) {
			num1 = 0;
			num2 = 8;
		} else if (que.equals("D")) {
			num1 = 9;
			num2 = 17;
		} else if (que.endsWith("W")) {
			num1 = 18;
			num2 = 26;
		}
		//获取手牌中鬼牌的数量
		Map<String, Object> map = getGui(paiList);
		List<String> newList = (List<String>) map.get("list");
		int num = (int) map.get("num");
		if (num > 0) {
			tingYongGroup(FenJie.fenJie(newList), paiList.size(), num, num1, num2, groupList);
			if (paiList.size() == 13) {
				tyGuoQiDui(FenJie.fenJie(newList), num, num1, num2, groupList);
			}
		} else {
			groupList.addAll(canHu(paiList, que));
			groupList.addAll(qiDuiCanHu(paiList, Param.TY_LZ, Param.PAINUM_THIRTEEN, que));
		}
		HashSet h = new HashSet(groupList);
		groupList.clear();
		groupList.addAll(h);
		return groupList;
	}
	/**
	 * 判断33332 一直递减 最后长度为0
	 * 
	 * @param count
	 *            一副牌（数组类型）
	 * @param len
	 *            胡牌长度
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

	//判断是否可以胡牌
	/**
	 * 判断33332 一直递减 最后长度为0
	 * @param count 一副牌（数组类型）
	 * @param len 胡牌长度
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean tryHU(int[] count, int len, int num1, int num2) {
		// TODO Auto-generated method stub
		if (len == 0)
			return true;
		if (len % 3 == 2) {
			for (int i = 0; i < 27; i++) {
				if (i < num1 || i > num2) {
					if (count[i] >= 2) {

						count[i] -= 2;

						if (tryHU(count, len - 2, num1, num2))//将能凑成一对的牌从队列Z移除，判断其他的牌是否能组成三个或者顺子

							return true;

						count[i] += 2;

					}
				}
			}

		}

		else {
			// 三个一样的 将三个一样的剔除队列。

			for (int i = 0; i < 30; i++) {

				if (i < num1 || i > num2) {
					if (count[i] >= 3) {

						count[i] -= 3;

						if (tryHU(count, len - 3, num1, num2))

							return true;

						count[i] += 3;

					}
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

						if (tryHU(count, len - 3, num1, num2))

							return true;

						count[i] += 1;

						count[i + 1] += 1;

						count[i + 2] += 1;

					}

				}

			}

		}

		return false;

	}

	/**
	 * 七对
	 * @param count
	 * @return
	 */
	public static boolean qiDuiisHu(int[] count) {

		boolean canHu = true;

		for (int i = 0; i < 30; i++) {

			if (count[i] % 2 != 0) {

				canHu = false;

			}

		}

		return canHu;
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
				list.add(translate(count[i]));
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
	 * 听用鬼牌-能胡牌组合
	 */
	private static void tingYongGroup(int[] count, int paiLength, int n, int num1, int num2, List<String> paiList) {

		for (int i = 0; i < 27; i++) {
			if (i < num1 || i > num2) {
				if (count[i] < 4) {
					count[i]++;
					//n--;
					int[] temp = new int[30];
					for (int j = 0; j < temp.length; j++) {
						temp[j] = count[j];
					}
					if (n > 1)
						tingYongGroup(count, paiLength, n - 1, num1, num2, paiList);
					else {
						for (int add = 0; add < 27; add++) {
							if (add < num1 || add > num2) {
								if (temp[add] < 4) {
									temp[add]++;
									int[] cList = new int[30];
									for (int j = 0; j < 30; j++) {
										cList[j] = temp[j];
									}
									if (tryHU(cList, paiLength + 1, num1, num2)) {
										paiList.add(translate(add));
									}
									temp[add]--;
								}
							}
						}
					}
					count[i]--;
				}
			}
		}
		/*}*/

	}

	/**
	 * 听用
	 * @param benIndex 
	 * @param tingType
	 * @param tingYNum
	 * @param count
	 */
	private static Map<String, Object> getTingYInfo(int benIndex, String tingType, int tingYNum, int[] count) {
		if (benIndex == 0) {//1T 
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[8];
				count[8] = 0;
			} else {
				tingYNum += count[8];
				tingYNum += count[2];
				count[8] = 0;
				count[2] = 0;
			}
		} else if (benIndex == 8) {//9T
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[7];
				count[7] = 0;
			} else {
				tingYNum += count[7];
				tingYNum += count[0];
				count[7] = 0;
				count[0] = 0;
			}
		} else if (benIndex == 9) {//1D
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[17];
				count[17] = 0;
			} else {
				tingYNum += count[17];
				tingYNum += count[10];
				count[17] = 0;
				count[10] = 0;
			}

		} else if (benIndex == 17) {//9D
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[9];
				count[9] = 0;
			} else {
				tingYNum += count[9];
				tingYNum += count[16];
				count[16] = 0;
				count[9] = 0;
			}
		} else if (benIndex == 18) {//1W
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[26];
				count[26] = 0;
			} else {
				tingYNum += count[26];
				tingYNum += count[19];
				count[19] = 0;
				count[26] = 0;
			}

		} else if (benIndex == 26) {//9W
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[25];
				count[25] = 0;
			} else {
				tingYNum += count[18];
				tingYNum += count[25];
				count[18] = 0;
				count[25] = 0;
			}
		} else {
			if (tingType.equals(Param.YB_ONE)) {
				tingYNum += count[benIndex - 1];
				count[benIndex - 1] = 0;
			} else {

				tingYNum += count[benIndex - 1];
				tingYNum += count[benIndex + 1];
				count[benIndex - 1] = 0;
				count[benIndex + 1] = 0;
			}
		}

		/*System.out.println("类型："+tingType);
		System.out.println("听用牌个数："+tingYNum);
		System.out.println("本金："+benIndex);*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", tingYNum);
		map.put("count", count);
		System.out.println("getTingYInfo" + map + count.length);
		return map;

	}
	/**
	 * 根据数组取余返回DTW
	 * @param i
	 * @return
	 */
	public static String translate(int i) {

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
		case 3:
			if (pre == 1) {
				r = "0Z";
			} else if (pre == 2) {
				r = "0F";
			} else if (pre == 3) {
				r = "0B";
			}

		default:

			break;

		}

		return r;

	}

	/**
	 * 根据牌获取其在序列Z的位置
	 * @param pai
	 * @return
	 */
	private static int getIndex(String pai) {
		Integer index = Integer.valueOf(pai.charAt(0) + "");
		if (pai.charAt(1) == 'T') {
			return index - 1;
		} else if (pai.charAt(1) == 'D') {
			return index + 9 - 1;
		} else if (pai.charAt(1) == 'W') {
			return index + 9 * 2 - 1;
		} else if (pai.equals("Z")) {
			return 27;
		} else if (pai.equals("F")) {
			return 28;
		} else {
			return 29;
		}
	}

	/**
	 * 根据本金和听用类型获取听用牌
	 */
	public static List<String> getTYPai(String tyType, String benJin) {
		List<String> tyPai = new ArrayList<String>();
		char benJinIndex = benJin.charAt(0);
		char benJinType = benJin.charAt(1);
		if (benJinIndex == '1') {
			if (tyType.equals(Param.YB_ONE)) {
				tyPai.add("9" + benJinType);

			} else {
				tyPai.add("9" + benJinType);
				tyPai.add("2" + benJinType);
			}
		} else if (benJinIndex == '9') {
			if (tyType.equals(Param.YB_ONE)) {
				tyPai.add("8" + benJinType);

			} else {
				tyPai.add("8" + benJinType);
				tyPai.add("1" + benJinType);
			}
		} else {
			Integer benJinIndexNum = Integer.valueOf(benJinIndex + "");
			if (tyType.equals(Param.YB_ONE)) {
				tyPai.add(String.valueOf(benJinIndexNum - 1) + benJinType);
			} else {
				tyPai.add(String.valueOf(benJinIndexNum - 1) + benJinType);
				tyPai.add(String.valueOf(benJinIndexNum + 1) + benJinType);
			}

		}

		return tyPai;
	}

	/**
	 * 获取手牌中鬼牌的数量
	 */
	private static Map<String, Object> getGui(List<String> paiList) {
		List<String> newList = new ArrayList<String>(paiList);
		int guiNum = 0;
		for (String pai : paiList) {
			if (pai.equals("0Z")) {
				guiNum++;
				newList.remove("0Z");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", newList);
		map.put("num", guiNum);
		return map;
	}
	/**
	 * 4个癞子牌
	 * 
	 * @param paiList
	 */
	public static Set<String> roundNumFour(List<String> paiList) {
		
		Set<String> hupaiSet = new HashSet<String>();
		hupaiSet.add("All");
		return hupaiSet;
	}

	/**
	 * 3个癞子牌
	 * 
	 * @param paiList
	 */
	public static Set<String> roundNumThree(List<String> paiList) {
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
		return hupaiSet;
	}

	/**
	 * 2个癞子牌
	 * 
	 * @param paiList
	 */
	public static Set<String> roundNumTwo(List<String> paiList) {
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
		return hupaiSet;
	}
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
					huPai.add(translate(i));
				}

				count[i]--;//循环一次就将牌还原，再在下一张牌的基础上加一张
			}
		}
		return huPai;
	}
	/**
	 * 1个癞子牌
	 * 
	 * @param paiList
	 */
	public static Set<String> roundNumOne(List<String> paiList) {
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
		return hupaiSet;
	}

	/**
	 * 听用鬼牌七对和牌
	 */
	private static void tyGuoQiDui(int[] count, int tGuiNum, int num1, int num2, List<String> huPaiList) {
		//int[] count = FenJie.fenJie(paiList);
		for (int i = 0; i < 27; i++) {
			if (i > num1 || i < num2) {
				if (count[i] < 4) {
					count[i]++;
					if (tGuiNum > 1) {
						tyGuoQiDui(count, tGuiNum - 1, num1, num2, huPaiList);
					} else {
						int[] temp = new int[30];
						for (int j = 0; j < 30; j++) {
							temp[j] = count[j];
						}
						for (int n = 0; n < 27; n++) {
							if (n < num1 || n > num2) {
								if (temp[n] < 4) {
									temp[n]++;
									if (qiDuiisHu(temp)) {
										huPaiList.add(translate(n));
									}
									temp[n]--;
								}
							}
						}
					}
					count[i]--;
				}
			}
		}
	}

	/**
	 * 
	 * @param paiList 手牌
	 * @param round 癞子数
	 * @return
	 */
	public static Set<String>  qiDuiCanHu(List<String> paiList,int round) {
		// TODO Auto-generated method stub
		
		Set<String> huPai = new HashSet<>();
		if(paiList.size()+round != 13){
			return huPai;
		}
		for (int i = 0; i < 27; i++) {
				String pai = HuPaiLogic.translate(i);
				List<String> newList = new ArrayList<String>(paiList);
				newList.add(pai);
				if (qiDuiisHu(FenJie.fenJie(newList),round))
					huPai.add(pai);
		}
//		System.out.println(huPai);
		return huPai;
	}

}
package com.wust.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class OrderCart implements Serializable {
	//key:���͵ȼ�  value:OrderItem��Ʊ��
		private Map<String, OrderItem> items = new HashMap<String, OrderItem>();
		private int totalQuantity;//������
		private double totalMoney;//�ܽ��
		private double totalVolume;//�����
		public double getTotalVolume() {
			totalVolume = 0;
			for(Map.Entry<String, OrderItem> me:items.entrySet()){
				totalVolume +=me.getValue().getVolume();
			}
			return totalVolume;
		}
		public void setTotalVolume(double totalVolume) {
			this.totalVolume = totalVolume;
		}
		public int getTotalQuantity() {
			totalQuantity = 0;
			for(Map.Entry<String, OrderItem> me:items.entrySet()){
				totalQuantity +=me.getValue().getQuantity();
			}
			return totalQuantity;
		}
		public void setTotalQuantity(int totalQuantity) {
			this.totalQuantity = totalQuantity;
		}
		public double getTotalMoney() {
			totalMoney = 0;
			for(Map.Entry<String, OrderItem> me:items.entrySet()){
				totalMoney +=me.getValue().getMoney();
			}
			return Double.parseDouble(String.format("%.3f", totalMoney));
		}
		public void setTotalMoney(double totalMoney) {
			this.totalMoney = totalMoney;
		}
		public Map<String, OrderItem> getItems() {
			return items;
		}
		//���µ������м���һ��������
		public void addOrderItem(OrderItem orderItem){
			if(items.containsKey(orderItem.getFormat()+"-"+orderItem.getLevel())){
				//�ж�Ӧ�Ĺ�����
				OrderItem item = items.get(orderItem.getFormat()+"-"+orderItem.getLevel());
				item.setQuantity(item.getQuantity()+orderItem.getQuantity());
				item.setVolume(item.getVolume()+orderItem.getVolume());
				item.setPack(item.getPack()+orderItem.getPack());
				item.setMoney(item.getMoney()+orderItem.getMoney());
			}else{
				items.put(orderItem.getFormat()+"-"+orderItem.getLevel(), orderItem);
			}
		}
}

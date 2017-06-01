package com.wust.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class OrderCart implements Serializable {
	//key:规格和等级  value:OrderItem开票项
		private Map<String, OrderItem> items = new HashMap<String, OrderItem>();
		private int totalQuantity;//总数量
		private double totalMoney;//总金额
		private double totalVolume;//总体积
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
		//向下单队列中加入一个订单项
		public void addOrderItem(OrderItem orderItem){
			if(items.containsKey(orderItem.getFormat()+"-"+orderItem.getLevel())){
				//有对应的购物项
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

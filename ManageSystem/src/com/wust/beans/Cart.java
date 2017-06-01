package com.wust.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.wust.domain.Product;

public class Cart implements Serializable {
		//key:��Ӧ�Ĳ�Ʒ��ID�͵���   value:CartItem��Ʊ��
		private Map<String, CartItem> items = new HashMap<String, CartItem>();
		private int totalQuantity;//������
		private double totalMoney;//�ܽ��
		private double totalDenomination;//�ܽ��
		private double totalBalance;//�ܽ��
		public int getTotalQuantity() {
			totalQuantity = 0;
			for(Map.Entry<String, CartItem> me:items.entrySet()){
				totalQuantity +=me.getValue().getQuantity();
			}
			return totalQuantity;
		}
		public void setTotalQuantity(int totalQuantity) {
			this.totalQuantity = totalQuantity;
		}
		public double getTotalMoney() {
			totalMoney = 0;
			for(Map.Entry<String, CartItem> me:items.entrySet()){
				totalMoney +=me.getValue().getMoney();
			}
            
			return Double.parseDouble(String.format("%.3f", totalMoney));
		}
		public void setTotalMoney(double totalMoney) {
			this.totalMoney = totalMoney;
		}
		public Map<String, CartItem> getItems() {
			return items;
		}
		public double getTotalDenomination() {
			totalDenomination = 0;
			for(Map.Entry<String, CartItem> me:items.entrySet()){
				totalDenomination +=me.getValue().getCheckdenomination();
			}
            
			return Double.parseDouble(String.format("%.3f", totalDenomination));
		}
		public void setTotalDenomination(double totalDenomination) {
			this.totalDenomination = totalDenomination;
		}
		public double getTotalBalance() {
			totalBalance = 0;
			for(Map.Entry<String, CartItem> me:items.entrySet()){
				totalBalance +=me.getValue().getBalance();
			}
            
			return Double.parseDouble(String.format("%.3f", totalBalance));
		}
		public void setTotalBalance(double totalBalance) {
			this.totalBalance = totalBalance;
		}
		//��Ʊ�������һ����Ʒ
		public void addProduct(Product product,double price,int quantity,double sumprice,double checkdenomination,double balance){
			if(items.containsKey(product.getId()+"-"+price)){
				//�ж�Ӧ�Ĺ�����
				CartItem item = items.get(product.getId()+"-"+price);
				item.setQuantity(item.getQuantity()+quantity);
			}else{
				CartItem item = new CartItem(product,price,quantity,sumprice,checkdenomination,balance);
				items.put(product.getId()+"-"+price, item);
			}
		}
}

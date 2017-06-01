package com.wust.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.wust.domain.Product;

public class Cart implements Serializable {
		//key:对应的产品的ID和单价   value:CartItem开票项
		private Map<String, CartItem> items = new HashMap<String, CartItem>();
		private int totalQuantity;//总数量
		private double totalMoney;//总金额
		private double totalDenomination;//总金额
		private double totalBalance;//总金额
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
		//向开票车中添加一个产品
		public void addProduct(Product product,double price,int quantity,double sumprice,double checkdenomination,double balance){
			if(items.containsKey(product.getId()+"-"+price)){
				//有对应的购物项
				CartItem item = items.get(product.getId()+"-"+price);
				item.setQuantity(item.getQuantity()+quantity);
			}else{
				CartItem item = new CartItem(product,price,quantity,sumprice,checkdenomination,balance);
				items.put(product.getId()+"-"+price, item);
			}
		}
}

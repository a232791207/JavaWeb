package com.wust.beans;
import java.io.Serializable;

import com.wust.domain.Product;

//开票项
public class CartItem implements Serializable {
	private Product product;
	private int quantity;//本项数量
	private double money;//本项小计
	private double price;
	private double checkdenomination;
	private double balance;
	public CartItem(Product product,double price,int quantity,double sumprice,double checkdenomination,double balance){
		
		this.product = product;
		this.price=price;
		this.quantity=quantity;
		this.money=sumprice;
		this.checkdenomination=checkdenomination;
		this.balance=balance;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Product getProduct() {
		return product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCheckdenomination() {
		return checkdenomination;
	}
	public void setCheckdenomination(double checkdenomination) {
		this.checkdenomination = checkdenomination;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}

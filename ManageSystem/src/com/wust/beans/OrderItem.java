package com.wust.beans;
import java.io.Serializable;

import com.wust.domain.Product;

//��Ʊ��
public class OrderItem implements Serializable {
	private String format;
	private String level;
	private double price;//��׼����
	private double realprice;//ʵ�ʵ���=��׼����-ֱ���Ż�-�����Ż�+һƱ���˷� 
	private double freight;//һƱ���˷�
	private double specialinc;//�����Ż�
	private double directinc;//ֱ���Ż�
	private int pack;//����������=��*��Ӧ��Ʒһ��������
	private int quantity;//��������number
	private double money;//����С��sumprice
	private String ifprofit;//�Ƿ���뷵��
	private String fare;//�Ƿ���ȡ���ȷ�
	private double volume;//���
	
	
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public OrderItem(String format, String level, double price,
			double realprice, double freight, double specialinc,
			double directinc, int pack, int quantity, double money,
			String ifprofit, String fare, double volume) {
		super();
		this.format = format;
		this.level = level;
		this.price = price;
		this.realprice = realprice;
		this.freight = freight;
		this.specialinc = specialinc;
		this.directinc = directinc;
		this.pack = pack;
		this.quantity = quantity;
		this.money = money;
		this.ifprofit = ifprofit;
		this.fare = fare;
		this.volume = volume;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public double getRealprice() {
		return realprice;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public double getSpecialinc() {
		return specialinc;
	}
	public void setSpecialinc(double specialinc) {
		this.specialinc = specialinc;
	}
	public double getDirectinc() {
		return directinc;
	}
	public void setDirectinc(double directinc) {
		this.directinc = directinc;
	}
	public int getPack() {
		return pack;
	}
	public void setPack(int pack) {
		this.pack = pack;
	}
	public String getIfprofit() {
		return ifprofit;
	}
	public void setIfprofit(String ifprofit) {
		this.ifprofit = ifprofit;
	}
	
}

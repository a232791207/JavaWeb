package com.wust.domain;

import java.io.Serializable;

public class Product implements Serializable{
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	private int id;
	public Product(){
		
	}
	public Product(int id, String format, String level, double thick,
			double volume, long num, int bagSlices) {
		super();
		this.id = id;
		this.format = format;
		this.level = level;
		this.thick = thick;
		this.volume = volume;
		this.num = num;
		this.bagSlices = bagSlices;
	}
	public Product(StoreRecord storeRecord) {
		this.format=storeRecord.getFormat();
		this.level=storeRecord.getLevel();
		this.height=storeRecord.getHeight();
		this.width=storeRecord.getWidth();
		this.thick=storeRecord.getThick();
		this.volume=storeRecord.getVolume();
		this.num=storeRecord.getNum();
		this.bagSlices=storeRecord.getBagSlices();
	}
	private String format;
	private String level;
	private double width;
	private double height;
	private double thick;
	private double volume;
	private long num;
	private int bagSlices;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public double getThick() {
		return thick;
	}
	public void setThick(double thick) {
		this.thick = thick;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getBagSlices() {
		return bagSlices;
	}
	public void setBagSlices(int bagSlices) {
		this.bagSlices = bagSlices;
	}
}

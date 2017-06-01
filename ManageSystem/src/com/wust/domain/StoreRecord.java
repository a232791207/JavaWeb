package com.wust.domain;

public class StoreRecord {
	private long id;
	private String time;
	private String format;
	private String level;
	private double width;
	private double height;
	private double thick;
	private double volume;
	private long num;
	private int bagSlices;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public int getBagSlices() {
		return bagSlices;
	}
	public void setBagSlices(int bagSlices) {
		this.bagSlices = bagSlices;
	}
}

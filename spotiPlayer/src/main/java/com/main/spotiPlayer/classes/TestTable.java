package com.main.spotiPlayer.classes;

public class TestTable {

	private int id;
	private int count;
	private String name;
	private double key;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getKey() {
		return key;
	}
	
	public void setKey(double key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "testTable [id=" + id + ", count=" + count + ", name=" + name + ", key=" + key + "]";
		//return "{id:" + id + ", count:" + count + ", name:\"" + name + "\", key:" + key + "}";
	}

}

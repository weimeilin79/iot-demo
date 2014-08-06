package org.blogdemo.mqtt.model;

import java.util.Date;

public class BreathRate {
	private Date occurTime = null;
	private int breathRate = 0;
	
	private int category = 0;
	private String categoryDesc = "Normal";
	
	
	
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public int getBreathRate() {
		return breathRate;
	}
	public void setBreathRate(int breathRate) {
		this.breathRate = breathRate;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	@Override
	public String toString(){
		return "BreathRate{ \n occurTime:["+occurTime+"] \n breathRate:["+breathRate+"] \n category:["+category+"] \n categoryDesc:["+categoryDesc+"] } ";
	}
	
}

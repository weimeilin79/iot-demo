package org.blogdemo.mqtt.model;

import java.util.Date;

public class InsulinPump {

	private Date occurTime = null;
	private int insulinPump = 0;
	
	private int category = 1;
	private String categoryDesc = "Non-diabetic";
	
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public int getInsulinPump() {
		return insulinPump;
	}
	public void setInsulinPump(int insulinPump) {
		this.insulinPump = insulinPump;
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
		return "InsulinPump{ \n occurTime:["+occurTime+"] \n insulinPump:["+insulinPump+"] \n category:["+category+"] \n categoryDesc:["+categoryDesc+"] } ";
	}
	
}

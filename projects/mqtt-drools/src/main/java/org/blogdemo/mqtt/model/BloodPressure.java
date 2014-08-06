package org.blogdemo.mqtt.model;

import java.util.Date;

public class BloodPressure {
	
	private Date occurTime = null;
	private int bloodpressure = 0;
	
	private int category = 0;
	private String categoryDesc = "Normal";
	
	
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public int getBloodpressure() {
		return bloodpressure;
	}
	public void setBloodpressure(int bloodpressure) {
		this.bloodpressure = bloodpressure;
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
		return "BloodPressure{ \n occurTime:["+occurTime+"] \n bloodpressure:["+bloodpressure+"] \n category:["+category+"] \n categoryDesc:["+categoryDesc+"] } ";
	}
	
	

}

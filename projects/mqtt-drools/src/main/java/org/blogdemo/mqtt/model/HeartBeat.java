package org.blogdemo.mqtt.model;

import java.util.Date;

public class HeartBeat {
	private Date occurTime = null;
	private int heartBeat = 0;
	
	private int category = 0;
	private String categoryDesc = "Warm up/Cool Down";
	
	
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public int getHeartBeat() {
		return heartBeat;
	}
	public void setHeartBeat(int heartBeat) {
		this.heartBeat = heartBeat;
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
		return "HeartBeat{ \n occurTime:["+occurTime+"] \n heartBeat:["+heartBeat+"] \n category:["+category+"] \n categoryDesc:["+categoryDesc+"] } ";
	}
	

}

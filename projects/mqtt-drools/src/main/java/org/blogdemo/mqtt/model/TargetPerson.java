package org.blogdemo.mqtt.model;

import java.util.HashMap;
import java.util.Set;

public class TargetPerson {
	
	private HashMap<Integer, Integer> bloodPressureCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> heartBeatCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> breathRateCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> insulinPumpCounter = new HashMap<Integer, Integer>();
	
	private String bloodPressureResult = "";
	private String heartBeatResult = "";
	private String breathRateResult = "";
	private String insulinPumpResult = "";
	private String overallResult = "";
	
	
	public HashMap<Integer, Integer> getBloodPressureCounter() {
		return bloodPressureCounter;
	}
	public void setBloodPressureCounter(
			HashMap<Integer, Integer> bloodPressureCounter) {
		this.bloodPressureCounter = bloodPressureCounter;
	}
	public HashMap<Integer, Integer> getHeartBeatCounter() {
		return heartBeatCounter;
	}
	public void setHeartBeatCounter(HashMap<Integer, Integer> heartBeatCounter) {
		this.heartBeatCounter = heartBeatCounter;
	}
	public HashMap<Integer, Integer> getBreathRateCounter() {
		return breathRateCounter;
	}
	public void setBreathRateCounter(HashMap<Integer, Integer> breathRateCounter) {
		this.breathRateCounter = breathRateCounter;
	}
	public HashMap<Integer, Integer> getInsulinPumpCounter() {
		return insulinPumpCounter;
	}
	public void setInsulinPumpCounter(HashMap<Integer, Integer> insulinPumpCounter) {
		this.insulinPumpCounter = insulinPumpCounter;
	}
	public String getBloodPressureResult() {
		return bloodPressureResult;
	}
	public void setBloodPressureResult(String bloodPressureResult) {
		this.bloodPressureResult = bloodPressureResult;
	}
	public String getHeartBeatResult() {
		return heartBeatResult;
	}
	public void setHeartBeatResult(String heartBeatResult) {
		this.heartBeatResult = heartBeatResult;
	}
	public String getBreathRateResult() {
		return breathRateResult;
	}
	public void setBreathRateResult(String breathRateResult) {
		this.breathRateResult = breathRateResult;
	}
	public String getInsulinPumpResult() {
		return insulinPumpResult;
	}
	public void setInsulinPumpResult(String insulinPumpResult) {
		this.insulinPumpResult = insulinPumpResult;
	}
	public String getOverallResult() {
		return overallResult;
	}
	public void setOverallResult(String overallResult) {
		this.overallResult = overallResult;
	}
	
	
	@Override
	public String toString(){
		String content = "Blood Pressure : \n==========================\n";
		
		Set<Integer> keys = bloodPressureCounter.keySet();
		for(Integer key: keys){
			content += "Category ["+key+"] :" + bloodPressureCounter.get(key)+"\n";
		}
		content += "Results ==> "+this.getBloodPressureResult()+"\n";
		
		content += "Heart Beat : \n========================== \n";
		
		keys = heartBeatCounter.keySet();
		for(Integer key: keys){
			content += "Category ["+key+"] :" + heartBeatCounter.get(key)+"\n";
		}
		content += "Results ==> "+this.getHeartBeatResult()+"\n";

		content += "Breath Rate : \n========================== \n";
		
		keys = breathRateCounter.keySet();
		for(Integer key: keys){
			content += "Category ["+key+"] :" + breathRateCounter.get(key)+"\n";
		}
		content += "Results ==> "+this.getBreathRateResult()+"\n";
		
		content += "Insulin Pump : \n========================== \n";
		
		keys = insulinPumpCounter.keySet();
		for(Integer key: keys){
			content += "Category ["+key+"] :" + insulinPumpCounter.get(key)+"\n";
		}
		content += "Results ==> "+this.getInsulinPumpResult()+"\n";
		
		
		content += "Overall Results ==> "+this.getOverallResult()+"\n";
		return content;
		
	}
	

}

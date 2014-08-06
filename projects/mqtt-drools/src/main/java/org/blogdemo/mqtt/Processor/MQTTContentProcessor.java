package org.blogdemo.mqtt.Processor;

import java.sql.Timestamp;
import java.util.Date;

import org.blogdemo.mqtt.model.BloodPressure;
import org.blogdemo.mqtt.model.BreathRate;
import org.blogdemo.mqtt.model.HeartBeat;
import org.blogdemo.mqtt.model.InsulinPump;

public class MQTTContentProcessor {
	
	public BloodPressure processBloodPressure(String content){
		BloodPressure bloodPressure = new BloodPressure();
		
		bloodPressure.setOccurTime(getDate(content));
		bloodPressure.setBloodpressure(getValue(content));
				
		return bloodPressure;
	}
	
	public BreathRate processBreathRate(String content){
		BreathRate breathRate = new BreathRate();
		
		breathRate.setOccurTime(getDate(content));
		breathRate.setBreathRate(getValue(content));
				
		return breathRate;
	}
	
	public HeartBeat processHeartBeat(String content){
		HeartBeat heartBeat = new HeartBeat();
		
		heartBeat.setOccurTime(getDate(content));
		heartBeat.setHeartBeat(getValue(content));
				
		return heartBeat;
	}
	
	public InsulinPump processInsulinPump(String content){
		InsulinPump insulinPump = new InsulinPump();
		
		insulinPump.setOccurTime(getDate(content));
		insulinPump.setInsulinPump(getValue(content));
				
		return insulinPump;
	}	
	
	private Date getDate(String content){
		
		String[] result;
		if(content != null && content.length() > 0 && content.contains(",")){
			result = content.split(",");
			if(result[0] != null){
				  Timestamp stamp = new Timestamp(new Long(result[0]));
				  Date occurTime = new Date(stamp.getTime());
				  return occurTime;
			}
		}
				
		return null;
	}	
	
	private int getValue(String content){
		
		String[] result;
		if(content != null && content.length() > 0 && content.contains(",")){
			result = content.split(",");
			if(result[1] != null){
				int bloodPressureValue = new Integer(result[1].trim());
				return bloodPressureValue;
				
			}
		}				
		return 0;
	}		

} 

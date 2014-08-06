package org.blogdemo.mqtt.Processor;

import java.util.HashMap;

import org.blogdemo.mqtt.model.BloodPressure;
import org.blogdemo.mqtt.model.BreathRate;
import org.blogdemo.mqtt.model.DiagnoseResult;
import org.blogdemo.mqtt.model.HeartBeat;
import org.blogdemo.mqtt.model.InsulinPump;
import org.blogdemo.mqtt.model.TargetPerson;

public class DiagnosesProcessor {
	
	private HashMap<Integer, Integer> bloodPressureCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> heartBeatCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> breathRateCounter = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> insulinPumpCounter = new HashMap<Integer, Integer>();
	
	public void bloodPressureCount(BloodPressure bloodPressure){
		
		Integer value = bloodPressureCounter.get(bloodPressure.getCategory());
		if(value == null){
			value = 0;
		}
		value++;
		bloodPressureCounter.put(bloodPressure.getCategory(), value);
	}
	
	public void bloodPressureTestHelper(){
		
		bloodPressureCounter.put(0, 4);
		bloodPressureCounter.put(1, 3);
		bloodPressureCounter.put(2, 1);
		
		breathRateCounter.put(0, 1);
		breathRateCounter.put(1, 5);
		breathRateCounter.put(2, 3);
		breathRateCounter.put(3, 2);
		
		heartBeatCounter.put(0, 1);
		heartBeatCounter.put(1, 1);
		heartBeatCounter.put(2, 1);
		
		insulinPumpCounter.put(0, 2);
		insulinPumpCounter.put(1, 3);
		insulinPumpCounter.put(2, 3);
		
	}
	
	public void heartBeatCount(HeartBeat heartBeat){
		Integer value = heartBeatCounter.get(heartBeat.getCategory());
		if(value == null){
			value = 0;
		}
		value++;
		heartBeatCounter.put(heartBeat.getCategory(), value);		
	}

	public void breathRateCount(BreathRate breathRate){
		Integer value = breathRateCounter.get(breathRate.getCategory());
		if(value == null){
			value = 0;
		}
		value++;
		breathRateCounter.put(breathRate.getCategory(), value);		
	}

	public void insulinPumpCount(InsulinPump insulinPump){
		Integer value = insulinPumpCounter.get(insulinPump.getCategory());
		if(value == null){
			value = 0;
		}
		value++;
		insulinPumpCounter.put(insulinPump.getCategory(), value);	
		System.out.println(this);
	}
	
	public TargetPerson getCurrentStatus(){
		TargetPerson targetPerson = new TargetPerson();
		targetPerson.setBloodPressureCounter(bloodPressureCounter);
		targetPerson.setHeartBeatCounter(heartBeatCounter);
		targetPerson.setBreathRateCounter(breathRateCounter);
		targetPerson.setInsulinPumpCounter(insulinPumpCounter);
		
		return targetPerson;
	}
	
	public DiagnoseResult getResult(TargetPerson targetPerson){
		DiagnoseResult result = new DiagnoseResult();
		
		result.setBloodPressureResult(targetPerson.getBloodPressureResult());
		result.setBreathRateResult(targetPerson.getBreathRateResult());
		result.setHeartBeatResult(targetPerson.getHeartBeatResult());
		result.setInsulinPumpResult(targetPerson.getInsulinPumpResult());
		result.setOverallResult(targetPerson.getOverallResult());
		
		return result;
	}
	
	

}

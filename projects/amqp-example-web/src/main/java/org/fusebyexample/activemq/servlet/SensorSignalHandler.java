/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author christina
 * */
package org.fusebyexample.activemq.servlet;

import org.fusebyexample.activemq.MQTTProducer;
import org.fusebyexample.activemq.MSGProducer;
import org.fusebyexample.activemq.model.BloodPressure;
import org.fusebyexample.activemq.model.BreathRate;
import org.fusebyexample.activemq.model.HeartBeat;
import org.fusebyexample.activemq.model.InsulinPump;

public class SensorSignalHandler {
	
	private Sensor heartbeatSensor;
	private Sensor breathrateSensor;
	private Sensor bloodpressureSensor;
	private Sensor insulinpumpSensor;
	private boolean started = false;
	
	
	
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public void start(String targetName){
	
		heartbeatSensor = new Sensor("heartBeat",targetName);
		heartbeatSensor.start();
			
		breathrateSensor = new Sensor("breathRate",targetName);
		breathrateSensor.start();
			
		bloodpressureSensor = new Sensor("bloodPressure",targetName);
		bloodpressureSensor.start();
			
		insulinpumpSensor = new Sensor("insulinPump",targetName);
		insulinpumpSensor.start();

	}
	
	public void stop(){
		heartbeatSensor.setStop();
		breathrateSensor.setStop();
		bloodpressureSensor.setStop();
		insulinpumpSensor.setStop();
	}
	
	
	class Sensor extends Thread{
		
		
		
		private String threadType = "";
		private boolean willStop = false;
		private String targetName = "";
		
		public Sensor(String threadType, String targetName){
			super();
			this.threadType = threadType;
			this.targetName = targetName;
		}
		
		public void setStop(){
			  willStop = true;
		}
		
		@Override
		public void run(){
			
			
			while(true){
				
				if(willStop){
					
					System.out.println("STOP SENDING ["+threadType+"] ....");
					break;
			
				}else if("heartBeat".equals(threadType)){
					MSGProducer heartbeatProducer = new MSGProducer("heartBeat");
					MQTTProducer heartbeatMQTTProducer = new MQTTProducer("heartBeat",targetName);
					
					HeartBeat heartBeat = new HeartBeat();
			    	heartbeatProducer.send(heartBeat.getRateInString());
			    	heartbeatMQTTProducer.publish(heartBeat.getRateInString());
			    	
			    	try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
				}else if("breathRate".equals(threadType)){
					
					MSGProducer breathRateProducer = new MSGProducer("breathRate");
					MQTTProducer breathRateMQTTProducer = new MQTTProducer("breathRate",targetName);
					
					BreathRate breathRate = new BreathRate();
					breathRateProducer.send(breathRate.getRateInString());
					breathRateMQTTProducer.publish(breathRate.getRateInString());
					
			    	try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
			    	
				}else if("bloodPressure".equals(threadType)){
					
					MSGProducer bloodPressureProducer = new MSGProducer("bloodPressure");
					MQTTProducer bloodPressureMQTTProducer = new MQTTProducer("bloodPressure",targetName);
					
					BloodPressure bloodPressure = new BloodPressure();
					bloodPressureProducer.send(bloodPressure.getRateInString());
					bloodPressureMQTTProducer.publish(bloodPressure.getRateInString());
					
			    	try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
			    	
				}else if("insulinPump".equals(threadType)){
					MSGProducer insulinPumpProducer = new MSGProducer("insulinPump");	
					MQTTProducer insulinPumpMQTTProducer = new MQTTProducer("insulinPump",targetName);
					
					InsulinPump insulinPump = new InsulinPump();
					insulinPumpProducer.send(insulinPump.getRateInString());
					insulinPumpMQTTProducer.publish(insulinPump.getRateInString());
					
			    	try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
			
		}  
	    	  

		
	}
}

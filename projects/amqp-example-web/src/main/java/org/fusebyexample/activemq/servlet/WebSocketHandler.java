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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.fusebyexample.activemq.MSGConsumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class WebSocketHandler implements OnTextMessage{
	
	private boolean startRead = false;
	private Connection connection = null;
	private MSGConsumer heartBeatConsumer = null;
	private MSGConsumer breathRateConsumer = null;
	private MSGConsumer bloodPressureConsumer = null;
	private MSGConsumer insulinPumpConsumer = null;
	
	private Map<String, String> resultMap = null;

	public void onClose(int closeCode, String message) {
		// TODO Auto-generated method stub
		
	}

	public void onOpen(Connection connection) {
		this.connection = connection;
		
		heartBeatConsumer = new MSGConsumer("heartBeat");
		breathRateConsumer = new MSGConsumer("breathRate");
		bloodPressureConsumer = new MSGConsumer("bloodPressure");
		insulinPumpConsumer = new MSGConsumer("insulinPump");
		
		resultMap = new HashMap<String, String>();
		
	}

	public void onMessage(String message) {
		System.out.println("Consumer:["+message+"]");
		
		if("startReading".equals(message)){
			startRead = true;
			
		}else{
			startRead = false;
		}
		while(true){
			try {
				
				if(!startRead)
					break;
				
				String heartBeatMsg = heartBeatConsumer.getMessage();
				System.out.println("GOT heartBeatMsg:["+heartBeatMsg+"]");
				String breathRateMsg = breathRateConsumer.getMessage();		
				System.out.println("GOT breathRateMsg:["+breathRateMsg+"]");
				String bloodPressureMsg = bloodPressureConsumer.getMessage();
				System.out.println("GOT bloodPressureMsg:["+bloodPressureMsg+"]");
				String insulinPumpMsg = insulinPumpConsumer.getMessage();
				System.out.println("GOT insulinPumpMsg:["+insulinPumpMsg+"]");
				
				
				if(heartBeatMsg != null && !"".equals(heartBeatMsg)){
					resultMap.put("heartBeat", heartBeatMsg);
				}else{
					resultMap.put("heartBeat", "null,null");
				}
				
				if(breathRateMsg != null && !"".equals(breathRateMsg)){
					resultMap.put("breathRate", breathRateMsg);
				}else{
					resultMap.put("breathRate", "null,null");
				}
				
				if(bloodPressureMsg != null && !"".equals(bloodPressureMsg)){
					resultMap.put("bloodPressure", bloodPressureMsg);
				}else{
					resultMap.put("bloodPressure", "null,null");
				}
				
				if(insulinPumpMsg != null && !"".equals(insulinPumpMsg)){
					resultMap.put("insulinPump", insulinPumpMsg);
				}else{
					resultMap.put("insulinPump", "null,null");
				}
				
				Gson gson = new GsonBuilder().create(); 
				String json = gson.toJson(resultMap);
				
				System.out.println("Start sending.........");
				connection.sendMessage(json);
				System.out.println("......Sent back to websocket:["+json+"]");
				Thread.sleep(5000);
			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}	

	
	
	
}
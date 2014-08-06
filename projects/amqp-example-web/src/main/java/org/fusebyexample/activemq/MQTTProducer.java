package org.fusebyexample.activemq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Set;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTProducer {

	private static final Logger logger = LoggerFactory.getLogger(MQTTProducer.class);
	
	
	private String targetName = "targetName";
	
	private String destinationNAME ="";
	
	private MQTT mqtt;
	
	
	
    BlockingConnection connection;
    
    
	public MQTTProducer(String topicName, String targetName){
    	
    	destinationNAME = "topic/"+topicName;
    	this.targetName = targetName;
    	logger.info("destinationNAME:["+destinationNAME+"]");
    	setup();
    }
	
	
	public void setup(){
		mqtt = new MQTT();
		try {
			Properties props = new Properties();
        
			props.load(getClass().getResourceAsStream("/mqtt.properties"));
			Set<Object> myPropKeys = props.keySet();
			
			for(Object key: myPropKeys){
				String stringKey = key.toString();
				if("brokerURL".equals(stringKey)){
					mqtt.setHost(props.getProperty(stringKey));	    	
				}else if("username".equals(stringKey)){
					mqtt.setUserName(props.getProperty(stringKey));
				}else{
					mqtt.setPassword(props.getProperty(stringKey));
				}
			}
			
			
	    	connection = mqtt.blockingConnection();
	    	connection.connect();
    	
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	
	public void publish(String content){
		content = content+","+this.targetName;
		try {
			connection.publish(destinationNAME, content.getBytes(), QoS.AT_LEAST_ONCE, false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}

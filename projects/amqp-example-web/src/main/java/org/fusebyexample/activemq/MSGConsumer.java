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

package org.fusebyexample.activemq;

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class MSGConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(MSGConsumer.class);

    private static final Boolean NON_TRANSACTED = false;
    private static final String CONNECTION_FACTORY_NAME = "myJmsFactory";
    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 1000; //120
    
    private  String destinationNAME = "";
    
    private ConnectionFactory factory = null;
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageConsumer consumer = null;
    
    public MSGConsumer(String queueName){
    	destinationNAME = "queue/"+queueName;
    	System.out.println("destinationNAME:["+queueName+"]");
    	
    	setupFactory();
    }
    	
    public void setupFactory(){
    	try {
	    	
    		Properties props = new Properties();
        	props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.qpid.amqp_1_0.jms.jndi.PropertiesFileInitialContextFactory");
        	props.setProperty(Context.PROVIDER_URL,"src/main/resources/biodash.properties");
        	javax.naming.Context ctx = new InitialContext(props);
        	
	        factory = (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY_NAME);
	        destination = (Destination) ctx.lookup(destinationNAME);
	
    	} catch (Throwable t) {
    		 System.err.println("Error receiving message");
    		t.printStackTrace();
           
        } 
    }
    
    public void closeConn(){
    	try{
	    	consumer.close();
	        session.close();
    	} catch (Throwable t) {
            LOG.error("Error receiving message", t);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOG.error("Error closing connection", e);
                }
            }
        }    
    }
    
   
    
    public String getMessage() {
        	Message message;
			try {
				 connection = factory.createConnection();
				 connection.start();
			
			     session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
			     
			     
			    consumer = session.createConsumer(destination);
			    
			     
			     message = consumer.receive(MESSAGE_TIMEOUT_MILLISECONDS);
			    
				if (message != null) {
		            if (message instanceof TextMessage) {
		            	String text = ((TextMessage) message).getText();
		            	System.out.println("Got message: " + text);
		                return text;
		            }
		        }
				 
			} catch (JMSException e) {
				e.printStackTrace();
			} finally{
				closeConn();
			}
			closeConn();
            return null;
        
    }
}
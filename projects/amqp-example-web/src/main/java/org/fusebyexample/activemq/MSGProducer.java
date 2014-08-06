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

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSGProducer {
    private static final Logger LOG = LoggerFactory.getLogger(MSGProducer.class);

    private static final Boolean NON_TRANSACTED = false;
    private static final long MESSAGE_TIME_TO_LIVE_MILLISECONDS = 0;
   // private static final int MESSAGE_DELAY_MILLISECONDS = 100;
    private static final String CONNECTION_FACTORY_NAME = "myJmsFactory";
    private String destinationNAME ="";
    
    private ConnectionFactory factory = null;
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;
    
    public MSGProducer(String queueName){
    	
    	destinationNAME = "queue/"+queueName;
    	//System.out.println("destinationNAME:["+queueName+"]");
    	
    	setupFactory();
    }
    
    public void setupFactory(){
        try {
            // JNDI lookup of JMS Connection Factory and JMS Destination
        	
        	Properties props = new Properties();
        	props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.qpid.amqp_1_0.jms.jndi.PropertiesFileInitialContextFactory");
        	props.setProperty(Context.PROVIDER_URL,"src/main/resources/biodash.properties");
        	javax.naming.Context ctx = new InitialContext(props);
        	
        	factory = (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY_NAME);
        	destination = (Destination) ctx.lookup(destinationNAME);
            

        } catch (Throwable t) {
        	t.printStackTrace();
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

    
    public void send(String content){
    	
             TextMessage message;
			try {
				
	            connection = factory.createConnection();
	            connection.start();

	            session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
	            producer = session.createProducer(destination);
	            producer.setTimeToLive(MESSAGE_TIME_TO_LIVE_MILLISECONDS);
	            
				
				message = session.createTextMessage(content);
				
	            producer.send(message);
	            
	            System.out.println("Sending content: ["+content+"]  ONE!");
	            //Thread.sleep(MESSAGE_DELAY_MILLISECONDS);
	            
	            producer.close();
	            session.close();
	            
	            
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
    }
    
    
    
   
}

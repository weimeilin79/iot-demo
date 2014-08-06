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
package org.fusebyexample.activemq.model;

import java.util.Calendar;

import org.fusebyexample.activemq.util.DataGenerator;

public class HeartBeat {

	public static final int MIN_BPM = 80;
	public static final int MAX_BPM =200;
	
	public static final int RANGE = 10;
	
	private int heartrate = 0;
	
	
	
	public HeartBeat(){
		heartrate = DataGenerator.randInt(MIN_BPM, MAX_BPM);
	}

	private Long[] getRate(){
		heartrate = DataGenerator.randInt(MIN_BPM, MAX_BPM);
		Long[] theResult = new Long[2];
		Calendar calendar= Calendar.getInstance();
		theResult[0] =  calendar.getTimeInMillis();
		theResult[1] =  new Long(heartrate);
		
		return theResult ;
		
	}
	
	public String getRateInString(){
		return DataGenerator.arraytoString(getRate());
		
	}
	
}

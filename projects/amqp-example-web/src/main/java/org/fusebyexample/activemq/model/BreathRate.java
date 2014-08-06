package org.fusebyexample.activemq.model;

import java.util.Calendar;

import org.fusebyexample.activemq.util.DataGenerator;

public class BreathRate {
	public static final int MIN_BPM = 5;
	public static final int MAX_BPM =30;
	
	public static final int RANGE = 3;
	
	private int breathRate = 0;
	
	
	public BreathRate(){
		breathRate = DataGenerator.randInt(MIN_BPM, MAX_BPM);
	}

	private Long[] getRate(){
		breathRate = DataGenerator.randInt(MIN_BPM, MAX_BPM);
		Long[] theResult = new Long[2];
		Calendar calendar= Calendar.getInstance();
		theResult[0] =  calendar.getTimeInMillis();
		theResult[1] =  new Long(breathRate);
		
		return theResult ;
		
	}
	
	public String getRateInString(){
		return DataGenerator.arraytoString(getRate());
		
	}
}

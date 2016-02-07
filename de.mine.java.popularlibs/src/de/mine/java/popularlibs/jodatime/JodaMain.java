package de.mine.java.popularlibs.jodatime;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.CopticChronology;

public class JodaMain {

	public static void main(String[] args) {
		
		
		// INSTANTS
		// ReadableInstant.class

		// DateTime.class  			- immutable
		// MutableDateTime.class 	- mutable
		
		// CHronology.class
	
		// ReadableDuration.class 
		// Duration.class
		
		// Period
		
		Chronology coptic = CopticChronology.getInstance();
		
		
		DateTime dt = new DateTime();
		
		
		new Thread(new Runnable() {
			DateTime dt = new DateTime();
			
			int sleepTime = 1000;
			MutableDateTime timeStampNextFeedback = new MutableDateTime();
			Duration duration = new Duration(10000);
			
			@Override
			public void run() {
				while(true){
					
					if(timeStampNextFeedback.isBeforeNow()){
						System.out.println("Feedback");
						// ++ counter
						timeStampNextFeedback.add(duration);
						Duration temp = duration;
						System.out.println();
					}
					
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String date = sdf.format(dateObj);
	    return date;
	}
	
	public String getRequiredDateYYYYDDMM(int days)
	{

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		
		// akash sir used here instance not getCalender to get the actual reqDate
		
		Calendar cal =Calendar.getInstance();
		//Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sim.format(cal.getTime());
		return reqDate;
	}
}

package javaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandonNumber()
	{
		Random num=new Random();
		int ranInt = num.nextInt(1000);
		return ranInt;
	}
	
	public String getRequiredDate(int days)
	{
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String dateRequired = sim.format(cal.getTime());
		return dateRequired;
	}

	public String getCurrentDate()
	{
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(date);
		return currentDate;
	}
}

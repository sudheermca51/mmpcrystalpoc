package org.h2k.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AppLibrary {
	
	public static String getFutureDate(String format,int d) 
	{

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,d);
		Date date = cal.getTime();
		System.out.println("Today's Date :" + date);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String formattedDate = sdf.format(date);
		System.out.println("formattedDate:: " + formattedDate);
		return formattedDate;	

	}
	public static int generateRandInteger()
	{
		Random rand = new Random();
		int randno  = 30+rand.nextInt(80-30);
		System.out.println("range::: "+ randno);
		return randno;
	}

	public static String generateRandString(String text)
	{
		Random rand = new Random();
		/**
		 * Random upper case character
		 */
		//Generate a random between 65 to 90 
		//randno = LB+rand.nextInt(UB-LB);
		int randno  = 65+rand.nextInt(90-65);
		System.out.println("range::: "+ randno);
		
		//type casting
		char upperCase = (char) randno;
		System.out.println("upper case char value :: "+ upperCase);
		
		
		/**
		 * Random lower case character
		 */
		//Generate a random number between 97 to 122
		randno = 97+rand.nextInt(122-97);
		char lowerCase = (char) randno;
		System.out.println("lower case char value::" + lowerCase);
		
		String randomString= text+upperCase+lowerCase;
		System.out.println("Random String::" +randomString );
		return randomString;
	}

}

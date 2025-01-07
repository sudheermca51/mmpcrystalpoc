package org.iit.healthcare.mmp.tests;


import java.util.HashMap;

import org.h2k.util.BaseClass;
import org.h2k.util.MMPLibrary;
import org.iit.healthcare.mmp.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentDatePickerTests extends BaseClass{

	@Parameters({"doctor","specialization","time","days","dateformat","symptoms"})
	@Test
	public void validateBookAppointment(String doctor,String specialization,String time,String days,String dateformat,String symptoms)
	{
		MMPLibrary mmpLib = new MMPLibrary(driver);
		mmpLib.launchBrowser(prop.getProperty("url"));
		mmpLib.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		mmpLib.navigateToAPatientModule("Schedule Appointment");
		ScheduleAppointmentPage schAppObj = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap = schAppObj.bookAppointment(doctor,specialization,time,days,dateformat,symptoms);
		HashMap<String,String> actualHMap = schAppObj.fetchPatientPortalData();
		Assert.assertEquals(actualHMap, expectedHMap);
		//Assert.assertTrue(expectedHMap.equals(actualHMap));
	}
	 
	 
	 

}

package org.iit.healthcare.mmp.tests;


import org.h2k.util.BaseClass;
import org.h2k.util.MMPLibrary;
import org.iit.healthcare.mmp.pages.EditProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EditProfileTests extends BaseClass{

	@Test
	public void validateEditFirstName()
	{
		MMPLibrary mmpLib = new MMPLibrary(driver);

		mmpLib.launchBrowser(prop.getProperty("url"));
		mmpLib.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		mmpLib.navigateToAPatientModule("Profile");
		EditProfilePage editProjObj = new EditProfilePage(driver);
		String actualFName = editProjObj.editAllFields();
		String expectedFName="ria1";
		Assert.assertEquals(actualFName, expectedFName);
	}



}

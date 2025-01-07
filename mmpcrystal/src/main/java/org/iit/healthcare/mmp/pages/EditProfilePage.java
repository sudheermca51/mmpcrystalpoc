package org.iit.healthcare.mmp.pages;

import org.h2k.util.AppLibrary;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class EditProfilePage {

WebDriver driver ;
	
	public EditProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	public String editAllFields()
	{
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.id("Ebtn")));
		action.click();

		//Fname Logic
		WebElement fnameWE = driver.findElement(By.id("fname"));
		action.moveToElement(fnameWE);
		action.sendKeys(fnameWE,Keys.CLEAR);
		String expectedFName = AppLibrary.generateRandString("QAAUT");
		action.sendKeys(fnameWE,expectedFName);
		String actualFName = fnameWE.getAttribute("value");
		action.perform();

		//Age Logic
		WebElement ageWE = driver.findElement(By.id("age"));
		action.moveToElement(ageWE);
		action.sendKeys(ageWE,Keys.CLEAR);
		String ageExpected = AppLibrary.generateRandInteger()+"";
		String ageActual = ageWE.getAttribute("value");
		action.sendKeys(ageActual,ageExpected);
		action.perform();


		WebElement saveButton = driver.findElement(By.id("Sbtn"));
		action.moveToElement(saveButton);
		action.click(saveButton);
		action.perform();

		Alert alrt = driver.switchTo().alert();
		System.out.println("Alert Text " + alrt.getText());
		alrt.accept();

		return actualFName;

	}


}


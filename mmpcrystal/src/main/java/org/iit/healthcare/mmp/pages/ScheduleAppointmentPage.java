package org.iit.healthcare.mmp.pages;

import java.time.Duration;
import java.util.HashMap;

import org.h2k.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage {

	protected WebDriver driver ;
	private By appointmentBtnXpath = By.xpath("//input[@value = 'Create new appointment']");
	private By frameID = By.id("myframe");
	private By datepickerID = By.id("datepicker");
	private By dpYearClassName= By.className("ui-datepicker-year");
	private By dpMonthClassName = By.className("ui-datepicker-month");
	
	public ScheduleAppointmentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public HashMap<String, String> bookAppointment(String doctorName,String Specilization,String time,String days,String format,String sym)
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(appointmentBtnXpath).click();
		driver.findElement(By.xpath("//h4[text() ='Dr."+doctorName+"']/parent::li/" + "div/p[text()='Description: "
				+ Specilization + "']/ancestor::ul/following-sibling::button")).click();
		expectedHMap.put("doctor", doctorName);

		WebElement webEle = driver.findElement(frameID);

		driver.switchTo().frame(webEle);
		WebElement datePickerWE = driver.findElement(datepickerID );
		datePickerWE.click();
		int d = Integer.parseInt(days);
		String futureDate[] = AppLibrary.getFutureDate("d/MMMM/YYYY",d).split("/");
		String expectedDay = futureDate[0];
		String expectedMonth = futureDate[1];
		String expectedYear = futureDate[2]; //2025

		String actualYear = driver.findElement(dpYearClassName).getText();//what is displayed 2024

		while(!(actualYear.equals(expectedYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(dpYearClassName).getText();//what is displayed 2025
		}

		String actualMonth = driver.findElement(dpMonthClassName).getText();//what is displayed 2024

		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(dpMonthClassName).getText();//what is displayed 2025
		}

		driver.findElement(By.linkText(expectedDay)).click();
		
		expectedHMap.put("date", datePickerWE.getAttribute("value"));
 

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='time']")));

		WebElement timeWebEle = driver.findElement(By.xpath("//select[@name='time']"));
		Actions myActions = new Actions(driver);
		Action mydblclick = myActions.click(timeWebEle).build();
		mydblclick.perform();

		// driver.findElement(By.xpath("//p[text()='Time:']")).click();

		Select selectTime = new Select(driver.findElement(By.xpath("//select[@name='time']")));

		System.out.println("time -------->>>>>" + time);

		selectTime.selectByVisibleText(time);

		expectedHMap.put("time", time);

		/*
		 * List<WebElement> selectOpt = selectTime.getAllSelectedOptions();
		 *
		 * System.out.println("selectOpt ------->>>>"+selectOpt);
		 */

		driver.findElement(By.xpath("//button[text() = 'Continue']")).click();
		
		driver.switchTo().defaultContent();

//		String mainHandleId = driver.getWindowHandle();
//
//		driver.switchTo().window(mainHandleId);

		WebElement symptomWE = driver.findElement(By.xpath("//textarea[@id='sym']"));
		symptomWE.click();
		symptomWE.sendKeys(sym);
		expectedHMap.put("sym", sym);

		driver.findElement(By.xpath("//input[@type = 'submit']")).click();

		System.out.println("expectedHMap ::" + expectedHMap);
		return expectedHMap;


	}
	public HashMap<String,String> fetchPatientPortalData()
	{
			HashMap<String,String> actualHMap = new HashMap<String,String>();
			actualHMap.put("doctor",driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[4]")).getText());
			actualHMap.put("date",driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[1]")).getText());
			actualHMap.put("time",driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[2]")).getText());
			actualHMap.put("sym",driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[3]")).getText());
			return actualHMap;
	
	
	
	}
}

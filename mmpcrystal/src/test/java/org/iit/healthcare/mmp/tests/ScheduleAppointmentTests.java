package org.iit.healthcare.mmp.tests;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
/*
 * #1. Enter the data and store in the Expected HashMap.
#2. The key details in the hashmap are same in expected and actual HMAP.
#3. The data entered to be fetched and stored in the HashMap.
#4. The xpath table should contain information about the details of the table. 
 */

public class ScheduleAppointmentTests {
	WebDriver driver;

	@Test
	public void validateBookAppointment()
	{
		invokeDriverInstance("chrome");
		launchBrowser("http://85.209.95.122/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		login("ria1","Ria12345");
		navigateToAPatientModule("Schedule Appointment");
		bookAppointment("charlie","cardiology","12pm",90,"dd/MM/YYYY","fever");
		//fetchPAtientPortalDate
	}

	public HashMap<String, String> bookAppointment(String doctorName,String Specilization,String time,int d,String format,String sym)
	{



		HashMap<String,String> expectedHMap = new HashMap<String,String>();

		driver.findElement(By.xpath("//input[@value = 'Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[text() ='Dr."+doctorName+"']/parent::li/" + "div/p[text()='"
				+ Specilization + "']/ancestor::ul/following-sibling::button")).click();

		expectedHMap.put("doctor", doctorName);

		WebElement webEle = driver.findElement(By.id("myframe"));

		driver.switchTo().frame(webEle);

		WebElement datePicker = driver.findElement(By.id("datepicker"));
		datePicker.click();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, d);
		Date date = cal.getTime();
		System.out.println("Today's Date :" + date);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String formattedDate = sdf.format(date);
		// driver.switchTo().frame(0);
		// driver.switchTo().parentFrame();
		datePicker.sendKeys(formattedDate);
		datePicker.sendKeys(Keys.ENTER);

		expectedHMap.put("date", formattedDate);

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

		String mainHandleId = driver.getWindowHandle();

		driver.switchTo().window(mainHandleId);

		WebElement symptomWE = driver.findElement(By.xpath("//textarea[@id='sym']"));
		symptomWE.click();
		symptomWE.sendKeys(sym);
		expectedHMap.put("sym", sym);

		driver.findElement(By.xpath("//input[@type = 'submit']")).click();

		System.out.println("expectedHMap ::" + expectedHMap);
		return expectedHMap;


	}
	//	public HashMap fetchPatientPortalData()
	//	{
	//		hashMap.put("doctor",driver.findElement().getText());
	//		hashMap.put("date",driver.findElement().getText());
	//		hashMap.put("time",driver.findElement().getText());
	//		hashMap.put("sym",driver.findElement().getText());
	//		return hashMap;
	//
	//
	//
	//	}
	public void navigateToAPatientModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space() = '"+moduleName+"']")).click();
	}
	public void launchBrowser(String url )
	{
		driver.get(url);
	}
	public void invokeDriverInstance(String browserName)
	{

		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}	

	}
	public void login(String username,String password)
	{
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();

	}

}

package org.h2k.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MMPLibrary {
	
	WebDriver driver ;
	public MMPLibrary(WebDriver driver) {
		this.driver = driver;
	}
	public void navigateToAPatientModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space() = '"+moduleName+"']")).click();
	}
	public void login(String username,String password)
	{
		
		driver.findElement(By.id("username")).sendKeys(username+"");
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();

	}
	public void launchBrowser(String url )
	{
		driver.manage().window().maximize();
		driver.get(url);

	}

}

package org.h2k.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	protected WebDriver driver;
	protected Properties prop;
	String browserName,environment;
	int i =10;
	
	public Properties loadProperties(String filePath) throws IOException
	{
		System.out.println("######################################Reading the property File:::"+ filePath+"################################################");
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		prop = new Properties();
		prop.load(fis);//hashmap 
		Set<Object> keys= prop.keySet();
		for(Object key : keys)
		{
			System.out.println("key:::" +key);
			System.out.println("value:::"+ prop.get(key));
		}
		
		System.out.println("######################################Reading the property File Completed#######################################################");
		return prop;
	}
	@BeforeTest
	public void setProperties() throws IOException  
	{

		System.out.println("absolute path"+ System.getProperty("user.dir"));
		prop=loadProperties("config/mmp.properties");
		browserName=prop.getProperty("browsername");
		environment=prop.getProperty("environment");
		if(environment.equals("qa"))
		{
			prop=loadProperties("config/mmp_qa.properties");
			System.out.println("url" + prop.getProperty("url"));
		}
		else if(environment.equals("dev"))
		{
			prop=loadProperties("config/mmp_dev.properties");
			System.out.println("url" + prop.getProperty("url"));
		}


	}
	@BeforeClass
	public void instantiateDriver()
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

	



}

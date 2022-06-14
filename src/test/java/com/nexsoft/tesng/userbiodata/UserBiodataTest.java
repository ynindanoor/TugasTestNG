package com.nexsoft.tesng.userbiodata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserBiodataTest {
	WebDriver driver;
	public JavascriptExecutor jse;
	
	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		jse = (JavascriptExecutor) driver;
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

	}
	
	@Test (priority = 0)
	public void login() {
		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("yunindanin@gmail.com");
		// input[placeholder='Password']
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Yunindanin");
	}
	
	@Test(priority = 1, dataProvider = "getNexSoftData", dataProviderClass = com.nexsoft.testng.dataprovider.DataProviderNexSoft.class)
	public void createData(String param1, String param2, String param3, String param4) {

		driver.get("http://localhost/cicool/administrator/userbiodata");

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println("create data --> " + sdf2.format(new Date().getTime()));


		driver.findElement(By.id("btn_add_new")).click();
		
		driver.findElement(By.id("first_name")).click();
		driver.findElement(By.id("first_name")).sendKeys(param1);
		
		driver.findElement(By.id("last_name")).click();
		driver.findElement(By.id("last_name")).sendKeys(param2);
		
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys(param3);
		
		driver.findElement(By.id("gender")).click();
		driver.findElement(By.id("gender")).sendKeys(param4);

		jse.executeScript("window.scrollBy(100, 500)", "");
		
		WebElement pilihphoto= driver.findElement(By.cssSelector("input[title='file input"));

		int randomVal = 1 + (int) (Math.random() * ((3 - 1) + 1));
		if (randomVal == 1)
			pilihphoto.sendKeys("C:\\Users\\NEXSOFT\\eclipse-workspace\\Yuninda\\TugasTestNG\\src\\test\\resources\\Lisa01.jpeg");
		else if (randomVal == 2)
			pilihphoto.sendKeys("C:\\Users\\NEXSOFT\\eclipse-workspace\\Yuninda\\TugasTestNG\\src\\test\\resources\\Lisa02.jpg");
		else
			pilihphoto.sendKeys("C:\\Users\\NEXSOFT\\eclipse-workspace\\Yuninda\\TugasTestNG\\src\\test\\resources\\Lisa03.jpg");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//a[@id='btn_save']")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test (priority = 1)
	public void getUsername() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println( driver.findElement(By.linkText("Yunindanin")).getText() + sdf2.format(new  Date().getTime()));
	}
	@Test (priority = 1)
	public void getUsername2() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println( driver.findElement(By.linkText("Yunindanin")).getText() +sdf2.format(new  Date().getTime()));
	}
}
package com.titanium.testcases;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.titanium.modules.LogOutToolsQA;
import com.titanium.modules.LoginToolsQA;
import com.titanium.utils.Constants;

public class TestMain {
	WebDriver driver;
	WebDriverWait wait;
	WebElement lblError;
	String baseUrl = "http://www.store.demoqa.com";
	String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	LoginToolsQA logIn;
	LogOutToolsQA logOut;
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		System.setProperty("webdriver.chrome.driver", chromePath);			
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		logIn = new LoginToolsQA(driver);
		logOut = new LogOutToolsQA(driver);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	@Test(priority=0, description="Test Case using valid credentials")
	public void validCredentials() throws InterruptedException{
		Assert.assertTrue(logIn.LogIn(Constants.strValidUserName, Constants.strValidPassword));
		Assert.assertTrue(logOut.LogOutApplication());
	}
	
	@Test(priority=1, description="Test Case using empty password")
	public void emptyPassword() {
		Assert.assertTrue(logIn.LogIn(Constants.strInvalidUserName, ""));
		lblError = driver.findElement(By.xpath(".//*[@id='ajax_loginform']/p[1]"));
		wait.until(ExpectedConditions.visibilityOf(lblError));
		assertEquals(lblError.getText(), "ERROR: The password field is empty.");
	}
	
	@Test(priority=2, description="Test Case using empty credentials")
	public void emptyCredentials(){
		Assert.assertTrue(logIn.LogIn("", ""));
		lblError = driver.findElement(By.xpath(".//*[@id='ajax_loginform']/p[1]"));
		wait.until(ExpectedConditions.visibilityOf(lblError));
		assertEquals(lblError.getText(), "Please enter your username and password.");
	}
	
	@Test(priority=3, description="Test Case using empty user name")
	public void emptyUserName(){
		Assert.assertTrue(logIn.LogIn("", Constants.strValidPassword));
		lblError = driver.findElement(By.xpath(".//*[@id='ajax_loginform']/p[1]"));
		wait.until(ExpectedConditions.visibilityOf(lblError));
		Assert.assertEquals(lblError.getText(), "ERROR: The username field is empty.");
	}
	
	@Test(priority=4, description="Test Case using wrong credentials")
	public void invalidCredentials(){
		Assert.assertTrue(logIn.LogIn(Constants.strInvalidUserName, Constants.strInvalidPassword));
		lblError = driver.findElement(By.xpath(".//*[@id='ajax_loginform']/p[1]"));
		wait.until(ExpectedConditions.visibilityOf(lblError));
		Assert.assertEquals(lblError.getText(), "ERROR: Invalid username. Lost your password?");
	}
}


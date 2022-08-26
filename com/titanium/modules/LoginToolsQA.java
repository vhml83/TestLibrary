package com.titanium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginToolsQA {
	WebDriver driver;
	
	public LoginToolsQA(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean LogIn(String strUserName, String strPassword) {
		try {
			driver.findElement(By.xpath(".//*[@id='account']/a")).click();
			driver.findElement(By.id("log")).clear();
			driver.findElement(By.id("log")).sendKeys(strUserName);
			driver.findElement(By.name("pwd")).clear();
			driver.findElement(By.name("pwd")).sendKeys(strPassword);
			driver.findElement(By.id("login")).click();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}
}

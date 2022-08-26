package com.titanium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutToolsQA {
	WebDriver driver;
	
	public LogOutToolsQA(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean LogOutApplication() {
		try {
			Thread.sleep(3000);
			System.out.println(driver.findElement(By.xpath(".//*[@id='wp-admin-bar-my-account']/a")).getText());
			driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}
	
}

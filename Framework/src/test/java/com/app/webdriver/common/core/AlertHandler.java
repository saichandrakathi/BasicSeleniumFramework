package com.app.webdriver.common.core;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHandler {

	public static boolean isAlertPresent(WebDriver driver) {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      Log.info("NO ALERT PRESENT");
	      return false;
	    }
	  }

	  public static void acceptPopupWindow(WebDriver driver) {
	    driver.switchTo().alert().accept();
	  }

	  public static void acceptPopupWindow(WebDriver driver, int timeout) {
	    new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver webDriver) {
	        if (isAlertPresent(webDriver)) {
	          webDriver.switchTo().alert().accept();
	          return true;
	        } else {
	          return false;
	        }
	      }
	    });
	  }

	  public static void dismissPopupWindow(WebDriver driver) {
	    driver.switchTo().alert().dismiss();
	  }
}

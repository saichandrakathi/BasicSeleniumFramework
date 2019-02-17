package com.app.webdriver.common.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class APPWebDriver extends EventFiringWebDriver {

	  private WebDriver webDriver;
	  private boolean isMobile;

	  public APPWebDriver(WebDriver webdriver, boolean isMobile) {
	    super(webdriver);

	    this.webDriver = webdriver;
	    this.isMobile = isMobile;
	  }

	  

	  public boolean isChrome() {
	    return webDriver instanceof ChromeDriver && !isMobile;
	  }

	
	  @Override
	  public void quit() {
	    super.quit();
	  }
	}
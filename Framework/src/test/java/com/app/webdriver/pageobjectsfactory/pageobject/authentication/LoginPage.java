package com.app.webdriver.pageobjectsfactory.pageobject.authentication;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.app.webdriver.common.logging.Log;
import com.app.webdriver.pageobjectsfactory.pageobject.BasePageObject;


public class LoginPage extends BasePageObject{
	
	  @FindBy(id = "userid")
	  private WebElement userName;
	  @FindBy(id = "password")
	  private WebElement password;
	  @FindBy(id = "btnActive")
	  private WebElement btnLogin;
	  

	  
	  public void login(String UserName, String Password, String NavigatorLink) {
		    fillInput(userName, UserName);
		    fillInput(password, Password);
		    waitAndClick(btnLogin);
		    waitFor.until(ExpectedConditions.elementToBeClickable(btnnavigator));
		    Log.log("INFO", "Logged in as user:"+UserName, true, driver);
		    clickNavigatorItem(NavigatorLink);
		  }
}

package com.app.webdriver.pageobjectsfactory.pageobject.authentication;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.webdriver.pageobjectsfactory.pageobject.BasePageObject;


public class LoginPage extends BasePageObject{
	
	  @FindBy(id = "userid")
	  private WebElement userName;
	  @FindBy(id = "password")
	  private WebElement password;
	  @FindBy(id = "btnActive")
	  private WebElement btnLogin;
	

	  
	  public void login(String UserName, String Password) {
		    fillInput(userName, UserName);
		    fillInput(password, Password);
		    scrollAndClick(btnLogin); 

		  }
}

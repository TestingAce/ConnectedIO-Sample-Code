package com.connectedio.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.connectedio.common.CommonMethods;


public class LoginPage {
	private WebDriver driver;
	private CommonMethods Common;
	
	public String EmailAddress="email_add";
	public String Password="password";
	public String SignInButton="Locator";
	public String SignOff="locator";
	public String LogYourSelfLink="locator";
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		Common = new CommonMethods(this.driver);
	this.driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
		public void Login(String userName,String password ){
			Common.setValueByName(EmailAddress,userName);
			Common.setValueByName(Password,password);
			Common.clickElementByXPATH(SignInButton);
		}
		
		public void clickLogOff(){
			Common.clickElementByXPATH(SignOff);
		}
		
		public void clickLogYourSelfLink(){
			System.out.println("ready to clink ");
			Common.clickElementByLinkText(LogYourSelfLink);
			System.out.println("link clicked");
		}
		
		public void enterUserName(String UserName){
			Common.setValueByName(EmailAddress,UserName);
		}
		
	   public void enterPassword(String password){
		   Common.setValueByName(Password,password);
		}
   
	   public void clickSignIn(){
		   Common.clickElementByXPATH(SignInButton);
	   }

}

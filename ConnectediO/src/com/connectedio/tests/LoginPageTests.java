package com.connectedio.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.connectedio.common.CommonMethods;
import com.connectedio.common.ScreenCapture;
import com.connectedio.pages.LoginPage;

public class LoginPageTests {

	public class LoginTests {
		private WebDriver driver;
		LoginPage LoginPage;
		CommonMethods Common;
		ScreenCapture SC;

		@Parameters({ "browser" })
		@BeforeMethod
		public void setUp() {
			Common = new CommonMethods(driver);
			driver = Common.openBrowser("chrome");
			Common.gotoURL("http:www.connectedio.com");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}

		@Test
		public void getTitle(){
			Common.getcurrenturl();
		}
		
		@Test
		public void Login01() throws InterruptedException {
			LoginPage = new LoginPage(driver);
			LoginPage.clickLogYourSelfLink();
			LoginPage.Login("connectedio@Test.com", "pass123");
			// Explicit Wait
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 10);
			 * wait.until(ExpectedConditions
			 * .visibilityOfElementLocated(By.id(element))); }
			 */
			Common.verifyText("Welcome to Connectedio");
			LoginPage.clickLogOff();

		}

		@Test
		public void Login02() throws InterruptedException {
			LoginPage = new LoginPage(driver);
			LoginPage.clickLogYourSelfLink();
			LoginPage.clickSignIn();
			// Fluent Wait
			/*
			 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //Wait
			 * for the condition .withTimeout(30, TimeUnit.SECONDS) // which to
			 * check for the condition with interval of 5 seconds.
			 * .pollingEvery(5, TimeUnit.SECONDS) //Which will ignore the
			 * NoSuchElementException .ignoring(NoSuchElementException.class);
			 */
			Common.verifyText(" Error: No match for E-Mail Address and/or Password.");
		}

		@AfterMethod(alwaysRun = true)
		public void tearDown() {
			Common.CloseBrowser();
		}

	}

}

package com.connectedio.common;

import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CommonMethods {
	private WebDriver driver;

	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver openBrowser(String sBrowserType) {
		if (sBrowserType.equalsIgnoreCase("Chrome")) {
			File chromedriver = new File("C:\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					chromedriver.getAbsolutePath());
			DesiredCapabilities Capabilities = new DesiredCapabilities();
			Capabilities = DesiredCapabilities.chrome();
			Capabilities.setCapability("chrome.switches",
					Arrays.asList("--start-maximized"));
			driver = new ChromeDriver(Capabilities);
			System.out.println("Chrome Browser is opened");
		} else if (sBrowserType.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
			// driver.manage().deleteAllCookies();
			System.out.println("FireFox Browser is opened");
		} else if (sBrowserType.equalsIgnoreCase("Remote")) {
			driver = new RemoteWebDriver(DesiredCapabilities.firefox());
		} else {
			System.out
					.println("Only Firefox and Chrome supported at this time. Please select the right browser");
		}

		driver.manage().window().maximize();
		System.out.println(" Browser is mazimized");
		driver.manage().deleteAllCookies();
		System.out.println("deleted all cookies");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public void gotoURL(String sURL) {
		driver.get(sURL);
		System.out.println("Navigating to " + sURL);
	}

	public void CloseBrowser() {
		try {
			driver.quit();
			System.out.println("Browser is closed");
		} catch (Exception e) {
			System.out.println("Unable to close the browser");
		}
	}

	public void verifyText(String expected) {
		try {
			driver.findElement(By.xpath("//*[contains(text(),'"
					+ expected.trim() + "')]"));
			System.out.println("On page " + driver.getTitle()
					+ ". Expected Text \"" + expected + "\" verified");
			// return true;
		} catch (NoSuchElementException e) {
			System.out.println("On page " + driver.getTitle()
					+ ". Expected Text \"" + expected + "\" not found");
			Assert.fail("On page " + driver.getTitle() + ". Expected Text \""
					+ expected + "\" not found");
		}

	}

	// --------------------------Locators----------------------------------------------------------------------------------
	/*
	 * Name locator example <input name="register" class="required"
	 * type="text"/> WebElement register=
	 * driver.findElement(By.name("register"));
	 */

	public void setValueByName(String elementName, String sValue) {
		try {
			driver.findElement(By.name(elementName)).clear();
			driver.findElement(By.name(elementName)).sendKeys(sValue);
			System.out.println(sValue + " entered");
		} catch (Exception e) {
			System.out.println(elementName + " not found");
		}

	}

	public void clickElementByXPATH(String element) {
		// driver.findElement(By.xpath("//*[@id='bodyContent']/div/div[1]/a[1]/u")).click();
		try {
			driver.findElement(By.xpath(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	public void clickElementByName(String element) {
		try {
			driver.findElement(By.id(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	public void clickElementByID(String element) {
		try {
			driver.findElement(By.id(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	public void clickElementByCss(String element) {
		try {
			driver.findElement(By.cssSelector(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	/*
	 * PartialLinkText example <a href="seleniumhq.org">Download selenium
	 * server</a> WebElement download =
	 * driver.findElement(By.PartialLinkText("Download"));
	 */

	public void clickElementByPartialLinkText(String element) {
		try {
			driver.findElement(By.partialLinkText(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	/*
	 * Link text example <a href="http://www.seleniumhq.org">Downloads</a>
	 * WebElement download = driver.findElement(By.linkText("Downloads"));
	 */

	public void clickElementByLinkText(String element) {
		try {
			driver.findElement(By.linkText(element)).click();
			System.out.println(element + " clicked");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not found");
		}
	}

	// getting main window handle
	public String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	// -----------------------------------POP-UP-----------------------------------------------------------------------------------------------
	// accept popup
	public void acceptPopUp() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			System.out.println("No popups found");
		}
	}

	// dismiss popup
	public void dismissPopUp() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			System.out.println("alert dismissed");
		} catch (Exception e) {
			System.out.println("Unable to get dismiss PopUp");
		}
	}

	// Popup getText
	public void getPopupText() {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
		} catch (Exception e) {
			System.out.println("Unable to get PopUp Text");
		}
	}

	// Popup sendkeys
	public void sendKeysPopup() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(null);
		} catch (Exception e) {
			System.out.println("Unable to Type in the Popup Textbox");
		}

	}

	// ----------------------------------Mouse
	// Hover------------------------------------------------------------

	// Mouse hover
	public void mouseHover() {
		Actions action = new Actions(driver);
		WebElement mainMenu = driver.findElement(By.linkText("MainMenu"));
		action.moveToElement(mainMenu)
				.moveToElement(driver.findElement(By.xpath("submenuxpath")))
				.click().build().perform();
	}

	// Using Mouse Hover to check the color change of the element
	public void mouseHoverColor() {
		WebElement searchBtn = driver.findElement(By.id("searchbtn"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
	}

	// ---------------------------------DropDowns-------------------------------------------------------------------------------------------------
	/*
	 * public void dropDown(String element){ Select dropdown = new
	 * Select(driver.findElement(By.id(element)).click();
	 * dropdown.selectByVisibleText(element); }
	 */

	// ----------------------------------iFrames---------------------------------------------------------------------------------------------------

	// Switching between different frames using Index
	public void switchToFrame(int frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with id " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frame
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " + frame
					+ e.getStackTrace());
		}

	}

	// Switching frames using ID
	public void switchFramesByID(String frame) {
		try {
			driver.switchTo().frame(frame);
			System.out.println("Navigated to frame with name " + frame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + frame
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with id " + frame);
		}
	}

	// Important to switch back to the parent frame
	public void switchtoDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out
					.println("unable to navigate back to main webpage from frame"
							+ e.getStackTrace());
		}
	}

	// ----------------------------------Scrolling the
	// page------------------------------------------------------------------

	// Scrolling to the bottom of the page
	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// Scrolling to the top of the page
	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(100, document.body.scrollHeight)");

	}

	// Scrolling to a specific element
	public static void scrollTo(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	public void getcurrenturl() {
		try {
			driver.getCurrentUrl();
		} catch (Exception e) {
			System.out.println("Unable to get the current URL");
		}
	}
}

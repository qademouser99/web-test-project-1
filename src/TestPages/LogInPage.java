package TestPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.Reporter;

public class LogInPage {

	private WebDriver driver;
	private Reporter reporter;

	public LogInPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	public WebElement getEmailTextBox() {
		return getWebElement(By.id("email"));
	}

	public WebElement getEmailPass() {
		return getWebElement(By.id("pass"));
	}

	public WebElement logInClick() {
		return getWebElement(By.id("loginbutton"));
	}

	private WebElement getWebElement(By selector) {
		WebElement webelement = null;
		if (this.driver != null) {
			webelement = this.driver.findElement(selector);
		} else {
			System.out.println("Driver is not initialized!");
		}
		return webelement;
	}

}

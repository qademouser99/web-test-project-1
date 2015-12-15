package TestPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.Reporter;

public class InvalidLoginPage {
	private WebDriver driver;
	private Reporter reporter;

	public InvalidLoginPage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Element
	public WebElement getForgotPassLink() {
		return getWebElement(By.linkText("Forgot password?"));
	}

	// Verify

	public void VerifyForgotPassLink() {
		try {
			if (this.getForgotPassLink().isDisplayed()) {
				this.reporter.pass("Test pass, forgot profile link is visible");
				Assert.assertTrue(this.getForgotPassLink().isDisplayed());
			} else {
				this.reporter.error("Test fail, forgot profile link is not visible");
				Assert.assertTrue(this.getForgotPassLink().isDisplayed());
			}
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profile name element not found");
		} catch (Exception ex) {
			this.reporter.error("Cant verify logged in username");
		}
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

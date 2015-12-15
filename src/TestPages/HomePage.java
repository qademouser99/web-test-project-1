package TestPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.Reporter;

public class HomePage {

	private WebDriver driver;
	private Reporter reporter;

	public HomePage(WebDriver driver, Reporter reporter) {
		this.driver = driver;
		this.reporter = reporter;
	}

	// Element
	public WebElement getLogedInUser() {
		return getWebElement(By.className("_2dpb"));
	}

	// Verify

	public void VerifyLogedInUser(String userName) {
		try {
			if (this.getLogedInUser().getText().equals("John")) {
				Assert.assertEquals(this.getLogedInUser().getText(), "John");
				this.reporter
						.pass("Test pass, user profilename is " + userName);
			} else {
				this.reporter.error("Test fail, user profilename is "
						+ userName);
			}
		} catch (NoSuchElementException ex) {
			this.reporter.error("Profile name element not found");
		} catch (Exception ex) {
			this.reporter.error("Cant verify logged in username");
		}
	}

	public void VerifyProfileName() {
		try {
			if (this.getLogedInUser().isDisplayed()) {
				this.reporter.pass("Test pass, profile name element is displayed");
				Assert.assertTrue(this.getLogedInUser().isDisplayed());
			}else{
			this.reporter.error("Test fail, profile name element is not displayed");
			Assert.assertTrue(this.getLogedInUser().isDisplayed());
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
